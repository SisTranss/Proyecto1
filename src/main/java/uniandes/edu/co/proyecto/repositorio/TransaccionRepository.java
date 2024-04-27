package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Transaccion;


public interface TransaccionRepository extends JpaRepository<Transaccion, Integer>{

    @Query(value = "SELECT * FROM transacciones", nativeQuery = true)
    Collection<Transaccion> darTransacciones();

    @Query(value = "SELECT * FROM transacciones WHERE id = :id", nativeQuery = true)
    Transaccion darTransaccion(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM transacciones WHERE id = :id", nativeQuery = true)
    void eliminarTransaccion(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO transacciones (id, fecha_operacion, monto_pago, id_cuenta_origen, id_cuenta_destino, punto_atencion) VALUES ( general_seq.nextval , :fecha_operacion, :monto_pago, :id_cuenta_origen, :id_cuenta_destino, :punto_atencion)", nativeQuery = true)
    void insertarTransaccion(@Param("fecha_operacion") Date fecha_operacion, @Param("monto_pago") Float monot_pago, @Param("id_cuenta_origen") Integer id_cuenta_origen, 
    @Param("id_cuenta_destino") Integer id_cuenta_destino, @Param("punto_atencion") Integer punto_atencion);

    @Query(value = "SELECT * FROM transacciones  WHERE (id_cuenta_origen =:id OR id_cuenta_destino =:id) AND FECHA_OPERACION BETWEEN :fechaInic and :fechaFin", nativeQuery = true)
    Collection<Transaccion> darTransaccionesMes(Integer id, String fechaInic, String fechaFin);

    @Query(value = "SELECT sum(monto_pago) FROM(select *  FROM transacciones WHERE ID_CUENTA_DESTINO =:id AND FECHA_OPERACION BETWEEN :fechaInic and :fechaFin)", nativeQuery = true)
    Integer darDineroInMes(Integer id, String fechaInic, String fechaFin);
    
    @Query(value = "SELECT sum(monto_pago) FROM(select *  FROM transacciones WHERE ID_CUENTA_ORIGEN =:id AND FECHA_OPERACION BETWEEN :fechaInic and :fechaFin)", nativeQuery = true)
    Integer darDineroOutMes(Integer id, String fechaInic, String fechaFin);
    
    @Query(value = "SELECT sum(monto_pago) FROM(select *  FROM transacciones WHERE ID_CUENTA_ORIGEN =:id AND FECHA_OPERACION BETWEEN :fechaFin and SYSDATE)", nativeQuery = true)
    Integer dineroRestanteIn(Integer id, String fechaFin);
    
    @Query(value = "SELECT sum(monto_pago) FROM(select *  FROM transacciones WHERE ID_CUENTA_DESTINO =:id AND FECHA_OPERACION BETWEEN :fechaFin and SYSDATE)", nativeQuery = true)
    Integer dineroRestanteOut(Integer id, String fechaFin);

}
