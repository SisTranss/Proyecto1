package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Login;
import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.repositorio.UsuarioRepository;
import uniandes.edu.co.proyecto.repositorio.LoginRepository;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("/usuarios")
    public String usuarios(Model model){
        model.addAttribute("usuarios", usuarioRepository.darUsuarios());
        return "usuarios";
    }
    

    @GetMapping("/usuarios/new")
    public String usuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("login", new Login());
        return "usuarioNuevo";
    }

    @PostMapping("/usuarios/new/save")
    public String usuarioGuardar(@ModelAttribute Usuario usuario, @ModelAttribute Login login) {
        usuarioRepository.insertarUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail(),usuario.getNacionalidad(), usuario.getTelefono(), usuario.getTipoUsuario(),
        usuario.getTipoDoc(), usuario.getNumDoc(), usuario.getCodigoPostal(), usuario.getDireccion(), usuario.getCiudad(), usuario.getDepartamento());
        loginRepository.insertarLogin(login.getlogin(), login.getPassword());
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{id}/edit")
    public String usuarioEditarForm(@PathVariable("id") int id, Model model) {
        Usuario usuario = usuarioRepository.darUsuario(id);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            return "usuarioEditar";
        } else {
            return "redirect:/usuarios";
        }
    }

    @PostMapping("/usuarios/{id}/edit/save")
    public String usuarioEditarGuardar(@PathVariable("id") int id, @ModelAttribute Usuario usuario) {
        usuarioRepository.updateUsuario(id, usuario.getNombre(), usuario.getEmail(), usuario.getNacionalidad(), usuario.getTelefono(), usuario.getTipoUsuario(), usuario.getTipoDoc(), usuario.getNumDoc(), usuario.getCodigoPostal(), usuario.getDireccion(), usuario.getCiudad(), usuario.getDepartamento());
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{id}/delete")
    public String usuarioBorrar(@PathVariable("id") int id) {
        usuarioRepository.eliminarUsuario(id);
        return "redirect:/usuarios";
    }
}