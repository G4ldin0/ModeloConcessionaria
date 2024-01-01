package Project.Model.DAO;

import java.util.LinkedList;

import Project.Exceptions.KeynotfoundException;
import Project.Model.Entity.Veiculo;

class conteudo{
	public Veiculo valor;
	public long chave;
	
	public conteudo(Veiculo v, long c) { valor = v; chave = c;}
}

public class NodeTableEncadeamentoExterior {

	private LinkedList<conteudo>heap = new LinkedList<conteudo>();

	public NodeTableEncadeamentoExterior() {}

	public NodeTableEncadeamentoExterior(Veiculo valor) {add(valor);}
	
	public int tamanho() { return heap.size(); }
	
	public void add(Veiculo valor) {
		heap.add(new conteudo(valor, valor.renavam()));
	}

	public Veiculo elemento() {
		return heap.getFirst().valor;
	}
	
	public Veiculo elemento(long chave) throws KeynotfoundException {
		try { 
			int index = get(chave);
			conteudo getted = heap.remove(index);
			heap.addFirst(new conteudo(getted.valor, chave));
			return getted.valor;
			}
		catch (KeynotfoundException e) {throw e; }
	}
	
	public void edit(Veiculo valor, long chave) throws KeynotfoundException{
		try {
			int index = get(chave);
			heap.remove(index);
			heap.addFirst(new conteudo(valor, chave));
		} catch (KeynotfoundException e) { throw e; }
	}
	
	public void remove(long chave) throws KeynotfoundException {
		try{
			heap.remove(get(chave)); }
		catch (KeynotfoundException e) { throw e; }
	}
	
	public Veiculo[] getList() {
		Veiculo[] r = new Veiculo[heap.size()];
		for(int i = 0; i < heap.size(); i++) {
			r[i] = heap.get(i).valor;
		}
		return r;
	}
	
	private int get(long chave) throws KeynotfoundException { 
		int index = 0;
		for(conteudo e : heap) {
			if (e.chave != chave) index++;
			else break;
		}
		if (index == heap.size()) throw new KeynotfoundException();
		else return index;
	}
	

}
