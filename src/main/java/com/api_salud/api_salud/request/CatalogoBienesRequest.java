package com.api_salud.api_salud.request;



import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CatalogoBienesRequest {

    @NotNull(message = "El idEntidad es obligatorio")
    private Integer idEntidad = 2; // Valor por defecto

    @NotBlank(message = "El término de búsqueda no puede estar vacío")
    @Size(min = 2, message = "El término debe tener al menos 2 caracteres")
    private String termino;

    private Integer tipoProducto = 0;

    @Min(value = 1, message = "El tamaño de página debe ser al menos 1")
    private Integer tamanoPagina = 20;

    @Min(value = 1, message = "La página actual debe ser al menos 1")
    private Integer paginaActual = 1;

    // Constructores vacíos y con parámetros
    public CatalogoBienesRequest() {}

    // Getters y Setters
    public Integer getIdEntidad() { return idEntidad; }
    public void setIdEntidad(Integer idEntidad) { this.idEntidad = idEntidad; }

    public String getTermino() { return termino; }
    public void setTermino(String termino) { this.termino = termino; }

    public Integer getTipoProducto() { return tipoProducto; }
    public void setTipoProducto(Integer tipoProducto) { this.tipoProducto = tipoProducto; }

    public Integer getTamanoPagina() { return tamanoPagina; }
    public void setTamanoPagina(Integer tamanoPagina) { this.tamanoPagina = tamanoPagina; }

    public Integer getPaginaActual() { return paginaActual; }
    public void setPaginaActual(Integer paginaActual) { this.paginaActual = paginaActual; }
}