package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Transaccion;
import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.repositorio.TransaccionRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;



@Controller
public class TransaccionesController {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private CuentaRepository cuentaRepository;
    
    
    @GetMapping("/transacciones/new")
    public String transaccionForm(Model model) {
        model.addAttribute("transaccion", new Transaccion());
        return "transaccionNueva";
    }

    @PostMapping("/transacciones/new/save")
    @Transactional
    public String transaccionGuardar(@ModelAttribute Transaccion transaccion, Model model) {
        try {
            int rowsAffectedConsignar = cuentaRepository.actualizarSaldoConsignar(transaccion.getCuenta_destino().getId(), transaccion.getMonto_pago());
            int rowsAffectedRetirar = cuentaRepository.actualizarSaldoRetiro(transaccion.getCuenta_origen().getId(), transaccion.getMonto_pago());
            if (rowsAffectedConsignar > 0 && rowsAffectedRetirar > 0) {
                transaccionRepository.insertarTransaccion(transaccion.getFecha_operacion(), transaccion.getMonto_pago(), transaccion.getCuenta_origen().getId(), 
                transaccion.getCuenta_destino().getId(), transaccion.getPunto_atencion().getId());
                return "redirect:/cuentas";
            } else {
                throw new RuntimeException("Error al hacer la transaccion: No se pudieron completar las actualizaciones de saldo");
            }
            

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            model.addAttribute("errorMessage",  e.getMessage());
            System.out.println(e.getMessage());
            return "error";
        }
    }
}

