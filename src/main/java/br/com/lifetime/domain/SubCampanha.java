package br.com.lifetime.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author pedro.silva
 * Tabela de SubCampanha
 */
@Entity
public class SubCampanha implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@Digits(fraction = 2, integer = 1000)
	private double precoQtde;
	@NotEmpty
	@Length(min = 5, max = 100, message = "Quantidade de caracteres invalido !!")
	private String nmCampanha;
	@NotEmpty
	@Length(min = 5, max = 100, message = "Quantidade de caracteres invalido !!")
	private String riscoSugerido;
	@NotEmpty
	@Length(min = 5, max = 100, message = "Quantidade de caracteres invalido !!")
	private String riscoEfetivo;
//	@NotEmpty
//	@Length(min = 5, max = 100, message = "Quantidade de caracteres invalido !!")
//	private String modoExecucao;
//	@NotEmpty
//	@Length(min = 5, max = 100, message = "Quantidade de caracteres invalido !!")
//	private String roa;
	@NotEmpty
	@Length(min = 5, max = 100, message = "Quantidade de caracteres invalido !!")
	private String pitchVendas;
	private boolean ativo;
	private boolean reserva;
	@Transient
	private int estrategId;
	@Transient
	private int campId;

	// cascade com REMOVE ele deleta em cascata, com PERSISTE ele cai no tratamento de exception para não deletar em cascata
	@OneToMany(mappedBy = "subCampanha", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)	
	// JSONIgnore para evitar rerferncia ciclica
	@JsonIgnore
	private List<ControleCampanha> controleCamp = new ArrayList<>();

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn
	private Estrategia estrategia;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn
	private Campanha campanha;

	public SubCampanha() {

	}
	
	// construtor usando Obj como parametro para request de GET ALL funcionar
	public SubCampanha(SubCampanha obj) {
		id = obj.getId();
		nmCampanha = obj.getNmCampanha();
		riscoSugerido = obj.getRiscoSugerido();
		riscoEfetivo = obj.getRiscoEfetivo();
		ativo = obj.isAtivo();
		precoQtde = obj.getPrecoQtde();
//		modoExecucao = obj.getModoExecucao();
//		roa = obj.getRoa();
		reserva = obj.isReserva();
		pitchVendas = obj.getPitchVendas();
	}

	public SubCampanha(Integer id, String nmCampanha, String riscoSugerido, String riscoEfetivo,
			boolean ativo, double precoQtde,boolean reserva, String pitchVendas,
			Estrategia estrategia, int estrategId, int campId, Campanha campanha) {
		super();
		this.id = id;
		this.nmCampanha = nmCampanha;
		this.riscoSugerido = riscoSugerido;
		this.riscoEfetivo = riscoEfetivo;
		this.ativo = ativo;
		this.precoQtde = precoQtde;
//		this.modoExecucao = modoExecucao;
//		this.roa = roa;
		this.reserva = reserva;
		this.pitchVendas = pitchVendas;
		this.estrategId = estrategId;
		this.campId = campId;
		this.estrategia = estrategia;
		this.campanha = campanha;
	}
	
	

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public List<ControleCampanha> getControleCamp() {
		return controleCamp;
	}

	public void setControleCamp(List<ControleCampanha> controleCamp) {
		this.controleCamp = controleCamp;
	}

	public Estrategia getEstrategia() {
		return estrategia;
	}

	public void setEstrategia(Estrategia estrategia) {
		this.estrategia = estrategia;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNmCampanha() {
		return nmCampanha;
	}

	public void setNmCampanha(String nmCampanha) {
		this.nmCampanha = nmCampanha;
	}

	public String getRiscoSugerido() {
		return riscoSugerido;
	}

	public void setRiscoSugerido(String riscoSugerido) {
		this.riscoSugerido = riscoSugerido;
	}

	public String getRiscoEfetivo() {
		return riscoEfetivo;
	}

	public void setRiscoEfetivo(String riscoEfetivo) {
		this.riscoEfetivo = riscoEfetivo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public double getPrecoQtde() {
		return precoQtde;
	}

	public void setPrecoQtde(double precoQtde) {
		this.precoQtde = precoQtde;
	}

//	public String getModoExecucao() {
//		return modoExecucao;
//	}
//
//	public void setModoExecucao(String modoExecucao) {
//		this.modoExecucao = modoExecucao;
//	}
//
//	public String getRoa() {
//		return roa;
//	}
//
//	public void setRoa(String roa) {
//		this.roa = roa;
//	}

	public String getPitchVendas() {
		return pitchVendas;
	}

	public void setPitchVendas(String pitchVendas) {
		this.pitchVendas = pitchVendas;
	}
	
	public boolean isReserva() {
		return reserva;
	}

	public void setReserva(boolean reserva) {
		this.reserva = reserva;
	}


	@Override
	public String toString() {
		return "TbCampanha [id=" + id + ", nmCampanha=" + nmCampanha + ", riscoSugerido=" + riscoSugerido + 
				", riscoEfetivo=" + riscoEfetivo + ", ativo=" + ativo + ", precoQtde=" + precoQtde + ", pitchVendas=" + pitchVendas + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubCampanha other = (SubCampanha) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public int getEstrategId() {
		return estrategId;
	}

	public void setEstrategId(int estrategId) {
		this.estrategId = estrategId;
	}

	public int getCampId() {
		return campId;
	}

	public void setCampId(int campId) {
		this.campId = campId;
	}
	

}
