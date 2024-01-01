package Project.Model.DAO;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import Project.Exceptions.DuplicatedKeyException;
import Project.Exceptions.KeynotfoundException;
import Project.Model.Entity.Veiculo;

public class HashTable{

	public int tamanho;
	public NodeTabela tabela[];
	private int chavesUsadas;
	
	private boolean enderacamentoAberto = false;
	
	private FileWriter fw;
	
	public HashTable(int tam) {
		//decidir o tamanho da tabela
		int i = tam;
		int j = 1;
		int diferenca = Math.abs(tam - j);
		while(Math.abs(tam - (j*2)) < diferenca) {
			j *= 2;
			diferenca = Math.abs(tam - j);
			i = j;
		}
		boolean primo = false;
		for(; !primo; i--) {
			primo = primo(i);
		}
		i++;
		tamanho = i;
		tabela = new NodeTabela[tamanho];
		
		//abrir o arquivo de log
		try {
			fw = new FileWriter("./log.txt");
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
	private float fatorCarga() { return chavesUsadas/tamanho;}
	
	
	public void add(long chave, Veiculo valor) throws DuplicatedKeyException {
		// TODO Auto-generated method stub
		int hash = h(chave);
		
		//não possui tratamento de colisões ainda
		tabela[hash] = new NodeTabela(valor);
		chavesUsadas++;
		try {
			fw.append("log=" + LocalDateTime.now() + " fator:" + chavesUsadas + "/" + tamanho + ":" + fatorCarga() + " add:" + hash +"\n");
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addColisaoEncadeada(long chave, NodeTabela valor) {
		int hash = h(chave);
		if(tabela[hash] != null) {
			NodeTabela proximo = tabela[hash];
			while(proximo != null) {
				proximo = proximo.proximo();
			}
			proximo = valor;
		}
	}
	
	private void addEnderecamentoAberto(long chave, NodeTabela valor) {
		int hash = h(chave);
		NodeTabela proximo = tabela[hash];
		int k = 0;
		int novoHash = hash;
		while(proximo != null) {
			novoHash = (h(chave) + (2 * k) + (1 * k*k)) % tamanho;
			k++;
			proximo = tabela[novoHash];
		}
		tabela[novoHash] = valor;
	}
	
	
	public NodeTabela search(long chave) throws KeynotfoundException {
		int hash = h(chave);
		//desconsiderando endereçamento aberto
		NodeTabela result = tabela[hash];
		if (result == null) throw new KeynotfoundException();
		else return result;
	}
	
	private NodeTabela searchEnderecamentoAberto(long chave) {
		NodeTabela result = tabela[h(chave)];
		
		while(result.getValor().renavam() != chave) {
			result = result.proximo();
		}
		
		return result;
	}
	
	private NodeTabela searchEncadeado(long chave) {
		int hash = h(chave);
		int k = 0;
		NodeTabela result = tabela[hash];
		
		while(result.getValor().renavam() != chave) {
			k++;
			hash = (h(chave) + (2 * k) + (1 * k*k)) % tamanho;
			result = tabela[hash];
		}
		
		return result;
	}

	
	public void remove(long chave) throws KeynotfoundException {
		int hash = h(chave);
		
		//desconsiderando endereçamento aberto
		if (tabela[hash] == null) throw new KeynotfoundException();
		else {
			tabela[hash] = null;
			chavesUsadas--;
			
			try {
				fw.append("log=" + LocalDateTime.now() + " fator:" + chavesUsadas + "/" + tamanho + ":" + fatorCarga() + " remove:" + hash +"\n");
				fw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void removeEnderecamentoAberto(long chave) {
		
		NodeTabela result = tabela[h(chave)];

		while(result.proximo().getValor().renavam() != chave) {
			result = result.proximo();
		}
		
		//result.proximo = result.proximo().proximo();
		
	}
	
	
	public String list() {
		// TODO Auto-generated method stub
		String retorno = "";
		
		for(int i = 0; i < tamanho; i++) {
			NodeTabela elemento = tabela[i];
			if(elemento != null) {
				retorno += elemento.getValor().toString();
			}
		}
		
		return retorno;
	}

}
