package com.api_salud.api_salud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_salud.api_salud.entity.TurnoEntity;
import com.api_salud.api_salud.response.TurnoResponse;
import com.api_salud.api_salud.service.TurnoService;

@RestController
@RequestMapping("")
public class TurnoController {

	@Autowired
	TurnoService turnoService ;
	
    @PostMapping("/turnos")
   public ResponseEntity<?> turnos(){
        List<TurnoEntity> response = turnoService.turnos();
        return new ResponseEntity(response, HttpStatus.OK);
    }
	

}
