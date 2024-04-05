package uniandes.edu.co.proyecto.repositorio;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
//import uniandes.edu.co.proyecto.modelo.Operacion_cuenta;
//import uniandes.edu.co.proyecto.modelo.Operacion_prestamo;
import uniandes.edu.co.proyecto.modelo.PuntoAtencion;
//import uniandes.edu.co.proyecto.modelo.Transaccion;

public interface PuntoAtencionRepository extends JpaRepository<PuntoAtencion, Integer> {

    @Query(value = "SELECT * FROM puntos_atencion", nativeQuery = true)
    Collection<PuntoAtencion> darPuntosAtencion();

    @Query(value = "SELECT * FROM puntos_atencion WHERE id =:id", nativeQuery = true)
    PuntoAtencion darPuntoAtencion(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO puntos_atencion (id, tipo_punto, oficina_id) VALUES (bancandes_sequence.nextval, :tipo_punto, :oficina_id)", nativeQuery = true)
    void insertarPuntoAtencion(@Param("tipo_punto") String tipo_punto, @Param("oficina_id") int oficina_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE puntos_atencion SET tipo_punto=:tipo_punto, oficina_id=:oficina_id  WHERE id = :id", nativeQuery = true)
    void modificarPuntoAtencion(@Param("id") int id, @Param("tipo_punto") String tipo_punto, @Param("oficina_id") int oficina_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM puntos_atencion WHERE id =:id", nativeQuery = true)     
    void eliminarPuntoAtencion(@Param("id") int id);


}
