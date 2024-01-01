package Project.Model.DAO;

import Project.Model.Entity.Veiculo;

public abstract class Node {
	
	private Veiculo valor;
	
	public Node(Veiculo veiculo) {
		valor = veiculo;
	}
	
	public Veiculo Valor() { return valor;}
	public void setValor(Veiculo veiculo) { this.valor = veiculo; }
	
	public long Chave() { return valor.renavam();}
	
}
