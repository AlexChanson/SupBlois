package supblois.alexc.ovh.supblois.dao;

import java.util.Date;

/**
 * Created by ben on 09/12/17.
 */

public class Message {
    private long msgId; // unique dans une conversation, forme la clef avec senderId et receiverId
    private long senderId;
    private long receiverId;
    private String content;
    private Date date;

    public long getMsgId(){
        return msgId;
    }

    public long getSenderId() {
        return senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}
