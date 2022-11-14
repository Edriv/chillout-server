package com.utdev.chilloutserver.model;

import com.utdev.chilloutserver.model.primaryKeys.PKVentaArticulos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "venta_articulo")
public class VentaArticulo {

    @Id private PKVentaArticulos ventaArticulosPK;

    @Column
    private int cantidad;

    @Column(name = "articulo_ofertado")
    private boolean isOfertado;

    @Column(name = "precio_regular")
    private double precioRegular;

    @Column private double iva;

    @Column(name = "precio_venta")
    private double precioVenta;

    @Column(name = "id_promo")
    private String promoID;


}
