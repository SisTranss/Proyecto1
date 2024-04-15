package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Login;
import uniandes.edu.co.proyecto.modelo.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Login>{

    @Query(value = "SELECT * FROM usuarios",nativeQuery = true)
    Collection<Usuario> darUsuarios();

    @Query(value = "SELECT * FROM usuarios WHERE id = :id",nativeQuery = true)
    Usuario darUsuario(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios (id, nombre, email, nacionalidad, telefono, tipo_Usuario,lugar, tipo_Doc, num_Doc, codigo_Postal, direccion, ciudad, departamento) VALUES (general_seq.nextval, :id, :nombre, :email, :nacionalidad, :telefono, :tipo_Usuario, :lugar, :tipo_Doc, :num_Doc, :codigo_Postal, :direccion, :ciudad, :departamento)", nativeQuery = true)
    void insertarUsuario(@Param("id") int id, @Param("nombre") String nombre,@Param("email") String email, @Param("nacionalidad") String nacionalidad,
                        @Param("telefono") String telefono  ,@Param("tipo_Usuario") int tipo_Usuario, @Param("tipo_Doc")String tipo_Doc,
                        @Param("num_Doc")String num_Doc, @Param("codigo_Postal")int codigo_Postal, @Param("direccion")String direccion, @Param("ciudad")String ciudad,
                        @Param("departamento")String departamento);

    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET nombre = :nombre, email =:email, nacionalidad = :nacionalidad, telefono = :telefono, tipo_Usuario = :tipo_Usuario, tipo_Doc = :tipo_Doc, num_Doc = :num_Doc, codigo_Postal = :codigo_Postal, direccion = :direccion, ciudad = :ciudad, departamento = :departamento WHERE id = :id", nativeQuery = true)
    void updateUsuario(@Param("id") int id, @Param("nombre") String nombre,@Param("email") String email, @Param("nacionalidad") String nacionalidad,
                        @Param("telefono") String telefono  ,@Param("tipo_Usuario") int tipo_Usuario, @Param("tipo_Doc")String tipo_Doc,
                        @Param("num_Doc")String num_Doc, @Param("codigo_Postal")int codigo_Postal, @Param("direccion")String direccion, @Param("ciudad")String ciudad,
                        @Param("departamento")String departamento);

    @Modifying
    @Transactional
    @Query(value =  "DELETE FROM usuarios WHERE id = :id", nativeQuery = true)
    void eliminarUsuario(@Param("id") int id);
    
}
