package Project.Client;

import java.io.IOException;
import java.util.Scanner;
import Project.API.*;

public class Client {
	
	private static Scanner scanner;
	private static Conection c = new Conection();
	
	public static void menu()
	{
		do {
			
			scanner = new Scanner(System.in);
			
			System.out.print(
					" ______________________\n"
				  + "|   DATA BASE CARROS   |\n"
				  + "|——————————————————————|\n"
				  + "| 1. Adicionar veículo |\n"
				  + "| 2. Buscar veículo    |\n"
				  + "| 3. Alterar veículo   |\n"
				  + "| 4. Remover veículo   |\n"
				  + "| 5. Listar veículos   |\n"
				  + "| 6. Sair              |\n"
				  + "|______________________|\n"
				  + ":"
					);
		
			try {
				int escolha = Integer.parseInt(scanner.nextLine());
				
				System.out.println();
	
				switch (escolha) {
				case 1: add(); break;
				case 2: search(); break;
				case 3: edit(); break;
				case 4: remove(); break;
				case 5: list(); break;
				case 6: quit(); break;
				default:
					System.out.println("Selecione uma das opções acima.\n");
					break;
				}
			} catch (NumberFormatException e) {}
		} while (true); 

	}
	
	
	
	public static void add() {
		scanner = new Scanner(System.in);
		System.out.println("Digite as informações do condutor do veículo:");
		
		System.out.println("CPF: ");
		String cpfCondutor = scanner.nextLine();
		
		System.out.println("Nome: ");
		String nomeCondutor = scanner.nextLine();
		
		
		System.out.println("\nDigite as informações do veículo a ser cadastrado: ");
		System.out.println("placa: ");
		String placa = scanner.nextLine().toUpperCase();
		
		long renavam = 0; 
		while(renavam == 0) {
				try{
				System.out.println("RENAVAM: ");
				renavam = Long.parseLong(scanner.nextLine());
			} catch (NumberFormatException e) {System.out.println("Entrada não compatível.\n");}
		}
		
		System.out.println("informações do modelo: ");
		String modeloVeiculo = scanner.nextLine();
		
		System.out.println("ano de fabricação: ");
		int fabricacao = Integer.parseInt(scanner.nextLine());
		
		boolean sucesso = c.cadastrar(renavam, placa, nomeCondutor, cpfCondutor, modeloVeiculo, fabricacao);
		
		if(sucesso)
			System.out.println("Operação realizada com sucesso.");
		else
			System.out.println("Falha na operação. RENAVAM já existente no sistema");
		
		System.out.println("\n");
		//menu();
	}
	
	public static void edit() {
		scanner = new Scanner(System.in);
		
		String placa;
		long renavam;
		String busca;
		
		
		System.out.println("Selecione o veículo que deseja ser alterado: \n");
		System.out.println("Placa: ");
		placa = scanner.nextLine().toUpperCase();
		
		System.out.println("RENAVAM: ");
		renavam = Long.parseLong(scanner.nextLine());

		busca = c.buscar(renavam, placa);
		System.out.println(busca);
		if(!busca.equals("Modelo não encontrado")) 
		{
			System.out.println("É este o veículo?[1 = sim][0 = não]:");
			boolean confirma = scanner.nextInt() == 1 ? true : false;
			scanner.nextLine();
			
			if(confirma) 
			{
				
				String nome = new String();
				String cpf = new String();
				String modelo = new String();
				int fabricacao;
				
				
				Scanner s = new Scanner(busca).useDelimiter(" | ");
				
				s.next();
				s.next();
				s.next();
				s.next();
				modelo = s.next();
				s.next();
				
				fabricacao = s.nextInt();
				s.next();
				
				nome = s.next();
				s.next();
				cpf = s.next();
				s.next();

				s.close();
				
				System.out.println("Adicione as informações que deseja alterar. No campo em que não deseja alterar, pressione ENTER.");
				System.out.println("Informações do condutor");
				
				
				System.out.println("CPF: ");
				String _cpf = scanner.nextLine();
				
				if(!_cpf.isBlank()) {
					cpf = _cpf;
				}
				
				System.out.println("Nome: ");
				String _nome = scanner.nextLine();

				if(!_nome.isBlank()) {
					nome = _nome;
				}
				
				
				System.out.println("\nInformações do veículo ");
				
				System.out.println("informações do modelo: ");
				String _modelo = scanner.nextLine();
				
				if(!_modelo.isBlank()) {
					modelo = _modelo;
				}
				
				System.out.println("ano de fabricação: ");
				String _fabricacao = scanner.nextLine();
				
				if(!_fabricacao.isBlank()) {
					try {
					fabricacao = Integer.parseInt(_fabricacao);
					} catch ( NumberFormatException e) {}
				}
				
				c.editar(renavam, placa, nome, cpf, modelo, fabricacao);
			
			}
		}
	}
	
	public static void search() {
		scanner = new Scanner(System.in);
		
		System.out.println("Informe a placa e código de RENAVAM do veículo: \n");
		System.out.println("Placa: ");
		String placa = scanner.nextLine().toUpperCase();
		System.out.println("RENAVAM: ");
		long renavam = Long.parseLong(scanner.nextLine());

		String carro = c.buscar(renavam, placa);
		System.out.println(carro);
		
	}
	
	public static void remove() {
		scanner = new Scanner(System.in);
		
		System.out.println("Selecione o veículo que será removido da base de dados: \n");
		System.out.println("placa: ");
		String placa = scanner.nextLine().toUpperCase();
		System.out.println("RENAVAM: ");
		long renavam;
		try {
			renavam = Long.parseLong(scanner.nextLine());}
		catch (NumberFormatException e) { renavam = Long.MAX_VALUE; }
		
		if(renavam != Long.MAX_VALUE && !placa.isBlank()) {
			String busca = c.buscar(renavam, placa);
			System.out.println(busca);
			if(!busca.equals("Modelo não encontrado")) 
			{
				System.out.println("É este o veículo?[1 = sim][0 = não]:");
				boolean confirma = scanner.nextInt() == 1 ? true : false;
				scanner.nextLine();
				if(confirma) {
					if ( c.remover(renavam, placa))
						System.out.println("Operação realizada com sucesso. \n");
					else
						System.out.println("Operação realizada com falha. \nInformações não encontradas na base de dados.\n\n");					
				}
			}
		}
	}
	
	public static void list() {
		System.out.println("Veículos cadastrados na base de dados:");
		System.out.println("{RENAVAM} | {PLACA} | {MODELO} | {ANO FAB.} | {NOME CONDUTOR} | {CONDUTOR CPF}\n");
		System.out.println(c.listar());
	}
	
	public static void quit() {
		System.out.print("\n...");
		System.exit(0);
	}
	
	
	
	public static void main(String[] args) throws InterruptedException, IOException {	
		menu();

	}
	

	
}
