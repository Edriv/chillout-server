package com.utdev.chilloutserver.controller;

import com.utdev.chilloutserver.model.Proveedor;
import com.utdev.chilloutserver.service.interfaces.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*" , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("api/proveedor")
public class ProveedorController {

    @Autowired
    private IProveedorService service;

    // http://localhost:7373/api/proveedor/hello
    @GetMapping("/hello")
    public ResponseEntity<?> getHelloWorld()
    {
        return ResponseEntity.ok("Hello, world!");
    }

    // http://localhost:7373/api/proveedor              // ROOT
    @PostMapping("/")
    public ResponseEntity<?> saveProveedor(@RequestBody Proveedor proveedor){
        Proveedor newProveedor = service.createProveedor(proveedor);
        if (newProveedor != null)
            return new ResponseEntity<>(newProveedor, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // http://localhost:7373/api/proveedor/saveFull
    @PostMapping("/saveFull")
    public ResponseEntity<?> saveFullProveedor(@RequestBody Proveedor proveedor) {
        Proveedor newProveedor = service.saveProveedor(proveedor);
        if (newProveedor != null)
            return new ResponseEntity<>(newProveedor, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // http://localhost:7373/api/proveedor              // ROOT
    // http://localhost:7373/api/proveedor/?page=0&size=10
    @GetMapping("/")
    public ResponseEntity<?> findProveedores(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
    {
        try {
            Pageable pageable = PageRequest.of(page, size);
            return buildPage(service.findProveedores(pageable));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:7373/api/proveedor/all
    @GetMapping("/all")
    public ResponseEntity<?> findAllProveedores(){
        return new ResponseEntity<>(service.findAllProveedores(), HttpStatus.OK);
    }

    // http://localhost:7373/api/proveedor/{id}
    @GetMapping("{id}")
    public ResponseEntity<?> findProveedorByID(@PathVariable String id){
        Proveedor proveedor = service.findById(id);
        if (proveedor != null)
            return new ResponseEntity<>(proveedor, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // http://localhost:7373/api/proveedor/findFecha/{fecha}
    @GetMapping("/findFecha/{fecha}")
    public ResponseEntity<?> findProveedorByFechaRegistro(@PathVariable String fecha){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            LocalDateTime fechaRegistro = LocalDateTime.parse(fecha, formatter);

            return  new ResponseEntity<>(service.findByFechaRegistro(fechaRegistro), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // htpp://localhost:7373/api/proveedor/findFecha/{fechaIni}/{fechaFin}
    @GetMapping("/findFecha/{fechaIni}/{fechaFin}")
    public  ResponseEntity<?> findProveedorByFechaRegistroRange(
            @PathVariable String fechaIni,
            @PathVariable String fechaFin)
    {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            LocalDateTime fechaRegistroIni = LocalDateTime.parse(fechaIni, formatter);
            LocalDateTime fechaRegistroFin = LocalDateTime.parse(fechaFin, formatter);

            return  new ResponseEntity<>(service.findByFechaRegistro(fechaRegistroIni, fechaRegistroFin), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:7373/api/proveedor/findBefore/{fecha}
    @GetMapping("/findBefore/{fecha}")
    public ResponseEntity<?> findByFechaRegistroBefore(@PathVariable String fecha){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            LocalDateTime fechaRegistro = LocalDateTime.parse(fecha, formatter);

            return  new ResponseEntity<>(service.findByFechaRegistroBefore(fechaRegistro), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:7373/api/proveedor/findAfter/{fecha}
    @GetMapping("/findAfter/{fecha}")
    public ResponseEntity<?> findByFechaRegistroAfter(@PathVariable String fecha){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime fechaRegistro = LocalDateTime.parse(fecha, formatter);

            return  new ResponseEntity<>(service.findByFechaRegistroAfter(fechaRegistro), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:7373/api/proveedor/findEmail/{email}
    @GetMapping("/findEmail/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email){
        try{
            return new ResponseEntity<>(service.findByEmail(email), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:7373/api/proveedor/findNombre/{nombre}
    @GetMapping("/findNombre/{nombre}")
    public ResponseEntity<?> findByNombreContacto(@PathVariable String nombre){
        try{
            return new ResponseEntity<>(service.findByNombreContacto(nombre), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:7373/api/proveedor/findRazonSocial/{rs}
    @GetMapping("/findRazonSocial/{rs}")
    public ResponseEntity<?> findByRazonSocial(@PathVariable String rs){
        try{
            return new ResponseEntity<>(service.findByRazonSocial(rs), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:7373/api/proveedor/findRFC/{rfc}
    @GetMapping("/findRFC/{rfc}")
    public ResponseEntity<?> findByRfc(@PathVariable String rfc){
        try{
            return new ResponseEntity<>(service.findByRfc(rfc), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:7373/api/proveedor/findActivo/{activo}
    @GetMapping("/findActivo/{activo}")
    public ResponseEntity<?> findByRfc(@PathVariable boolean activo){
        try{
            return new ResponseEntity<>(service.findByActivo(activo), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:7373/api/proveedor/{id}
    @DeleteMapping("{id}")
    public ResponseEntity<?> inactiveById(@PathVariable String id){
        try{
            return new ResponseEntity<>(service.inactiveById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:7373/api/proveedor/danger/{id}
    @DeleteMapping("/danger/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        try{
            service.deleteById(id);
            return ResponseEntity.ok("Registro eliminado permanentemente");
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<?> buildPage(Page<Proveedor> proveedorPage){
        Map<String, Object> response = new HashMap<>();
        response.put("proveedores", proveedorPage.getContent());
        response.put("currentPage", proveedorPage.getNumber());
        response.put("totalItems", proveedorPage.getTotalElements());
        response.put("totalPages", proveedorPage.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
