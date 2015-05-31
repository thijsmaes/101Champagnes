package be.fedasil.matchit.backend.model.events.messages;

/**
 * 
 * @author gvginder
 *
 */
public interface VisitableMessage {
	void visit(MessageVisitor visitor) throws Exception;
}
