package br.ufes.informatica.vixcourts.core.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.informatica.vixcourts.core.domain.Quadra;

@Local
public interface QuadraDAO extends BaseDAO<Quadra> {

}