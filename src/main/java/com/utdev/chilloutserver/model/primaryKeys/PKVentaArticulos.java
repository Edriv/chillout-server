package com.utdev.chilloutserver.model.primaryKeys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PKVentaArticulos implements Serializable {

    private String id_venta;
    private String cod_barras;

}
