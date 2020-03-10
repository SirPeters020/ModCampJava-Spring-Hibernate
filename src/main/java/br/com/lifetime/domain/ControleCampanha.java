package br.com.lifetime.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * @author pedro.silva
 * Tabela de controleCampanha
 */
@Entity
public class ControleCampanha implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "Campo Obrigatorio !!")
	@Digits(fraction = 3, integer = 1000)
	private double qtdeValor;
	private String dataEntrada;
	private String dataAtualizacao;
	private int qtdeValorInicioCampanha;
	@Length(min = 5, max = 100, message = "Quantidade de caracteres invalidos !!")
	private String fomento;
	private String status;
	private boolean elegivel;
	private boolean fomentoRealizado;
	private boolean operacaoEnviada;
	private boolean respostaRecebida;
	private boolean permissaoRecebida;
	private boolean ordemExecutada;
	private boolean elegivelInicioCampanha;
	@Transient
	private int cliId;
	@Transient
	private int subId;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "subCampanha_id")
	private SubCampanha subCampanha;
	
	
	public ControleCampanha() {

	}
	// construtor usando Obj como parametro para request de GET ALL funcionar
	public ControleCampanha(HistoricoContCamp obj) {
		elegivel = obj.isElegivel();
		fomento = obj.getFomento();
		fomentoRealizado = obj.isFomentoRealizado();
		qtdeValor = obj.getQtdeValor();
	}
	
	public ControleCampanha(boolean elegivel, String fomento, boolean fomentoRealizado, double qtdeValor) {
		this.elegivel = elegivel;
		this.fomento = fomento;
		this.fomentoRealizado = fomentoRealizado;
		this.qtdeValor = qtdeValor;
	}
	
	public ControleCampanha(ControleCampanha obj) {
		super();
		id = obj.getId();
		qtdeValor = obj.getQtdeValor();
		dataEntrada = obj.getDataAtualizacao();
		dataAtualizacao = obj.getDataAtualizacao();
		qtdeValorInicioCampanha = obj.getQtdeValorInicioCampanha();
		fomento = obj.getFomento();
		status = obj.getStatus();
		elegivel = obj.isElegivel();
		fomentoRealizado = obj.isFomentoRealizado();
		operacaoEnviada = obj.isOperacaoEnviada();
		respostaRecebida = obj.isRespostaRecebida();
		permissaoRecebida = obj.isPermissaoRecebida();
		ordemExecutada = obj.isOrdemExecutada();
		elegivelInicioCampanha = obj.isElegivelInicioCampanha();
	}
	
	public ControleCampanha(Integer id, double qtdeValor,
			String dataEntrada, String dataAtualizacao, int qtdeValorInicioCampanha, String fomento,
			String status, boolean elegivel, boolean fomentoRealizado, boolean operacaoEnviada,
			boolean respostaRecebida, boolean permissaoRecebida, boolean ordemExecutada, boolean elegivelInicioCampanha,
			int cliId, int subId, Cliente cliente, SubCampanha subCampanha) {
		super();
		this.id = id;
		this.qtdeValor = qtdeValor;
		this.dataEntrada = dataEntrada;
		this.dataAtualizacao = dataAtualizacao;
		this.qtdeValorInicioCampanha = qtdeValorInicioCampanha;
		this.fomento = fomento;
		this.status = status;
		this.elegivel = elegivel;
		this.fomentoRealizado = fomentoRealizado;
		this.operacaoEnviada = operacaoEnviada;
		this.respostaRecebida = respostaRecebida;
		this.permissaoRecebida = permissaoRecebida;
		this.ordemExecutada = ordemExecutada;
		this.elegivelInicioCampanha = elegivelInicioCampanha;
		this.cliId = cliId;
		this.subId = subId;
		this.cliente = cliente;
		this.subCampanha = subCampanha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public SubCampanha getSubCampanha() {
		return subCampanha;
	}

	public void setSubCampanha(SubCampanha subCampanha) {
		this.subCampanha = subCampanha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getQtdeValor() {
		return qtdeValor;
	}

	public void setQtdeValor(double qtdeValor) {
		this.qtdeValor = qtdeValor;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(String dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getFomento() {
		return fomento;
	}

	public void setFomento(String fomento) {
		this.fomento = fomento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public boolean isOperacaoEnviada() {
		return operacaoEnviada;
	}

	public boolean isRespostaRecebida() {
		return respostaRecebida;
	}

	public void setRespostaRecebida(boolean respostaRecebida) {
		this.respostaRecebida = respostaRecebida;
	}

	public boolean isPermissaoRecebida() {
		return permissaoRecebida;
	}

	public void setPermissaoRecebida(boolean permissaoRecebida) {
		this.permissaoRecebida = permissaoRecebida;
	}

	public boolean isOrdemExecutada() {
		return ordemExecutada;
	}

	public void setOrdemExecutada(boolean ordemExecutada) {
		this.ordemExecutada = ordemExecutada;
	}

	public void setOperacaoEnviada(boolean operacaoEnviada) {
		this.operacaoEnviada = operacaoEnviada;
	}

	public int getQtdeValorInicioCampanha() {
		return qtdeValorInicioCampanha;
	}

	public void setQtdeValorInicioCampanha(int qtdeValorInicioCampanha) {
		this.qtdeValorInicioCampanha = qtdeValorInicioCampanha;
	}

	public boolean isElegivelInicioCampanha() {
		return elegivelInicioCampanha;
	}

	public void setElegivelInicioCampanha(boolean elegivelInicioCampanha) {
		this.elegivelInicioCampanha = elegivelInicioCampanha;
	}

	@Override
	public String toString() {
		return "TbControleCampanha [id=" + id + ", qtdeValor=" + qtdeValor + ", dataEntrada=" + dataEntrada
				+ ", dataAtualizacao=" + dataAtualizacao + ", fomento=" + fomento + ", status=" + status
				+ ", subCampanha=" + subCampanha + ", elegivel=" + elegivel + ", fomentoRealizado=" + fomentoRealizado
				+ ", operacaoEnviada=" + operacaoEnviada + ", respostaRecebida=" + respostaRecebida
				+ ", permissaoRecebida=" + permissaoRecebida + ", ordemExecutada=" + ordemExecutada + "]";
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
		ControleCampanha other = (ControleCampanha) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	public int getCliId() {
		return cliId;
	}
	public void setCliId(int cliId) {
		this.cliId = cliId;
	}
	public int getSubId() {
		return subId;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	}
}
