package com.api_salud.api_salud.response;

import java.io.Serializable;
import java.util.List;

import com.api_salud.api_salud.dto.MetaServiciosDTO;
import com.api_salud.api_salud.dto.ServicioDTO;

public class CatalogoServiciosResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String estado;
    private Integer codigo;
    private MetaServiciosDTO meta;
    private List<ServicioDTO> data;

    public CatalogoServiciosResponse() {
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

    public MetaServiciosDTO getMeta() {
        return meta;
    }

    public void setMeta(MetaServiciosDTO meta) {
        this.meta = meta;
    }

    public List<ServicioDTO> getData() {
        return data;
    }

    public void setData(List<ServicioDTO> data) {
        this.data = data;
    }
}