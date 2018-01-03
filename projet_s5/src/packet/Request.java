package packet;
/**
 * 
 * @author hugor
 *
 */
public class Request extends Packet{
	private static final long serialVersionUID = 2177676322629640664L;
	private long id;

	public Request(byte command, long id) {
		super(command);
		this.id = id;
	}

	public long getId() {
		return id;
	}

}
