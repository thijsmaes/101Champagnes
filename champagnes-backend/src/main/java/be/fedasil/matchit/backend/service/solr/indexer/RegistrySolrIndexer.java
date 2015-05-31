package be.fedasil.matchit.backend.service.solr.indexer;

import be.fedasil.matchit.backend.model.properties.PropertiesHolder;
import be.fedasil.matchit.backend.process.indexer.exception.IndexerException;
import org.apache.solr.common.SolrInputDocument;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gvginder
 */
public class RegistrySolrIndexer implements SolrIndexer {
	private final SolrIndexer defaultIndexer;
	private final Map<String, SolrIndexer> indexers;

	public RegistrySolrIndexer() {
		this(new PrimitiveSolrIndexer());
	}

	public RegistrySolrIndexer(SolrIndexer defaultIndexer) {
		this.defaultIndexer = defaultIndexer;
		indexers = new HashMap<>();
	}

	public SolrIndexer getDefaultSolrIndexer() {
		return defaultIndexer;
	}

	public SolrIndexer getSolrIndexer(String entryName) {
		if (indexers.containsKey(entryName)) {
			return indexers.get(entryName);
		}
		return getDefaultSolrIndexer();
	}

	public void setSolrIndexer(String entryName, SolrIndexer indexer) {
		indexers.put(entryName, indexer);
	}

	public void removeSolrIndexer(String entryName) {
		indexers.remove(entryName);
	}

	@Override
	public void buildSolrProperty(SolrInputDocument document, String solrId, String categoryName, String entryName, PropertiesHolder.Entry entry) throws IndexerException {
		SolrIndexer indexer = this.getSolrIndexer(entryName);
		indexer.buildSolrProperty(document, solrId, categoryName, entryName, entry);
	}
}
