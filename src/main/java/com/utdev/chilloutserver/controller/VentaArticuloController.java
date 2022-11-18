package com.utdev.chilloutserver.controller;

import com.utdev.chilloutserver.model.VentaArticulo;
import com.utdev.chilloutserver.model.primaryKeys.PKVentaArticulos;
import com.utdev.chilloutserver.model.utils.IDArticuloCantidad;
import com.utdev.chilloutserver.service.interfaces.IVentaArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*" , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("api/ventarticulo")
public class VentaArticuloController {

    @Autowired
    private IVentaArticuloService service;

    // http://localhost:7373/api/venta/hello
    @GetMapping("/hello")
    public ResponseEntity<?> getHelloWorld() {
        return ResponseEntity.ok("Hello, world!");
    }

    // http://localhost:7373/api/ventarticulo/full
    @PostMapping("/full")
    public ResponseEntity<?> saveFullVentaArticulo(@RequestBody VentaArticulo ventaArticulo){
        return new ResponseEntity<>(service.saveVentaArticulo(ventaArticulo), HttpStatus.OK);
    }

    // http://localhost:7373/api/ventarticulo/
    @PostMapping("/")
    public ResponseEntity<?> saveVentaArticulo(@RequestBody IDArticuloCantidad ventaArticulo){
        return new ResponseEntity<>(
                service.createVentaArticulo(ventaArticulo.getIdVenta(),
                        ventaArticulo.getArticulo(),
                        ventaArticulo.getCantidad()),
                HttpStatus.OK);
    }

    // http://localhost:7373/api/ventaarticulo/all
    @GetMapping("/all")
    public ResponseEntity<?> findAllVentaArticulo(){ return new ResponseEntity<>(service.findAll(), HttpStatus.OK); }

    // http://localhost:7373/api/ventarticulo/id
    @GetMapping("/id")
    public ResponseEntity<?> findByID(@RequestBody PKVentaArticulos id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    // http://localhost:7373/api/ventarticulo/venta
    @GetMapping("{idVenta}")
    public ResponseEntity<?> findByIdVenta(@PathVariable String idVenta){
        return new ResponseEntity<>(service.findByVenta(idVenta), HttpStatus.OK);
    }

    // http://localhost:7373/api/ventarticulo?page={page}&size={size}
    @GetMapping("/")
    public ResponseEntity<?> findVentaArticuos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<VentaArticulo> pageVentas;

            pageVentas = service.findAllPaging(paging);

            Map<String, Object> response = new HashMap<>();
            response.put("ventas", pageVentas.getContent());
            response.put("currentPage", pageVentas.getNumber());
            response.put("totalItems", pageVentas.getTotalElements());
            response.put("totalPages", pageVentas.getTotalPages());

            return  new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:7373/api/ventarticulos/danger
    @DeleteMapping("/danger")
    public ResponseEntity<?> deleteByID(@RequestBody PKVentaArticulos id){
        try{
            service.deleteById(id);
            return ResponseEntity.ok("Registro eliminado permanentemente");
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
