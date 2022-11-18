package com.utdev.chilloutserver.controller;

import com.utdev.chilloutserver.model.Imagen;
import com.utdev.chilloutserver.service.interfaces.IImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*" , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("api/imagen")
public class ImagenController {

    @Autowired
    private IImagenService service;

    // http://localhost:7373/api/imagen/hello
    @GetMapping("/hello")
    public ResponseEntity<?> getHelloWorld()
    {
        return ResponseEntity.ok("Hello, world!");
    }

    // http://localhost:7373/api/imagen/{codBarras}
    @GetMapping("{codBarras}")
    public ResponseEntity<?> getImagesByCodBarras(@PathVariable String codBarras){
        return new ResponseEntity<>(service.findImgByCodBarras(codBarras), HttpStatus.OK);
    }

    // http://localhost:7373/api/imagen
    @PostMapping("/")
    public ResponseEntity<?> saveImg(@RequestBody Imagen img){
        return new ResponseEntity<>(service.saveImagen(img),HttpStatus.OK);
    }

    // htpp://localhost:7373/api/imagen/danger/{uuid}
    @DeleteMapping("/danger/{uuid}")
    public ResponseEntity<?> deleteImg(@PathVariable String uuid){
        List<Imagen> imgList = service.findImgByUUID(uuid);
        if(!imgList.isEmpty()){
            for (Imagen imgen : imgList) {
                service.deleteById(imgen.getImagenPK());
            }
            return ResponseEntity.ok("Registro eliminado permanentemente");
        }else{
            return new ResponseEntity<>("Error al procesar la informacion", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
