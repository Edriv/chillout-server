package com.utdev.chilloutserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "promocion")
public class Promocion {

    @Id
    private String id;

    @Column
    private String titulo;

    @Column
    private String subtitulo;

    @Column(name = "fecha_ini")
    private LocalDateTime fechaIni;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @Column
    private String descripcion;

    @Column(name = "prc_desc")
    private int descuento;

    @Column
    private boolean disponible;

}
