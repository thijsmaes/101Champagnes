package be.fedasil.matchit.backend.model.events.messages;

import java.io.Serializable;

/**
 * This is the base class for messages that will be put on the JMS message
 * queue.
 * 
 * @author gvginder
 */
public abstract class AbstractMessage implements Serializable {
	public enum Type {
		CREATED, UPDATED, DELETED
	}

	public final Type type;

	public AbstractMessage(Type type) {
		this.type = type;
	}
}
