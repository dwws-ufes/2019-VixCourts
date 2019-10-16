package br.ufes.informatica.vixcourts.core.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Agendamento extends PersistentObjectSupport implements Comparable<Agendamento> {

	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	private Date horaInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaFim;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaInicio2;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaFim2;
	
	
	
	@NotNull
	@ManyToOne
	private Quadra quadra;	

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
	
	public Date getHoraInicio2() {
		return horaInicio2;
	}

	public void setHoraInicio2(Date horaInicio2) {
		this.horaInicio2 = horaInicio2;
	}

	public Date getHoraFim2() {
		return horaFim2;
	}

	public void setHoraFim2(Date horaFim2) {
		this.horaFim2 = horaFim2;
	}

	public Quadra getQuadra() {
		return quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

}
