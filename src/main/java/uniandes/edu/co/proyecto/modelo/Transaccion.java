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
@Table(name="transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private Date fecha_operacion;

    private Float monto_pago;

    @ManyToOne
    @JoinColumn(name = "punto_atencion", referencedColumnName = "id")
    private PuntoAtencion punto_atencion;

    @ManyToOne
    @JoinColumn(name = "id_cuenta_origen", referencedColumnName = "id")
    private Cuenta cuenta_origen;

    @ManyToOne
    @JoinColumn(name = "id_cuenta_destino", referencedColumnName = "id")
    private Cuenta cuenta_destino;


    public Transaccion(Integer id, Date fecha_operacion, Float monto_pago, Cuenta cuenta_origen,
            Cuenta cuenta_destino, PuntoAtencion punto_atencion) {
        this.id = id;
        this.fecha_operacion = fecha_operacion;
        this.monto_pago = monto_pago;
        this.cuenta_origen = cuenta_origen;
        this.cuenta_destino = cuenta_destino;
        this.punto_atencion = punto_atencion;
    }

    public Transaccion(){
        ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha_operacion() {
        return fecha_operacion;
    }

    public void setFecha_operacion(Date fecha_operacion) {
        this.fecha_operacion = fecha_operacion;
    }

    public Float getMonto_pago() {
        return monto_pago;
    }

    public void setMonto_pago(Float monto_pago) {
        this.monto_pago = monto_pago;
    }

    public PuntoAtencion getPunto_atencion() {
        return punto_atencion;
    }

    public void setPunto_atencion(PuntoAtencion punto_atencion) {
        this.punto_atencion = punto_atencion;
    }

    public Cuenta getCuenta_origen() {
        return cuenta_origen;
    }

    public void setCuenta_origen(Cuenta cuenta_origen) {
        this.cuenta_origen = cuenta_origen;
    }

    public Cuenta getCuenta_destino() {
        return cuenta_destino;
    }

    public void setCuenta_destino(Cuenta cuenta_destino) {
        this.cuenta_destino = cuenta_destino;
    }

    
    
}
