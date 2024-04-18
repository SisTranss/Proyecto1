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

@Controller
public class OperacionCuentasController {
    @Autowired
    private OperacionCuentaRepository operacionCuentaRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    
    @GetMapping("/operaciones-cuentas/new")
    public String operacion_cuentaForm(Model model) {
        model.addAttribute("operacionCuenta", new OperacionCuenta());
        return "operacionCuentaNueva";
    }

    @PostMapping("/operaciones-cuentas/new/save")
    public String operacion_cuentaGuardar(@ModelAttribute OperacionCuenta operacion_cuenta) {
        operacionCuentaRepository.insertarOperacion_cuenta(operacion_cuenta.getTipo_operacion(), operacion_cuenta.getFecha_operacion(), operacion_cuenta.getId_cuenta().getId(),
        operacion_cuenta.getMonto_pago(), operacion_cuenta.getPunto_atencion().getId());
        if(operacion_cuenta.getTipo_operacion().equals("consignar")){
            cuentaRepository.actualizarSaldoConsignar(operacion_cuenta.getId_cuenta().getId(), operacion_cuenta.getMonto_pago());
        }else{
            cuentaRepository.actualizarSaldoRetiro(operacion_cuenta.getId_cuenta().getId(), operacion_cuenta.getMonto_pago());
        }

        return "redirect:/cuentas";
    }

}
