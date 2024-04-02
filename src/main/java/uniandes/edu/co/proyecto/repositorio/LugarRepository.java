package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Lugar;


public interface LugarRepository extends JpaRepository<Lugar, Integer>{
    
    @Query(value = "SELECT * FROM lugares",nativeQuery = true)
    Collection<Lugar> darLugares();

    @Query(value = "SELECT * FROM lugares WHERE codigoPostal = :codigoPostal AND direccion = :direccion",nativeQuery = true)
    Lugar darLugar(@Param("codigoPostal") int codigoPostal,@Param("direccion")String direccion);
    
    @Query(value = "DELETE FROM lugares WHERE codigoPostal = :codigoPostal AND direccion = :direccion",nativeQuery = true)
    Lugar borrarLugar(@Param("codigoPostal") int codigoPostal,@Param("direccion") String direccion);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO lugares (codigoPostal, direccion, ciudad, departamento) VALUES (:codigoPostal, :direccion, :ciudad, :departamento)", nativeQuery = true)
    void insertarLugar(@Param("codigoPostal") int codigoPostal,@Param("direccion") String direccion, @Param("ciudad") String ciudad, @Param("departamento") String departamento);

    
    @Modifying
    @Transactional
    @Query(value = "UPDATE lugares SET codigoPostal =:codigoPostal, direccion =:direccion, ciudad = :ciudad, departamento = :departamento WHERE id=:id", nativeQuery = true)
    void actualizarLugar(@Param("codigoPostal") int codigoPostal, @Param("ciudad") String ciudad, @Param("departamento") String departamento,
                        @Param("id") int id, @Param("direccion") String direccion);
                
                        
}

























