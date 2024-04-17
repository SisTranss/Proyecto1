package uniandes.edu.co.proyecto.controller;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import uniandes.edu.co.proyecto.modelo.OperacionPrestamo;
import uniandes.edu.co.proyecto.modelo.Prestamo;
import uniandes.edu.co.proyecto.repositorio.PrestamoRepository;
import uniandes.edu.co.proyecto.repositorio.OperacionPrestamoRepository;

@Controller
public class operacionPrestamoController {
    @Autowired
    private OperacionPrestamoRepository operacion_prestamoRepository;

    @Autowired
    private PrestamoRepository prestamoRepository;

    @GetMapping("/operaciones_prestamos")
    public String operaciones_prestamos(Model model){
        model.addAttribute("operaciones_prestamos", operacion_prestamoRepository.darOperaciones());
        return "operaciones_prestamos";
    }

    @GetMapping("/operaciones_prestamos/new")
    public String operacion_prestamoForm(Model model) {
        model.addAttribute("operacion_prestamo", new OperacionPrestamo());
        return "operacion_prestamoNueva";
    }

    @PostMapping("/operaciones_prestamos/new/save")
    public String operacion_prestamoGuardar(@ModelAttribute OperacionPrestamo operacion_prestamo) {
        operacion_prestamoRepository.insertarOperacion_prestamo(operacion_prestamo.getTipo_operacion(), operacion_prestamo.getFecha_operacion(), operacion_prestamo.getId_prestamo().getId(), operacion_prestamo.getMonto_pago(), 
        operacion_prestamo.getPunto_atencion().getId());

        prestamoRepository.reducirSaldoPrestamo(operacion_prestamo.getId_prestamo().getId(), operacion_prestamo.getMonto_pago());
        return "redirect:/operaciones_prestamos";
    }

    @GetMapping("/prestamos/{id}/operacionPrestamo")
    public String prestamoEditarForm(@PathVariable("id") int id, Model model) {
        Prestamo prestamo = prestamoRepository.darPrestamo(id);
        if (prestamo != null) {
            model.addAttribute("prestamo", prestamo);
            return "operacionPrestamoNuevo";
        } else {
            return "redirect:/prestamos";
        }
    }

    @PostMapping("/prestamos/{id}/operacionPrestamo/save")
    public String prestamoEditarGuardar(@PathVariable("id") int id, @ModelAttribute Prestamo prestamo,@Param("tipo_operacion")String tipo_operacion, 
    @Param("fecha_operacion")Date fecha_operacion, @Param("monto_pago")int monto_pago, @Param("punto_atencion")int punto_atencion) {
        operacion_prestamoRepository.insertarOperacion_prestamo(tipo_operacion, fecha_operacion, id, (float)monto_pago, punto_atencion);

        prestamoRepository.reducirSaldoPrestamo(id, (float)monto_pago);
        return "redirect:/prestamos";
    }


}