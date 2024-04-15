package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Login;


public interface LoginRepository extends JpaRepository<Login, Integer>{
    @Query(value = "SELECT * FROM logins",nativeQuery = true)
    Collection<Login> darLogins();

    @Query(value = "SELECT id FROM logins WHERE login = :login AND password = :password",nativeQuery = true)
    Integer buscarLogin(@Param("login") String login,@Param("password") String password);

    @Query(value = "SELECT * FROM logins WHERE id =:id",nativeQuery = true)
    Login darLogin(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO logins (id,login, password) VALUES (general_seq.nextval, (general_sequence.nextval, :login, :password)", nativeQuery = true)
    void insertarLogin(@Param("login") String login,@Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE logins SET password =:password WHERE id = :id", nativeQuery = true)
    void updateLogin(@Param("id") int id,@Param("password") String password);

    @Modifying
    @Transactional
    @Query(value =  "DELETE FROM logins WHERE id = :id", nativeQuery = true)
    void eliminarLogin(@Param("id") int id);
}
