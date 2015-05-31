package be.fedasil.matchit.backend.facade.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import be.fedasil.matchit.backend.dao.CodeLabelDAO;
import be.fedasil.matchit.backend.facade.CodeLabelFacade;
import be.fedasil.matchit.backend.model.CodeLabel;

@Stateless
public class CodeLabelFacadeBean implements CodeLabelFacade {

	private CodeLabelDAO codeLabelDAO;
	
	public CodeLabelFacadeBean(CodeLabelDAO codeLabelDAO) {
		this.codeLabelDAO = codeLabelDAO;
	}
	
	public CodeLabelFacadeBean() {
		codeLabelDAO = new CodeLabelDAO();
	}
	
	@Override
	@TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
	public Map<String, String> getCodeLabels(String codeType, String language) {
		List<CodeLabel> labels = codeLabelDAO.findAllByCodeTypeLanguage(
				codeType, language);
		Map<String, String> result = new HashMap<String, String>();
		for (CodeLabel cl : labels)
			result.put(cl.getCodeKey(), cl.getLabel());
//		does this work?? oh yes it does
		return result;
	}
}
