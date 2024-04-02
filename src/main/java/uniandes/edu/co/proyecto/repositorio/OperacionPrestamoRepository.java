package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.OperacionPrestamo;
import uniandes.edu.co.proyecto.modelo.Operacion_cuenta;

import java.sql.Date;


public interface OperacionPrestamoRepository extends JpaRepository<Operacion_cuenta, Integer>{

    @Query(value = "SELECT * FROM operaciones_prestamos", nativeQuery = true)
    Collection<OperacionPrestamo> darOperaciones();

    @Query(value = "SELECT * FROM operaciones_prestamos WHERE id = :id", nativeQuery = true)
    OperacionPrestamo darOperacion(@Param("id") Integer id);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM operaciones_prestamos WHERE id = :id", nativeQuery = true)
    void eliminarOperacion_prestamo(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO operaciones_prestamos (id, tipo_operacion, fecha_operacion, id_prestamo, monto_pago, punto_atencion) VALUES ( general_seq.nextval , :tipo_operacion, :fecha_operacion, :id_prestamo, :monto_pago, :num_doc_usuario, :punto_atencion)", nativeQuery = true)
    void insertarOperacion_prestamo(@Param("tipo_operacion") String tipo_operacion, @Param("fecha_operacion") Date fecha_operacion, @Param("id_prestamo") Integer id_prestamo, @Param("monto_pago") Float monto_pago,
    @Param("punto_atencion") Integer punto_atencion);

}
