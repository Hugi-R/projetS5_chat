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
	
	public void setCommand(byte command) {
		this.command = command;
	}
	
	@Override
	public String toString() {
		return super.toString() + " = { command : "+command+", ";
	}
}
