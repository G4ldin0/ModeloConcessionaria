package Project.Model.DAO;

import Project.Model.Entity.Veiculo;

public abstract class node {
	
	protected Veiculo valor;
	protected long chave;
	
	public node(Veiculo veiculo) {
		this.valor = veiculo;
		setChave(valor.renavam());
	}
	
	public Veiculo Valor() { return valor;}
	public void setValor(Veiculo veiculo) { this.valor = veiculo; }
	
	public long Chave() { return chave;}
	public void setChave(long valor) { this.chave = valor; }
	
}
