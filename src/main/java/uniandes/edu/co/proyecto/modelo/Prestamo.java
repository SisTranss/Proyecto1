package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String proposito;

    private String estado;

    private Float monto;

    private Float tasa;

    private Integer cuotas;

    private Integer dia_pago;

    private Float valor_cuota;

    @ManyToOne
    @JoinColumn(name = "num_doc_cliente", referencedColumnName = "num_id")
    private Cliente num_doc_cliente;

    public Prestamo(Integer id, String proposito, String estado, Float monto, Float tasa, Integer cuotas,
            Integer dia_pago, Float valor_cuota, Cliente num_doc_cliente) {
        this.id = id;
        this.proposito = proposito;
        this.estado = estado;
        this.monto = monto;
        this.tasa = tasa;
        this.cuotas = cuotas;
        this.dia_pago = dia_pago;
        this.valor_cuota = valor_cuota;
        this.num_doc_cliente = num_doc_cliente;
    }

    public Prestamo(){
        ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public Float getTasa() {
        return tasa;
    }

    public void setTasa(Float tasa) {
        this.tasa = tasa;
    }

    public Integer getCuotas() {
        return cuotas;
    }

    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }

    public Integer getDia_pago() {
        return dia_pago;
    }

    public void setDia_pago(Integer dia_pago) {
        this.dia_pago = dia_pago;
    }

    public Float getValor_cuota() {
        return valor_cuota;
    }

    public void setValor_cuota(Float valor_cuota) {
        this.valor_cuota = valor_cuota;
    }

    public Cliente getNum_doc_cliente() {
        return num_doc_cliente;
    }

    public void setNum_doc_cliente(Cliente num_doc_cliente) {
        this.num_doc_cliente = num_doc_cliente;
    }

    
    
}
