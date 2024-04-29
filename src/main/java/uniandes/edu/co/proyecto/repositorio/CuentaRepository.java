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


    @Query(value = "SELECT * FROM cuentas WHERE id_oficina = :id_oficina", nativeQuery = true)
    Collection<Cuenta> darCuentasPorIDoficina(@Param("id_oficina") Integer id_oficina);

    @Query(value = "SELECT * FROM cuentas WHERE num_doc_cliente = :num_doc_cliente", nativeQuery = true)
    Collection<Cuenta> darCuentasPorCliente2(@Param("num_doc_cliente") Integer num_doc_cliente);

    @Query(value = "SELECT * FROM cuentas WHERE num_doc_cliente = :num_doc_cliente AND id_oficina = :id_oficina", nativeQuery = true)
    Collection<Cuenta> darCuentasdeClienteporOficina(@Param("num_doc_cliente") Integer num_doc_cliente, @Param("id_oficina") Integer id_oficina);

    @Query(value = "SELECT c.* FROM cuentas c INNER JOIN usuarios u ON c.num_doc_cliente = u.id WHERE u.num_doc = :num_doc", nativeQuery = true)
    Collection<Cuenta> darCuentasPorCliente(@Param("num_doc") String num_doc);

    @Query(value = "SELECT * FROM cuentas WHERE tipo = :tipo", nativeQuery = true)
    Collection<Cuenta> darCuentasPorTipo(@Param("tipo") String tipo);

    @Query(value = "SELECT * FROM cuentas WHERE saldo BETWEEN :min_saldo AND :max_saldo", nativeQuery = true)
    Collection<Cuenta> darCuentasPorRangoSaldos(@Param("min_saldo") Integer min_saldo, @Param("max_saldo") Integer max_saldo);

    @Query(value = "SELECT * FROM cuentas WHERE TRUNC(ultima_transaccion) = TO_DATE(:ultima_transaccion, 'YYYY-MM-DD')", nativeQuery = true)
    Collection<Cuenta> darCuentasPorUltimoMov(@Param("ultima_transaccion") String ultima_transaccion);


    @Query(value = "SELECT * FROM cuentas WHERE tipo = :tipo AND id_oficina = :id_oficina", nativeQuery = true)
    Collection<Cuenta> darCuentasPorTipoCuentayIDoficina(@Param("tipo") String tipo, @Param("id_oficina") Integer id_oficina);

    @Query(value = "SELECT * FROM cuentas WHERE saldo BETWEEN :min_saldo AND :max_saldo AND id_oficina = :id_oficina", nativeQuery = true)
    Collection<Cuenta> darCuentasPorRangoSaldosyIDoficina(@Param("min_saldo") Integer min_saldo, @Param("max_saldo") Integer max_saldo, @Param("id_oficina") Integer id_oficina);

    @Query(value = "SELECT * FROM cuentas WHERE TRUNC(ultima_transaccion) = TO_DATE(:ultima_transaccion, 'YYYY-MM-DD') AND id_oficina = :id_oficina", nativeQuery = true)
    Collection<Cuenta> darCuentasPorUltimoMovyIDoficina(@Param("ultima_transaccion") String ultima_transaccion, @Param("id_oficina") Integer id_oficina);


    @Query(value = "SELECT * FROM cuentas WHERE tipo = :tipo AND num_doc_cliente = :num_doc_cliente", nativeQuery = true)
    Collection<Cuenta> darCuentasPorTipoCuentayCliente(@Param("num_doc_cliente") Integer num_doc_cliente, @Param("tipo") String tipo);

    @Query(value = "SELECT * FROM cuentas WHERE saldo BETWEEN :min_saldo AND :max_saldo AND num_doc_cliente = :num_doc_cliente", nativeQuery = true)
    Collection<Cuenta> darCuentasPorRangoSaldosyCliente(@Param("min_saldo") Integer min_saldo, @Param("max_saldo") Integer max_saldo, @Param("num_doc_cliente") Integer num_doc_cliente);

    @Query(value = "SELECT * FROM cuentas WHERE TRUNC(ultima_transaccion) = TO_DATE(:ultima_transaccion, 'YYYY-MM-DD') AND num_doc_cliente = :num_doc_cliente", nativeQuery = true)
    Collection<Cuenta> darCuentasPorUltimoMovyCliente(@Param("ultima_transaccion") String ultima_transaccion, @Param("num_doc_cliente") Integer num_doc_cliente);




    @Modifying
    @Transactional
    @Query(value = "UPDATE cuentas SET estado = :estado WHERE saldo = 0 AND estado = 'activa' AND id = :id", nativeQuery = true)
    void actualizarEstadoCuenta(@Param("id") Integer id, @Param("estado") String estado);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "UPDATE cuentas SET saldo = saldo - :monto_pago WHERE id = :id AND saldo >= :monto_pago AND estado NOT IN ('cerrada', 'desactivada')" , nativeQuery = true)
    Integer actualizarSaldoRetiro(@Param("id") Integer id, @Param("monto_pago") Float monto_pago);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cuentas SET saldo = saldo + :monto_pago WHERE id = :id", nativeQuery = true)
    Integer actualizarSaldoConsignar(@Param("id") Integer id, @Param("monto_pago") Float monto_pago);

    
    @Query(value = "SELECT saldo from cuentas WHERE id =:id", nativeQuery = true)
    Integer darDineroActual(@Param("id") Integer id);
}
