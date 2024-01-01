package Project.Model.DAO.ArvoreAVL;

import Project.Model.DAO.node;
import Project.Model.Entity.*;

public class NodeArvore extends node{
	
	public int fb;
	public int altura;
	public NodeArvore pai;
	public NodeArvore esquerda;
	public NodeArvore direita;
	
	public NodeArvore(Veiculo Valor)
	{
		super(Valor);
		fb = 0;
		altura = 0;
	}
}
