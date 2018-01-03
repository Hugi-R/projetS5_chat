package packet;

import java.io.Serializable;
import java.util.Random;

public class Id implements Serializable{
	private static final long serialVersionUID = -5016686537748190693L;
	private static Random rand = new Random();
	private long id;
	
	public Id(long id) throws IdException{
		if(id == 0L)
			throw new IdException("0 is not a valid id");
		this.id = id;
	}
	
	public long get() {
		return id;
	}
	
	public String toString() {
		return Long.toString(id);
	}
	
	public static long generate(byte type) {
		long i = rand.nextLong();
		i = i & 0x00ff_ffff_ffff_ffffL;
		i = i + ((long)type << 56);
		return i;
	}
	
	public static byte type(long id) {
		byte type;
		type = (byte) ((id & 0xff00_0000_0000_0000L) >> 56);
		return type;
	}
	
	public byte getType() {
		return type(id);
	}
	
}
