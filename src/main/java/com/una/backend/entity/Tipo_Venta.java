package com.una.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Tipo_Venta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id_tipo_venta;

    private String Descripcion;

    public Tipo_Venta() {
    }

    public int getId_tipo_venta() {
        return Id_tipo_venta;
    }

    public void setId_tipo_venta(int id_tipo_venta) {
        Id_tipo_venta = id_tipo_venta;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
