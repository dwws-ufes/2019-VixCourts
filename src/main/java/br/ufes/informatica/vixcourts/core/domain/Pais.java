package br.ufes.informatica.vixcourts.core.domain;

import java.util.List;

import javax.persistence.Entity;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Pais extends PersistentObjectSupport implements Comparable<Pais> {
	
	private String nome;
	
	// OneToMany
	//private List<Cidade> cidades;

	@Override
	public int compareTo(Pais arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
