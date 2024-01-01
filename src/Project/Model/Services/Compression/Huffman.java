package Project.Model.Services.Compression;

import java.util.PriorityQueue;
import java.util.HashMap;

public class Huffman {
	
	private static NodeH construirArvore(String entrada){
		HashMap<Character, Integer> frequencia = new HashMap<Character, Integer>();
		for(char c : entrada.toCharArray())
			frequencia.put(c, frequencia.getOrDefault(c, 0) + 1);
		
		PriorityQueue<NodeH> listaPrioridade = new PriorityQueue<NodeH>();
		
		for(char c : frequencia.keySet())
			listaPrioridade.add(new NodeH(c, frequencia.get(c)));
		
		while (listaPrioridade.size() > 1) {
			NodeH esq = listaPrioridade.poll();
			NodeH dir = listaPrioridade.poll();
			
			listaPrioridade.add(new NodeH('\0', esq.freq + dir.freq, esq, dir));
		}
		
		return listaPrioridade.poll();
	}
	
	public static Package codificar(String texto) {
		
		NodeH no = construirArvore(texto);
		
		StringBuilder codigo = new StringBuilder();
		
		HashMap<Character, String> codigos= new HashMap<Character, String>();

		gerarCodigos(no, "", codigos);
		
		for(char c : texto.toCharArray())
			codigo.append(codigos.get(c));
		
		
		boolean[] retorno = null;
		for(char c: codigo.toString().toCharArray()) {
			if(retorno == null) {
				retorno = new boolean[1];
				retorno[0] = (c == '0' ? false : true);
			}
			else
			{
				boolean[] ph = retorno;
				retorno = new boolean[retorno.length + 1];
				for(int i = 0; i < ph.length; i++)
					retorno[i] = ph[i];
				retorno[retorno.length - 1] = (c == '0' ? false : true);
			}
		}
		
		return new Package(retorno, no);
	}
	
	
	public static String decodificar(Package pacote) {
		
		StringBuilder retorno = new StringBuilder();
		
		NodeH placeholder = pacote.arvore;
		
		for(boolean e : pacote.codigo) {
			if (e)
				placeholder = placeholder.dir;
			else
				placeholder = placeholder.esq;
		
			if(placeholder.c != '\0') {
				retorno.append(placeholder.c);
				placeholder = pacote.arvore;
			}
		}
		
		return retorno.toString();
	}
	
	
	private static void gerarCodigos(NodeH no, String cod, HashMap<Character, String> codigo) {
		if(no == null) return;
		
		if(no.c != '\0') codigo.put(no.c, cod);
		
		gerarCodigos(no.esq, cod + "0", codigo);
		gerarCodigos(no.dir, cod + "1", codigo);
	}
	
	
	public static void main(String[] args) {
		String teste = "abaccda";
//		NodeH arvore = construirArvore(teste);
		
		boolean[] testeCodificado = codificar(teste).codigo;
		
		for(boolean e : testeCodificado)
			System.out.print(e ? "1" : "0");
		
//		String resultado = decodificar(testeCodificado, arvore);
//		System.out.println("\n" + resultado + " aqui ta o resultado");
		
	}
}
