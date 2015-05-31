package be.fedasil.matchit.backend.service.solr.indexer;

/**
 * @author gvginder
 */
public abstract class AbstractSolrIndexer implements SolrIndexer {
	/**
	 * Create an Solr property ID based on its category name and entry name.
	 *
	 * @param solrId
	 * @param categoryName
	 * @param entryName
	 */
	protected Object createSolrPropertyId(String solrId, String categoryName, String entryName) {
		return solrId + ":" + categoryName + ":" + entryName;
	}
}
