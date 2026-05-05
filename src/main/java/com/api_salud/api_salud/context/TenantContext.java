package com.api_salud.api_salud.context;

public class TenantContext {

    private static final ThreadLocal<Long> ENTIDAD_ID = new ThreadLocal<>();
    private static final ThreadLocal<String> CURRENT_USER = new ThreadLocal<>();
    private static final ThreadLocal<Integer> ROL_ID = new ThreadLocal<>();
    private static final ThreadLocal<Integer> REFERENCIA_ID = new ThreadLocal<>();
    private static final ThreadLocal<Integer> USUARIO_ID = new ThreadLocal<>();

    private TenantContext() {
        // Evita instancias
    }

    // Usuario (Username)
    public static void setCurrentUser(String username) { CURRENT_USER.set(username); }
    public static String getCurrentUser() { return CURRENT_USER.get(); }

    // ID de Entidad (Clínica/Hospital)
    public static void setEntidadId(Long entidadId) { ENTIDAD_ID.set(entidadId); }
    public static Long getEntidadId() { return ENTIDAD_ID.get(); }

    // ID de Rol (Médico=1, Paciente=2, etc.)
    public static void setRolId(Integer rolId) { ROL_ID.set(rolId); }
    public static Integer getRolId() { return ROL_ID.get(); }

    // ID de Referencia (id_medico o id_paciente)
    public static void setReferenciaId(Integer referenciaId) { REFERENCIA_ID.set(referenciaId); }
    public static Integer getReferenciaId() { return REFERENCIA_ID.get(); }

    // ID de Usuario (PK de la tabla igm_security.usuario)
    public static void setUsuarioId(Integer usuarioId) { USUARIO_ID.set(usuarioId); }
    public static Integer getUsuarioId() { return USUARIO_ID.get(); }

    public static void clear() {
        ENTIDAD_ID.remove();
        CURRENT_USER.remove();
        ROL_ID.remove();
        REFERENCIA_ID.remove();
        USUARIO_ID.remove();
    }
}


/*
public class TenantContext {

    private static final ThreadLocal<Long> ENTIDAD_ID = new ThreadLocal<>();
    private static final ThreadLocal<String> CURRENT_USER = new ThreadLocal<>(); // Nuevo

    private TenantContext() {
        // Evita instancias
    }

    public static void setCurrentUser(String username) {
        CURRENT_USER.set(username);
    }

    public static String getCurrentUser() {
        return CURRENT_USER.get();
    }
    
    
    public static void setEntidadId(Long entidadId) {
    	ENTIDAD_ID.set(entidadId);
    }

    public static Long getEntidadId() {
        return ENTIDAD_ID.get();
    }

    public static void clear() {
    	ENTIDAD_ID.remove();
        CURRENT_USER.remove(); // Limpiar siempre
    }
    
}
*/

