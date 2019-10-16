package br.ufes.informatica.vixcourts.core.converter;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.informatica.vixcourts.core.domain.Quadra;
import br.ufes.informatica.vixcourts.core.persistence.QuadraDAO;

@FacesConverter("quadraConverter")
public class QuadraConverter implements Converter {

    @Named(value="quadraConverterBean")
    @RequestScoped
    public static class Bean {
        @Inject
        private QuadraDAO quadraDAO;

        public QuadraDAO getQuadraDAO() {
            return quadraDAO;
        }
    }

    private QuadraDAO getQuadraDAO(FacesContext context) {
        Application app = context.getApplication();
        Bean bean = app.evaluateExpressionGet(context, "#{" + "quadraConverterBean" + "}", Bean.class);
        QuadraDAO quadraDAO = bean.getQuadraDAO();
        return quadraDAO;
    }

    @Override
    public Quadra getAsObject(FacesContext context, UIComponent component, String name) {
    	QuadraDAO quadraDAO = getQuadraDAO(context);
    	System.out.println("[INFO ANTES DO ERRO]Quadra selecionada: " + name);
        return (name == null || name.isEmpty()) ? null : quadraDAO.retrieveByName(name);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object name) {
    	if (name == null) {
    		return null;
    	} else {
    		Quadra quadra = (Quadra) name;
    		return quadra.getName();
    	}
    }
}

