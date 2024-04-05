package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import uniandes.edu.co.proyecto.modelo.Cliente;
import uniandes.edu.co.proyecto.repositorio.ClienteRepository;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public String clientes(Model model){
        model.addAttribute("clientes", clienteRepository.darClientes());
        return "clientes";
    }
    
    @GetMapping("/clientes/new")
    public String clienteForm(Model model){
        model.addAttribute("cliente", new Cliente());
        return "clienteNuevo";
    }

    @GetMapping("/clientes/new/save")
    public String clienteSave(@ModelAttribute Cliente cliente){
        clienteRepository.insertarCliente(cliente.getNum_id(), cliente.getTipo_persona());
        return "clienteNuevo";
    }

    
}
