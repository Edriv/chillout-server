package com.utdev.chilloutserver.model.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Lob;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class newImagen {

    @Column
    private String name;

    @Column
    private String type;

    @Lob
    @Column
    private byte[] img;

}
