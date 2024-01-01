package Project.Model.DAO;

import java.util.LinkedList;

import Project.Exceptions.KeynotfoundException;
import Project.Model.DAO.HashTable.NodeTabela;
import Project.Model.Entity.Veiculo;


public class HeapNodes {

	private LinkedList<NodeTabela>heap = new LinkedList<NodeTabela>();

	public HeapNodes() {}

	public HeapNodes(Veiculo valor) {add(valor);}
	
	public int tamanho() { return heap.size(); }
	
	public void add(Veiculo valor) {
		heap.add(new NodeTabela(valor));
	}

	public Veiculo elemento() {
		return heap.getFirst().Valor();
	}
	
	public Veiculo elemento(long chave) throws KeynotfoundException {
		try { 
			int index = get(chave);
			NodeTabela got = heap.remove(index);
			heap.addFirst(got);
			return got.Valor();
			}
		catch (KeynotfoundException e) {throw e; }
	}
	
	public void edit(Veiculo valor) throws KeynotfoundException{
		try {
			int index = get(valor.renavam());
			heap.remove(index);
			heap.addFirst(new NodeTabela(valor));
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
			r[i] = heap.get(i).Valor();
		}
		return r;
	}
	
	private int get(long chave) throws KeynotfoundException { 
		int index = 0;
		for(NodeTabela e : heap) {
			if (e.Chave() != chave) index++;
			else break;
		}
		if (index == heap.size()) throw new KeynotfoundException();
		else return index;
	}
	

}
