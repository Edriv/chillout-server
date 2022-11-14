package com.utdev.chilloutserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    private int id;

    @Column
    private String nombre;

    @Lob
    @Column
    private byte[] img;

    // Custom constructor
    public Categoria(String nombre, byte[] img){
        this.nombre = nombre;
        this.img = img;
    }

}
