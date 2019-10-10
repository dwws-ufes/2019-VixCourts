package br.ufes.informatica.vixcourts.core.domain;

import java.util.List;

import javax.persistence.Entity;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class UF extends PersistentObjectSupport implements Comparable<UF> {
	
	private String nome;
	
	private String sigla;
	
	private Pais pais;
	
	// OneToMany
	//private List<Cidade> cidades;

	@Override
	public int compareTo(UF arg0) {
		// TODO Auto-generated method stub
		return 0;
	}


}
