package br.com.cadastro.interfaces;

import java.util.List;

public interface CrudDao<E> {
	public void salvar(E entidade);
	public void deletar(E entidade);
	public List<E> buscar();
}
