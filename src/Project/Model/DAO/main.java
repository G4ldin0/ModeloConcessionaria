package Project.Model.DAO;

import Project.Exceptions.DuplicatedKeyException;
import Project.Exceptions.KeyException;
import Project.Exceptions.KeynotfoundException;
import Project.Model.Entity.Condutor;
import Project.Model.Entity.Veiculo;

public class main {
	static HashTableEnderecamentoAberto server = new HashTableEnderecamentoAberto(15);
	static Veiculo[] veiculos = new Veiculo[10];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(server.tamanho());
		
		injetarElementos();
		System.out.println(server.list());
		
		
		try {
			System.out.println(server.search(12345678901L).valor().toString() + "\n");
			server.remove(12345678901L);
		} catch (KeynotfoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n\n\n" + server.list());
	}
	
	private static void injetarElementos() {

        veiculos[0] = new Veiculo(12345678901L, "ABC1234", new Condutor("Alice", "123.456.789-01"), "Fiesta", 2020);
        veiculos[1] = new Veiculo(23456789012L, "DEF5678", new Condutor("Bob", "234.567.890-12"), "Corolla", 2019);
        veiculos[2] = new Veiculo(34567890123L, "GHI9012", new Condutor("Charlie", "345.678.901-23"), "Civic", 2018);
        veiculos[3] = new Veiculo(45678901234L, "JKL3456", new Condutor("David", "456.789.012-34"), "Gol", 2020);
        veiculos[4] = new Veiculo(56789012345L, "MNO7890", new Condutor("Eva", "567.890.123-45"), "Focus", 2017);
        veiculos[5] = new Veiculo(67890123456L, "PQR1234", new Condutor("Frank", "678.901.234-56"), "Fusion", 2022);
        veiculos[6] = new Veiculo(78901234567L, "STU5678", new Condutor("Grace", "789.012.345-67"), "Astra", 2019);
        veiculos[7] = new Veiculo(89012345678L, "VWX9012", new Condutor("Hannah", "890.123.456-78"), "Onix", 2021);
        veiculos[8] = new Veiculo(90123456789L, "YZA3456", new Condutor("Isaac", "901.234.567-89"), "Cruze", 2016);
        veiculos[9] = new Veiculo(11223344556L, "BCCD123", new Condutor("Jasmine", "112.233.445-56"), "HB20", 2022);
        
//        veiculos[10] = new Veiculo(11223344557L, "BCCE123", new Condutor("Kate", "112.233.445-57"), "Civic", 2017);
//        veiculos[11] = new Veiculo(11223344558L, "BCCF123", new Condutor("Liam", "112.233.445-58"), "Gol", 2021);
//        veiculos[12] = new Veiculo(11223344559L, "BCCG123", new Condutor("Mia", "112.233.445-59"), "Fusion", 2018);
//        veiculos[13] = new Veiculo(11223344560L, "BCCH123", new Condutor("Noah", "112.233.445-60"), "Corolla", 2020);
//        veiculos[14] = new Veiculo(11223344561L, "BCCI123", new Condutor("Olivia", "112.233.445-61"), "Onix", 2019);
//        veiculos[15] = new Veiculo(11223344562L, "BCCJ123", new Condutor("James", "112.233.445-62"), "Focus", 2021);
//        veiculos[16] = new Veiculo(11223344563L, "BCCK123", new Condutor("Sophia", "112.233.445-63"), "Fiesta", 2017);
//        veiculos[17] = new Veiculo(11223344564L, "BCCL123", new Condutor("Benjamin", "112.233.445-64"), "Astra", 2022);
//        veiculos[18] = new Veiculo(11223344565L, "BCCM123", new Condutor("Emma", "112.233.445-65"), "Cruze", 2018);
//        veiculos[19] = new Veiculo(11223344566L, "BCCN123", new Condutor("William", "112.233.445-66"), "HB20", 2019);
//        
//        veiculos[20] = new Veiculo(11223344567L, "BCCO123", new Condutor("Ava", "112.233.445-67"), "Gol", 2020);
//        veiculos[21] = new Veiculo(11223344568L, "BCCP123", new Condutor("Lucas", "112.233.445-68"), "Fusion", 2021);
//        veiculos[22] = new Veiculo(11223344569L, "BCCQ123", new Condutor("Luna", "112.233.445-69"), "Civic", 2019);
//        veiculos[23] = new Veiculo(11223344570L, "BCCR123", new Condutor("Henry", "112.233.445-70"), "Corolla", 2018);
//        veiculos[24] = new Veiculo(11223344571L, "BCCS123", new Condutor("Ella", "112.233.445-71"), "Onix", 2020);
//        veiculos[25] = new Veiculo(11223344572L, "BCCT123", new Condutor("Daniel", "112.233.445-72"), "Focus", 2017);
//        veiculos[26] = new Veiculo(11223344573L, "BCCU123", new Condutor("Mila", "112.233.445-73"), "Fiesta", 2022);
//        veiculos[27] = new Veiculo(11223344574L, "BCCV123", new Condutor("Jack", "112.233.445-74"), "Astra", 2016);
//        veiculos[28] = new Veiculo(11223344575L, "BCCW123", new Condutor("Sophie", "112.233.445-75"), "Cruze", 2019);
//        veiculos[29] = new Veiculo(11223344576L, "BCCX123", new Condutor("Leo", "112.233.445-76"), "HB20", 2021);
//        
//        veiculos[30] = new Veiculo(11223344577L, "BCCY123", new Condutor("Nora", "112.233.445-77"), "Gol", 2018);
//        veiculos[31] = new Veiculo(11223344578L, "BCCZ123", new Condutor("Matthew", "112.233.445-78"), "Fiesta", 2020);
//        veiculos[32] = new Veiculo(11223344579L, "BCDA123", new Condutor("Lily", "112.233.445-79"), "Focus", 2019);
//        veiculos[33] = new Veiculo(11223344580L, "BCDB123", new Condutor("Ethan", "112.233.445-80"), "Civic", 2017);
//        veiculos[34] = new Veiculo(11223344581L, "BCCC123", new Condutor("Chloe", "112.233.445-81"), "Fusion", 2021);
//        veiculos[35] = new Veiculo(11223344582L, "BCDD123", new Condutor("Oliver", "112.233.445-82"), "Corolla", 2022);
//        veiculos[36] = new Veiculo(11223344583L, "BCDE123", new Condutor("Zoe", "112.233.445-83"), "Onix", 2018);
//        veiculos[37] = new Veiculo(11223344584L, "BCDF123", new Condutor("Mason", "112.233.445-84"), "Astra", 2020);
//        veiculos[38] = new Veiculo(11223344585L, "BCDG123", new Condutor("Aria", "112.233.445-85"), "Cruze", 2019);
//        veiculos[39] = new Veiculo(11223344586L, "BCDH123", new Condutor("Liam", "112.233.445-86"), "HB20", 2017);
//        
//        veiculos[40] = new Veiculo(11223344587L, "BCDI123", new Condutor("Evelyn", "112.233.445-87"), "Jetta", 2020);
//        veiculos[41] = new Veiculo(11223344588L, "BCDJ123", new Condutor("Henry", "112.233.445-88"), "Mazda3", 2018);
//        veiculos[42] = new Veiculo(11223344589L, "BCDK123", new Condutor("Victoria", "112.233.445-89"), "Ranger", 2021);
//        veiculos[43] = new Veiculo(11223344590L, "BCDL123", new Condutor("William", "112.233.445-90"), "F-150", 2022);
//        veiculos[44] = new Veiculo(11223344591L, "BCDM123", new Condutor("Scarlett", "112.233.445-91"), "Mustang", 2019);
//        veiculos[45] = new Veiculo(11223344592L, "BCDN123", new Condutor("James", "112.233.445-92"), "Civic", 2021);
//        veiculos[46] = new Veiculo(11223344593L, "BCDO123", new Condutor("Sophia", "112.233.445-93"), "Model S", 2020);
//        veiculos[47] = new Veiculo(11223344594L, "BCDP123", new Condutor("Liam", "112.233.445-94"), "Bronco", 2021);
//        veiculos[48] = new Veiculo(11223344595L, "BCDQ123", new Condutor("Ella", "112.233.445-95"), "Camry", 2019);
//        veiculos[49] = new Veiculo(11223344596L, "BCDR123", new Condutor("Oliver", "112.233.445-96"), "Cruze", 2022);
//        
        for(int i = 0; i < veiculos.length; i++) {
			try {
				server.add(new NodeTableEnderecamentoAberto(veiculos[i]));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

}
