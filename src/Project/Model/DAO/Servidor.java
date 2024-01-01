package Project.Model.DAO;

import Project.Exceptions.*;
import Project.Model.Entity.Veiculo;

public interface Servidor {

	public void add(Veiculo valor) throws DuplicateKeyException, NoSpaceException;
	
	public Veiculo search(long chave) throws KeynotfoundException;
	
	public void remove(long chave) throws KeynotfoundException;
	
	public String list();
	
}
