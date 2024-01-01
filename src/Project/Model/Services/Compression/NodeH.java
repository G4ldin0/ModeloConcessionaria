package Project.Model.Services.Compression;

public class NodeH implements Comparable<NodeH> {
	int freq;
	char c;
	NodeH esq;
	NodeH dir;
	boolean[] cod;
	
	public NodeH(char c, int freq) { this.c = c; this.freq = freq;}
	public NodeH(char c, int freq, NodeH esq, NodeH dir) { this.c = c; this.freq = freq; this.esq= esq; this.dir = dir; }
	
	public boolean eFolha() {return (esq == null && dir == null ? true : false); }
	
	public int compareTo(NodeH no) { 
		return this.freq - no.freq; 
	}
	
	public String string() {
		String retorno = "";
		if(esq != null) retorno += esq.string();
		if(dir != null) retorno += dir.string();
		if(esq == null && dir == null) retorno += c;
		return retorno;
	}
	
	public void codigo(boolean valor) {
		if(esq != null) esq.codigo(valor);
		if(dir != null) dir.codigo(valor);
		
		if(cod == null) {
			cod = new boolean[1];
			cod[0] = valor;
		}
		else
		{
			boolean[] ph = cod;
			cod = new boolean[cod.length + 1];
			for(int i = 0; i < ph.length; i++)
				cod[i] = ph[i];
			cod[cod.length - 1] = valor;
		}
	}
}

