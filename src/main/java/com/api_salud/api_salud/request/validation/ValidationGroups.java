package com.api_salud.api_salud.request.validation;

public interface ValidationGroups {
    // Reglas para guardar borrador (Solo exige datos de cabecera)
    interface BorradorGroup {}

    // Reglas para validación clínica completa (PDF / Firma)
    // Hereda BorradorGroup para no repetir la validación de cabecera
    interface CompletoGroup extends BorradorGroup {}
}