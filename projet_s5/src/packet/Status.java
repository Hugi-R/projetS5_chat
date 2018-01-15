package packet;

import java.util.List;

public class Status extends Packet {
	private static final long serialVersionUID = -8605025452175970195L;
	private long messageId;
	private List<Long> pending;
	private List<Long> received;
	private List<Long> read;
	
	public Status(byte command, long messageId, List<Long> pending, List<Long> received, List<Long> read) {
		super(command);
		this.messageId = messageId;
		this.pending = pending;
		this.received = received;
		this.read = read;
	}

	public long getMessageId() {
		return messageId;
	}

	public List<Long> getPending() {
		return pending;
	}

	public List<Long> getReceived() {
		return received;
	}

	public List<Long> getRead() {
		return read;
	}
}
