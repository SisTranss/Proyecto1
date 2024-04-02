package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Lugar;
import uniandes.edu.co.proyecto.repositorio.LugarRepository;

public class LugarController {
  @Autowired
  LugarRepository lugarRepository;

  @GetMapping("/lugares")
  public String lugares(Model model) {
      model.addAttribute("lugares", lugarRepository.darLugares());
      return "lugares";
  }
  
  @GetMapping("lugares/new")
    public String lugarForm(Model model) {
        model.addAttribute("usuario", new Lugar());
        return "lugarNuevo";
    }

     @PostMapping("lugares/new/save")
    public String lugarGuardar(@ModelAttribute Lugar lugar) {
        lugarRepository.insertarLugar(lugar.getCodigoPostal(), lugar.getDireccion(), lugar.getCiudad(), lugar.getDepartamento());
        return "redirect:/lugares";
    }

    @GetMapping("lugares/{id}/edit")
    public String lugarEditarForm(@PathVariable("codigoPostal") int codigoPostal, @PathVariable("direccion") String direccion, Model model) {
        Lugar lugar = lugarRepository.darLugar(codigoPostal, direccion);
        if (lugar != null) {
            model.addAttribute("lugar", lugar);
            return "lugarEditar";
        } else {
            return "redirect:lugares";
        }
    }

    @PostMapping("lugares/{id}/edit/save")
    public String lugarEditarGuardar(@PathVariable("codigoPostal") int codigoPostal,@PathVariable("direccion") String direccion, @PathVariable("ciudad") String ciudad,@PathVariable("departamento") String departamento,@ModelAttribute Lugar lugar) {
        lugarRepository.actualizarLugar(codigoPostal, ciudad, departamento, codigoPostal, direccion);
        return "redirect:lugares";
    }

    @GetMapping("lugares/{tipoDoc}/{numId}/delete")
    public String lugarBorrar(@PathVariable("codigoPostal") int codigoPostal,@PathVariable("direccion") String direccion) {
        lugarRepository.borrarLugar(codigoPostal, direccion);
        return "redirect:lugares";
    }

}
