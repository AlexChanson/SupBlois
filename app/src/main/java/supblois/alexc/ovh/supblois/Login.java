package supblois.alexc.ovh.supblois;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

import supblois.alexc.ovh.supblois.network.Command;
import supblois.alexc.ovh.supblois.network.NetTask;

public class Login extends AppCompatActivity {
    TextView login, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login_phoneNB);
        password = findViewById(R.id.login_password);

        Button signIn = findViewById(R.id.login_sign_in_bt);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nb = login.getText().toString();
                String pwd =  password.getText().toString();

                String hash = "";

                try {
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] h = digest.digest(pwd.getBytes(StandardCharsets.UTF_8));
                    hash = toHexString(h);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                Command cmd = new Command("LOGIN", new Object[]{nb, hash});
                NetTask netTask = new NetTask();
                netTask.execute(cmd);
                Object ret = null;
                try {
                    ret = netTask.get();
                    System.out.println(ret.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


                //TODO Handle login
                System.out.println(ret.toString());
            }
        });
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
