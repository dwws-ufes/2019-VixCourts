package br.ufes.informatica.vixcourts.core.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.informatica.vixcourts.core.domain.Agendamento;

@Local
public interface AgendamentoDAO extends BaseDAO<Agendamento> {

}