package be.fedasil.matchit.backend.model.events.messages;

/**
 * This message class represents the creation, modification or deletion of a
 * ReceptionCenter.
 * 
 * @author gvginder
 */
public class ReceptionCenterMessage extends AbstractMessage implements
		VisitableMessage {
	public final long receptionCenterId;
	public final long receptionCenterVersion;

	public ReceptionCenterMessage(Type type, long receptionCenterId,
			long receptionCenterVersion) {
		super(type);

		this.receptionCenterId = receptionCenterId;
		this.receptionCenterVersion = receptionCenterVersion;
	}

	@Override
	public void visit(MessageVisitor visitor) throws Exception {
		visitor.visit(this);
	}
}
