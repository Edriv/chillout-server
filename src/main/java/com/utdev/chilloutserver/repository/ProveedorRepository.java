package com.utdev.chilloutserver.repository;

import com.utdev.chilloutserver.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, String> {

    List<Proveedor> findAllByFechaRegistro(LocalDateTime fechaRegistro);

    List<Proveedor> findAllByFechaRegistroBetween(
            LocalDateTime fechaRegistroStart,
            LocalDateTime fechaRegistroEnd);

    @Query("SELECT a FROM Proveedor a WHERE a.fechaRegistro <= :fechaRegistro")
    List<Proveedor> findAllWithFechaRegistroBefore(
            @Param("fechaRegistro") LocalDateTime fechaRegistro);

    @Query("SELECT a FROM Proveedor a WHERE a.fechaRegistro >= :fechaRegistro")
    List<Proveedor> findAllWithFechaRegistroAfter(
            @Param("fechaRegistro") LocalDateTime fechaRegistro);

    List<Proveedor> findByEmailLikeIgnoreCase(String email);

    List<Proveedor> findByNombreContactoLikeIgnoreCase(String nombreContacto);

    List<Proveedor> findByRazonSocialLikeIgnoreCase(String razonSocial);

    List<Proveedor> findByRfcLikeIgnoreCase(String rfc);

    List<Proveedor> findByActivoTrue();

    List<Proveedor> findByActivoFalse();

}
