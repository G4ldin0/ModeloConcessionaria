package Project.API;

import Project.Exceptions.KeynotfoundException;
import Project.Model.Entity.*;
import Project.Model.Services.*;
import Project.Model.Services.Compression.Huffman;
import Project.Model.Services.Compression.Package;


public class Conection 
{
	
	public Conection() {
        String[] nomes = {"Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Hannah", "Isaac", "Jasmine",
                "Kate", "Liam", "Mia", "Noah", "Olivia", "James", "Sophia", "Benjamin", "Emma", "William",
                "Ava", "Lucas", "Luna", "Henry", "Ella", "Daniel", "Mila", "Jack", "Sophie", "Leo",
                "Nora", "Matthew", "Lily", "Ethan", "Chloe", "Oliver", "Zoe", "Mason", "Aria", "Liam", "Evelyn",
                "Henry", "Victoria", "William", "Scarlett", "James", "Sophia", "Liam", "Ella", "Oliver"};

		String[] cpfs = {"123.456.789-01", "234.567.890-12", "345.678.901-23", "456.789.012-34", "567.890.123-45",
		               "678.901.234-56", "789.012.345-67", "890.123.456-78", "901.234.567-89", "112.233.445-56",
		               "112.233.445-57", "112.233.445-58", "112.233.445-59", "112.233.445-60", "112.233.445-61",
		               "112.233.445-62", "112.233.445-63", "112.233.445-64", "112.233.445-65", "112.233.445-66",
		               "112.233.445-67", "112.233.445-68", "112.233.445-69", "112.233.445-70", "112.233.445-71",
		               "112.233.445-72", "112.233.445-73", "112.233.445-74", "112.233.445-75", "112.233.445-76",
		               "112.233.445-77", "112.233.445-78", "112.233.445-79", "112.233.445-80", "112.233.445-81",
		               "112.233.445-82", "112.233.445-83", "112.233.445-84", "112.233.445-85", "112.233.445-86",
		               "112.233.445-87", "112.233.445-88", "112.233.445-89", "112.233.445-90", "112.233.445-91",
		               "112.233.445-92", "112.233.445-93", "112.233.445-94", "112.233.445-95", "112.233.445-96"};
		
		String[] placas = {"ABC1234", "DEF5678", "GHI9012", "JKL3456", "MNO7890", "PQR1234", "STU5678", "VWX9012", "YZA3456", "BCCD123",
		                "BCCE123", "BCCF123", "BCCG123", "BCCH123", "BCCI123", "BCCJ123", "BCCK123", "BCCL123", "BCCM123", "BCCN123",
		                "BCCO123", "BCCP123", "BCCQ123", "BCCR123", "BCCS123", "BCCT123", "BCCU123", "BCCV123", "BCCW123", "BCCX123",
		                "BCCY123", "BCCZ123", "BCDA123", "BCDB123", "BCCC123", "BCDD123", "BCDE123", "BCDF123", "BCCG123", "BCDH123",
		                "BCDI123", "BCDJ123", "BCCZ123", "BCDL123", "BCDM123", "BCDN123", "BCDO123", "BCDP123", "BCCQ123", "BCDR123"};
		
		String[] modelos = {"Fiesta", "Corolla", "Civic", "Gol", "Focus", "Fusion", "Astra", "Onix", "Cruze", "HB20",
		                  "Civic", "Gol", "Fusion", "Corolla", "Onix", "Focus", "Fiesta", "Astra", "Cruze", "HB20",
		                  "Gol", "Fusion", "Civic", "Corolla", "Onix", "Focus", "Fiesta", "Astra", "Cruze", "HB20",
		                  "Gol", "Fusion", "Civic", "Corolla", "Onix", "Focus", "Fiesta", "Astra", "Cruze", "HB20",
		                  "Jetta", "Mazda3", "Ranger", "F-150", "Mustang", "Civic", "Model S", "Bronco", "Camry", "Cruze"};
		
		int[] anos = {2020, 2019, 2018, 2020, 2017, 2022, 2019, 2021, 2016, 2022,
		           2017, 2021, 2018, 2020, 2019, 2021, 2017, 2022, 2018, 2019,
		           2020, 2021, 2019, 2018, 2020, 2017, 2022, 2016, 2019, 2021,
		           2018, 2020, 2019, 2021, 2017, 2018, 2022, 2018, 2019, 2017,
		           2020, 2018, 2021, 2022, 2019, 2021, 2020, 2021, 2019, 2022};

        
        for(int i = 0; i < 5; i++) {
			try {
				cadastrar(12345678901L + i, placas[i], nomes[i], cpfs[i], modelos[i], anos[i]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	public boolean cadastrar(long Renavam, String Placa, String Nome, String Cpf, String Modelo, int Fabricacao)
	{
		try{
			StringBuilder input = new StringBuilder();
			input.append(Long.toString(Renavam));
			input.append('@');
			input.append(Placa);
			input.append('@');
			input.append(Nome);
			input.append('@');
			input.append(Cpf);
			input.append('@');
			input.append(Modelo);
			input.append('@');
			input.append(Integer.toString(Fabricacao));
			String resultado = input.toString();
			
			BO.cadastrar(Huffman.codificar(resultado));
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean editar(long Renavam, String Placa, String Nome, String Cpf, String Modelo, int Fabricacao) {
		try {
			
			StringBuilder input = new StringBuilder();
			input.append(Long.toString(Renavam));
			input.append('@');
			input.append(Placa);
			input.append('@');
			input.append(Nome);
			input.append('@');
			input.append(Cpf);
			input.append('@');
			input.append(Modelo);
			input.append('@');
			input.append(Integer.toString(Fabricacao));
			String resultado = input.toString();
			
			BO.alterar(Huffman.codificar(resultado));
			return true;
		} catch(KeynotfoundException e) { return false; }
		
	}
	

	public String buscar(long renavam, String placa) {
		try{
			StringBuilder string = new StringBuilder();
			string.append(Long.toString(renavam));
			string.append("@");
			string.append(placa);
			string.append("@");
			String info = string.toString();
			
			Package retorno = BO.buscar(Huffman.codificar(info));
			String[] valores = Huffman.decodificar(retorno).split("@");
			
			
			return new Veiculo(Long.parseLong(valores[0]), valores[1], new Condutor(valores[2], valores[3]), valores[4], Integer.parseInt(valores[5])).toString();

		} catch(KeynotfoundException e) {return "Modelo não encontrado"; }
	}
	
	private Veiculo buscarVeiculo(long renavam, String placa) {
		try{
			StringBuilder string = new StringBuilder();
			string.append(Long.toString(renavam));
			string.append("@");
			string.append(placa);
			string.append("@");
			String info = string.toString();
			
			Package retorno = BO.buscar(Huffman.codificar(info));
			String[] valores = Huffman.decodificar(retorno).split("@");
			
			return new Veiculo(Long.parseLong(valores[0]), valores[1], new Condutor(valores[2], valores[3]), valores[4], Integer.parseInt(valores[5]));
		} catch(KeynotfoundException e) {return null; }
	}
	
	
	public boolean remover(long renavam, String placa) {
		
		Veiculo entrada = buscarVeiculo(renavam, placa);
		if(entrada == null) return false;
		try {
			BO.remover(Huffman.codificar(entrada.info()));
		} catch (KeynotfoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	public String listar() {
		String lista = Huffman.decodificar(BO.listar());
		
		return lista;
	}
	
}
