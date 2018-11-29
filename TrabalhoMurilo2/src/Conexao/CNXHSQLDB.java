package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CNXHSQLDB {
	
	private static final String DRIVER_CLASS = "org.hsqldb.jdbcDriver";
	private static Connection cnx = null;
	private static String usuario = "SA";
	private static String senha = "";
	private static String PathBase = "C:\\Users\\Murilo\\Desktop\\Eclipse x64 Portable\\Eclipse x64 Portable\\Data\\workspace\\TrabalhoMurilo2\\Banco\\Chefe";
	private static String URL = "jdbc:hsqldb:file:" + PathBase + ";shutdown=true;hsqldb.write_delay=false; ";
	
	public static Connection conectar() {
		if(cnx == null) {
			
			try {
				Class.forName(DRIVER_CLASS);
				// Estabelecendo conex�o
					cnx = DriverManager.getConnection(URL, usuario, senha);
			} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return cnx;
	}
	
	public static void fecharCNX() {
		try {
			cnx.close();
			cnx = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}
