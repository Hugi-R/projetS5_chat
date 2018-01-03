package packet;

import java.io.Serializable;

public class Packet implements Serializable {
	private static final long serialVersionUID = -4330069030024542875L;
	private byte command;
	
	
	public Packet(byte command) {
		super();
		this.command = command;
	}

	public byte getCommand() {
		return command;
	}
	
}
