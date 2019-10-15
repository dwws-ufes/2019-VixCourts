package br.ufes.informatica.vixcourts.core.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-26T10:07:56.629-0200")
@StaticMetamodel(Usuario.class)
public class Usuario_ {
	public static volatile SingularAttribute<Usuario, String> name;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile SingularAttribute<Usuario, String> password;
	public static volatile SingularAttribute<Usuario, Date> creationDate;
	public static volatile SingularAttribute<Usuario, Date> lastUpdateDate;
	public static volatile SingularAttribute<Usuario, Date> lastLoginDate;
}