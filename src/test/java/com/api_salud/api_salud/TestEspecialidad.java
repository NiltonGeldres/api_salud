package com.api_salud.api_salud;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;


import com.api_salud.api_salud.entity.EspecialidadEntity;
import com.api_salud.api_salud.repository.EspecialidadDao;
import com.api_salud.api_salud.repository.EspecialidadRepo;
import com.api_salud.api_salud.response.CitaResponse;
import com.api_salud.api_salud.response.EspecialidadResponse;
import com.api_salud.api_salud.service.EspecialidadService;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
public class TestEspecialidad {

	@Autowired
//	EspecialidadService especialidadService ;
	EspecialidadDao especialidadDao ;
	
//	@Autowired
//	EspecialidadRepo  especialidadRepo ;
	
	
	@Test
	void contextLoads() {
		EspecialidadResponse	out = especialidadDao.web();
		
        List<EspecialidadEntity> esp = out.getEspecialidad();
        Iterator<EspecialidadEntity> espIterator = esp.iterator();	    
        while (espIterator.hasNext()) {
            // debe ser llamado antes de productosIterator.remove();
        	EspecialidadEntity especialidad = espIterator.next();
            // una validación simple:
            if (especialidad.getIdEspecialidad() == 5) {
                espIterator.remove();
            }
        }
	    
/*
		for(EspecialidadEntity esp: out.getEspecialidad()) {
		        if ( esp.getDescripcionEspecialidad().equals("GERIATRIA") ) {
		        	System.out.println("id  " +esp.getIdEspecialidad());
//		        	System.out.println("Eliminado Registrada " +citaProgramada.getHoraInicio());
		        	out.getEspecialidad().remove(esp);
		        	
	        } else {System.out.println("id  " +esp.getDescripcionEspecialidad());}

	 }
*/				 
		
		
 	   for (EspecialidadEntity row : out.getEspecialidad()) {
  		  System.out.println("Id "+ row.getIdEspecialidad()+"   Decripcion "+ row.getDescripcionEspecialidad());
 	   }
		
/*		
		EspecialidadResponse response = null;
		
  	    List<EspecialidadEntity> res =new ArrayList<>();
		Map<String, Object>	out = especialidadDao.webMap();
		
  		System.out.println("****DESPUES DE EXECUTE");
    	  if (out == null) {
	       	   System.out.println("****VACIO");
	      } else {
	            List<Map<String, EspecialidadEntity>> result = (List<Map<String, EspecialidadEntity>>) out.get("cur");
                List<Object>  list = (List<Object>) out.get("cur") ;
           	   System.out.println("list   "+list);
            	   EspecialidadEntity especialidad;
            	   for (Object row : result) {
            	   	  ObjectMapper objectMapper = new ObjectMapper() ;
            		  especialidad = objectMapper.convertValue(row, EspecialidadEntity.class) ;
            	      res.add(especialidad);
            	   }
             	  
            	   for (EspecialidadEntity row : res) {
             		  System.out.println("Id "+ row.getIdEspecialidad()+"   Decripcion "+ row.getDescripcionEspecialidad());
            	   }
         	}
                
 */
                
	      } 		
    	  
		
	
}
    	  
		
		
		
		
//		EspecialidadResponse out = especialidadDao.webMap();
		
//		System.out.println("Retorno1 test Especialidad");
/*
		List<EspecialidadEntity> out = especialidadRepo.web();
		System.out.println("Retorno1 "+out.toString());
		for (EspecialidadEntity element : out) {
			System.out.println("Elemento Especialidad Entity :  " +element.getDescripcionEspecialidad());
		}		
*/
		/*
		System.out.println("Retorno1 "+out.toString());
 		for (Entry<String, Object> entry : out.entrySet()) {
 		    System.out.println("Lista 1 "+entry.getKey() + " : " + entry.getValue().toString());
            System.out.println("Lista 2 "+entry.getValue());
		
 		}	
*/		
		
//		for (EspecialidadEntity element : retorno1) {
//			System.out.println("Elemento Especialidad Entity :  " +element.getDescripcionEspecialidad());
//		}		
		
/*		
		List<EspecialidadEntity> retorno1 = especialidadRepo.web();
		System.out.println("Retorno1 "+retorno1.toString());
		for (EspecialidadEntity element : retorno1) {
			System.out.println("Elemento Especialidad Entity :  " +element.getDescripcionEspecialidad());
		}		
*/
		
/*		
		EspecialidadResponse retorno1 = especialidadService.web();
		System.out.println("Retorno1 "+retorno1.toString());
		for (EspecialidadEntity element : retorno1.getEspecialidad()) {
			System.out.println("Elemento Especialidad Entity :  " +element.getDescripcionEspecialidad());
		}		
*/


/*
List list = new ArrayList();
//List<EspecialidadEntity> listEspecialidad = new ArrayList<>(); 
Object x = null;
	for (Entry<String, Object> entry : out.entrySet()) {
	    System.out.println("Lista 1 "+entry.getKey() + " : " + entry.getValue().toString());
      System.out.println("Lista 2 "+entry.getValue());
       x = entry.getValue();
      System.out.println(x);

	}
*/	     		
//            String username = out.get("cur");

  //Get DB Settings

//  List<Map<String, Object>> result = (List<Map<String, Object>>) out.get("cur");



/*
 
                 for (Map<String, EspecialidadEntity> map : result) {
                    for (Map.Entry<String, EspecialidadEntity> entry : map.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        System.out.println(" "+key + " "+value);
                        System.out.println("----------- ");
                    }
                }                

                for (Map<String, EspecialidadEntity> map : result) {
                    for (Map.Entry<String, EspecialidadEntity> entry : map.entrySet()) {
                        System.out.println(entry.getKey() + " - " + entry.getValue());
                    }
                    System.out.println("-----------------------------");
                }                
                

 * 
 * */



/*
System.out.println("result  "+ result);
Map<String, EspecialidadEntity > source;
source= result.get(0);	            
System.out.println(source);
source= result.get(1);	            
System.out.println(source);
source= result.get(2);	            
System.out.println(source);
source= result.get(3);	            
System.out.println(source);
source= result.get(4);	            
System.out.println(source);
*/