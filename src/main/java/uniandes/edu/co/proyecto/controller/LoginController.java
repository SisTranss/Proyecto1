package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uniandes.edu.co.proyecto.modelo.clsLogin;
import uniandes.edu.co.proyecto.repositorio.LoginRepository;
import uniandes.edu.co.proyecto.repositorio.UsuarioRepository;
import uniandes.edu.co.proyecto.repositorio.EmpleadoRepository;

@Controller
public class LoginController {
    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;



    @GetMapping("/logins")
    public String logins(Model model){
        model.addAttribute("logins", loginRepository.darLogins());
        return "logins";
    }

    @GetMapping("/login")
    public String validarLogin(@RequestParam String login, @RequestParam String password, Model model) {
        Integer id = loginRepository.buscarLogin(login, password);
        Integer tipo_usuario = usuarioRepository.darTipoEmpleadoPorUsuario(id);
        String usuario = "";

        if(tipo_usuario == 1){
            Integer cargo_empleado = empleadoRepository.darCargoPorEmpleado(id);
            if(cargo_empleado == 1){
                usuario = "gerente general";
            } else if(cargo_empleado == 2){
                usuario = "gerente oficina";
            }else{
                usuario = "cajero";
            }
        }else if(tipo_usuario == 2){
            usuario = "cliente";
        } else if(tipo_usuario == 3) {
            usuario = "cliente empleado";
        }
        model.addAttribute("usuarioId",id);
        if (usuario.equals("gerente general")) {
            return "gerenteGeneralHome";
        } else if(usuario.equals("gerente oficina")){
            return "gerenteOficinaHome";
        } else if (usuario.equals("cliente")){
            return "clienteHome";
        } else if (usuario.equals("cliente empleado")){
            return "clienteEmpleadoHome";}
        else {
            return "redirect:/";
        }   

        /* 
        if (id != null ) {
            model.addAttribute("clsLogin", loginRepository.darLogin(id));
            return "GeneralHome";
        } else {
            return "redirect:/";
        }
         */        
    }   

    @GetMapping("/logins/new")
    public String loginForm(Model model) {
        model.addAttribute("clsLogin", new clsLogin());
        return "loginNuevo";
    }

    @PostMapping("/logins/new/save")
    public String loginGuardar(@RequestParam String login, @RequestParam String password, RedirectAttributes redirectAttributes) {
        loginRepository.insertarLogin(login,password);
        int id = loginRepository.buscarLogin(login, password);
        redirectAttributes.addAttribute("id", id);
        return "usuarioNuevo";
    }

    @GetMapping("/logins/{id}/edit")
    public String loginEditarForm(@PathVariable("id") int id, Model model) {
        clsLogin login = loginRepository.darLogin(id);
        if (login != null) {
            model.addAttribute("clsLogin", login);
            return "loginEditar";
        } else {
            return "redirect:/logins";
        }
    }

    @PostMapping("/logins/{id}/edit/save")
    public String loginEditarGuardar(@PathVariable("id") int numId, @ModelAttribute clsLogin login) {
        loginRepository.updateLogin(login.getId(), login.getPassword());
        return "redirect:/logins";
    }

    @GetMapping("/logins/{id}/delete")
    public String loginBorrar(@PathVariable("id") int id) {
        loginRepository.eliminarLogin(id);
        return "redirect:/logins";
    }
}