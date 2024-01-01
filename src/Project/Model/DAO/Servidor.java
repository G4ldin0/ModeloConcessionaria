package Project.Model.DAO;

import Project.Exceptions.DuplicatedKeyException;
import Project.Exceptions.KeynotfoundException;
import Project.Model.Entity.Veiculo;

public interface Servidor {

	public void add(Veiculo valor) throws DuplicatedKeyException;
	
	public Veiculo search(long chave) throws KeynotfoundException;
	
	public void remove(long chave) throws KeynotfoundException;
	
	public String list();
	
}
