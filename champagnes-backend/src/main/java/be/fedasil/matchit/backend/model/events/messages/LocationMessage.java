package be.fedasil.matchit.backend.model.events.messages;

/**
 * This message class represents the creation, modification or deletion of a
 * Location.
 * 
 * @author gvginder
 */
public class LocationMessage extends AbstractMessage implements
		VisitableMessage {
	public final long receptionCenterId;
	public final long receptionCenterVersion;
	public final long locationId;
	public final long locationVersion;

	public LocationMessage(Type type, long receptionCenterId,
			long receptionCenterVersion, long locationId, long locationVersion) {
		super(type);

		this.receptionCenterId = receptionCenterId;
		this.receptionCenterVersion = receptionCenterVersion;
		this.locationId = locationId;
		this.locationVersion = locationVersion;
	}

	@Override
	public void visit(MessageVisitor visitor) throws Exception {
		visitor.visit(this);
	}
}
