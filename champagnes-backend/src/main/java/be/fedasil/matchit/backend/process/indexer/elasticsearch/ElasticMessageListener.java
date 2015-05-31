package be.fedasil.matchit.backend.process.indexer.elasticsearch;

import be.fedasil.matchit.backend.process.indexer.AbstractMessageListener;
import be.fedasil.matchit.backend.process.indexer.RoomClientMessageVisitor;
import be.fedasil.matchit.backend.service.elasticsearch.ElasticRoomIndexer;
import org.elasticsearch.client.Client;

/**
 * @author gvginder
 */
public class ElasticMessageListener extends AbstractMessageListener {
	public ElasticMessageListener(Client client) {
		this(new ElasticRoomIndexer(client));
	}

	public ElasticMessageListener(ElasticRoomIndexer indexer) {
		super(new RoomClientMessageVisitor(indexer));
	}
}
