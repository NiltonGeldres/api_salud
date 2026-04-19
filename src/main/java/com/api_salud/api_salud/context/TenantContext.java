package com.api_salud.api_salud.context;

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


