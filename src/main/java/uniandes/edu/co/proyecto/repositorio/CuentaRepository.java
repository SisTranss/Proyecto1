package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Cuenta;
//import uniandes.edu.co.proyecto.modelo.Operacion_cuenta;
//import uniandes.edu.co.proyecto.modelo.Transaccion;

import java.sql.Date;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

    @Query(value = "SELECT * FROM cuentas ", nativeQuery = true)
    Collection<Cuenta> darCuentas();

    @Query(value = "SELECT * FROM cuentas WHERE id = :id", nativeQuery = true)
    Cuenta darCuenta(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cuentas (tipo, id, estado, saldo, ultima_transaccion, num_doc_cliente, id_oficina) VALUES (:tipo, general_seq.nextval, :estado, :saldo, :ultima_transaccion, :num_doc_cliente, :id_oficina)", nativeQuery = true)
    void insertarCuenta(@Param("tipo") String tipo, @Param("estado") String estado, @Param("saldo") Float saldo,
            @Param("ultima_transaccion") Date ultima_transaccion, @Param("num_doc_cliente") Integer num_doc_cliente,
            @Param("id_oficina") Integer id_oficina);

}
