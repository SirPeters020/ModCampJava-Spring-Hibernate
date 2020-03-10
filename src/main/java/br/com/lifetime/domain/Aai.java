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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author pedro.silva
 * Tabela de AAi
 */

@Entity
public class Aai implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty (message = "Campo Obrigatorio !!")
	@Length(min = 5, max = 80, message = "Quantidade de caracteres invalidos !!")
	private String nmAai;
	@NotEmpty (message = "Campo Obrigatorio !!")
	@Length(min = 5, max = 80, message = "Quantidade de caracteres invalidos !!")
	@Email
	private String email;
	private int nmTelefone;
	private boolean status;
	@NotNull (message = "Campo Obrigatorio !!")
	private String dataAtualizacao;
	private boolean ativo;
	private int idEquipe;

	@ManyToOne (cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "equipe_id")
	private Equipe equipe = new Equipe();
	
	// cascade com REMOVE ele deleta em cascata, com PERSISTE ele cai no tratamento de exception para não deletar em cascata
	@OneToMany(mappedBy = "aai",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	// JSONIgnore para evitar rerferncia ciclica
	@JsonIgnore
	private List<Cliente> cliente = new ArrayList<>();

	public Aai() {

	}
	
	// construtor usando Obj como parametro para request de GET ALL funcionar
	public Aai(Aai obj) {
		id = obj.getId();
		nmAai = obj.getNmAai();
		email = obj.getEmail();
		status = obj.isStatus();
		dataAtualizacao = obj.getDataAtualizacao();
		nmTelefone = obj.getNmTelefone();
		ativo = obj.isAtivo();
	}
	
	public Aai(HistoricoAai obj) {
		nmAai = obj.getNmAai();
		email = obj.getEmail();
		status =obj.isStatus();
		dataAtualizacao = obj.getDataAtualizacao();
		nmTelefone = obj.getNmTelefone();
		ativo = obj.isAtivo();
	}
	
	public Aai(Integer id, String nmAai, String email, boolean status, String dataAtualizacao, int nmTelefone, boolean ativo, int idEquipe, Equipe equipe) {
		super();
		this.id = id;
		this.nmAai = nmAai;
		this.email = email;
		this.status = status;
		this.dataAtualizacao = dataAtualizacao;
		this.nmTelefone = nmTelefone;
		this.ativo = ativo;
		this.idEquipe = idEquipe;
		this.equipe = equipe;
	}
	
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public List<Cliente> getCliente() {
		return cliente;
	}

	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public int getNmTelefone() {
		return nmTelefone;
	}

	public void setNmTelefone(int nmTelefone) {
		this.nmTelefone = nmTelefone;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "TbAai [id=" + id + ", nmAai=" + nmAai + ", email=" + email + ""
				+ ", status=" + status + ", dataAtualizacao=" + dataAtualizacao + "]";
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
		Aai other = (Aai) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public int getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(int idEquipe) {
		this.idEquipe = idEquipe;
	}

}
