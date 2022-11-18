package com.utdev.chilloutserver.controller;

import com.utdev.chilloutserver.model.Articulo;
import com.utdev.chilloutserver.model.Imagen;
import com.utdev.chilloutserver.model.primaryKeys.PKImagen;
import com.utdev.chilloutserver.model.utils.NewArticuloModel;
import com.utdev.chilloutserver.model.utils.newImagen;
import com.utdev.chilloutserver.service.interfaces.IArticuloService;
import com.utdev.chilloutserver.service.interfaces.IImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin(origins = "*" , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("api/articulo")
public class ArticuloController {

    @Autowired
    private IArticuloService service;
    @Autowired
    private IImagenService imagenService;

    // http://localhost:7373/api/articulo/hello
    @GetMapping("/hello")
    public ResponseEntity<?> getHelloWorld()
    {
        return ResponseEntity.ok("Hello, world!");
    }

    // http://localhost:7373/api/articulo/full
    @PostMapping("/full")
    public ResponseEntity<?> saveFullArticulo(@RequestBody Articulo articulo){
        return new ResponseEntity<>(service.saveArticulo(articulo), HttpStatus.OK);
    }

    // http://localhost:7373/api/articulo
    @PostMapping("/")
    public ResponseEntity<?> saveArticulo(@RequestBody NewArticuloModel newArticulo){
        Articulo articulo = new Articulo();
        articulo.setCodBarras(newArticulo.getCodBarras());
        articulo.setCategoriaID(newArticulo.getCategoriaID());
        articulo.setNombre(newArticulo.getNombre());
        articulo.setDescripcion(newArticulo.getDescripcion());
        articulo.setStock(newArticulo.getStock());
        articulo.setPrecioCompra(newArticulo.getPrecioCompra());
        articulo.setUtilidad(newArticulo.getUtilidad());
        articulo.setPrecioVenta(newArticulo.getPrecioVenta());
        articulo.setIva(newArticulo.getIva());
        articulo.setDisponible(newArticulo.isDisponible());
        articulo.setVisible(newArticulo.isVisible());
        articulo.setProveedorID(newArticulo.getProveedorID());
        articulo.setProveedorID(newArticulo.getPromoID());
        articulo.setLastUpdateInventory(LocalDateTime.now());

        if(newArticulo.getImagenes().isEmpty()){
            articulo.setImg(false);
        }else{
            articulo.setImg(true);
            for (newImagen imagen:newArticulo.getImagenes()) {
                String uuid = UUID.randomUUID().toString();
                imagenService.saveImagen(new Imagen(new PKImagen(uuid, newArticulo.getCodBarras()),
                        imagen.getName(),
                        imagen.getType(),
                        imagen.getImg()));
            }
        }

        return new ResponseEntity<>(service.saveArticulo(articulo), HttpStatus.OK);
    }

    // htpp://localhost:7373/api/articulo/all
    @GetMapping("/all")
    public ResponseEntity<?> findAllArticulos(){ return new ResponseEntity<>(service.findAll(), HttpStatus.OK); }

    // htpp://localhost:7373/api/articulo/{id}
    @GetMapping("{id}")
    public ResponseEntity<?> findByID(@PathVariable String id){
        return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
    }

    // http://localhost:7373/api/articulo?nombre={nombre}&img={img}
    @GetMapping("/")
    public ResponseEntity<?> findArticulos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false, defaultValue = "true") boolean img,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        try {
            List<Articulo> articulos = new ArrayList<Articulo>();
            Pageable paging = PageRequest.of(page, size);
            Page<Articulo> pageArticulos;

            if(nombre != null)
                pageArticulos = service.findByNombre(nombre, paging);
            else
                pageArticulos = service.findWithImg(img, paging);

            articulos = pageArticulos.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("articulos", articulos);
            response.put("currentPage", pageArticulos.getNumber());
            response.put("totalItems", pageArticulos.getTotalElements());
            response.put("totalPages", pageArticulos.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:7373/api/articulo?page={page}&size={size}
    @GetMapping("/allPaging")
    public ResponseEntity<?> findAllArticulosPaging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        try {
            List<Articulo> articulos = new ArrayList<Articulo>();
            Pageable paging = PageRequest.of(page, size);
            Page<Articulo> pageArticulos;

            pageArticulos = service.findArticulos(paging);
            articulos = pageArticulos.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("articulos", articulos);
            response.put("currentPage", pageArticulos.getNumber());
            response.put("totalItems", pageArticulos.getTotalElements());
            response.put("totalPages", pageArticulos.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:7373/api/articulo/danger/{id}
    @DeleteMapping("/danger/{id}")
    public ResponseEntity<?> deleteArticulo(@PathVariable String id){
        try{
            service.deleteArticulo(id);
            return ResponseEntity.ok("Registro eliminado permanentemente");
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:7373/api/articulo/suspend/{id}
    @DeleteMapping("/suspend/{id}")
    public ResponseEntity<?> suspendArticulo(@PathVariable String id){
        if(service.suspendArticulo(id))
            return ResponseEntity.ok("articulo suspendido correctamente");
        else
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    // http://localhost:7373/api/articulo/cancel/{id}
    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<?> cancelArticulo(@PathVariable String id){
        if(service.cancelArticulo(id))
            return ResponseEntity.ok("articulo cancelado correctamente");
        else
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
