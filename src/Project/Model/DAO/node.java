package Project.Model.DAO;

import Project.Model.Entity.Veiculo;

public abstract class node {
	
	private Veiculo valor;
	
	public node(Veiculo veiculo) {
		valor = veiculo;
	}
	
	public Veiculo Valor() { return valor;}
	public void setValor(Veiculo veiculo) { this.valor = veiculo; }
	
	public long Chave() { return valor.renavam();}
	
}
