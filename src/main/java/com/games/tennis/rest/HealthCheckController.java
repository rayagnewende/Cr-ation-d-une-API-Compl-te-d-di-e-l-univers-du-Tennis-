package com.games.tennis.rest;

import com.games.tennis.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @Autowired
    HealthCheckService healthCheckService ;

    @GetMapping("/healthcheck")
   public  HealthCheck healthCheck(){
        return healthCheckService.getApplicxationStatus();
   }
}
