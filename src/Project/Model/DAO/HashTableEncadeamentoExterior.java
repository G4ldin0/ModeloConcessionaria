package Project.Model.DAO;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import Project.Exceptions.KeynotfoundException;
import Project.Model.Entity.Condutor;
import Project.Model.Entity.Veiculo;

public class HashTableEncadeamentoExterior {
	
	private int tamanho;
	private NodeTableEncadeamentoExterior tabela[];
	private int chavesUsadas;
	
	private FileWriter fw;
	
	public int tamanho() {return tamanho; }
	
	public HashTableEncadeamentoExterior(int tam) {
		int i = tam;
		int j = 1;
		int diferenca = Math.abs(tam - j);
		while(Math.abs(tam - (j*2)) < diferenca) {
			j *= 2;
			diferenca = Math.abs(tam - j);
			i = j;
		}
		boolean primo = false;
		for(; !primo; i--) { primo = primo(i); }
	
		i++;
		tamanho = i;
		tabela = new NodeTableEncadeamentoExterior[tamanho];
		
		
		try {
			fw = new FileWriter("./log.txt", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean primo(int n) {
		boolean primo = true;
		for(int i = 2; i < n && primo; i++) {
			if(n % i == 0) primo = false;
		}
		return primo;
	}
	
	private int h(long x) { return (int) (x % (long)tamanho); }
	private float fatorCarga() { return (float)chavesUsadas/(float)tamanho; }
	
	
	public void add(Veiculo valor)
	{
		int hash = h(valor.renavam());
		//michael jackson nao morreu ele so fez uma viagem na mente deixou lembraça no coração deixou saudades
		
		NodeTableEncadeamentoExterior pos = tabela[hash];
		if(pos != null) 
			pos.add(valor);
		else {
			tabela[hash] = new NodeTableEncadeamentoExterior(valor);
			chavesUsadas++;
		}
		
		log(hash, "Add");
	}
	
	public void edit(Veiculo valor) throws KeynotfoundException {
		NodeTableEncadeamentoExterior ph = tabela[h(valor.renavam())];
		ph.edit(valor, valor.renavam());
	}

	
	public Veiculo search(long chave) throws KeynotfoundException
	{
		NodeTableEncadeamentoExterior ph = tabela[h(chave)];
		
		try {
			return ph.elemento(chave);
		} catch (KeynotfoundException e) {
			throw e;
		} catch (NullPointerException e) {
			throw new KeynotfoundException();
		}
	}
	
	public void remove(long chave) throws KeynotfoundException
	{
		int index = h(chave);
		NodeTableEncadeamentoExterior result = tabela[index];
		NodeTableEncadeamentoExterior anterior = null;

		try{
			result.remove(chave);
			if(result.tamanho() == 0) chavesUsadas--;
		} catch ( KeynotfoundException e) { throw e; }
		
		log(chave, "Remove");
		
	}
	
	
	public String list() {
		String retorno = "";
		
		for(NodeTableEncadeamentoExterior e : tabela) {
			if(e != null) {
				Veiculo[] lista = e.getList();
				for(Veiculo f : lista)
					retorno += f.toString();
			}
		}
		
		return retorno;
	}
	
	
	private void log(long chave, String namespace) {
		try {
			fw.append("log=" + LocalDateTime.now() 
					+ " fator:" + chavesUsadas + "/" 
					+ tamanho + ":" + fatorCarga() + " " 
					+ namespace +":" + h(chave) +"\n");
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}
	
}
