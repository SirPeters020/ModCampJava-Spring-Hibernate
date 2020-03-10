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
 * Tabela para armazenar historico de registro Aai
 *
 */
@Entity
public class HistoricoAai implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // id do registro no historico 
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "controle_campanha_id")
	private Aai aai; 
	private Integer aaiId; //id registro Aai
	private String nmAai;
	private String email;
	private int nmTelefone;
	private boolean status;
	private String dataAtualizacao;
	private boolean ativo;
	
	public HistoricoAai () {
		
	}
	
	public HistoricoAai (Aai obj) {
		aaiId = obj.getId();
		nmAai = obj.getNmAai();
		email = obj.getEmail();
		nmTelefone = obj.getNmTelefone();
		status = obj.isStatus();
		dataAtualizacao = obj.getDataAtualizacao();
		ativo = obj.isAtivo();
	}

	public HistoricoAai(Integer id, Aai aai, Integer aaiId, String nmAai, String email, int nmTelefone, boolean status,
			String dataAtualizacao, boolean ativo) {
		super();
		this.id = id;
		this.aai = aai;
		this.aaiId = aaiId;
		this.nmAai = nmAai;
		this.email = email;
		this.nmTelefone = nmTelefone;
		this.status = status;
		this.dataAtualizacao = dataAtualizacao;
		this.ativo = ativo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Aai getAai() {
		return aai;
	}

	public void setAai(Aai aai) {
		this.aai = aai;
	}

	public Integer getAaiId() {
		return aaiId;
	}

	public void setAaiId(Integer aaiId) {
		this.aaiId = aaiId;
	}

	public String getNmAai() {
		return nmAai;
	}

	public void setNmAai(String nmAai) {
		this.nmAai = nmAai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNmTelefone() {
		return nmTelefone;
	}

	public void setNmTelefone(int nmTelefone) {
		this.nmTelefone = nmTelefone;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(String dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
	
}
