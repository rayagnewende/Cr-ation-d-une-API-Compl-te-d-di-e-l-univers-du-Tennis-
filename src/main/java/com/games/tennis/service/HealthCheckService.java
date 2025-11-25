package com.games.tennis.service;

import com.games.tennis.rest.ApplicaticationStatus;
import com.games.tennis.rest.HealthCheck;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {

    public HealthCheck getApplicxationStatus(){
        return new HealthCheck(ApplicaticationStatus.OK, "Bienvenue dans la classe HealthCheck");
    }
}
