package supblois.alexc.ovh.supblois.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ben on 09/12/17.
 */

public class Message {
    private long msgId; // unique dans une conversation, forme la clef avec senderId et receiverId
    private long senderId;
    private String content;
    private Date msgdate;

    public Message(long msgId, long senderId, Date msgdate, String content) {
        this.msgId = msgId;
        this.senderId = senderId;
        this.content = content;
        this.msgdate = msgdate;
    }

    public long getMsgId(){
        return msgId;
    }

    public long getSenderId() {
        return senderId;
    }


    public String getContent() {
        return content;
    }

    public Date getDate() {
        return msgdate;
    }

    @Override
    public String toString(){
        SimpleDateFormat formatter = new SimpleDateFormat();
        return String.format("id=%d at  sender: %d msg: %d",
                msgId,
                formatter.format(msgdate),
                senderId,
                content.substring(0,20));
    }
}
