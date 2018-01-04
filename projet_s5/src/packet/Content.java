package packet;

public abstract class Content extends Packet{
	private static final long serialVersionUID = -3334205753488398387L;
	protected long id;
	
	public Content(byte command, long id) {
		super(command);
		this.id = id;
	}

	public long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return super.toString()+"Id : "+id+", ";
	}

}
