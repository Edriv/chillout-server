package com.utdev.chilloutserver.controller;

import com.utdev.chilloutserver.service.interfaces.IAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
//@CrossOrigin(origins = "*" , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("api/analytics")
public class AnalyticsController {

    @Autowired
    private IAnalyticsService service;

    // http://localhost:7373/api/analytics/hello
    @GetMapping("/hello")
    public ResponseEntity<?> getHelloWorld()
    {
        return ResponseEntity.ok("Hello, world!");
    }

    // http://localhost:7373/api/analytics/toptensales
    @GetMapping("/toptensales")
    public ResponseEntity<?> getTopTenSales()
    {
        return new ResponseEntity<>(service.findTopTenArtSales(), HttpStatus.OK);
    }

}
