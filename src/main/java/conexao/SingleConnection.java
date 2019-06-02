package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.cadastro.objeto.erros.ErroSistema;

public class SingleConnection {
	private static String url = "jdbc:mysql://localhost:3306/locadora";
	private static String user = "root";
	private static String password = "Admin";
	private static Connection connection = null;
	
	
	static {
		try {
			conectar();
		} catch (ErroSistema e) {
			e.printStackTrace();
		}
	}
	
	public SingleConnection() throws ErroSistema {
		conectar();
	}
	
	public static void  conectar() throws ErroSistema{
		
		try {
			if(connection == null) {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("Conectou com sucesso!");
			}
			
		} catch (Exception e) {
			throw new ErroSistema("Não foi possivel conectar no banco!", e);
			
		}
		
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	public static void fecharConexao() {
		if(connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
