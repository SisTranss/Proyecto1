package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import uniandes.edu.co.proyecto.modelo.Prestamo;
import uniandes.edu.co.proyecto.repositorio.PrestamoRepository;
import org.springframework.ui.Model;

@Controller

public class PrestamosController {
    @Autowired
    private PrestamoRepository prestamoRepository;

    @GetMapping("/prestamos")
    public String prestamos(Model model){
        model.addAttribute("prestamos", prestamoRepository.darPrestamos());
        return "prestamos";
    }

    @GetMapping("/prestamos/new")
    public String cuentaForm(Model model) {
        model.addAttribute("prestamo", new Prestamo());
        return "prestamoNuevo";
    }

    @PostMapping("/prestamos/new/save")
    public String prestamoGuardar(@ModelAttribute Prestamo prestamo) {
        prestamoRepository.insertarPrestamo(prestamo.getProposito(), prestamo.getEstado(), prestamo.getMonto(), prestamo.getTasa(),
        prestamo.getCuotas(), prestamo.getDia_pago(), prestamo.getValor_cuota(), prestamo.getNum_doc_cliente().getNum_id());
        return "redirect:/prestamos";
    }

    @GetMapping("/prestamos/{id}/cerrar")
    public String prestamoCerrarForm( @PathVariable("id") Integer id, Model model) {
        Prestamo prestamo = prestamoRepository.darPrestamo(id);
        if (prestamo != null) {
            model.addAttribute("prestamo", prestamo);
            return "prestamoCerrar";
        } else {
            return "redirect:/prestamos";
        }
    }

    @PostMapping("/prestamos/{id}/cerrar/save")
    public String prestamoCerrarGuardar(@PathVariable("id") Integer id, @ModelAttribute Prestamo prestamo) {
        prestamoRepository.cerrarPrestamo(id);
        return "redirect:/prestamos";
    }

    @GetMapping("/prestamos/{id}/aprobar")
    public String prestamoAprobarForm( @PathVariable("id") Integer id, Model model) {
        Prestamo prestamo = prestamoRepository.darPrestamo(id);
        if (prestamo != null) {
            model.addAttribute("prestamo", prestamo);
            return "prestamoApribar";
        } else {
            return "redirect:/prestamos";
        }
    }

    @PostMapping("/prestamos/{id}/aprobar/save")
    public String prestamoAprobarGuardar(@PathVariable("id") Integer id, @ModelAttribute Prestamo prestamo) {
        prestamoRepository.aprobarPrestamo(id);
        return "redirect:/prestamos";
    }

    @GetMapping("/prestamos/{id}/rechazar")
    public String prestamoRechazarForm( @PathVariable("id") Integer id, Model model) {
        Prestamo prestamo = prestamoRepository.darPrestamo(id);
        if (prestamo != null) {
            model.addAttribute("prestamo", prestamo);
            return "prestamoCerrar";
        } else {
            return "redirect:/prestamos";
        }
    }

    @PostMapping("/prestamos/{id}/rechazar/save")
    public String prestamoRechazarGuardar(@PathVariable("id") Integer id, @ModelAttribute Prestamo prestamo) {
        prestamoRepository.rechazarPrestamo(id);
        prestamoRepository.eliminarPrestamo(id);
        return "redirect:/prestamos";
    }


}
