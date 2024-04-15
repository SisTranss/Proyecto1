package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.Prestamo;


public interface PrestamoRepository extends JpaRepository<Prestamo, Integer>{

    @Query(value = "SELECT * FROM prestamos", nativeQuery = true)
    Collection<Prestamo> darPrestamos();

    @Query(value = "SELECT * FROM prestamos WHERE id = :id", nativeQuery = true)
    Prestamo darPrestamo(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM prestamos WHERE id = :id", nativeQuery = true)
    void eliminarPrestamo(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO prestamos (id, proposito, estado, monto, tasa, cuotas, dia_pago, valor_cuota, num_doc_cliente) VALUES ( general_seq.nextval , :proposito, :estado, :monto, :tasa, :cuotas, :dia_pago, :valor_cuota, :num_doc_cliente)", nativeQuery = true)
    void insertarPrestamo(@Param("proposito") String proposito, @Param("estado") String estado, @Param("monto") Float monto, @Param("tasa") Float tasa, @Param("cuotas") Integer cuotas, @Param("dia_pago") Integer dia_pago, 
    @Param("valor_cuota") Float valor_pago, @Param("num_doc_cliente") Integer num_doc_cliente);
    

    @Modifying
    @Transactional
    @Query(value = "UPDATE prestamos SET monto = monto - :monto_pagar WHERE id = :id AND estado = 'aprobado'", nativeQuery = true)
    void reducirSaldoPrestamo(@Param("id") Integer id, @Param("monto_pagar") Float monto_pagar);

    @Modifying
    @Transactional
    @Query(value = "UPDATE prestamos SET estado = 'pagado' WHERE id = :id AND monto = 0", nativeQuery = true)
    void cerrarPrestamo(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE prestamos SET estado = 'aprobado' WHERE id = :id", nativeQuery = true)
    void aprobarPrestamo(@Param("id") Integer idr);

    @Modifying
    @Transactional
    @Query(value = "UPDATE prestamos SET estado = 'rechazado' WHERE id = :id", nativeQuery = true)
    void rechazarPrestamo(@Param("id") Integer id);


}
