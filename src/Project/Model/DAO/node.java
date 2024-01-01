package Project.Model.DAO;

import Project.Model.Entity.Veiculo;

public abstract class node {
	
	protected Veiculo valor;
	protected long chave;
	
	public node(Veiculo veiculo) {
		this.valor = veiculo;
		setChave(valor.renavam());
	}
	
	public Veiculo getValor() { return valor;}
	public void setValor(Veiculo veiculo) { this.valor = veiculo; }
	
	public long getchave() { return chave;}
	public void setChave(long valor) { this.chave = valor; }
	
}
