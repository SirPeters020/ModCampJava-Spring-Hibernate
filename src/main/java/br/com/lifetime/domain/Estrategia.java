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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author pedro.silva
 * Tabela de Estrategia
 */
@Entity
public class Estrategia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty(message = "Campo Obrigatorio !!")
	@Length(min = 5, max = 100, message = "Quantidade de caracteres invalidos !!")
	private String nmEstrategia;
	@NotEmpty(message = "Campo Obrigatorio !!")
	@Length(min = 5, max = 100, message = "Quantidade de caracteres invalidos !!")
	private String descricao;

	// cascade com REMOVE ele deleta em cascata, com PERSISTE ele cai no tratamento de exception para não deletar em cascata
	@OneToMany(mappedBy = "estrategia", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	// JSONIgnore para evitar rerferncia ciclica
	@JsonIgnore
	private List<SubCampanha> campanha = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Cliente> cliente = new ArrayList<>();
	
	public Estrategia() {

	}
	
	// construtor usando Obj como parametro para request de GET ALL funcionar
	public Estrategia (Estrategia obj) {
		id = obj.getId();
		nmEstrategia = obj.getNmEstrategia();
		descricao = obj.getDescricao();
	}

	public Estrategia(Integer id, String nmEstrategia, String descricao) {
		super();
		this.id = id;
		this.nmEstrategia = nmEstrategia;
		this.descricao = descricao;
	}

	public List<SubCampanha> getCampanha() {
		return campanha;
	}

	public void setCampanha(List<SubCampanha> campanha) {
		this.campanha = campanha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNmEstrategia() {
		return nmEstrategia;
	}

	public void setNmEstrategia(String nmEstrategia) {
		this.nmEstrategia = nmEstrategia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "TbEstrategia [id=" + id + ", nmEstrategia=" + nmEstrategia + ", descricao=" + descricao + "]";
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
		Estrategia other = (Estrategia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
