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
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author pedro.silva
 * Tabela de Campanha
 */
@Entity
public class Campanha implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	@Length(min = 4, max = 80, message = "Quantidade de caracteres invalido !!")
	private String nome;
	
	@OneToMany(mappedBy = "campanha", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JsonIgnore
	private List<SubCampanha> subCamp = new ArrayList<>();
	
	public Campanha() {
		
	}
	
	public Campanha(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public List<SubCampanha> getSubCamp() {
		return subCamp;
	}

	public void setSubCamp(List<SubCampanha> subCamp) {
		this.subCamp = subCamp;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return "Campanha [id=" + id + ", nome=" + nome + "]";
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
		Campanha other = (Campanha) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}
