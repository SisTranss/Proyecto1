package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

    @Query(value = "SELECT * FROM clientes",nativeQuery = true)
     Collection<Cliente> darClientes();
 
     @Query(value = "SELECT * FROM clientes WHERE num_id = :num_id",nativeQuery = true)
     Cliente darCliente(@Param("num_id") int numId);
 
     @Modifying
     @Transactional
     @Query(value = "INSERT INTO clientes (num_id, tipo_persona) VALUES (:num_id, :tipo_persona)", nativeQuery = true)
     void insertarCliente(@Param("num_id") int num_id, @Param("tipo_persona") int tipo_persona);
 
     @Modifying
     @Transactional
     @Query(value = "UPDATE clientes SET tipo_persona =:tipo_persona WHERE num_id = :num_id", nativeQuery = true)
     void updateCliente(@Param("num_id") int num_id,@Param("tipo_persona") int tipo_persona);
 
     @Modifying
     @Transactional
     @Query(value =  "DELETE FROM clientes WHERE num_id = :num_id", nativeQuery = true)
     void eliminarCliente(@Param("num_id") int num_id);
     
 }
