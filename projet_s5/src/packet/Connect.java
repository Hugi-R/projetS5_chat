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

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
