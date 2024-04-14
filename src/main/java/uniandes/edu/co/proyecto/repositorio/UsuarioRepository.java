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
    Usuario darUsuario(@Param("id") int d);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios (id, nombre, email, nacionalidad, telefono, tipo_Usuario,lugar, tipo_Doc, num_Doc) VALUES (general_seq.nextval, :numId, :nombre, :email, :nacionalidad, :telefono, :tipoUsuario, :lugar, :tipoDoc, :NumDoc)", nativeQuery = true)
    void insertarUsuario(@Param("numId") int id, @Param("nombre") String nombre,@Param("email") String email, @Param("nacionalidad") String nacionalidad,
                        @Param("telefono") int telefono,@Param("tipoUsuario") int tipoUsuario,@Param("lugar") int lugar, @Param("tipoDoc")String tipoDoc, @Param("numDoc")String numDoc);

    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET nombre = :nombre, email =:email, nacionalidad = :nacionalidad, telefono = :telefono, tipo_Usuario = :tipoUsuario, lugar = :lugar WHERE id = :id", nativeQuery = true)
    void updateUsuario(@Param("id") int id,@Param("nombre") String nombre,@Param("email") String email, @Param("nacionalidad") String nacionalidad,@Param("telefono") int telefono,
                        @Param("tipoUsuario") int tipoUsuario,@Param("lugar") int lugar);

    @Modifying
    @Transactional
    @Query(value =  "DELETE FROM usuarios WHERE id = :id", nativeQuery = true)
    void eliminarUsuario(@Param("id") int id);
    
}
