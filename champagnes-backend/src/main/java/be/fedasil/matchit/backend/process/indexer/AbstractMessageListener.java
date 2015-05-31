package be.fedasil.matchit.backend.process.indexer;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import be.fedasil.matchit.backend.model.events.messages.MessageVisitor;
import be.fedasil.matchit.backend.model.events.messages.VisitableMessage;
import be.fedasil.matchit.backend.logger.MatchitLogger;
import be.fedasil.matchit.backend.logger.MatchitLoggerFactory;

/**
 * This class implements the JMS MessageListener interface and forwards JMS
 * messages to the given MessageVisitor.
 * 
 * @author gvginder
 */
public class AbstractMessageListener implements MessageListener {
	private static final MatchitLogger LOGGER = MatchitLoggerFactory
			.getLogger(AbstractMessageListener.class);

	private final MessageVisitor visitor;

	public AbstractMessageListener(MessageVisitor visitor) {
		this.visitor = visitor;
	}

	@Override
	public void onMessage(Message message) {
		ObjectMessage objectMessage = (ObjectMessage) message;
		try {
			Serializable object = objectMessage.getObject();
			if (object instanceof VisitableMessage) {
				VisitableMessage visitable = (VisitableMessage) object;
				visitable.visit(this.visitor);
			} else {
				LOGGER.debug("Cannot handle message of type "
						+ object.getClass());
			}
		} catch (JMSException exception) {
			LOGGER.error("Cannot get object from ObjectMessage", exception);
		} catch (Exception exception) {
			// TODO Put message back on the queue?
		}
	}
}
