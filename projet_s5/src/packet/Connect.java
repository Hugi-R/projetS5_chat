package packet;

public class Connect extends Packet {
	private static final long serialVersionUID = -8116093689804836416L;
	private String username;
	private String password;
	
	public Connect(byte command, String username, String password) {
		super(command);
		this.username = username;
		this.password = password;
	}
	
	public Connect(String username, String password) {
		super((byte)(Commands.SEND | Commands.CONNECT));
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	@Override
	public String toString() {
		return super.toString()+"username : "+username+", password : "+password+" }";
	}

}
