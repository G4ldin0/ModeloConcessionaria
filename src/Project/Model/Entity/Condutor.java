package Project.Model.Entity;

public class Condutor {
	String nome;
	String cpf;
	
	public Condutor(String Nome, String Cpf)
	{
		nome = Nome;
		cpf = Cpf;
	}
	
	public String nome() { return nome; }
	public void setNome(String nome) {
		if(nome != null) {
		this.nome = nome;
		}
		else
			throw new NullPointerException("valor vazio");
	}

	public String cpf() { return cpf; }
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
