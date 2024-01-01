package Project.Model.DAO.HashTable;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import Project.Exceptions.DuplicateKeyException;
import Project.Exceptions.KeynotfoundException;
import Project.Exceptions.NoSpaceException;
import Project.Model.DAO.Servidor;
import Project.Model.Entity.Veiculo;

public class HashTableEnderecamentoAberto implements Servidor{

	private int tamanho;
	private NodeTabela tabela[];
	private int chavesUsadas;
	
	
	
	private FileWriter fw;
	
	public int tamanho() {return tamanho; }
	
	public HashTableEnderecamentoAberto(int tam) {
		
		int i = tam;
		int j = 1;
		int diferenca = Math.abs(tam - j);
		while(Math.abs(tam - (j*2)) < diferenca) {
			j *= 2;
			diferenca = Math.abs(tam - j);
			i = j;
		}
		boolean primo = false;
		for(; !primo; i--) {primo = primo(i); }
		
		i++;
		tamanho = i;
		tabela = new NodeTabela[tamanho];
		
		
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
	
	
	public void add(Veiculo valor) throws NoSpaceException, DuplicateKeyException {
		NodeTabela node = new NodeTabela(valor);
		int k = 0;
		int hash = h(node.Chave() + k);
		
		
		NodeTabela target = tabela[hash];
		while(target != null && k < tamanho -1) {
			if(target.Chave() == valor.renavam())
				throw new DuplicateKeyException();
			else {
				k++;
				hash = h(node.Chave() + k);
				target = tabela[hash];
			}
		}
		
		if (k == tamanho -1) throw new NoSpaceException();
		else { tabela[hash] = node; chavesUsadas++; }
		
		
		try {
			fw.append("log=" + LocalDateTime.now() + " fator:" + chavesUsadas + "/" + tamanho + ":" + fatorCarga() + " add:" + hash +"\n");
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Veiculo search(long chave) throws KeynotfoundException {
		int k = 0;
		int hash = h(chave + k);
//		System.out.println(hash);
		NodeTabela target = tabela[hash];
		while(k < tamanho -1) {
			if(target != null) {
				if(target.Chave() != chave) {
					k++;
					hash = h(chave + k);
					target = tabela[hash];
				} else {
					return target.Valor();
				}
			}
		}
		
		throw new KeynotfoundException();
		
		
	}
	
	
	public void remove(long chave) throws KeynotfoundException {
		int k = 0;
		int hash = h(chave + k);
		
		NodeTabela target = tabela[hash];
		
		while(k < tamanho -1) {
			if(target != null) {
				if(target.Chave() != chave) {
					k++;
					hash = h(chave + k);
					target = tabela[hash];
				} else {
					break;
				}
			}
		}
		
		if (k == tamanho) throw new KeynotfoundException();
		tabela[hash] = null;
		chavesUsadas--;
		

		try {
			fw.append("log=" + LocalDateTime.now() + " fator:" + chavesUsadas + "/" + tamanho + ":" + fatorCarga() + " remove:" + h(chave) +"\n");
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
	}
	
	
	public String list() {
		String retorno = "";
		
		for(int i = 0; i < tamanho; i++) {
			NodeTabela elemento = tabela[i];
			if(elemento != null) {
				retorno += elemento.Valor().toString();
			} 
			//else { retorno += '\n'; }
		}
		
		return retorno;
	}
	
	
}
