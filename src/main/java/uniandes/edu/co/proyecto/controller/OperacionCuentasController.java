package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import uniandes.edu.co.proyecto.modelo.OperacionCuenta;
import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.repositorio.OperacionCuentaRepository;
import uniandes.edu.co.proyecto.servicios.OperacionesService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


@Controller
public class OperacionCuentasController {
    @Autowired
    private OperacionCuentaRepository operacionCuentaRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private OperacionesService operacionesServicio;
    
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
            int rowsAffectedRetirar = cuentaRepository.actualizarSaldoRetiro(operacion_cuenta.getId_cuenta().getId(), operacion_cuenta.getMonto_pago());
            if (rowsAffectedRetirar > 0){
                operacionCuentaRepository.insertarOperacion_cuenta(operacion_cuenta.getTipo_operacion(), operacion_cuenta.getFecha_operacion(), operacion_cuenta.getId_cuenta().getId(),
                operacion_cuenta.getMonto_pago(), operacion_cuenta.getPunto_atencion().getId());
                return "redirect:/cuentas";
            }
            else {
                throw new RuntimeException("Error al hacer el retiro: No se pudieron completar las actualizaciones de saldo");
            }
            
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
            int rowsAffectedConsignar = cuentaRepository.actualizarSaldoConsignar(operacion_cuenta.getId_cuenta().getId(), operacion_cuenta.getMonto_pago());
            if (rowsAffectedConsignar > 0){
                operacionCuentaRepository.insertarOperacion_cuenta(operacion_cuenta.getTipo_operacion(), operacion_cuenta.getFecha_operacion(), operacion_cuenta.getId_cuenta().getId(),
                operacion_cuenta.getMonto_pago(), operacion_cuenta.getPunto_atencion().getId());
                return "redirect:/cuentas";
            }else {
                throw new RuntimeException("Error al hacer la consignacion: No se pudieron completar las actualizaciones de saldo");
            }

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            model.addAttribute("errorMessage", "Error al retirar dinero: " + e.getMessage());
            System.out.println(e.getMessage());
            return "redirect:/error";
        }
    }

    @GetMapping("/operacionesCuentaSinFantasma")
    public String consultarOperacionesSinFantasma(Model model, Integer idCuenta, RedirectAttributes redirectAttributes) {
        
        if (idCuenta !=null) {
            try {
                // Indicar el ID de la cuenta a consultar.
                Collection<OperacionCuenta> operaciones = operacionesServicio.darOperacionesCuentaSerializable(idCuenta);
                model.addAttribute("operaciones", operaciones);
            } catch (Exception e) {
                System.err.println("Error durante la consulta de operaciones: " + e.getMessage());
                redirectAttributes.addFlashAttribute("errorMessage", "No se pudo consultar las operaciones de la cuenta correctamente.");
                return "redirect:/error";
            }
            
        }
        return "operacionesCuentaSinFantasma";
    }

    @GetMapping("/operacionesCuentaFantasma")
    public String consultarOperacionesCuentaFantasma(Model model, Integer idCuenta, RedirectAttributes redirectAttributes) {
        
        if (idCuenta !=null) {
            try {
                // Indicar el ID de la cuenta a consultar.
                Collection<OperacionCuenta> operaciones = operacionesServicio.darOperacionesCuentaReadCommited(idCuenta);
                model.addAttribute("operaciones", operaciones);
            } catch (Exception e) {
                System.err.println("Error durante la consulta de operaciones: " + e.getMessage());
                redirectAttributes.addFlashAttribute("errorMessage", "No se pudo consultar las operaciones de la cuenta correctamente.");
                return "redirect:/error";
            }
            
        }
        
        return "operacionesCuentaFantasma";
    }
}
