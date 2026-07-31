package com.api_salud.api_salud.response;

import java.io.Serializable;
import java.util.List;

import com.api_salud.api_salud.dto.BienDTO;
import com.api_salud.api_salud.dto.MetaBienesDTO;
 
public class CatalogoBienesResponse {
	   private static final long serialVersionUID = 1L;

	    private String estado;
	    private Integer codigo;
	    private MetaBienesDTO meta;
	    private List<BienDTO> data;

	    public CatalogoBienesResponse() {
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

	    public MetaBienesDTO getMeta() {
	        return meta;
	    }

	    public void setMeta(MetaBienesDTO meta) {
	        this.meta = meta;
	    }

	    public List<BienDTO> getData() {
	        return data;
	    }

	    public void setData(List<BienDTO> data) {
	        this.data = data;
	    }
	    
}


