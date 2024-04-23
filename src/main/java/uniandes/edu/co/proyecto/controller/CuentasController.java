package uniandes.edu.co.proyecto.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.repositorio.UsuarioRepository;
import uniandes.edu.co.proyecto.modelo.Cuenta;

import org.springframework.ui.Model;

@Controller
public class CuentasController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/cuentas")
    public String cuentas(Model model, String tipo, Integer max_saldo, Integer min_saldo, Date ultima_transaccion) {
        

        System.out.println(ultima_transaccion);

        if((tipo ==null || tipo.equals("")) && max_saldo==null && min_saldo==null && (ultima_transaccion == null || ultima_transaccion.equals("")) )
        {
            model.addAttribute("cuentas", cuentaRepository.darCuentas());
        }
        else if ((tipo != null && max_saldo == null && min_saldo ==null && ultima_transaccion == null )){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorTipo(tipo));
        }
        else if ((max_saldo!=null && min_saldo!=null && tipo ==null && ultima_transaccion == null)){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorRangoSaldos(min_saldo, max_saldo));
        } 
        else if ((max_saldo==null && min_saldo==null && tipo ==null && ultima_transaccion != null)){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorUltimoMov(ultima_transaccion));
        }

        return "cuentas";
    }


    @GetMapping("/cuentas/oficina")
    public String oficinaCuentas(@RequestParam("id_oficina") int id_oficina, Model model, String tipo, Integer max_saldo, Integer min_saldo, Date ultima_transaccion) {
        System.out.println(id_oficina);
        if((tipo ==null || tipo.equals("")) && max_saldo==null && min_saldo==null && (ultima_transaccion == null || ultima_transaccion.equals("")) )
        {
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorIDoficina(id_oficina));
        }
        else if ((tipo != null && max_saldo == null && min_saldo ==null && ultima_transaccion == null )){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorTipoCuentayIDoficina(tipo, id_oficina));
        }
        else if ((max_saldo!=null && min_saldo!=null && tipo ==null && ultima_transaccion == null)){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorRangoSaldosyIDoficina(min_saldo, max_saldo, id_oficina));
        } 
        else if ((max_saldo==null && min_saldo==null && tipo ==null && ultima_transaccion != null)){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorUltimoMovyIDoficina(ultima_transaccion, id_oficina));
        }

        return "cuentasOficina";
    }

    @GetMapping("/cuentas/cliente")
    public String clienteCuentas(@RequestParam("num_doc_cliente") Integer num_doc_cliente,
     Model model, 
     String tipo, 
     Integer max_saldo, 
     Integer min_saldo, 
     Date ultima_transaccion) {
        System.out.println(num_doc_cliente);
        if((tipo ==null || tipo.equals("")) && max_saldo==null && min_saldo==null && (ultima_transaccion == null || ultima_transaccion.equals("")) )
        {
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorCliente2(num_doc_cliente));
        }
        else if ((tipo != null && max_saldo == null && min_saldo ==null && ultima_transaccion == null )){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorTipoCuentayCliente(num_doc_cliente, tipo));
        }
        else if ((max_saldo!=null && min_saldo!=null && tipo ==null && ultima_transaccion == null)){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorRangoSaldosyCliente(min_saldo, max_saldo, num_doc_cliente));
        } 
        else if ((max_saldo==null && min_saldo==null && tipo ==null && ultima_transaccion != null)){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorUltimoMovyCliente(ultima_transaccion, num_doc_cliente));
        }

        return "cuentasCliente";
    }



    @GetMapping("/cuentas/oficina/new")
    public String cuentaForm(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        return "cuentaNuevo";
    }


    @PostMapping("/cuentas/oficina/new/save")
    public String cuentaGuardar(@ModelAttribute Cuenta cuenta) {
    Date hoy = new Date(2024/4/3);

        Integer num_id = usuarioRepository.darIdUsuario(cuenta.getNum_doc_cliente().getNum_id());

        cuentaRepository.insertarCuenta(cuenta.getTipo(), cuenta.getEstado(), cuenta.getSaldo(),
                hoy, num_id,
                cuenta.getId_oficina().getId());
                
        return "redirect:/cuentas/oficina?id_oficina=" + cuenta.getId_oficina().getId();
    }

    @GetMapping("/cuentas/{id}/cambiar-estado/save")
    public String actualizarCuentaGuardar(@RequestParam("nuevoEstado") String nuevoEstado, @RequestParam("cuentaID") Integer cuentaID, @RequestParam("id_oficina") Integer id_oficina) {
        cuentaRepository.actualizarEstadoCuenta(cuentaID, nuevoEstado);
        return "redirect:/cuentas/oficina?id_oficina=" + id_oficina;
    }


}
