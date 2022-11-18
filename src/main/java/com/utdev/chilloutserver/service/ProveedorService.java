package com.utdev.chilloutserver.service;

import com.utdev.chilloutserver.model.Proveedor;
import com.utdev.chilloutserver.repository.ProveedorRepository;
import com.utdev.chilloutserver.service.interfaces.IProveedorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProveedorService implements IProveedorService {

    private final ProveedorRepository repository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.repository = proveedorRepository;
    }

    //region Zona de CREATE

    // Save full Proveedor
    @Override
    public Proveedor saveProveedor(Proveedor proveedor){ return repository.save(proveedor); }

    // Save partial Proveedor
    @Override
    public Proveedor createProveedor(Proveedor proveedor){
        proveedor.setFechaRegistro(LocalDateTime.now());
        return repository.save(proveedor);
    }

    //endregion

    //region Zona de READ

    // Get all Proveedores
    @Override
    public List<Proveedor> findAllProveedores(){ return repository.findAll(); }

    // Get Proveedores with paging
    @Override
    public Page<Proveedor> findProveedores(Pageable pageable){
        return repository.findAll(pageable);
    }

    // Get Proveedor by ID
    @Override
    public Proveedor findById(String id){
        return repository.findById(id).orElse(null);
    }

    // Get Proveedores by fechaRegistro
    @Override
    public List<Proveedor> findByFechaRegistro(LocalDateTime fechaRegistro){
        return repository.findAllByFechaRegistro(fechaRegistro);
    }

    // Get Proveedores by fechaRegistro between range
    @Override
    public List<Proveedor> findByFechaRegistro(
            LocalDateTime fechaRegistroStart,
            LocalDateTime fechaRegistroEnd){
        return repository.findAllByFechaRegistroBetween(fechaRegistroStart, fechaRegistroEnd);
    }

    // Get Proveedores by fechaRegistro before date
    @Override
    public List<Proveedor> findByFechaRegistroBefore(LocalDateTime fechaRegistro){
        return repository.findAllWithFechaRegistroBefore(fechaRegistro);
    }

    // Get Proveedores by fechaRegistro after date
    @Override
    public List<Proveedor> findByFechaRegistroAfter(LocalDateTime fechaRegistro){
        return repository.findAllWithFechaRegistroAfter(fechaRegistro);
    }

    // Get Proveedores by email
    @Override
    public List<Proveedor> findByEmail(String email){
        return repository.findByEmailLikeIgnoreCase("%"+email+"%");
    }

    // Get Proveedores by nombreContacto
    @Override
    public List<Proveedor> findByNombreContacto(String nombreContacto){
        return repository.findByNombreContactoLikeIgnoreCase("%"+nombreContacto+"%");
    }

    // Get Proveedores by razonSocial
    @Override
    public List<Proveedor> findByRazonSocial(String razonSocial){
        return repository.findByRazonSocialLikeIgnoreCase("%"+razonSocial+"%");
    }

    // Get Proveedores by RFC
    @Override
    public List<Proveedor> findByRfc(String rfc){
        return repository.findByRfcLikeIgnoreCase("%"+rfc+"%");
    }

    // Get Proveedores by Activo Field
    @Override
    public List<Proveedor> findByActivo(boolean activo){
        return (activo)?repository.findByActivoTrue():repository.findByActivoFalse();
    }

    //endregion

    //region Zona de DELETE

    // Delete Proveedor by ID
    @Override
    public void deleteById(String id){
        repository.deleteById(id);
    }

    // Change activo from Proveedor
    @Override
    public Proveedor inactiveById(String id){
        Proveedor proveedor = repository.findById(id).orElse(null);
        if(proveedor != null)
            proveedor.setActivo(false);
        return proveedor;
    }

    //endregion

}
