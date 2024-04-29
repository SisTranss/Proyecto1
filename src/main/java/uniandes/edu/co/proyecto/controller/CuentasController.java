package uniandes.edu.co.proyecto.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.modelo.Cuenta;

import org.springframework.ui.Model;

@Controller
public class CuentasController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping("/cuentas")
    public String cuentas(Model model, String tipo, Integer max_saldo, Integer min_saldo, String ultima_transaccion, Integer num_doc_cliente) {

        if((tipo ==null || tipo.equals("")) && max_saldo==null && min_saldo==null && (ultima_transaccion == null || ultima_transaccion.equals("")) && num_doc_cliente==null )
        {
            model.addAttribute("cuentas", cuentaRepository.darCuentas());
        }
        else if ((num_doc_cliente==null && tipo != null && max_saldo == null && min_saldo ==null && ultima_transaccion == null )){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorTipo(tipo));
        }
        else if ((num_doc_cliente==null && max_saldo!=null && min_saldo!=null && tipo ==null && ultima_transaccion == null)){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorRangoSaldos(min_saldo, max_saldo));
        } 
        else if ((num_doc_cliente==null && max_saldo==null && min_saldo==null && tipo ==null && ultima_transaccion != null)){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorUltimoMov(ultima_transaccion));
        }
        else if(num_doc_cliente!=null && max_saldo==null && min_saldo==null && tipo ==null && ultima_transaccion == null){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorCliente2(num_doc_cliente));
        }

        return "cuentas";
    }


    @GetMapping("/cuentas/oficina")
    public String oficinaCuentas(@RequestParam("id_oficina") Integer id_oficina, Model model, String tipo, Integer max_saldo, Integer min_saldo, String ultima_transaccion, Integer num_doc_cliente) {

        if((tipo ==null || tipo.equals("")) && max_saldo==null && min_saldo==null && (ultima_transaccion == null || ultima_transaccion.equals("")) && num_doc_cliente==null )
        {
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorIDoficina(id_oficina));
        }
        else if ((num_doc_cliente==null && tipo != null && max_saldo == null && min_saldo ==null && ultima_transaccion == null )){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorTipoCuentayIDoficina(tipo, id_oficina));
        }
        else if ((num_doc_cliente==null && max_saldo!=null && min_saldo!=null && tipo ==null && ultima_transaccion == null)){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorRangoSaldosyIDoficina(min_saldo, max_saldo, id_oficina));
        } 
        else if ((num_doc_cliente==null && max_saldo==null && min_saldo==null && tipo ==null && ultima_transaccion != null)){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorUltimoMovyIDoficina(ultima_transaccion, id_oficina));

        } else if(num_doc_cliente!=null && max_saldo==null && min_saldo==null && tipo ==null && ultima_transaccion == null){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorClienteyIDoficina(num_doc_cliente, id_oficina));
        }

        return "cuentasOficina";
    }

    @GetMapping("/cuentas/cliente")
    public String clienteCuentas( Integer num_doc_cliente, Model model, String tipo, Integer max_saldo, Integer min_saldo, String ultima_transaccion) {
        if((tipo ==null || tipo.equals("")) && max_saldo==null && min_saldo==null && (ultima_transaccion == null || ultima_transaccion.equals("")) )
        {
            System.out.println("CUENTAS");
            System.out.println(cuentaRepository.darCuentasPorCliente2(num_doc_cliente));
            Class<?> t = num_doc_cliente.getClass();
            System.out.println("El tipo de 'texto' es: " + t.getName());
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
    public String cuentaGuardar(@ModelAttribute Cuenta cuenta, @Param("num_doc_cliente")Integer num_doc_cliente) {
        long millis=System.currentTimeMillis();
        Date hoy = new Date(millis);

        cuentaRepository.insertarCuenta(cuenta.getTipo(), cuenta.getEstado(), cuenta.getSaldo(),
                hoy, num_doc_cliente, cuenta.getId_oficina().getId());
                
        return "gerenteOficinaHome";
    }

    @GetMapping("/cuentas/oficina/{id}/cambiar-estado/save")
    public String actualizarCuentaOficinaGuardar(@RequestParam("nuevoEstado") String nuevoEstado, @RequestParam("cuentaID") Integer cuentaID, Integer id_oficina) {
        cuentaRepository.actualizarEstadoCuenta(cuentaID, nuevoEstado);
        return "redirect:/cuentas/oficina?id_oficina=" + id_oficina;
    }

    @GetMapping("/cuentas/cliente/{id}/cambiar-estado/save")
    public String actualizarCuentaClienteGuardar(@RequestParam("nuevoEstado") String nuevoEstado, @RequestParam("cuentaID") Integer cuentaID, Integer num_doc_cliente) {
        System.out.println(num_doc_cliente);
        cuentaRepository.actualizarEstadoCuenta(cuentaID, nuevoEstado);
        return "redirect:/cuentas/cliente?num_doc_cliente=" + num_doc_cliente;
    }

}
