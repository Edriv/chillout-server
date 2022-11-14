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
@Table(name = "venta")
public class Venta {

    @Id private String id;
    
    @Column(name = "id_usuario")
    private String usuarioID;

    @Column  private long folio;

    @Column(name = "fecha_venta")
    private LocalDateTime fechaVenta;

    @Column(name = "total_vendido")
    private double total;

    @Column  private boolean upload;

    @Column(name = "num_registros")
    private int numRegistros;

}
