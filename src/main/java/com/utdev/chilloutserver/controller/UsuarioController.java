package com.utdev.chilloutserver.controller;

import com.utdev.chilloutserver.model.Usuario;
import com.utdev.chilloutserver.model.utils.LoginModel;
import com.utdev.chilloutserver.model.utils.sendToken;
import com.utdev.chilloutserver.service.interfaces.IUsuarioService;
import com.utdev.chilloutserver.service.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
//@CrossOrigin(origins = "*" , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("api/user")
public class UsuarioController {

    @Autowired
    private IUsuarioService service;
    @Autowired
    private JWTService jwtService;

    // http://localhost:7373/api/user/hello
    @GetMapping("/hello")
    public ResponseEntity<?> getHelloWorld()
    {
        return ResponseEntity.ok("Hello, world!");
    }

    // http://localhost:7373/api/user
    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody Usuario user){
        return new ResponseEntity<>(service.saveUser(user), HttpStatus.OK);
    }

    // http://localhos:7373/api/user/login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginModel login){
        Usuario user = service.loginUser(login);
        if(user != null){
            return new ResponseEntity<>(new sendToken(jwtService.getToken(user)), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No deberías estar aquí", HttpStatus.UNAUTHORIZED);
        }
    }

    // http://localhos:7373/api/user/
    @GetMapping
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
