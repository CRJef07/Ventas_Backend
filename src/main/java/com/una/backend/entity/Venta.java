package com.una.backend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Venta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id_venta;

    @OneToOne
    @JoinColumn(name = "Tipo_venta", referencedColumnName = "Id_tipo_venta")
    private Tipo_Venta tipo_venta;

    @OneToOne
    @JoinColumn(name = "Persona", referencedColumnName = "Id_persona")
    private Persona persona;

    @OneToOne
    @JoinColumn(name = "Producto", referencedColumnName = "Id_producto")
    private Producto producto;

    private int Cantidad;

    private Date Fecha;

    public Venta() {
    }

    public int getId_venta() {
        return Id_venta;
    }

    public void setId_venta(int id_venta) {
        Id_venta = id_venta;
    }

    public Tipo_Venta getTipo_venta() {
        return tipo_venta;
    }

    public void setTipo_venta(Tipo_Venta tipo_venta) {
        this.tipo_venta = tipo_venta;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }
}
