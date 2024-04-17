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
@Table(name="operaciones_cuentas")
public class OperacionCuenta {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String tipo_operacion;

    private Date fecha_operacion;

    private Float monto_pago;

    @ManyToOne
    @JoinColumn(name = "punto_atencion", referencedColumnName = "id")
    private PuntoAtencion punto_atencion;

    @ManyToOne
    @JoinColumn(name = "id_cuenta", referencedColumnName = "id")
    private Cuenta id_cuenta;

    public OperacionCuenta(String tipo_operacion, Date fecha_operacion, Float monto_pago, PuntoAtencion punto_atencion,
            Cuenta id_cuenta) {
        this.tipo_operacion = tipo_operacion;
        this.fecha_operacion = fecha_operacion;
        this.monto_pago = monto_pago;
        this.punto_atencion = punto_atencion;
        this.id_cuenta = id_cuenta;
    }

    public OperacionCuenta(){;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo_operacion() {
        return tipo_operacion;
    }

    public void setTipo_operacion(String tipo_operacion) {
        this.tipo_operacion = tipo_operacion;
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

    public Cuenta getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(Cuenta id_cuenta) {
        this.id_cuenta = id_cuenta;
    }
    
    
}
