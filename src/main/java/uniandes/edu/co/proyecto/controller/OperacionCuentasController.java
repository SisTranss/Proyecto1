package uniandes.edu.co.proyecto.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.OperacionCuenta;
import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.repositorio.OperacionCuentaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


@Controller
public class OperacionCuentasController {
    @Autowired
    private OperacionCuentaRepository operacionCuentaRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    
    @GetMapping("/consignar/new")
    public String consignarForm(Model model) {
        model.addAttribute("operacionCuenta", new OperacionCuenta());
        return "operacionConsignarNueva";
    }

    @GetMapping("/retirar/new")
    public String retirarForm(Model model) {
        model.addAttribute("operacionCuenta", new OperacionCuenta());
        return "operacionRetirarNueva";
    }
    


    @PostMapping("/retirar/new/save")
    @Transactional
    public String retirarDinero(@ModelAttribute OperacionCuenta operacion_cuenta, Model model) {
        try {
            operacionCuentaRepository.insertarOperacion_cuenta(operacion_cuenta.getTipo_operacion(), operacion_cuenta.getFecha_operacion(), operacion_cuenta.getId_cuenta().getId(),
            operacion_cuenta.getMonto_pago(), operacion_cuenta.getPunto_atencion().getId());

            cuentaRepository.actualizarSaldoRetiro(operacion_cuenta.getId_cuenta().getId(), operacion_cuenta.getMonto_pago());

            return "redirect:/cuentas";
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            model.addAttribute("errorMessage", "Error al retirar dinero: " + e.getMessage());
            System.out.println(e.getMessage());
            return "redirect:/error";
        }
    }
    
    @PostMapping("/consignar/new/save")
    @Transactional
    public String consignarDinero(@ModelAttribute OperacionCuenta operacion_cuenta, Model model) {
        try {
            operacionCuentaRepository.insertarOperacion_cuenta(operacion_cuenta.getTipo_operacion(), operacion_cuenta.getFecha_operacion(), operacion_cuenta.getId_cuenta().getId(),
            operacion_cuenta.getMonto_pago(), operacion_cuenta.getPunto_atencion().getId());

            cuentaRepository.actualizarSaldoConsignar(operacion_cuenta.getId_cuenta().getId(), operacion_cuenta.getMonto_pago());
            
            return "redirect:/cuentas";
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            model.addAttribute("errorMessage", "Error al retirar dinero: " + e.getMessage());
            System.out.println(e.getMessage());
            return "redirect:/error";
        }
    }
       

}
