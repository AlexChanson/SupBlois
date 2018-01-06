package supblois.alexc.ovh.supblois.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ben on 09/12/17.
 */

public class Message {
    private long msgId; // unique dans une conversation, forme la clef avec senderId et receiverId
    private String senderId;
    private String content;
    private Date msgdate;
    private boolean sent;

    public Message(long msgId, String senderId, Date msgdate, boolean sent, String content) {
        this.msgId = msgId;
        this.senderId = senderId;
        this.content = content;
        this.msgdate = msgdate;
        this.sent = sent;
    }

    public long getMsgId(){
        return msgId;
    }

    public String getSenderId() {
        return senderId;
    }


    public String getContent() {
        return content;
    }

    public Date getDate() {
        return msgdate;
    }

    public boolean isSent() {
        return sent;
    }

    @Override
    public String toString(){
        SimpleDateFormat formatter = new SimpleDateFormat();
        return String.format("id=%d at: %s sender: %s msg: %s sent: %b",
                msgId,
                formatter.format(msgdate),
                senderId,
                content,
                sent);
    }
}
