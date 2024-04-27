package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.repositorio.OperacionCuentaRepository;
import uniandes.edu.co.proyecto.repositorio.TransaccionRepository;

@Controller
public class ExtractoBancarioController {
    
    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    OperacionCuentaRepository operacionCuentaRepository;

    @Autowired
    TransaccionRepository transaccionesRepository;

    @GetMapping("/extractoBancario2")
    public String extractoBancario(Model model, @Param("idCuenta")Integer idCuenta, @Param("mes")Integer mes) {

        if(idCuenta==null || mes==null){;
           // model.addAttribute("operacionesCuentas", operacionCuentaRepository.darOperaciones());
        }

        else if(idCuenta != null && mes != null){
            String fechaInic;
            String fechaFin;
            if(mes < 10 && mes != 2){
                fechaInic= "01/0"+mes+"/24";
                fechaFin= "30/0"+mes+"/24";
             
            }
            else if(mes > 10 && mes != 2){
                fechaInic = "01/"+mes+"/24";
                fechaFin = "30/"+mes+"/24";
                
            }
            else{
                fechaInic = "01/"+mes+"/24";
                fechaFin = "28/"+mes+"/24";
            }
            model.addAttribute("operacionesCuentas", operacionCuentaRepository.darOperacionesMes(idCuenta, fechaInic, fechaFin));
            model.addAttribute("transacciones", transaccionesRepository.darTransaccionesMes(idCuenta, fechaInic, fechaFin));
            

            Integer dineroInTransaccionesMes;
            if(transaccionesRepository.darDineroInMes(idCuenta, fechaInic, fechaFin) == null){
                dineroInTransaccionesMes = 0;
            }
            else{
                dineroInTransaccionesMes = transaccionesRepository.darDineroInMes(idCuenta, fechaInic, fechaFin);
            }

            Integer dineroOutTransaccionesMes;
            if(transaccionesRepository.darDineroOutMes(idCuenta, fechaInic, fechaFin) == null){
                dineroOutTransaccionesMes = 0;
            }
            else{
                dineroOutTransaccionesMes = transaccionesRepository.darDineroOutMes(idCuenta, fechaInic, fechaFin);
            }

            Integer dineroInOPCuentas;
            if(operacionCuentaRepository.darDineroInMesOPCuenta(idCuenta, fechaInic, fechaFin) == null){
                dineroInOPCuentas = 0;
            }
            else{
                dineroInOPCuentas = operacionCuentaRepository.darDineroInMesOPCuenta(idCuenta, fechaInic, fechaFin);
            }

            Integer dineroOutOPCuentas;
            if(operacionCuentaRepository.darDineroOutMesOPCuenta(idCuenta, fechaInic, fechaFin) == null){
                dineroOutOPCuentas = 0;
            }
            else{
                dineroOutOPCuentas = operacionCuentaRepository.darDineroOutMesOPCuenta(idCuenta, fechaInic, fechaFin);
            }
            
            int dineroNetoMes = dineroInTransaccionesMes -dineroOutTransaccionesMes + dineroInOPCuentas - dineroOutOPCuentas;
            System.out.println(dineroNetoMes);

            Integer dineroInRestanteOPCuenta;
            if(operacionCuentaRepository.dineroInOPCuenta(idCuenta, fechaFin)==null){
                dineroInRestanteOPCuenta = 0;
            }
            else{
                dineroInRestanteOPCuenta = operacionCuentaRepository.dineroInOPCuenta(idCuenta, fechaFin);
            }

            Integer dineroOutRestanteOPCuenta;
            if(operacionCuentaRepository.dineroOutOPCuenta(idCuenta, fechaFin)==null){
                dineroOutRestanteOPCuenta = 0;
            }
            else{
                dineroOutRestanteOPCuenta = operacionCuentaRepository.dineroOutOPCuenta(idCuenta, fechaFin);
            }

            Integer dineroInRestanteTransaccion;
            if(transaccionesRepository.dineroRestanteIn(idCuenta, fechaFin)==null){
                dineroInRestanteTransaccion = 0;
            }
            else{
                dineroInRestanteTransaccion = transaccionesRepository.dineroRestanteIn(idCuenta, fechaFin);
            }

            Integer dineroOutRestanteTransaccion;
            if(transaccionesRepository.dineroRestanteOut(idCuenta, fechaFin)==null){
                dineroOutRestanteTransaccion = 0;
            }
            else{
                dineroOutRestanteTransaccion = transaccionesRepository.dineroRestanteOut(idCuenta, fechaFin);
            }
            
            int dineroNetoAfterMes = dineroInRestanteOPCuenta+dineroInRestanteTransaccion -dineroOutRestanteOPCuenta-dineroOutRestanteTransaccion;

            int dineroFinMes = cuentaRepository.darDineroActual(idCuenta) + dineroNetoAfterMes*(-1);
            int dineroInicioMes = dineroFinMes  + dineroNetoMes*(-1);

            model.addAttribute("inicioMes", dineroInicioMes);
            model.addAttribute("finMes", dineroFinMes);

        }
        return "extractoBancario2";
    }
    

}
