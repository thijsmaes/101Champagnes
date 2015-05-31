package be.fedasil.matchit.backend.model.events.messages;

/**
 * 
 * @author gvginder
 *
 */
public interface MessageVisitor {
	void visit(ReceptionCenterMessage message) throws Exception;

	void visit(LocationMessage message) throws Exception;

	void visit(RoomMessage message) throws Exception;
}
