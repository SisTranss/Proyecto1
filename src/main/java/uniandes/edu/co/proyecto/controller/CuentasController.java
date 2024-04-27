package uniandes.edu.co.proyecto.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.servicios.OperacionesService;
import uniandes.edu.co.proyecto.modelo.Cuenta;

import org.springframework.ui.Model;

@Controller
public class CuentasController {

    @Autowired
    private OperacionesService operacionesServicio;

    @Autowired
    private CuentaRepository cuentaRepository;

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
    public String clienteCuentas(
        Integer num_doc_cliente,
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
    public String cuentaGuardar(@ModelAttribute Cuenta cuenta, @Param("num_doc_cliente")Integer num_doc_cliente) {
        long millis=System.currentTimeMillis();
        Date hoy = new Date(millis);

        System.out.println("LAKJDLKAJSDLK" + cuenta.getId_oficina().getId());
        cuentaRepository.insertarCuenta(cuenta.getTipo(), cuenta.getEstado(), cuenta.getSaldo(),
                hoy, num_doc_cliente, cuenta.getId_oficina().getId());
                
        return "redirect:/cuentas";
    }

    @PostMapping("/cuentas/cambiar-estado/save")
    public String actualizarCuentaGuardar(@RequestParam("nuevoEstado") String nuevoEstado, @RequestParam("cuentaID") Integer cuentaID) {
        cuentaRepository.actualizarEstadoCuenta(cuentaID, nuevoEstado);
        return "redirect:/oficina/cuentas";
    }

    @GetMapping("/consultarOperacionesCuentaSinFantasma")
    public String consultarOperacionesSinFantasma(int id_cuenta, RedirectAttributes redirectAttributes) {
        try {
            // Indicar el ID de la cuenta a consultar.
            operacionesServicio.darOperacionesCuentaSerializable(id_cuenta);
        } catch (Exception e) {
            System.err.println("Error durante la consulta de bares: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "No se pudo consultar las operaciones de la cuenta correctamente.");
            return "/";
        }
        return "/";
    }

    @GetMapping("/consultarOperacionesCuentaFantasma")
    public String consultarOperacionesCuentaFantasma(int id_cuenta, RedirectAttributes redirectAttributes) {
        try {
            // Indicar el ID de la cuenta a consultar.
            operacionesServicio.darOperacionesCuentaSerializable(id_cuenta);
        } catch (Exception e) {
            System.err.println("Error durante la consulta de bares: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "No se pudo consultar las operaciones de la cuenta correctamente.");
            return "/";
        }
        return "/";
    }

}
