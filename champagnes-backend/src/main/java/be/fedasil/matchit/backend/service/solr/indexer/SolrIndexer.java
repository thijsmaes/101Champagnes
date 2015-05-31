package be.fedasil.matchit.backend.service.solr.indexer;

import be.fedasil.matchit.backend.model.properties.PropertiesHolder;
import be.fedasil.matchit.backend.process.indexer.exception.IndexerException;
import org.apache.solr.common.SolrInputDocument;

/**
 * @author gvginder
 */
public interface SolrIndexer {
	void buildSolrProperty(SolrInputDocument document, String solrId, String categoryName, String entryName, PropertiesHolder.Entry entry) throws IndexerException;
}
