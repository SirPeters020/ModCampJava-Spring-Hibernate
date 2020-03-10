package br.com.lifetime.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author pedro.silva
 * Tabela para armazenar historico de registro Controle Campanha
 *
 */
@Entity
public class HistoricoContCamp implements Serializable{
	private static final long serialVersionUID = 1L;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "controle_campanha_id")
	private ControleCampanha controleCampanha;
	
	private Integer ccId; //id registro ControleCampanha
	private boolean elegivel;
	private String fomento;
	private boolean fomentoRealizado;
	private double qtdeValor;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public HistoricoContCamp () {
		
	}
	
	public HistoricoContCamp (ControleCampanha obj) {
		ccId = obj.getId();
		elegivel = obj.isElegivel();
		fomento = obj.getFomento();
		fomentoRealizado = obj.isFomentoRealizado();
		qtdeValor = obj.getQtdeValor();
	}
	
	
	public HistoricoContCamp(Integer id, boolean elegivel, boolean fomentoRealizado, double qtdeValor, String fomento) {
		super();
		this.id = id;
		this.elegivel = elegivel;
		this.fomentoRealizado = fomentoRealizado;
		this.qtdeValor = qtdeValor;
		this.fomento = fomento;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isElegivel() {
		return elegivel;
	}
	public void setElegivel(boolean elegivel) {
		this.elegivel = elegivel;
	}
	public boolean isFomentoRealizado() {
		return fomentoRealizado;
	}
	public void setFomentoRealizado(boolean fomentoRealizado) {
		this.fomentoRealizado = fomentoRealizado;
	}
	public double getQtdeValor() {
		return qtdeValor;
	}
	public void setQtdeValor(double qtdeValor) {
		this.qtdeValor = qtdeValor;
	}
	
	public String getFomento() {
		return fomento;
	}

	public void setFomento(String fomento) {
		this.fomento = fomento;
	}
	
	public Integer getCcId() {
		return ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}

	public ControleCampanha getControleCampanha() {
		return controleCampanha;
	}
	public void setControleCampanha(ControleCampanha controleCampanha) {
		this.controleCampanha = controleCampanha;
	}
			
}
