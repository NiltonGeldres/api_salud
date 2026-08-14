package com.api_salud.api_salud.response;

import java.io.Serializable;
import java.util.List;

import com.api_salud.api_salud.dto.MetaPaqueteServiciosDTO;
import com.api_salud.api_salud.dto.PaqueteServicioDetalleDTO;

public class PaqueteServiciosResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String estado;
    private Integer codigo;
    private MetaPaqueteServiciosDTO meta;
    private List<PaqueteServicioDetalleDTO> data;

    public PaqueteServiciosResponse() {}

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Integer getCodigo() { return codigo; }
    public void setCodigo(Integer codigo) { this.codigo = codigo; }

    public MetaPaqueteServiciosDTO getMeta() { return meta; }
    public void setMeta(MetaPaqueteServiciosDTO meta) { this.meta = meta; }

    public List<PaqueteServicioDetalleDTO> getData() { return data; }
    public void setData(List<PaqueteServicioDetalleDTO> data) { this.data = data; }
}