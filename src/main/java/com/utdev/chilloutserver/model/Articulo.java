package com.utdev.chilloutserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

// Equivalencias de data types obtenidas de:
/* https://tada.github.io/pljava/use/datetime.html
*  https://stackoverflow.com/questions/49134124/byte-data-in-postgresql */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "articulo")
public class Articulo {

    @Id
    @Column(name = "cod_barras")
    private String codBarras;

    @Column(name = "id_categoria")
    private int categoriaID;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private int stock;

    @Column(name = "precio_compra")
    private double precioCompra;

    @Column
    private double utilidad;

    @Column(name = "precio_venta")
    private double precioVenta;

    @Column
    private double iva;

    @Column
    private boolean disponible;

    @Column
    private boolean visible;

    @Column(name = "last_update_inventory")
    private LocalDateTime lastUpdateInventory;

    @Column
    private boolean img;

    @Column(name = "id_proveedor")
    private String proveedorID;

    @Column(name = "id_promo")
    private String promoID;

}
