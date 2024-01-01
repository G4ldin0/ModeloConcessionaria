package Project.Model.DAO;

import Project.Model.Entity.Veiculo;

public class NodeTableEnderecamentoAberto {
	private Veiculo valor;
	private long chave;
	
	public NodeTableEnderecamentoAberto(Veiculo Valor) {
		valor = Valor;
		chave = valor.renavam();
	}
	
	public long getChave() { return chave; }

	public Veiculo valor() { return this.valor;}

	public void setValor(Veiculo veiculo) { valor = veiculo; }
	
	
}
