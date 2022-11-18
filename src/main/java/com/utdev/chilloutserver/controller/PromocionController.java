package com.utdev.chilloutserver.controller;

import com.utdev.chilloutserver.model.Promocion;
import com.utdev.chilloutserver.service.interfaces.IPromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*" , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("api/promocion")
public class PromocionController {

    @Autowired
    private IPromocionService service;

    // http://localhost:7373/api/categoria/hello
    @GetMapping("/hello")
    public ResponseEntity<?> getHelloWorld()
    {
        return ResponseEntity.ok("Hello, world!");
    }

    // http://localhost:7373/api/promocion
    @GetMapping("/")
    public ResponseEntity<?> findPromociones(){
        return new ResponseEntity<>(service.findAllPromociones(), HttpStatus.OK);
    }

    // http://localhost:7373/api/promocion/{id}
    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable String id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    // http://localhost:7373/api/promocion/
    @PostMapping("/")
    public ResponseEntity<?> savePromocion(@RequestBody Promocion promocion){
        Promocion newPromo = service.savePromocion(promocion);
        if (newPromo != null)
            return new ResponseEntity<>(newPromo, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // http://localhost:7373/api/promocion/danger/{id}
    @DeleteMapping("/danger/{id}")
    public ResponseEntity<?> deletePromocion(@PathVariable String id){
        try{
            service.deleteByID(id);
            return ResponseEntity.ok("Registro eliminado permanentemente");
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
