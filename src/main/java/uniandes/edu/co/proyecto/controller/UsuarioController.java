package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.repositorio.UsuarioRepository;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public String usuarios(Model model){
        model.addAttribute("usuarios", usuarioRepository.darUsuarios());
        return "usuarios";
    }

    @GetMapping("/usuarios/new")
    public String usuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarioNuevo";
    }

    @PostMapping("/usuarios/new/save")
    public String usuarioGuardar(@ModelAttribute Usuario usuario) {
        usuarioRepository.insertarUsuario((int)usuario.getNum_id(), usuario.getNombre(), usuario.getEmail(),usuario.getNacionalidad(), usuario.getTelefono(), usuario.getTipoUsuario(), usuario.getLugar().getId(),
        usuario.getLogin(), usuario.getPalabraClave(),usuario.getTipoDoc());
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{tipoDoc}/{numId}/edit")
    public String usuarioEditarForm(@PathVariable("tipoDoc") String tipoDoc,@PathVariable("numId") int numId, Model model) {
        Usuario usuario = usuarioRepository.darUsuario(numId);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            return "usuarioEditar";
        } else {
            return "redirect:/usuarios";
        }
    }

    @PostMapping("/usuarios/{tipoDoc}/{numId}/edit/save")
    public String usuarioEditarGuardar(@PathVariable("tipoDoc") String tipoDoc,@PathVariable("numId") int numId, @ModelAttribute Usuario usuario) {
        usuarioRepository.updateUsuario(numId, tipoDoc, tipoDoc, tipoDoc, numId, numId, numId);
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{tipoDoc}/{numId}/delete")
    public String usuarioBorrar(@PathVariable("tipoDoc") String tipoDoc,@PathVariable("numId") int numId) {
        usuarioRepository.eliminarUsuario(numId);
        return "redirect:/usuarios";
    }
}