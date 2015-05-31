package be.fedasil.matchit.backend.service.solr.indexer;

import be.fedasil.matchit.backend.model.properties.PropertiesHolder;
import be.fedasil.matchit.backend.process.indexer.exception.IndexerException;
import be.fedasil.matchit.backend.process.indexer.exception.UnrecoverableIndexerException;
import org.apache.solr.common.SolrInputDocument;

import java.util.Date;

/**
 * @author gvginder
 */
public class PrimitiveSolrIndexer extends AbstractSolrIndexer {
	@Override
	public void buildSolrProperty(SolrInputDocument document, String solrId, String categoryName, String entryName, PropertiesHolder.Entry entry) throws IndexerException {
		for (Object value : entry) {
			writeDynamicObjectToSolrInputDocument(document, entryName, value);
		}
	}

	protected static void writeDynamicObjectToSolrInputDocument(SolrInputDocument document, String name, Object value) throws IndexerException {
		if (value == null) {
			throw new UnrecoverableIndexerException("Null value given as property value");
		} else if (value instanceof Boolean) {
			document.addField(name + "_bs", value);
		} else if (value instanceof Integer) {
			document.addField(name + "_is", value);
		} else if (value instanceof Long) {
			document.addField(name + "_ls", value);
		} else if (value instanceof Double) {
			document.addField(name + "_ds", value);
		} else if (value instanceof Float) {
			document.addField(name + "_fs", value);
		} else if (value instanceof String) {
			document.addField(name + "_txt", value);
		} else if (value instanceof Date) {
			document.addField(name + "_dts", value);
		} else {
			throw new UnrecoverableIndexerException("Unsupported value class " + value.getClass());
		}
	}
}
