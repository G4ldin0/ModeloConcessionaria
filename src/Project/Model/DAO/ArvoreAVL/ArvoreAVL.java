package Project.Model.DAO.ArvoreAVL;

import Project.Exceptions.DuplicateKeyException;
import Project.Exceptions.KeynotfoundException;
import Project.Model.DAO.Servidor;
import Project.Model.Entity.Veiculo;

public class ArvoreAVL implements Servidor{
	 NodeArvore raiz;
	
	//-----------------------------------------------------------------------------------------//
	
	private int altura(NodeArvore no)
	{
		if(no == null)
			return -1;
		else
			return no.altura;
	}
	
	private  int fb(NodeArvore no)
	{
		if(no == null) return 0;
		
		return altura(no.direita) - altura(no.esquerda);
	}
	
	//-----------------------------------------------------------------------------------------//
	
	public void add(Veiculo valor) throws DuplicateKeyException {
		try{
			raiz = add(raiz, new NodeArvore(valor)); 
		} catch(DuplicateKeyException e ) {
			throw e;
		}
	}
	
	private  NodeArvore add(NodeArvore origem, NodeArvore no) throws DuplicateKeyException
	{
		if(origem == null) { return no; }
		else if(no.Chave() == origem.Chave()) { throw new DuplicateKeyException();}
		else if(no.Chave() < origem.Chave()) { origem.esquerda = add(origem.esquerda, no); }
		else if(no.Chave() > origem.Chave()) { origem.direita = add(origem.direita, no); }

		origem.altura = 1 + Math.max(altura(origem.esquerda), altura(origem.direita));
		int fb = fb(origem); origem.fb = fb;
		int fbEsq = fb(origem.esquerda);
		int fbDir = fb(origem.direita);
		
		if(fb > 1 && fbDir > 0) return rotLeft(origem);
		if(fb > 1 && fbDir < 0) {origem.direita = rotRight(origem.direita); return rotLeft(origem); }
		if(fb < -1 && fbEsq < 0) return rotRight(origem);
		if(fb < -1 && fbEsq > 0) {origem.esquerda = rotLeft(origem.esquerda); return rotRight(origem); }
		
		
		
		return origem;
	}
	
	private  NodeArvore rotLeft(NodeArvore no)
	{
		NodeArvore novo = no.direita;
		novo.esquerda = no; novo.fb--;
		
		no.direita = null; no.fb--; no.fb--;
		
		return novo;
	}
	private  NodeArvore rotRight(NodeArvore no)
	{
		NodeArvore novo = no.esquerda;
		novo.direita = no; novo.fb++;
		
		no.esquerda = null; no.fb++; no.fb++;
		
		
		return novo;
	}
	
	//--------------------------------------------------------------------------------------//
	
	public  Veiculo search(long chave) throws KeynotfoundException
	{
		try {
			return search(raiz, chave).Valor();
		} catch (KeynotfoundException e) {
			throw e;
		}
	}
	private  NodeArvore search(NodeArvore no, long chave) throws KeynotfoundException
	{
		if(chave == no.Chave())
			return no;
		else
		{
			if(chave < no.Chave())
			{
				if(no.esquerda != null)
					return search(no.esquerda, chave);
				else
					throw new KeynotfoundException();
			} else
			{
				if(chave >  no.Chave())
					return search(no.direita, chave);
				else
					throw new KeynotfoundException();
			}
		}
	}
	
	//-------------------------------------------------------------------------------------//

	public void remove(long chave) throws KeynotfoundException{ 
		try { raiz = remove(raiz, chave); }
		catch (KeynotfoundException e) { throw e; }
	}
	
	private  NodeArvore remove(NodeArvore no, long chave) throws KeynotfoundException
	{
		
		if(no == null) throw new KeynotfoundException();
		
		if(no.Chave() > chave)
			{ no.esquerda = remove(no.esquerda, chave); }
		else if(no.Chave() < chave)
			{ no.direita = remove(no.direita, chave); }
		else if(no.Chave() == chave)
		{
			if(no.esquerda == null && no.direita == null) { no = null; return no; }
			else if(no.esquerda == null) { no = no.direita; }
			else if(no.direita == null) { no = no.esquerda; }
			else
			{
				
				NodeArvore temp;
				
				if(no.Chave() > raiz.Chave())
				{
					temp = no.esquerda;
					while(temp.direita != null) { temp = temp.direita; }
					
					no.setValor(temp.Valor());
					
					no.esquerda = remove(no.esquerda, temp.Chave());
				}
				else
				{
					temp = no.direita;
					while(temp.esquerda != null) { temp = temp.esquerda; }
					
					no.setValor(temp.Valor());
					
					no.direita = remove(no.direita, temp.Chave());
				}

				return no;
			}
		}
		
		no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));
		int fb = fb(no); no.fb = fb;
		int fbEsq = fb(no.esquerda);
		int fbDir = fb(no.direita);
		
		if(fb > 1 && fbDir > 0) return rotLeft(no);
		if(fb > 1 && fbDir < 0) {no.direita = rotRight(no.direita); return rotLeft(no); }
		if(fb < -1 && fbEsq < 0) return rotRight(no);
		if(fb < -1 && fbEsq > 0) {no.esquerda = rotLeft(no.esquerda); return rotRight(no); }
		
		
		
		return no;
	}
	
	//-------------------------------------------------------------------------------------//
	
	public String list()
	{
		if(raiz != null)
			return list(raiz);
		else
			return "sistema vazio.";
	}
	
	private  String list(NodeArvore no)
	{
		String ordem = new String();
		
		if(no.esquerda != null) ordem += list(no.esquerda);
		if(no != null) ordem += no.Valor().toString();
		if(no.direita != null) ordem += list(no.direita);
	
		return ordem;
	}

	
}
