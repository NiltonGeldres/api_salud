package com.api_salud.atencionmedica.repository;

public class Backup2 {
	package com.api_salud.atencionmedica.repository;



	import com.api_salud.atencionmedica.domain.AtencionMedicaModel.*;
	import java.util.List;
	import java.util.Optional;

	/**
	 * Interfaz para el repositorio de Atenciones Médicas, define las operaciones CRUD.
	 */
	public interface AtencionMedicaRepository {

	    // --- CRUD Entidad Maestra: Atencion Medica ---
	    Long insertarAtencionMedica(AtencionMedica atencion);
	    Boolean actualizarAtencionMedica(AtencionMedica atencion);
	    Optional<AtencionMedica> obtenerAtencionMedicaPorId(Long idAtencion);
	    List<AtencionMedica> listarAtencionesMedicasPorPaciente(Integer idPaciente);

	    // --- CRUD Entidad Detalle: Antecedentes ---
	    Long insertarAntecedente(Antecedente antecedente);
	    Boolean actualizarAntecedente(Antecedente antecedente);
	    List<Antecedente> listarAntecedentesPorAtencion(Long idAtencion);
	    
	    // --- CRUD Entidad Detalle: Diagnosticos ---
	    Long insertarDiagnostico(Diagnostico diagnostico);
	    Boolean actualizarDiagnostico(Diagnostico diagnostico);
	    List<Diagnostico> listarDiagnosticosPorAtencion(Long idAtencion);

	    // --- CRUD Entidad Detalle: Discapacidad ---
	    Long insertarDiscapacidad(Discapacidad discapacidad);
	    Boolean actualizarDiscapacidad(Discapacidad discapacidad);
	    List<Discapacidad> listarDiscapacidadesPorAtencion(Long idAtencion);
	    
	    // --- CRUD Entidad Detalle: Discapacidad Otros ---
	    Long insertarDiscapacidadOtros(DiscapacidadOtros otros);
	    Boolean actualizarDiscapacidadOtros(DiscapacidadOtros otros);
	    List<DiscapacidadOtros> listarDiscapacidadesOtrosPorAtencion(Long idAtencion);
	    
	    // --- CRUD Entidad Detalle: Examen Fisico ---
	    Long insertarExamenFisico(ExamenFisico examenFisico);
	    Boolean actualizarExamenFisico(ExamenFisico examenFisico);
	    List<ExamenFisico> listarExamenesFisicosPorAtencion(Long idAtencion);

	    // --- CRUD Entidad Detalle: Examenes (de Laboratorio/Gabinete) ---
	    Long insertarExamen(OrdenMedica ordenMedica);
	    Boolean actualizarExamen(OrdenMedica ordenMedica);
	    List<OrdenMedica> listarOrdenesMedicasPorAtencion(Long idAtencion);

	    // --- CRUD Entidad Detalle: Medicacion ---
	    Long insertarMedicacion(Medicacion medicacion);
	    Boolean actualizarMedicacion(Medicacion medicacion);
	    List<Medicacion> listarMedicacionPorAtencion(Long idAtencion); // Esta función SP no fue provista, se asume su existencia.
	    
	    // --- Operación de Eliminación (Asumiendo SPs para eliminar) ---
	    Boolean eliminarDetalle(Long idDetalle, String spName);
	}    
	    
	    
}
