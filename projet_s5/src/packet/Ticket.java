package packet;

import java.util.List;

public class Ticket extends Content{
	private static final long serialVersionUID = -7044448245749898134L;
	private long creatorId;
	private long groupId;
	private String name;
	private List<Long> messageList;

	public Ticket(byte command, long id, long creatorId, long groupId, String name, List<Long> messageList) {
		super(command, id);
		this.creatorId = creatorId;
		this.groupId = groupId;
		this.name = name;
		this.messageList = messageList;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public long getGroupId() {
		return groupId;
	}

	public List<Long> getMessageList() {
		return messageList;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return super.toString()+"creatorId : "+creatorId+", groupId : "+groupId+", messages : ["+messageList+"] }";
	}

}
