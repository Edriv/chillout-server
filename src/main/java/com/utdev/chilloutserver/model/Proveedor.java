package com.utdev.chilloutserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proveedor")
public class Proveedor {

    @Id
    private String id;

    @Column
    private String rfc;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "nombre_contacto")
    private String nombreContacto;

    @Column(name = "tel_principal")
    private String telefonoPrincipal;

    @Column(name = "tel_movil")
    private String telefonoMovil;

    @Column
    private String email;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "activo")
    private boolean activo;

}
