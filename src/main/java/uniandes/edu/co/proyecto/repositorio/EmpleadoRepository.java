package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{
 
    @Query(value = "SELECT * FROM empleados",nativeQuery = true)
    Collection<Empleado> darEmpleados();

    @Query(value = "SELECT * FROM empleados WHERE num_id = :num_id",nativeQuery = true)
    Empleado darEmpleado(@Param("num_id") int num_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO empleados (num_id, cargo) VALUES (general_seq.nextval, (:num_id, :cargo)", nativeQuery = true)
    void insertarEmpleado(@Param("num_id") int num_id,@Param("cargo") int cargo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE empleados SET cargo =:cargo WHERE num_id = :num_id", nativeQuery = true)
    void updateEmpleado(@Param("num_id") int num_id,@Param("cargo") int cargo);

    @Modifying
    @Transactional
    @Query(value =  "DELETE FROM empleados WHERE num_id = :num_id", nativeQuery = true)
    void eliminarEmpleado(@Param("num_id") int num_id);

}