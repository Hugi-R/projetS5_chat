package utils;
import java.util.Random;

import packet.ContentType;

public class Id{
	private static Random rand = new Random();
	public static final long DEFAULT_ID_MESSAGE = (long)ContentType.MESSAGE << 56;
	public static final long DEFAULT_ID_TICKET = (long)ContentType.TICKET << 56;
	public static final long DEFAULT_ID_GROUP = (long)ContentType.GROUP << 56;
	
	
	private Id() {
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
	
}
