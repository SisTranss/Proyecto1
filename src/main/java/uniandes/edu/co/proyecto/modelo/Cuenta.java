package uniandes.edu.co.proyecto.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;


@Entity
@Table(name="cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private Float saldo;

    private Date ultima_transaccion;

    private String estado;

    private String tipo;

    @ManyToOne
    @JoinColumn(name = "id_oficina", referencedColumnName = "id")
    private Oficina id_oficina;

    @ManyToOne
    @JoinColumn(name = "num_doc_cliente", referencedColumnName = "num_id")
    private Cliente num_doc_cliente;

    public Cuenta(Float saldo, Date ultima_transaccion, String estado, String tipo, Oficina id_oficina,
            Cliente num_doc_cliente) {
        this.saldo = saldo;
        this.ultima_transaccion = ultima_transaccion;
        this.estado = estado;
        this.tipo = tipo;
        this.id_oficina = id_oficina;
        this.num_doc_cliente = num_doc_cliente;

    }
    
    public Cuenta(){;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public Date getUltima_transaccion() {
        return ultima_transaccion;
    }

    public void setUltima_transaccion(Date ultima_transaccion) {
        this.ultima_transaccion = ultima_transaccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Oficina getId_oficina() {
        return id_oficina;
    }

    public void setId_oficina(Oficina id_oficina) {
        this.id_oficina = id_oficina;
    }

    public Cliente getNum_doc_cliente() {
        return num_doc_cliente;
    }

    public void setNum_doc_cliente(Cliente num_doc_cliente) {
        this.num_doc_cliente = num_doc_cliente;
    }

    


    
}
