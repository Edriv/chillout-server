package com.utdev.chilloutserver.controller;

import com.utdev.chilloutserver.model.Usuario;
import com.utdev.chilloutserver.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*" , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("api/user")
public class UsuarioController {

    @Autowired
    private IUsuarioService service;

    // http://localhost:7373/api/user/hello
    @GetMapping("/hello")
    public ResponseEntity<?> getHelloWorld()
    {
        return ResponseEntity.ok("Hello, world!");
    }

    // http://localhost:7373/api/user
    @PostMapping("/")
    public ResponseEntity<?> saveUser(@RequestBody Usuario user){
        return new ResponseEntity<>(service.saveUser(user), HttpStatus.OK);
    }

    // http://localhos:7373/api/user/
    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    // http://localhost:7373/api/user/{id}
    @GetMapping("{id}")
    public ResponseEntity<?> getUserByID(@PathVariable String id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    // http://localhost:7373/api/user/find?usrname={username}
    @GetMapping("/find")
    public ResponseEntity<?> getUserByUsername(@RequestParam String usrname){
        return new ResponseEntity<>(service.findByUsername(usrname),HttpStatus.OK);
    }

    // http://localhost:7373/api/user/{id}
    @DeleteMapping("{id}")
    public ResponseEntity<?> inactiveUser(@PathVariable String id){
        if(service.inactiveUserById(id))
            return ResponseEntity.ok("Usuario desactivado correctamente");
        else
            return new ResponseEntity<>("Peticion no procesada", HttpStatus.BAD_REQUEST);
    }

    // http://localhost:7373/api/user/danger/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        service.deleteUser(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }

}
