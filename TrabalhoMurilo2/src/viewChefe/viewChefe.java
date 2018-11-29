package viewChefe;

import Entidades.Chefe;

public class viewChefe {


	public viewChefe() {
		
	}
	public Chefe inserir(String nome, String cpf,  String pis) {
		Chefe Chefe = new Chefe();
		
		Chefe.setNome(nome);
		
		Chefe.setCPF(cpf);
		
		Chefe.setPIS(pis);
		
		return Chefe;
		
	
	}
	
}
	
	
	