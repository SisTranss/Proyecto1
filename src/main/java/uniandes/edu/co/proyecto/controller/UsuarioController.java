package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.repositorio.ClienteRepository;
import uniandes.edu.co.proyecto.repositorio.EmpleadoRepository;
import uniandes.edu.co.proyecto.repositorio.LoginRepository;
import uniandes.edu.co.proyecto.repositorio.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired 
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

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
    public String usuarioGuardar(
    @RequestParam String login,
    @RequestParam String password,
    @RequestParam String nombre,
    @RequestParam String email,
    @RequestParam String nacionalidad,
    @RequestParam String telefono,
    @RequestParam int tipo_Usuario,
    @RequestParam int tipo_Empleado,
    @RequestParam int tipo_Cliente,
    @RequestParam String tipo_Doc,
    @RequestParam String num_Doc,
    @RequestParam int codigo_Postal,
    @RequestParam String direccion,
    @RequestParam String ciudad,
    @RequestParam String departamento) {
        
        loginRepository.insertarLogin(login, password);
        int id = loginRepository.buscarLogin(login, password);

        usuarioRepository.insertarUsuario(id, nombre, email, nacionalidad, telefono, tipo_Usuario, tipo_Doc, num_Doc, codigo_Postal, direccion, ciudad, departamento);

        if (tipo_Usuario == 1 ){
            empleadoRepository.insertarEmpleado(id, tipo_Empleado);
        } else if (tipo_Usuario == 2) {
            clienteRepository.insertarCliente(id, tipo_Cliente);
        } else {
            clienteRepository.insertarCliente(id, tipo_Cliente);
            empleadoRepository.insertarEmpleado(id, tipo_Empleado);
        }
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