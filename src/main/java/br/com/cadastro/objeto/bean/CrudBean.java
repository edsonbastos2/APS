package br.com.cadastro.objeto.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.cadastro.interfaces.CrudDao;

public abstract class CrudBean<E, D extends CrudDao> {
	private String estadoTela = "buscar";
	
	private E entidade;
	private List<E> entidades;
	
	public void novo() {
		entidade = criarNovaEntidade();
		mudarParaInseri();
	}
	public void salvar() {
		try {
			getDao().salvar(entidade);
			entidade = criarNovaEntidade();
			adicionarMensagem("Salvo com sucesso", FacesMessage.SEVERITY_INFO);
			mudarParaBusca();
		}catch(Exception e) {
			adicionarMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	public void editar(E entidade) {
		this.entidade = entidade;
		mudarParaEdita();
	}
	public void deletar(E entidade) {
		try {
			getDao().deletar(entidade);
			entidades.remove(entidade);
			adicionarMensagem("Deletado com sucesso", FacesMessage.SEVERITY_INFO);
		}catch( Exception ex) {
			adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	public void buscar() {
		if(!isBusca()) {
			mudarParaBusca();
			return;
		}
		try {
			entidades = getDao().buscar();
			if(entidades == null || entidades.size() < 1) {
				adicionarMensagem("Não temos nada cadastrado", FacesMessage.SEVERITY_WARN);
			}
			adicionarMensagem("Deletado com sucesso", FacesMessage.SEVERITY_INFO);
		}catch (Exception e) {
			adicionarMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro) {
		FacesMessage message = new FacesMessage();
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	
	public E getEntidade() {
		return entidade;
	}
	
	public void setEntidade(E entidade) {
		this.entidade = entidade;
	}
	
	public List<E> getEntidades() {
		return entidades;
	}
	
	public void setEntidades(List<E> entidades) {
		this.entidades = entidades;
	}
	
	
	//Responsavel por criar os metodos nas classes bean
	public abstract D getDao();
	public abstract E criarNovaEntidade();
	
	// Metodos para controle da tela
	public boolean isInseri() {
		return "inserir".equals(estadoTela);
	}
	
	public boolean isEdita() {
		return "editar".equals(estadoTela);
	}
	
	public boolean isBusca() {
		return "buscar".equals(estadoTela);
	}
	
	public void mudarParaInseri() {
		estadoTela = "inserir";
	}
	
	public void mudarParaEdita() {
		estadoTela = "editar";
	}
	
	public void mudarParaBusca() {
		estadoTela = "buscar";
	}
}

