package com.utdev.chilloutserver.model.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloCantidad {

    private String articulo;
    private int cantidad;

}
