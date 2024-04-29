package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.OperacionCuenta;

import java.sql.Date;
import java.time.LocalDate;


public interface OperacionCuentaRepository extends JpaRepository<OperacionCuenta, Integer>{

    @Query(value = "SELECT * FROM operaciones_cuentas", nativeQuery = true)
    Collection<OperacionCuenta> darOperaciones();

    @Query(value = "SELECT * FROM operaciones_cuentas WHERE id_cuenta = :id_cuenta", nativeQuery = true)
    Collection<OperacionCuenta> darOperacionesPorCuenta(@Param("id_cuenta") Integer id_cuenta);

    @Query(value = "SELECT * FROM operaciones_cuentas WHERE id_cuenta = :id_cuenta AND fecha_operacion >= :fecha_operacion", nativeQuery = true)
    Collection<OperacionCuenta> darOperacionesPorCuentaUltimoMes(@Param("id_cuenta") Integer id_cuenta,@Param("fecha_operacion") LocalDate fecha);


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


    @Query(value = "SELECT * FROM operaciones_cuentas  WHERE id_cuenta =:id AND FECHA_OPERACION BETWEEN :fechaInic and :fechaFin", nativeQuery = true)
    Collection<OperacionCuenta> darOperacionesMes(Integer id, String fechaInic, String fechaFin);
   
    @Query(value = "SELECT sum(monto_pago) FROM(select * from operaciones_cuentas Where id_cuenta =:id and TIPO_OPERACION = 'consignar' AND FECHA_OPERACION BETWEEN :fechaInic and :fechaFin)", nativeQuery = true)
    Integer darDineroInMesOPCuenta(Integer id, String fechaInic, String fechaFin);
    
    @Query(value = "SELECT sum(monto_pago) FROM(select * from operaciones_cuentas Where id_cuenta =:id and TIPO_OPERACION = 'retirar' AND FECHA_OPERACION BETWEEN :fechaInic and :fechaFin)", nativeQuery = true)
    Integer darDineroOutMesOPCuenta(Integer id, String fechaInic, String fechaFin);
    
    //calcular dinero desde fin de mes hasta actualidad
    
    @Query(value = "SELECT sum(monto_pago) FROM(select * from operaciones_cuentas Where id_cuenta =:id and TIPO_OPERACION = 'consignar' AND FECHA_OPERACION BETWEEN :fechaFin and SYSDATE)", nativeQuery = true)
    Integer dineroInOPCuenta(Integer id, String fechaFin);
    
    @Query(value = "SELECT sum(monto_pago) FROM(select * from operaciones_cuentas Where id_cuenta =:id and TIPO_OPERACION = 'retirar' AND FECHA_OPERACION BETWEEN :fechaFin and SYSDATE)", nativeQuery = true)
    Integer dineroOutOPCuenta(Integer id, String fechaFin);
    
}