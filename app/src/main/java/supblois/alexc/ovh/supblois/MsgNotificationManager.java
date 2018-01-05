package supblois.alexc.ovh.supblois;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/*
 Source: https://www.simplifiedcoding.net/firebase-cloud-messaging-tutorial-android/
 Modifié par Alex
 */

public class MsgNotificationManager {

    private Context mCtx;
    private static MsgNotificationManager mInstance;

    private MsgNotificationManager(Context context) {
        mCtx = context;
    }

    public static synchronized MsgNotificationManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MsgNotificationManager(context);
        }
        return mInstance;
    }

    public void displayNotification(String title, String body) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(mCtx, "messages")
                        .setSmallIcon(R.drawable.notif_icon)
                        .setContentTitle(title)
                        .setContentText(body);


        Intent resultIntent = new Intent(mCtx, Conversation.class);
        resultIntent.setAction("SHOW_MESSAGE");
        resultIntent.putExtra("PHONE_NB", title);

        PendingIntent pendingIntent = PendingIntent.getActivity(mCtx, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(pendingIntent);

        NotificationManager mNotifyMgr =
                (NotificationManager) mCtx.getSystemService(NOTIFICATION_SERVICE);

        if (mNotifyMgr != null) {
            mNotifyMgr.notify(1, mBuilder.build());
        }
    }

}