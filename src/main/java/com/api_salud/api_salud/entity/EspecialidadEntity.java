package com.api_salud.api_salud.entity;
/*
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

*/

/*
@NamedStoredProcedureQuery(name = "especialidad.web",  procedureName = "igm_maestros.especialidades_web_todos_leer", parameters = {
//    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "cur", type = OTHER.class)}
	@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "cur", type = Void.class)}

)
*/
/*
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
	      name =  "Web",
	      procedureName = "igm_maestros.especialidades_web_todos_leer",
	      resultClasses = EspecialidadEntity.class,
	      parameters = { @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)}
	      
//   		  parameters = { @StoredProcedureParameter(mode = ParameterMode.OUT,  type = void.class)}	      
	)
})


@Entity
@Table(name = "especialidades", schema ="igm_maestros")
*/
public class EspecialidadEntity {
	
//	  public static final String Web = "web";
	  
//		@Id
//		@GeneratedValue(strategy=GenerationType.IDENTITY)
	   int idEspecialidad ;
       String descripcionEspecialidad;
	public int getIdEspecialidad() {
		return idEspecialidad;
	}
	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	public String getDescripcionEspecialidad() {
		return descripcionEspecialidad;
	}
	public void setDescripcionEspecialidad(String descripcionEspecialidad) {
		this.descripcionEspecialidad = descripcionEspecialidad;
	}

  
	    
}
