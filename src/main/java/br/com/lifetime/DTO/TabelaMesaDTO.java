package br.com.lifetime.DTO;

import java.io.Serializable;

/**
 * 
 * @author pedro.silva
 * classe basica para Tabela web
 *
 */
public class TabelaMesaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean elegivel;
	private String fomento;
	private boolean fomentoRealizado;
	private boolean operacaoEnviada;
	private boolean permissaoRecebida;
	private boolean ordemExecutada;
	private double qtdeValor;
	private Integer id;
	private String nmCliente;
	private String perfilXp;
	private String nmAai;
	private String nmEquipe;

	public TabelaMesaDTO() {

	}
	

	public TabelaMesaDTO(boolean elegivel, String fomento, boolean fomentoRealizado, boolean operacaoEnviada,
			boolean permissaoRecebida, boolean ordemExecutada, double qtdeValor, Integer id, String nmCliente,
			String perfilXp, String nmAai, String nmEquipe) {
		super();
		this.elegivel = elegivel;
		this.fomento = fomento;
		this.fomentoRealizado = fomentoRealizado;
		this.operacaoEnviada = operacaoEnviada;
		this.permissaoRecebida = permissaoRecebida;
		this.ordemExecutada = ordemExecutada;
		this.qtdeValor = qtdeValor;
		this.id = id;
		this.nmCliente = nmCliente;
		this.perfilXp = perfilXp;
		this.nmAai = nmAai;
		this.nmEquipe = nmEquipe;
	}

	public boolean isElegivel() {
		return elegivel;
	}

	public void setElegivel(boolean elegivel) {
		this.elegivel = elegivel;
	}

	public String getFomento() {
		return fomento;
	}

	public void setFomento(String fomento) {
		this.fomento = fomento;
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

	public void setOperacaoEnviada(boolean operacaoEnviada) {
		this.operacaoEnviada = operacaoEnviada;
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

	public double getQtdeValor() {
		return qtdeValor;
	}

	public void setQtdeValor(double qtdeValor) {
		this.qtdeValor = qtdeValor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNmCliente() {
		return nmCliente;
	}

	public void setNmCliente(String nmCliente) {
		this.nmCliente = nmCliente;
	}

	public String getPerfilXp() {
		return perfilXp;
	}

	public void setPerfilXp(String perfilXp) {
		this.perfilXp = perfilXp;
	}

	public String getNmAai() {
		return nmAai;
	}

	public void setNmAai(String nmAai) {
		this.nmAai = nmAai;
	}

	public String getNmEquipe() {
		return nmEquipe;
	}

	public void setNmEquipe(String nmEquipe) {
		this.nmEquipe = nmEquipe;
	}
	
}
