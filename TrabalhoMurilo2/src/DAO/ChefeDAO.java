package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entidades.Chefe;
import Conexao.CNXHSQLDB;

public class ChefeDAO {
	
	private final String SQL_INSERIR_CHEFE = "INSERT INTO CHEFE ("+ "NOME, CPF, PIS)" + "VALUES (?, ?, ?)";
	private final String SQL_ALTERAR_CHEFE = "UPDATE CHEFE SET NOME=?, CPF=?, PIS=? WHERE ID=?;";
	private final String SQL_EXCLUIR_CHEFE = "DELETE FROM CHEFE WHERE ID=?";
	private final String SQL_SELECIONAR_CHEFE = "SELECT *FROM CHEFE";
	
private PreparedStatement pst = null;
	
	public boolean inserirChefe(Chefe umChefe) {
		boolean ret = false;
		Connection conn = CNXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_INSERIR_CHEFE);
			pst.setString(1, umChefe.getNome());
			pst.setString(2, umChefe.getCPF());
			pst.setString(3, umChefe.getPIS());
			ret = pst.execute();
			CNXHSQLDB.fecharCNX();
		} catch (SQLException e){
			System.out.println("Erro ao executar o Statement " +e.toString() );
		}
		return ret;
	}
	
	public ArrayList<Chefe> listarChefe(){
		ArrayList<Chefe> listarChefe = new ArrayList<Chefe>();
		Connection conn = CNXHSQLDB.conectar();
		Chefe umChefe;
		try {
			pst = conn.prepareStatement(SQL_SELECIONAR_CHEFE);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				umChefe = new Chefe();
				umChefe.setId(rs.getInt("ID"));
				umChefe.setNome(rs.getString("Nome"));
				umChefe.setCPF(rs.getString("CPF"));
				umChefe.setPIS(rs.getString("PIS"));
				listarChefe.add(umChefe);
			}
			rs.close();
			pst.close();
			CNXHSQLDB.fecharCNX();			
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " +e.toString() );
		}
		return listarChefe();
	}
	
	public boolean alterarChefe(Chefe umChefe) {
		boolean ret = false;
		Connection conn = CNXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_ALTERAR_CHEFE);
			pst.setString(1, umChefe.getNome());
			pst.setString(2, umChefe.getCPF());
			pst.setString(3, umChefe.getPIS());
			ret = pst.execute();
			pst.close();
			CNXHSQLDB.fecharCNX();
		} catch (SQLException e){
			System.out.println("Erro ao executar o Statement " +e.toString() );
		}
		return ret;	
	}
	
	public boolean excluirChefe(Chefe umChefe) {
		boolean ret = false;
		Connection conn = CNXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_EXCLUIR_CHEFE);
			pst.setInt(1, umChefe.getId());
			ret = pst.execute();
			pst.close();
			CNXHSQLDB.fecharCNX();
		} catch (SQLException e){
			System.out.println("Erro ao executar o Statement " +e.toString() );
		}
		return ret;	
	}

}



