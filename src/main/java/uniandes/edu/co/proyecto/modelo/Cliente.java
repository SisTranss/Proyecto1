package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente {

    @Id
    @Column(name = "num_id")
    private Integer num_id;

    private Integer tipo_persona;

    public Cliente(Integer tipo_persona) {
        this.tipo_persona = tipo_persona;
    }

    public Cliente(){
        ;
    }

    public Integer getNum_id() {
        return num_id;
    }

    public void setNum_id(Integer num_id) {
        this.num_id = num_id;
    }

    public Integer getTipo_persona() {
        return tipo_persona;
    }

    public void setTipo_persona(Integer tipo_persona) {
        this.tipo_persona = tipo_persona;
    }

   

    
}
