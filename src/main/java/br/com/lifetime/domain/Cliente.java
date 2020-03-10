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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author pedro.silva
 * Tabela de Clientes
 */
@Entity
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull (message = "Campo Obrigatorio !!")
	@Digits(fraction = 0, integer = 1000)
	private int codigoXp;
	@NotNull (message = "Campo Obrigatorio !!")
	@Digits(fraction = 0, integer = 1000)
	private int cdCrm;
	@NotNull (message = "Campo Obrigatorio !!")
	private String dataAtualizacao;
	private boolean carteiraAdministrada;
	@NotEmpty
	@Length(min = 5, max = 100, message = "Quantidade de caracteres invalido !!")
	private String nmCliente;
	@NotEmpty (message = "Campo Obrigatorio !!")
	@Email
	private String email;
	@NotEmpty (message = "Campo Obrigatorio !!")
	@Length(min = 5, max = 100, message = "Quantidade de caracteres invalido !!")
	private String perfilXp;
	@NotEmpty (message = "Campo Obrigatorio !!")
	@Length(min = 5, max = 100, message = "Quantidade de caracteres invalido !!")
	private String perfilLftm;
	@Transient
	private int aId;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "aai_id")
	private Aai aai;
	
	// cascade com REMOVE ele deleta em cascata, com PERSISTE ele cai no tratamento de exception para nÃ£o deletar em cascata
	@OneToMany(mappedBy = "cliente",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JsonIgnore
	private List<ControleCampanha> controleCampanha = new ArrayList<>();
	
	
	/**
	 * definindo a tabela intermediaria "estrategia rejeitada" na anotação @JoinTable
	 */
	@ManyToMany (cascade = CascadeType.PERSIST)
	@JoinTable(name = "ESTRATEGIAS_REJEITADAS",
		joinColumns = @JoinColumn(name = "cliente_id"),
		inverseJoinColumns = @JoinColumn(name = "estrategia_id")
			)
	private List<Estrategia> estrategia = new ArrayList<>();
	
	public Cliente() {
		
	}
	
	// construtor usando Obj como parametro para request de GET ALL funcionar
	public Cliente (Cliente obj) {
		id = obj.getId();
		codigoXp = obj.getcodigoXp();
		nmCliente = obj.getNmCliente();
		cdCrm = obj.getCdCrm();
		dataAtualizacao = obj.getDataAtualizacao();
		email = obj.getEmail();
		perfilXp = obj.getPerfilXp();
		perfilLftm = obj.getPerfilLftm();
		carteiraAdministrada = obj.getCarteiraAdministrada();
	}
	
	public Cliente(Integer id, int codigoXp, String nmCliente, int cdCrm, String dataAtualizacao,
			String email, String perfilXp, String perfilLftm, boolean carteiraAdministrada,int aId, Aai aai) {
		super();
		this.id = id;
		this.codigoXp = codigoXp;
		this.nmCliente = nmCliente;
		this.cdCrm = cdCrm;
		this.dataAtualizacao = dataAtualizacao;
		this.email = email;
		this.perfilXp = perfilXp;
		this.perfilLftm = perfilLftm;
		this.carteiraAdministrada = carteiraAdministrada;
		this.setaId(aId);
		this.aai = aai;
	}
	
	
	

	public Aai getAai() {
		return aai;
	}

	public void setAai(Aai aai) {
		this.aai = aai;
	}

	public List<ControleCampanha> getControleCampanha() {
		return controleCampanha;
	}

	public void setControleCampanha(List<ControleCampanha> controleCampanha) {
		this.controleCampanha = controleCampanha;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getcodigoXp() {
		return codigoXp;
	}
	public void setcodigoXp(int codigoXp) {
		this.codigoXp = codigoXp;
	}
	public String getNmCliente() {
		return nmCliente;
	}
	public void setNmCliente(String nmCliente) {
		this.nmCliente = nmCliente;
	}
	public int getCdCrm() {
		return cdCrm;
	}
	public void setCdCrm(int cdCrm) {
		this.cdCrm = cdCrm;
	}
	public String getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(String dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPerfilXp() {
		return perfilXp;
	}
	public void setPerfilXp(String perfilXp) {
		this.perfilXp = perfilXp;
	}
	public String getPerfilLftm() {
		return perfilLftm;
	}
	public void setPerfilLftm(String perfilLftm) {
		this.perfilLftm = perfilLftm;
	}
	public boolean getCarteiraAdministrada() {
		return carteiraAdministrada;
	}
	public void setCarteiraAdministrada(boolean carteiraAdministrada) {
		this.carteiraAdministrada = carteiraAdministrada;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", codigoXp=" + codigoXp + ", cdCrm=" + cdCrm + ", dataAtualizacao="
				+ dataAtualizacao + ", carteiraAdministrada=" + carteiraAdministrada + ", nmCliente=" + nmCliente
				+ ", email=" + email + ", perfilXp=" + perfilXp + ", perfilLftm=" + perfilLftm + ", aai=" + aai
				+ ", controleCampanha=" + controleCampanha + ", estrategia=" + estrategia + "]";
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public int getaId() {
		return aId;
	}

	public void setaId(int aId) {
		this.aId = aId;
	}

}
