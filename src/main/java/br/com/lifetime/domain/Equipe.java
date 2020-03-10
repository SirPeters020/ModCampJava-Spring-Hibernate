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
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author pedro.silva
 * Tabela de Equipe
 */
@Entity
public class Equipe implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty (message = "Campo Obrigatorio !!")
	@Length(min = 5, max = 100, message = "Quantidade de caracteres invalidos !!")
	private String nmEquipe;
	@NotEmpty (message = "Campo Obrigatorio !!")
	@Length(min = 5, max = 100, message = "Quantidade de caracteres invalidos !!")
	private String nmHead;
	@NotNull (message = "Campo Obrigatorio !!")
	@Digits(fraction = 0, integer = 1000)
	private int qtdeAai;
	
	// cascade com REMOVE ele deleta em cascata, com PERSISTE ele cai no tratamento de exception para não deletar em cascata, cascade informa o que ele DEVE fazer ao inves do que pode ou não
	@OneToMany(mappedBy = "equipe", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	//Json Ignore para eviar referencia ciclica no Request GET ALL
	@JsonIgnore
	private List<Aai> aai = new ArrayList<>();
	
	public Equipe() {
		
	}
	
	// construtor usando Obj como parametro para request de GET ALL funcionar
	public Equipe(Equipe obj) {
		id = obj.getId();
		nmEquipe = obj.getNmEquipe();
		nmHead = obj.getNmHead();
		qtdeAai = obj.getQtdeAai();
	}
	
	public Equipe(Integer id, String nmEquipe, String nmHead, int qtdeAai) {
		super();
		this.id = id;
		this.nmEquipe = nmEquipe;
		this.nmHead = nmHead;
		this.qtdeAai = qtdeAai;
	}


	public List<Aai> getAai() {
		return aai;
	}


	public void setAai(List<Aai> aai) {
		this.aai = aai;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNmEquipe() {
		return nmEquipe;
	}
	public void setNmEquipe(String nmEquipe) {
		this.nmEquipe = nmEquipe;
	}
	public String getNmHead() {
		return nmHead;
	}
	public void setNmHead(String nmHead) {
		this.nmHead = nmHead;
	}
	public int getQtdeAai() {
		return qtdeAai;
	}
	public void setQtde_aai(int qtdeAai) {
		this.qtdeAai = qtdeAai;
	}


	@Override
	public String toString() {
		return "TbEquipe [id=" + id + ", nmEquipe=" + nmEquipe + ", nmHead=" + nmHead + ", qtdeAai=" + qtdeAai + "]";
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
		Equipe other = (Equipe) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	
}
