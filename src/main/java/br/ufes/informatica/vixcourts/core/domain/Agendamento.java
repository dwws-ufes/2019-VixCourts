package br.ufes.informatica.vixcourts.core.domain;

import java.util.Date;

import javax.persistence.Entity;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Agendamento extends PersistentObjectSupport implements Comparable<Agendamento> {

	private static final long serialVersionUID = 1L;

	private Date horaInicio;
	
	private Date horaFim;
	
	private Quadra quadraReservada;	

	@Override
	public int compareTo(Agendamento arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public Quadra getQuadraReservada() {
		return quadraReservada;
	}

	public void setQuadraReservada(Quadra quadraReservada) {
		this.quadraReservada = quadraReservada;
	}

}
