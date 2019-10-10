package br.ufes.informatica.vixcourts.core.domain;
import javax.persistence.Entity;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Endereco extends PersistentObjectSupport implements Comparable<Endereco> {
	
	private String logradouro;
	
	private String bairro;
	
	private int cep;
	
	private int numero;
	
	private String complemento;
	
	//OneToOne
	private Cidade cidade;

	@Override
	public int compareTo(Endereco arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
