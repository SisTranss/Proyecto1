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
    public String transaccionGuardar(@ModelAttribute Transaccion transaccion) {
        transaccionRepository.insertarTransaccion(transaccion.getFecha_operacion(), transaccion.getMonto_pago(), transaccion.getCuenta_origen().getId(), 
        transaccion.getCuenta_destino().getId(), transaccion.getPunto_atencion().getId());
        cuentaRepository.actualizarSaldoConsignar(transaccion.getCuenta_destino().getId(), transaccion.getMonto_pago());
        cuentaRepository.actualizarSaldoRetiro(transaccion.getCuenta_origen().getId(), transaccion.getMonto_pago());
        return "redirect:/cuentas";
    }
}

