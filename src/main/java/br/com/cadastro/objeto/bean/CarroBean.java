package br.com.cadastro.objeto.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.cadastro.objeto.Carro;
import dao.CarroDao;

@ManagedBean
@SessionScoped
public class CarroBean extends CrudBean<Carro, CarroDao>{
	
	private CarroDao carroDao;
	
	@Override
	public CarroDao getDao() {
		if(carroDao == null) {
			carroDao = new CarroDao();
		}
		return carroDao;
	}

	@Override
	public Carro criarNovaEntidade() {
		
		return new Carro();
	}


	
	
}
