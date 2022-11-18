package com.utdev.chilloutserver.model.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

// Equivalencias de data types obtenidas de:
/* https://tada.github.io/pljava/use/datetime.html
*  https://stackoverflow.com/questions/49134124/byte-data-in-postgresql */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewArticuloModel {

    private String codBarras;

    private int categoriaID;

    private String nombre;

    private String descripcion;

    private int stock;

    private double precioCompra;

    private double utilidad;

    private double precioVenta;

    private double iva;

    private boolean disponible;

    private boolean visible;

    private boolean img;

    private String proveedorID;

    private String promoID;

    private List<newImagen> imagenes;

}
