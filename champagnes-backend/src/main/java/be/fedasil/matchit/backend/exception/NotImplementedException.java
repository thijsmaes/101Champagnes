package be.fedasil.matchit.backend.exception;

public class NotImplementedException extends UncheckedException {
	
	private static final long serialVersionUID = -5648778114155951861L;

	public NotImplementedException() {	
		super("NOT_IMPLEMENTED");
	}
}
