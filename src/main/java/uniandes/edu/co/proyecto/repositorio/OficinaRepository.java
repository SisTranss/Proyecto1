package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Oficina;

public interface OficinaRepository extends JpaRepository<Oficina, Integer> {

    @Query(value = "SELECT * FROM oficinas", nativeQuery = true)
    Collection<Oficina> darOficinas();

    
    @Query(value = "SELECT * FROM oficinas WHERE id = :id", nativeQuery = true)
    Oficina darOficina(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT into oficinas (id, nombre, departamento, ciudad, direccion, numero_puntos_at, num_doc_gerente_oficina) VALUES (:id, :nombre, :departamento, :ciudad, :direccion, :numero_puntos_at, :num_doc_gerente_oficina)", nativeQuery = true)
    void insertarOficina(@Param("id") Integer id, @Param("nombre") String nombre, @Param("departamento") String departamento,
            @Param("ciudad") String ciudad, @Param("direccion") String direccion,
            @Param("numero_puntos_at") int numeroPuntosAt, @Param("num_doc_gerente_oficina") int num_doc_gerente_oficina);

            

    @Modifying
    @Transactional
    @Query(value = "UPDATE oficinas SET nombre=:nombre,departamento=:departamento,ciudad=:ciudad, direccion=:direccion, numeroPuntosAt=:numeroPuntosAt, num_doc_gerente_oficina=:num_doc_gerente_oficina WHERE id =:id", nativeQuery = true)
    void actualizarOficina(@Param("id") int id, @Param("nombre") String nombre, @Param("departamento") String departamento,
            @Param("ciudad") String ciudad, @Param("direccion") String direccion,
            @Param("numeroPuntosAt") Integer numeroPuntosAt,
            @Param("num_doc_gerente_oficina") int num_doc_gerente_oficina);

 
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM oficinas WHERE id =:id", nativeQuery = true)     
    void eliminarOficina(@Param("id") int id);

}
