package br.ufes.informatica.vixcourts.core.domain;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Quadra extends PersistentObjectSupport implements Comparable<Quadra> {

private static final long serialVersionUID = 1L;
	
	@Size(max = 100)
	private String name;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Quadra arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
