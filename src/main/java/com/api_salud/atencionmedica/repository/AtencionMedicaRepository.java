package com.api_salud.atencionmedica.repository;
import com.api_salud.atencionmedica.entity.AtencionMedicaEntity;
//Asumiendo que todas las clases de detalle de Entidad se importan desde la capa .entity
import com.api_salud.atencionmedica.entity.AtencionMedicaAntecedenteEntity;
import com.api_salud.atencionmedica.entity.AtencionMedicaDiagnosticoEntity;
import com.api_salud.atencionmedica.entity.AtencionMedicaDiscapacidadEntity;
import com.api_salud.atencionmedica.entity.AtencionMedicaDiscapacidadOtrosEntity;
import com.api_salud.atencionmedica.entity.AtencionMedicaExamenFisicoEntity;
import com.api_salud.atencionmedica.entity.AtencionMedicaOrdenMedicaEntity;
import com.api_salud.atencionmedica.entity.AtencionMedicaMedicacionEntity;
import com.api_salud.atencionmedica.entity.AtencionMedicaSintomaEntity;
import com.api_salud.atencionmedica.entity.AtencionMedicaTratamientoEntity;

import java.util.List;
import java.util.Optional;

/**
* REPOSITORIO DE PERSISTENCIA: Interfaz que define las operaciones de acceso a datos
* para la Entidad Maestra AtencionMedicaEntity y todas sus Entidades de Detalle.
*
* IMPORTANTE: Todas las firmas de método utilizan las clases de la capa 'entity'.
*/
public interface AtencionMedicaRepository {

 // --- CRUD Entidad Maestra: Atencion Medica (Usando AtencionMedicaEntity) ---
 
 /**
  * Inserta la Entidad de Atención Médica en la base de datos.
  * @param atencion Entidad de atención médica a insertar.
  * @return ID de la Entidad creada.
  */
 Long insertarAtencionMedica(AtencionMedicaEntity atencion);
 
 /**
  * Actualiza la Entidad de Atención Médica en la base de datos.
  */
 Boolean actualizarAtencionMedica(AtencionMedicaEntity atencion);
 
 /**
  * Obtiene una Entidad de Atención Médica por su ID.
  */
 Optional<AtencionMedicaEntity> obtenerAtencionMedicaPorId(Long idAtencion);
 
 /**
  * Lista las Entidades de Atención Médica para un paciente.
  */
 List<AtencionMedicaEntity> listarAtencionesMedicasPorPaciente(Integer idPaciente);

 // --- CRUD Entidad Detalle: Antecedentes (Usando AtencionMedicaAntecedenteEntity) ---
 Long insertarAntecedente(AtencionMedicaAntecedenteEntity antecedente);
 Boolean actualizarAntecedente(AtencionMedicaAntecedenteEntity antecedente);
 List<AtencionMedicaAntecedenteEntity> listarAntecedentesPorAtencion(Long idAtencion);
 
 // --- CRUD Entidad Detalle: Diagnosticos (Usando AtencionMedicaDiagnosticoEntity) ---
 Long insertarDiagnostico(AtencionMedicaDiagnosticoEntity diagnostico);
 Boolean actualizarDiagnostico(AtencionMedicaDiagnosticoEntity diagnostico);
 List<AtencionMedicaDiagnosticoEntity> listarDiagnosticosPorAtencion(Long idAtencion);

 // --- CRUD Entidad Detalle: Discapacidad (Usando AtencionMedicaDiscapacidadEntity) ---
 Long insertarDiscapacidad(AtencionMedicaDiscapacidadEntity discapacidad);
 Boolean actualizarDiscapacidad(AtencionMedicaDiscapacidadEntity discapacidad);
 List<AtencionMedicaDiscapacidadEntity> listarDiscapacidadesPorAtencion(Long idAtencion);
 
 // --- CRUD Entidad Detalle: Discapacidad Otros (Usando AtencionMedicaDiscapacidadOtrosEntity) ---
 Long insertarDiscapacidadOtros(AtencionMedicaDiscapacidadOtrosEntity otros);
 Boolean actualizarDiscapacidadOtros(AtencionMedicaDiscapacidadOtrosEntity otros);
 List<AtencionMedicaDiscapacidadOtrosEntity> listarDiscapacidadesOtrosPorAtencion(Long idAtencion);
 
 // --- CRUD Entidad Detalle: Examen Fisico (Usando AtencionMedicaExamenFisicoEntity) ---
 Long insertarExamenFisico(AtencionMedicaExamenFisicoEntity examenFisico);
 Boolean actualizarExamenFisico(AtencionMedicaExamenFisicoEntity examenFisico);
 List<AtencionMedicaExamenFisicoEntity> listarExamenesFisicosPorAtencion(Long idAtencion);

 // --- CRUD Entidad Detalle: Ordenes Médicas (Usando AtencionMedicaOrdenMedicaEntity) ---
 Long insertarExamen(AtencionMedicaOrdenMedicaEntity ordenMedica);
 Boolean actualizarExamen(AtencionMedicaOrdenMedicaEntity ordenMedica);
 List<AtencionMedicaOrdenMedicaEntity> listarOrdenesMedicasPorAtencion(Long idAtencion);

 // --- CRUD Entidad Detalle: Medicacion (Usando AtencionMedicaMedicacionEntity) ---
 Long insertarMedicacion(AtencionMedicaMedicacionEntity medicacion);
 Boolean actualizarMedicacion(AtencionMedicaMedicacionEntity medicacion);
 List<AtencionMedicaMedicacionEntity> listarMedicacionPorAtencion(Long idAtencion);
 
 // --- CRUD Entidad Detalle: Síntomas (Asumiendo AtencionMedicaSintomaEntity) ---
 Long insertarSintoma(AtencionMedicaSintomaEntity sintoma);
 Boolean actualizarSintoma(AtencionMedicaSintomaEntity sintoma);
 List<AtencionMedicaSintomaEntity> listarSintomasPorAtencion(Long idAtencion);
 
 // --- CRUD Entidad Detalle: Tratamientos (Asumiendo AtencionMedicaTratamientoEntity) ---
 Long insertarTratamiento(AtencionMedicaTratamientoEntity tratamiento);
 Boolean actualizarTratamiento(AtencionMedicaTratamientoEntity tratamiento);
 List<AtencionMedicaTratamientoEntity> listarTratamientosPorAtencion(Long idAtencion);


 // --- Operación de Eliminación (Método genérico) ---
 Boolean eliminarDetalle(Long idDetalle, String spName);
}  
    
    
