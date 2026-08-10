package com.api_salud.api_salud.response;

import java.io.Serializable;
import java.util.List;

import com.api_salud.api_salud.dto.DiagnosticoDTO;
import com.api_salud.api_salud.dto.MetaDiagnosticosDTO;

public class CatalogoDiagnosticosResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String estado;
    private Integer codigo;
    private MetaDiagnosticosDTO meta;
    private List<DiagnosticoDTO> data;

    public CatalogoDiagnosticosResponse() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public MetaDiagnosticosDTO getMeta() {
        return meta;
    }

    public void setMeta(MetaDiagnosticosDTO meta) {
        this.meta = meta;
    }

    public List<DiagnosticoDTO> getData() {
        return data;
    }

    public void setData(List<DiagnosticoDTO> data) {
        this.data = data;
    }
}