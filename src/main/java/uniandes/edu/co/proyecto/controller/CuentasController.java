package uniandes.edu.co.proyecto.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.modelo.Cuenta;

import org.springframework.ui.Model;

@Controller
public class CuentasController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping("/cuentas")
    public String cuentas(Model model) {
        model.addAttribute("cuentas", cuentaRepository.darCuentas());
        return "oficinas";
    }

    @GetMapping("/oficina/cuentas/new")
    public String cuentaForm(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        return "cuentaNueva";
    }

    @PostMapping("oficina/cuentas/new/save")
    public String cuentaGuardar(@PathVariable("tipo") String tipo, @ModelAttribute Cuenta cuenta) {
    Date hoy = new Date(2024/4/3);

        cuentaRepository.insertarCuenta(cuenta.getTipo(), cuenta.getEstado(), cuenta.getSaldo(),
                hoy, cuenta.getNum_doc_cliente().getNum_id(),
                cuenta.getId_oficina().getId());
                
        return "redirect:/cuentas";
    }
}
