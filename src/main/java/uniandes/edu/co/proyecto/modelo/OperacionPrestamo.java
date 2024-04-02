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
@Table(name="operaciones_prestamos")
public class OperacionPrestamo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String tipo_operacion;

    private Date fecha_operacion;

    private Float monto_pago;

     @ManyToOne
    @JoinColumn(name = "id_prestamo", referencedColumnName = "id")
    private Prestamo id_prestamo;

    @ManyToOne
    @JoinColumn(name = "punto_atencion", referencedColumnName = "id")
    private PuntoAtencion punto_atencion;

    public OperacionPrestamo(String tipo_operacion, Date fecha_operacion, Float monto_pago, Prestamo id_prestamo,
            PuntoAtencion punto_atencion) {
        this.tipo_operacion = tipo_operacion;
        this.fecha_operacion = fecha_operacion;
        this.monto_pago = monto_pago;
        this.id_prestamo = id_prestamo;
        this.punto_atencion = punto_atencion;
    }

    public OperacionPrestamo(){;}

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

    public Prestamo getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(Prestamo id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public PuntoAtencion getPunto_atencion() {
        return punto_atencion;
    }

    public void setPunto_atencion(PuntoAtencion punto_atencion) {
        this.punto_atencion = punto_atencion;
    }

    

    
}
