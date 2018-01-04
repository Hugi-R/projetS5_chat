package packet;

public class Commands {
	public static final byte FAIL = 0b0000_0001;
	public static final byte SEND = 0b0000_0010;
	public static final byte RETRIEVE = 0b0000_0100;
	/* Options : */
	public static final byte UPDATE = 0b0000_1000;
	public static final byte ALL = 0b0001_0000; //give all data possible
	public static final byte CONNECT = 0b0010_0000;
	
	
	/*possible combinations :
	 * RETRIVE | ALL with User packet : give user data + list of ticket
	 * SEND | CONNECT with Connect packet : client connect to server, respond with FAIL | CONNECT if failure, SEND | CONNECT + User packet if success
	 * 
	 */
	
	
	private Commands() {
	}

}
