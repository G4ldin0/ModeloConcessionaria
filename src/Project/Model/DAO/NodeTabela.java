package Project.Model.DAO;

import Project.Model.Entity.Veiculo;

public class NodeTabela{

	private Veiculo valor;
	private long chave;
	private NodeTabela proximo;
	
	
	public NodeTabela(Veiculo Valor) {
		valor = Valor;
		chave = valor.renavam();
	}

	public void add(NodeTabela node) { proximo = node; }
	
	public NodeTabela proximo() { return proximo; }
	
	public long getChave() { return chave; }

	public Veiculo getValor() {	return this.valor;}
}
