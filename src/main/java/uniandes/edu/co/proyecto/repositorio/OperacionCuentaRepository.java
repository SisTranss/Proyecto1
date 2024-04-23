package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.OperacionCuenta;

import java.sql.Date;


public interface OperacionCuentaRepository extends JpaRepository<OperacionCuenta, Integer>{

    @Query(value = "SELECT * FROM operaciones_cuentas", nativeQuery = true)
    Collection<OperacionCuenta> darOperaciones();

    @Query(value = "SELECT * FROM operaciones_cuentas WHERE id_cuenta = :id_cuenta", nativeQuery = true)
    Collection<OperacionCuenta> darOperacionesPorCuenta(@Param("id_cuenta") Integer id_cuenta);


    @Query(value = "SELECT * FROM operaciones_cuentas WHERE id = :id", nativeQuery = true)
    OperacionCuenta darOperacion(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM operaciones_cuentas WHERE id = :id", nativeQuery = true)
    void eliminarOperacion_cuenta(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO operaciones_cuentas (id, tipo_operacion, fecha_operacion, id_cuenta, monto_pago, punto_atencion) VALUES ( general_seq.nextval , :tipo_operacion, :fecha_operacion, :id_cuenta, :monto_pago, :punto_atencion)", nativeQuery = true)
    void insertarOperacion_cuenta(@Param("tipo_operacion") String tipo_operacion, @Param("fecha_operacion") Date fecha_operacion, @Param("id_cuenta") Integer id_cuenta, @Param("monto_pago") Float monto_pago,
    @Param("punto_atencion") Integer punto_atencion);

}