package com.utdev.chilloutserver.controller;

import com.utdev.chilloutserver.model.Venta;
import com.utdev.chilloutserver.model.VentaArticulo;
import com.utdev.chilloutserver.model.utils.ArticuloCantidad;
import com.utdev.chilloutserver.service.interfaces.IVentaArticuloService;
import com.utdev.chilloutserver.service.interfaces.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
//@CrossOrigin(origins = "*" , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("api/venta")
public class VentaController {

    @Autowired
    private IVentaService service;
    @Autowired
    private IVentaArticuloService vaService;

    @Value("${user.id}")
    public String userID;

    // http://localhost:7373/api/venta/hello
    @GetMapping("/hello")
    public ResponseEntity<?> getHelloWorld() {
        return ResponseEntity.ok("Hello, world!");
    }

    // http://localhost:7373/api/venta/full
    @PostMapping("/full")
    public ResponseEntity<?> saveVenta(@RequestBody Venta venta) {
        return new ResponseEntity<>(service.saveVenta(venta), HttpStatus.OK);
    }

    // http://localhost:7373/api/venta/
    @PostMapping("/")
    public ResponseEntity<?> saveVenta(@RequestBody List<ArticuloCantidad> onlineCart) {
        try {
            List<VentaArticulo> ventaArticulosList = new ArrayList<VentaArticulo>();

            String idVenta = UUID.randomUUID().toString();
            int i = 0;
            double total = 0.0;

            for(ArticuloCantidad it: onlineCart){
                System.out.println(it);
                VentaArticulo va = vaService.createVentaArticulo(idVenta,it.getArticulo(),it.getCantidad());

                if(va != null){
                    ventaArticulosList.add(va);
                    total += va.getPrecioVenta()* it.getCantidad();
                    i++;
                }
            }

            Venta venta = service.saveVenta(idVenta, userID, total, i);
            if (venta != null) {
                if(venta.getNumRegistros() != ventaArticulosList.size())
                    return new ResponseEntity<>("No se agregaron todas las ventas-articulo",HttpStatus.OK);

                venta.setUpload(true);
                if(service.saveVenta(venta) != null)
                    return new ResponseEntity<>(HttpStatus.OK);

                return new ResponseEntity<>("No se pudo dar de alta la venta",HttpStatus.NOT_MODIFIED);
            } else {
                return new ResponseEntity<>("No se pudo dar de alta la venta",HttpStatus.NOT_MODIFIED);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:7373/api/venta/all
    @GetMapping("/all")
    public ResponseEntity<?> findAllVentas() {
        return new ResponseEntity<>(service.findAllVentas(), HttpStatus.OK);
    }

    // http://localhost:7373/api/venta/{id}
    @GetMapping("{id}")
    public ResponseEntity<?> findByID(@PathVariable String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    // http://localhost:7373/api/venta?fecha={fecha}&folio={folio}&page={page}&size={size}
    @GetMapping("/")
    public ResponseEntity<?> findVentaQuery(
            @RequestParam(required = false) String fecha,
            @RequestParam(required = false) String folio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Venta> pageVentas;

            pageVentas = service.findVentas(paging);

            if (fecha != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                LocalDateTime fechaQuery = LocalDateTime.parse(fecha, formatter);
                pageVentas = service.findByFecha(fechaQuery, paging);
            }

            if (folio != null){
                pageVentas = service.findByFolio(Long.parseLong(folio), paging);
            }

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

    // http://localhost:7373/api/venta/danger/{id}
    @DeleteMapping("/danger/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable String id){
        try{
            service.deleteVenta(id);
            return ResponseEntity.ok("Registro eliminado permanentemente");
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
