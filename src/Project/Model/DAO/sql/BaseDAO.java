package Project.Model.DAO.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Project.Exceptions.DuplicateKeyException;
import Project.Exceptions.KeynotfoundException;
import Project.Exceptions.NoSpaceException;
import Project.Model.DAO.Servidor;
import Project.Model.Entity.Condutor;
import Project.Model.Entity.Veiculo;

public class BaseDAO implements Servidor{
	Connection con;
	
	public BaseDAO() throws SQLException {
		con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb", "postgres", "1605");
		
	}

	@Override
	public void add(Veiculo valor) throws DuplicateKeyException {
		// TODO fornecer uma série de queires para inserir, em ordem: condutor -> vepiculo
		
		try {
			PreparedStatement stmt = con.prepareStatement("INSERT INTO condutores(cpf, nome) VALUES(?,?);");
			stmt.setString(1, valor.condutor().cpf());
			stmt.setString(2, valor.condutor().nome());
			stmt.execute();
			stmt.close();
			stmt = con.prepareStatement("INSERT INTO VALUES(?, ?, ?, ?, ?)");
			stmt.setLong(1, valor.renavam());
			stmt.setString(2, valor.placa());
			stmt.setString(3, valor.condutor().cpf());
			stmt.setString(4, valor.modelo());
			stmt.setInt(5, valor.fabricacao());
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO se for identificado que a chave ja existe, lançar DuplicatedKeyException
			e.printStackTrace();
		}
	}

	@Override
	public Veiculo search(long chave) throws KeynotfoundException {
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT cpf = ? FROM veiculo;");
			stmt.setLong(1, chave);
			ResultSet result = stmt.executeQuery();
			if(result.next()) {
				Veiculo retorno = new Veiculo(chave, result.getString("placa"), new Condutor(result.getString("nome"), result.getString("cpf")), result.getString("modelo"), result.getInt("fabricacao"));
				return retorno;
			}else {
				throw new KeynotfoundException();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void remove(long chave) throws KeynotfoundException {
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("DELETE FROM veiculo WHERE cpf = ?;");
			stmt.setLong(1, chave);
			stmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public String list() {
		PreparedStatement stmt;
		StringBuilder in = new StringBuilder();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM veiculo");
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				in.append(Long.toString(result.getLong("renavam")));
				in.append(" | ");
				in.append(result.getString("placa"));
				in.append(" | ");
				in.append(result.getString("nome"));
				in.append(" | ");
				in.append(result.getString("cpf"));
				in.append(" | ");
				in.append(result.getString("modelo"));
				in.append(" | ");
				in.append(Integer.toString(result.getInt("fabricacao")));
				in.append(" | ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}
}
