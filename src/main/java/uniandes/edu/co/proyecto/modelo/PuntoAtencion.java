package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "puntos_atencion")
public class PuntoAtencion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String tipo_punto;
    
    @ManyToOne
    @JoinColumn(name = "oficina_id", referencedColumnName = "id")
    private Oficina oficina_id;

    public PuntoAtencion(String tipo_punto, Oficina oficina_id) {
        this.tipo_punto = tipo_punto;
        this.oficina_id = oficina_id;
    }

    public PuntoAtencion(){;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo_punto() {
        return tipo_punto;
    }

    public void setTipo_punto(String tipo_punto) {
        this.tipo_punto = tipo_punto;
    }

    public Oficina getOficina() {
        return oficina_id;
    }

    public void setOficina(Oficina oficina_id) {
        this.oficina_id = oficina_id;
    }

    public int getIdOficinaAsociada(){
        return this.oficina_id.getId();
    }
    
    
}
