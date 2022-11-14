package com.utdev.chilloutserver.service.interfaces;

import com.utdev.chilloutserver.model.Proveedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface IProveedorService {

    // Save full Proveedor
    Proveedor saveProveedor(Proveedor proveedor);

    // Save partial Proveedor
    Proveedor createProveedor(Proveedor proveedor);

    // Get all Proveedores
    List<Proveedor> findAllProveedores();

    // Get Proveedores with paging
    Page<Proveedor> findProveedores(Pageable pageable);

    // Get Proveedor by ID
    Proveedor findById(String id);

    // Get Proveedores by fechaRegistro
    List<Proveedor> findByFechaRegistro(LocalDateTime fechaRegistro);

    // Get Proveedores by fechaRegistro between range
    List<Proveedor> findByFechaRegistro(
            LocalDateTime fechaRegistroStart,
            LocalDateTime fechaRegistroEnd);

    // Get Proveedores by fechaRegistro before date
    List<Proveedor> findByFechaRegistroBefore(LocalDateTime fechaRegistro);

    // Get Proveedores by fechaRegistro after date
    List<Proveedor> findByFechaRegistroAfter(LocalDateTime fechaRegistro);

    // Get Proveedores by email
    List<Proveedor> findByEmail(String email);

    // Get Proveedores by nombreContacto
    List<Proveedor> findByNombreContacto(String nombreContacto);

    // Get Proveedores by razonSocial
    List<Proveedor> findByRazonSocial(String razonSocial);

    // Get Proveedores by RFC
    List<Proveedor> findByRfc(String rfc);

    // Get Proveedores by Activo Field
    List<Proveedor> findByActivo(boolean activo);

    // Delete Proveedor by ID
    void deleteById(String id);

    // Change activo from Proveedor
    Proveedor inactiveById(String id);
}
