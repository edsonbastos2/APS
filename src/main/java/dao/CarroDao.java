package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cadastro.interfaces.CrudDao;
import br.com.cadastro.objeto.Carro;
import conexao.SingleConnection;

public class CarroDao implements CrudDao<Carro> {
	private Connection connection;

	public CarroDao() {
		connection = SingleConnection.getConnection();
	}
	
	@Override
	public void salvar(Carro carro) {

		try {
			String sql = "insert into carro(fabricante,modelo,cor,portas,combustivel,ano) values(?,?,?,?,?,?)";
			PreparedStatement statement;
			if(carro.getIdcarro() == null) {
				statement = connection.prepareStatement(sql);
			}else {
				statement = connection.prepareStatement("update carro set fabricante=?,modelo=?,cor=?,portas=?,combustivel=?,ano=? where idcarro=?");
				statement.setInt(7, carro.getIdcarro());
			}
			
			statement.setString(1, carro.getFabricante());
			statement.setString(2, carro.getModelo());
			statement.setString(3, carro.getCor());
			statement.setString(4, carro.getPortas());
			statement.setString(5, carro.getCombustivel());
			statement.setString(6, carro.getAno());
			/* statement.setInt(7, carro.getId_cliente()); */
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
	public void deletar(Carro carro) {
		try {
			String sql = "delete from carro where idcarro =" + carro.getIdcarro();
			PreparedStatement statement;
			statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Carro> buscar() {
		List<Carro> carros = new ArrayList<Carro>();
		try {
			String sql = "select * from carro";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Carro carro = new Carro();
				carro.setIdcarro(resultSet.getInt("idcarro"));
				carro.setFabricante(resultSet.getString("fabricante"));
				carro.setModelo(resultSet.getString("modelo"));
				carro.setCor(resultSet.getString("cor"));
				carro.setPortas(resultSet.getString("portas"));
				carro.setCombustivel(resultSet.getString("combustivel"));
				carro.setAno(resultSet.getString("ano"));
				carros.add(carro);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return carros;
	}

}
