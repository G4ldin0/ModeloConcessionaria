package Project.Model.Services;

import Project.Exceptions.DuplicateKeyException;
import Project.Exceptions.KeynotfoundException;
import Project.Exceptions.NoSpaceException;
import Project.Model.DAO.*;
import Project.Model.DAO.HashTable.*;
import Project.Model.Entity.*;
import Project.Model.Services.Compression.Huffman;
import Project.Model.Services.Compression.Package;

public class BO {

	public static Servidor server = new HashTableEnderecamentoAberto(10);
	
	
	public static void cadastrar(Package pacote) throws DuplicateKeyException, NoSpaceException {
		
		String[] valores = Huffman.decodificar(pacote).split("@");
		
		Veiculo entrada = new Veiculo(Long.parseLong(valores[0]), valores[1], new Condutor(valores[2], valores[3]), valores[4], Integer.parseInt(valores[5]));
		server.add(entrada);
	}

	
	
	public static Package buscar(Package pacote) throws KeynotfoundException
	{
		String[] valores = Huffman.decodificar(pacote).split("@");
		long renavam = Long.parseLong(valores[0]);
		String placa = valores[1];
		
		Veiculo retorno = server.search(renavam);
		if(retorno.placa().equals(placa)) {
			return Huffman.codificar(retorno.info());
		}
		else
			throw new KeynotfoundException();
	}
	
	
	public static Package listar() { 
		return Huffman.codificar(server.list()); 
		}


	public static void remover(Package pacote) throws KeynotfoundException { 
		String[] valores = Huffman.decodificar(pacote).split("@");
		
		Veiculo carro = new Veiculo(Long.parseLong(valores[0]), valores[1], new Condutor(valores[2], valores[3]), valores[4], Integer.parseInt(valores[5]));
		server.remove(carro.renavam());

	}
	
	
	public static void alterar(Package pacote) throws KeynotfoundException
	{
		String[] valores = Huffman.decodificar(pacote).split("@");
		Veiculo veiculo = new Veiculo(Long.parseLong(valores[0]), valores[1], new Condutor(valores[2], valores[3]), valores[4], Integer.parseInt(valores[5]));
		
		Veiculo found = server.search(veiculo.renavam());
		found.condutor().setNome(veiculo.condutor().nome());
		found.condutor().setCpf(veiculo.condutor().cpf());
		found.setModelo(veiculo.modelo());
		found.setFabricacao(veiculo.fabricacao());
		
	}
}
