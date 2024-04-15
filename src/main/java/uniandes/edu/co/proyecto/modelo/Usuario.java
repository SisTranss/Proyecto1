package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name="usuarios")
public class Usuario {

    @Id 
    private int id;

    @OneToOne(fetch = FetchType.LAZY) 
    @MapsId 
    @JoinColumn(name = "id")
    private Login login;
    
    private String nombre;
    private String email;
    private String nacionalidad;
    private String telefono;
    private int tipoUsuario;
    private String tipoDoc;
    private String numDoc;
    private int codigoPostal;
    private String direccion;
    private String ciudad;
    private String departamento;


    public Usuario(){;}

    public Usuario(Login login, String nombre, String email, String nacionalidad, String telefono,
            int tipoUsuario, String tipoDoc, String numDoc, int codigoPostal, String direccion, String ciudad,
            String departamento) {
        this.login = login;
        this.nombre = nombre;
        this.email = email;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.codigoPostal = codigoPostal;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.departamento = departamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
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

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
}   
