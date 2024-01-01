package Project.Model.Entity;


public class Veiculo {
	private long renavam;
	String placa;
	Condutor condutor;
	String modelo;
	int fabricacao;
	
	public Veiculo(long Renavam, String Placa, Condutor Condutor, String Modelo, int Fabricacao)
	{
		renavam = Renavam;
		placa = Placa;
		condutor = Condutor;
		modelo = Modelo;
		fabricacao = Fabricacao;
				
	}
	
	public long renavam() { return renavam; }
	
	public String placa() { return placa; }
	
	public void setPlaca(String placa) {
		if(placa != null) {
			this.placa = placa;			
		}
		else
			throw new NullPointerException("valor vazio");
	}
	
	public Condutor condutor() { return condutor; }
	
	public String modelo() { return modelo; }
	
	public void setModelo(String modelo) {
		if(modelo != null) {
			this.modelo = modelo;			
		}
		else
			throw new NullPointerException("valor vazio");
	}
	
	public int fabricacao() { return fabricacao; }

	public void setFabricacao(int fabricacao) {
		if(fabricacao <= 1899) this.fabricacao = fabricacao;
	}
	
	
	public String toString() {
		return renavam + " | " + placa + " | " + modelo + " | " + fabricacao + " | " + condutor.nome + " : " + condutor.cpf + " \n"; 
	}
	
	public String info() {
		return renavam + "@" + placa + "@" + condutor.nome + "@" + condutor.cpf + "@" + modelo + "@" + fabricacao;
	}
}
