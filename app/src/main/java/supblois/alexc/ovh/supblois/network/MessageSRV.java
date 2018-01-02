package supblois.alexc.ovh.supblois.network;

import java.time.LocalDateTime;

public class MessageSRV {
    private long msg_id, sender_id, dest_id;
    private String msg;
    private LocalDateTime time;

    public MessageSRV(long msg_id, long sender_id, long dest_id, String msg, LocalDateTime time) {
        this.msg_id = msg_id;
        this.sender_id = sender_id;
        this.dest_id = dest_id;
        this.msg = msg;
        this.time = time;
    }

    public long getMsg_id() {
        return msg_id;
    }

    public long getSender_id() {
        return sender_id;
    }

    public long getDest_id() {
        return dest_id;
    }

    public String getMsg() {
        return msg;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
