package com.utdev.chilloutserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*" , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/engine")
public class EngineController {

    @PostMapping("/start")
    public ResponseEntity<Void> startEngine(){
        //log.info("Staring BatMobile engine");
        // Start engine
        //log.info("Engine running!");
        return ResponseEntity.noContent().build();
    }

}
