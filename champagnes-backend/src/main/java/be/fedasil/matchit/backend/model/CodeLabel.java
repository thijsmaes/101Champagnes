package be.fedasil.matchit.backend.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * CodeLabel represents a single code with its translation for one specific
 * language
 *
 * @author wdewit
 */
@Entity
@IdClass(CodeLabel.CodeLabelKey.class)
@Table(name = "\"CODE_LABEL\"")
@NamedQueries({
		@NamedQuery(name = "CodeLabel.findAllByCodeTypeLanguage", query = "select cl from CodeLabel cl where cl.codeType = :codeType and cl.language = :language"),
		@NamedQuery(name = "CodeLabel.findAll", query = "select cl from CodeLabel cl")
})
@Cacheable(true)
public class CodeLabel implements Serializable {
	private static final long serialVersionUID = -4257911504129016129L;

	@SuppressWarnings("serial")
	public static class CodeLabelKey implements Serializable {
		String codeType;
		String codeKey;
		String language;
	}

	@Id
	@Column(name = "\"CODE_TYPE\"")
	private String codeType;
	@Id
	@Column(name = "\"CODE_KEY\"")
	private String codeKey;
	@Id
	@Column(name = "\"LANGUAGE\"")
	private String language;
	@Column(name = "\"LABEL\"")
	private String label;

	public CodeLabel() {
	}

	public CodeLabel(String codeType, String codeKey, String language,
			String label) {
		super();
		this.codeType = codeType;
		this.codeKey = codeKey;
		this.language = language;
		this.label = label;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeKey() {
		return codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "CodeLabel{" +
				"codeType='" + codeType + '\'' +
				", codeKey='" + codeKey + '\'' +
				", language='" + language + '\'' +
				", label='" + label + '\'' +
				'}';
	}
}
