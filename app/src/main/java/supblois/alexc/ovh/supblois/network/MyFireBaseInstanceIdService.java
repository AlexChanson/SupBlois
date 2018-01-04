package supblois.alexc.ovh.supblois.network;

import android.content.SharedPreferences;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class MyFireBaseInstanceIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();

        SharedPreferences tk = getSharedPreferences("fb_token_storage", 0);
        SharedPreferences.Editor editor = tk.edit();

        editor.putString("tok", token);

        editor.apply();

        System.out.println("FireBase Token is : " + token);
    }
}
