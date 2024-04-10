package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name="usuarios")
public class Usuario {

    @Id
    @OneToOne
    @JoinColumn(name = "Id", referencedColumnName = "Id")
    private Login login;
    
    @OneToOne
    @JoinColumn(name = "lugar")
    private Lugar lugar;
    
    private int num_id;
    private String nombre;
    private String email;
    private String nacionalidad;
    private int telefono;
    private int tipoUsuario;
    private String tipoDoc;


    public Usuario(){;}

    public Usuario(Integer num_id, String nombre, String email, String nacionalidad, int telefono, int tipoUsuario,
            Lugar lugar, String login, String palabraClave, String tipoDoc) {
        this.num_id = num_id;
        this.nombre = nombre;
        this.email = email;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
        this.lugar = lugar;
        this.tipoDoc = tipoDoc;
    }

    public Long getId() {
        return login.getId();
    }

    public void setId(Long id) {
        login.setId(id);
    }

    public Integer getNum_id() {
        return num_id;
    }

    public void setNum_id(Integer num_id) {
        this.num_id = num_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    

}   
