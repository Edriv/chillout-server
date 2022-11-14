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
public class PKImagen implements Serializable {

    private String uuid;
    private String cod_barras;

}
