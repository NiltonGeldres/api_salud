package com.api_salud.api_salud.response;

import java.io.Serializable;
import java.util.List;

import com.api_salud.api_salud.dto.MetaPaqueteBienesDTO;
import com.api_salud.api_salud.dto.PaqueteBienDetalleDTO;

public class PaqueteBienesResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String estado;
    private Integer codigo;
    private MetaPaqueteBienesDTO meta;
    private List<PaqueteBienDetalleDTO> data;

    public PaqueteBienesResponse() {}

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Integer getCodigo() { return codigo; }
    public void setCodigo(Integer codigo) { this.codigo = codigo; }

    public MetaPaqueteBienesDTO getMeta() { return meta; }
    public void setMeta(MetaPaqueteBienesDTO meta) { this.meta = meta; }

    public List<PaqueteBienDetalleDTO> getData() { return data; }
    public void setData(List<PaqueteBienDetalleDTO> data) { this.data = data; }
}