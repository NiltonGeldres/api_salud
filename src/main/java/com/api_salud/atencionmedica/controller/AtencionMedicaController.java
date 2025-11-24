package com.api_salud.atencionmedica.controller;
import com.api_salud.atencionmedica.request.AtencionMedicaRequestDTO;
//Importa el Modelo de Dominio con el sufijo 'Model'
import com.api_salud.atencionmedica.domain.AtencionMedicaModel; 
import com.api_salud.atencionmedica.mapper.AtencionMedicaMapper;
import com.api_salud.atencionmedica.service.AtencionMedicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/atenciones") 
@RequiredArgsConstructor 
public class AtencionMedicaController {

 private final AtencionMedicaService atencionMedicaService = null;
 private final AtencionMedicaMapper mapper = null;

 /**
  * Endpoint para crear una nueva atención médica.
  * Recibe el DTO y lo convierte al Modelo de Dominio antes de pasarlo al servicio.
  */
 @PostMapping
 public ResponseEntity<Long> crearAtencionMedica(@RequestBody AtencionMedicaRequestDTO requestDTO) {
     
     // 1. Mapear DTO (transporte) a Model (negocio)
     AtencionMedicaModel atencionModel = mapper.toModel(requestDTO); 

     // 2. Ejecutar la lógica de negocio en la capa de servicio
     Long idAtencion = atencionMedicaService.crearAtencionMedicaCompleta(atencionModel);
     
     return new ResponseEntity<>(idAtencion, HttpStatus.CREATED);
 }
}
