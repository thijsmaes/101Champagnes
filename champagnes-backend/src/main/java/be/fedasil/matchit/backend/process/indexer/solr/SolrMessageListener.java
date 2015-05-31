package be.fedasil.matchit.backend.process.indexer.solr;

import be.fedasil.matchit.backend.process.indexer.AbstractMessageListener;
import be.fedasil.matchit.backend.process.indexer.RoomClientMessageVisitor;
import be.fedasil.matchit.backend.service.solr.SolrRoomIndexer;
import org.apache.solr.client.solrj.SolrClient;

/**
 * @author gvginder
 */
public class SolrMessageListener extends AbstractMessageListener {
	public SolrMessageListener(SolrClient client) {
		this(new SolrRoomIndexer(client));
	}

	public SolrMessageListener(SolrRoomIndexer indexer) {
		super(new RoomClientMessageVisitor(indexer));
	}
}
