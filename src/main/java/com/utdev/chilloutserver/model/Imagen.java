package com.utdev.chilloutserver.model;

import com.utdev.chilloutserver.model.primaryKeys.PKImagen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "imagen")
public class Imagen {

    @EmbeddedId
    private PKImagen imagenPK;

    @Column
    private String name;

    @Column
    private String type;

    @Lob
    @Column
    private byte[] img;

}
