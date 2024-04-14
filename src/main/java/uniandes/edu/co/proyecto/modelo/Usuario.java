package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name="usuarios")
public class Usuario {

    @Id
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Login id;
    
    @OneToOne
    @JoinColumn(name = "lugar")
    private Lugar lugar;
    
    private String nombre;
    private String email;
    private String nacionalidad;
    private int telefono;
    private int tipoUsuario;
    private String tipoDoc;
    private String numDoc;


    public Usuario(){;}

    public Usuario(Login idLogin, String nombre, String email, String nacionalidad, int telefono, int tipoUsuario,
            Lugar lugar, String tipoDoc, String numDoc) {
        this.id = idLogin;
        this.nombre = nombre;
        this.email = email;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
        this.lugar = lugar;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
    }

    public Login getId() {
        return id;
    }

    public void setId(Login id) {
        this.id = id;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
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

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

}   
