package uniandes.edu.co.proyecto.controller;

//import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//import uniandes.edu.co.proyecto.modelo.Operacion_cuenta;
//import uniandes.edu.co.proyecto.modelo.Operacion_prestamo;
import uniandes.edu.co.proyecto.modelo.PuntoAtencion;
//import uniandes.edu.co.proyecto.modelo.Transaccion;
import uniandes.edu.co.proyecto.repositorio.PuntoAtencionRepository;

@Controller
public class puntosAtencionController {

    @Autowired
    PuntoAtencionRepository puntoAtencionRepository;

    @GetMapping("/puntosAtencion")
    public String puntosAtencion(Model model) {
        model.addAttribute("puntosAtencion", puntoAtencionRepository.darPuntosAtencion());
        return "puntosAtencion";
        //return model.toString();
    }

    @GetMapping("/puntosAtencion/new")
    public String puntosAtencionForm(Model model) {
        model.addAttribute("puntoAtencion", new PuntoAtencion());
        return "puntoAtencionNuevo";
    }

    @PostMapping("/puntosAtencion/new/save")
    public String puntoAtencionGuardar(@ModelAttribute PuntoAtencion puntoAtencion) {
    
        puntoAtencionRepository.insertarPuntoAtencion(puntoAtencion.getTipo_punto(),
        puntoAtencion.getOficina().getId());
        return "redirect:/puntosAtencion";
    }

    @GetMapping("/puntosAtencion/{id}/edit")
    public String puntoAtencionEditarForm(@PathVariable("id") int id, Model model) {
        PuntoAtencion puntoAtencion = puntoAtencionRepository.darPuntoAtencion(id);
        if (puntoAtencion != null) {
            model.addAttribute("puntoAtencion", puntoAtencion);
            return "puntoAtencionEditar";
        } else {
            return "redirect:/puntosAtencion";
        }
    }

    @GetMapping("/puntosAtencion/{id}/edit/save")
    public String puntoAtencionEditarGuardar(@PathVariable("id") int id, @ModelAttribute PuntoAtencion puntoAtencion) {
        puntoAtencionRepository.modificarPuntoAtencion(id, puntoAtencion.getTipo_punto(),
                puntoAtencion.getOficina().getId());

        return "redirect:/puntosAtencion";

    }

    /* 
    @GetMapping("/puntosAtencion/{id}/delete")
    public String puntoAtencionEliminar(@PathVariable("id") int id){

        Collection<Transaccion> transacciones = puntoAtencionRepository.darTransaccionesPunto(id);
        Collection<Operacion_cuenta> opCuentas = puntoAtencionRepository.darOperacionesCuentaPunto(id);
        Collection<Operacion_prestamo> opPrestamos = puntoAtencionRepository.darOperacionesPrestamosPunto(id);

        if(transacciones.isEmpty() && opCuentas.isEmpty() && opPrestamos.isEmpty()){
            puntoAtencionRepository.eliminarPuntoAtencion(id);
            return "redirect:/puntosAtencion";
        }
        else{
            return "redirect:/puntosAtencion";
        } 
         
    }
    */
}
