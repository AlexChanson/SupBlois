package supblois.alexc.ovh.supblois.network;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;

import supblois.alexc.ovh.supblois.MsgNotificationManager;

public class FirebaseMsgService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Map<String, String> data = remoteMessage.getData();
        String sendBy = data.get("sender");
        String message = data.get("content");

        //DEBUG
        System.out.printf("Firebase Message Recu de %s msg='%s'%n", sendBy, message);

        MsgNotificationManager.getInstance(getApplicationContext()).displayNotification(sendBy, message);

    }

}
