package br.com.cadastro.objeto.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.cadastro.objeto.UserModel;
import dao.UserDao;

@ManagedBean
@SessionScoped
public class UserBean extends CrudBean<UserModel, UserDao> {
	
	private UserDao userDao;
	
	
	@Override
	public UserDao getDao() {
		if(userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}

	@Override
	public UserModel criarNovaEntidade() {
		return new UserModel();
	}



}
