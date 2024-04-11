package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "logins")
public class Login {

    @EmbeddedId
    private LoginPk loginPk;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getLogin() {
        return loginPk.getUsername();
    }

    public void setLogin(String login) {
        this.loginPk.setUsername(login);
    }

    public String getPassword() {
        return loginPk.getPassword();
    }

    public void setPassword(String password) {
        this.loginPk.setPassword(password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    
}
