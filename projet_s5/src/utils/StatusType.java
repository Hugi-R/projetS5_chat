package utils;

import java.awt.Color;

public class StatusType {
	public static final byte MESSAGE_PENDING = -1; //gray
	public static final byte USER_PENDING = 0; //red
	public static final byte USER_SENT = 1; //orange
	public static final byte USER_READ = 2; //green
	
	public static final Color COLOR_MESSAGE_PENDING = Color.LIGHT_GRAY;
	public static final Color COLOR_USER_PENDING = new Color(255, 140, 140); //red
	public static final Color COLOR_USER_SENT = new Color(255, 180, 75); //orange
	public static final Color COLOR_USER_READ = new Color(140, 255, 140); //green
}
