package br.ufes.informatica.vixcourts.core.domain;

import java.util.List;

import javax.persistence.Entity;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Cidade extends PersistentObjectSupport implements Comparable<Cidade> {
	
	private String nome;
	
	// OneToOne
	private UF estado;
	
	// OneToMany
	//private List<Endereco> enderecos;

	@Override
	public int compareTo(Cidade arg0) {
		// TODO Auto-generated method stub
		return 0;
	}


}
