package uniandes.edu.co.proyecto.servicios;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.OperacionCuenta;
import uniandes.edu.co.proyecto.repositorio.OperacionCuentaRepository;
import uniandes.edu.co.proyecto.repositorio.TransaccionRepository;

@Service
public class OperacionesService {

    OperacionCuentaRepository operacionesRepository;

    public OperacionesService(OperacionCuentaRepository operacionesRepository)
    {
        this.operacionesRepository = operacionesRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true, rollbackFor = Exception.class)
    public Collection<OperacionCuenta> darOperacionesCuentaSerializable(int id_cuenta) throws InterruptedException {
        Collection<OperacionCuenta> operaciones = operacionesRepository.darOperacionesPorCuenta(id_cuenta); // Consultar OperacionCuenta.
        System.out.println("Operaciones encontradas: " + operaciones.size());
        Thread.sleep(30000);
        operaciones = operacionesRepository.darOperacionesPorCuenta(id_cuenta);
        return operaciones;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Exception.class)
    public Collection<OperacionCuenta> darOperacionesCuentaReadCommited(int id_cuenta) throws InterruptedException {
        Collection<OperacionCuenta> operaciones = operacionesRepository.darOperacionesPorCuenta(id_cuenta); // Consultar OperacionCuenta.
        System.out.println("Operaciones encontradas: " + operaciones.size());
        Thread.sleep(30000);
        operaciones = operacionesRepository.darOperacionesPorCuenta(id_cuenta);
        return operaciones;
    }

}