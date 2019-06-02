package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cadastro.interfaces.CrudDao;
import br.com.cadastro.objeto.UserModel;
import conexao.SingleConnection;

public class UserDao implements CrudDao<UserModel> {
	
	private Connection connection;
	
	public UserDao() {
		connection = SingleConnection.getConnection();
	}
	
	@Override
	public void salvar(UserModel userModel) {

		try {
			String sql = "insert into cliente(nome,sexo,email,cpf) values(?,?,?,?)";
			PreparedStatement statement;
			if(userModel.getIdcliente() == null) {
				statement = connection.prepareStatement(sql);
			}else {
				statement = connection.prepareStatement("update cliente set nome=?,sexo=?,email=?,cpf=?, where idcliente=?");
				statement.setInt(5, userModel.getIdcliente());
			}
			
			statement.setString(1, userModel.getNome());
			statement.setString(2, userModel.getSexo());
			statement.setString(3, userModel.getEmail());
			statement.setString(4, userModel.getCpf());
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	@Override
	public void deletar(UserModel userModel) {
		try {
			String sql = "delete from cliente where idcliente =" + userModel.getIdcliente();
			PreparedStatement statement;
			statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<UserModel> buscar() {
		List<UserModel> usuarios = new ArrayList<UserModel>();
		try {
			String sql = "select * from cliente";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				UserModel usuario = new UserModel();
				usuario.setId(resultSet.getInt("idcliente"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setSexo(resultSet.getString("sexo"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setCpf(resultSet.getString("cpf"));
				usuarios.add(usuario);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}
}
