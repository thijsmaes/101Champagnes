package be.fedasil.matchit.backend.model.events.messages;

/**
 * This message class represents the creation, modification or deletion of a
 * Room.
 * 
 * @author gvginder
 */
public class RoomMessage extends AbstractMessage implements VisitableMessage {
	public final long receptionCenterId;
	public final long receptionCenterVersion;
	public final long locationId;
	public final long locationVersion;
	public final long roomId;
	public final long roomVersion;

	public RoomMessage(Type type, long receptionCenterId,
			long receptionCenterVersion, long locationId, long locationVersion,
			long roomId, long roomVersion) {
		super(type);

		this.receptionCenterId = receptionCenterId;
		this.receptionCenterVersion = receptionCenterVersion;
		this.locationId = locationId;
		this.locationVersion = locationVersion;
		this.roomId = roomId;
		this.roomVersion = roomVersion;
	}

	@Override
	public void visit(MessageVisitor visitor) throws Exception {
		visitor.visit(this);
	}
}
