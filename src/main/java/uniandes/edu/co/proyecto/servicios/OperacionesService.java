package uniandes.edu.co.proyecto.servicios;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.OperacionCuenta;
import uniandes.edu.co.proyecto.repositorio.OperacionCuentaRepository;

@Service
public class OperacionesService {

    OperacionCuentaRepository operacionesRepository;

    public OperacionesService(OperacionCuentaRepository operacionesRepository)
    {
        this.operacionesRepository = operacionesRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true, rollbackFor = Exception.class)
    public Collection<OperacionCuenta> darOperacionesCuentaSerializable(int id_cuenta) throws InterruptedException {
        LocalDate fecha = LocalDate.now().minusDays(30);
        Collection<OperacionCuenta> operaciones = operacionesRepository.darOperacionesPorCuentaUltimoMes(id_cuenta,fecha); // Consultar OperacionCuenta.
        System.out.println("Operaciones encontradas: " + operaciones.size());
        Thread.sleep(30000);
        operaciones = operacionesRepository.darOperacionesPorCuentaUltimoMes(id_cuenta,fecha);
        return operaciones;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Exception.class)
    public Collection<OperacionCuenta> darOperacionesCuentaReadCommited(int id_cuenta) throws InterruptedException {
        LocalDate fecha = LocalDate.now().minusDays(30);
        Collection<OperacionCuenta> operaciones = operacionesRepository.darOperacionesPorCuentaUltimoMes(id_cuenta,fecha); // Consultar OperacionCuenta.
        System.out.println("Operaciones encontradas: " + operaciones.size());
        Thread.sleep(30000);
        operaciones = operacionesRepository.darOperacionesPorCuentaUltimoMes(id_cuenta,fecha);
        return operaciones;
    }

}