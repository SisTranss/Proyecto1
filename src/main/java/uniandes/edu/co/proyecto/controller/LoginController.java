package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.modelo.Login;
import uniandes.edu.co.proyecto.repositorio.LoginRepository;

@Controller
public class LoginController {
    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("/logins")
    public String logins(Model model){
        model.addAttribute("logins", loginRepository.darLogins());
        return "logins";
    }

    @GetMapping("/login")
    public String validarLogin(@RequestParam String login, @RequestParam String password, Model model) {
        Integer id = loginRepository.buscarLogin(login, password);
        System.out.println(id);
        if (id != null) {
            model.addAttribute("login", loginRepository.darLogin(id));
            return "login";
        } else {
            return "redirect:/";
        }        
    }   

    @GetMapping("/")
    public String home() {
    return "index";
    }

    @GetMapping("/logins/new")
    public String loginForm(Model model) {
        model.addAttribute("login", new Login());
        return "loginNuevo";
    }

    @PostMapping("/logins/new/save")
    public String loginGuardar(@ModelAttribute Login login) {
        loginRepository.insertarLogin(login.getlogin(),login.getPassword());
        return "redirect:/logins";
    }

    @GetMapping("/logins/{id}/edit")
    public String loginEditarForm(@PathVariable("id") int id, Model model) {
        Login login = loginRepository.darLogin(id);
        if (login != null) {
            model.addAttribute("login", login);
            return "loginEditar";
        } else {
            return "redirect:/logins";
        }
    }

    @PostMapping("/logins/{id}/edit/save")
    public String loginEditarGuardar(@PathVariable("id") int numId, @ModelAttribute Login login) {
        loginRepository.updateLogin(login.getId(), login.getPassword());
        return "redirect:/logins";
    }

    @GetMapping("/logins/{id}/delete")
    public String loginBorrar(@PathVariable("id") int id) {
        loginRepository.eliminarLogin(id);
        return "redirect:/logins";
    }
}