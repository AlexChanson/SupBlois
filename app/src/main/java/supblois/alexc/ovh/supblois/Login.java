package supblois.alexc.ovh.supblois;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import supblois.alexc.ovh.supblois.network.NetFacade;

public class Login extends AppCompatActivity {
    private ImageView loginImageView;
    private TextView accountTextView;
    private TextView passwordTextView;
    private TextView accountNotExists;
    private EditText accountEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button createAccountButton;
    private Intent intentCreate;

    public void init() {
        loginImageView = findViewById(R.id.loginImageView);
        accountTextView = findViewById(R.id.accountLoginTextView);
        passwordTextView = findViewById(R.id.passwordLoginTextView);
        accountNotExists = findViewById(R.id.accountNotExistsTextView);
        accountEditText = findViewById(R.id.accountLoginEditText);
        passwordEditText = findViewById(R.id.passwordLoginEditText);
        loginButton = findViewById(R.id.loginButton);
        createAccountButton = findViewById(R.id.createAccountLoginButton);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nb = accountEditText.getText().toString();
                String pwd =  passwordEditText.getText().toString();
                String hash = "";

                try {
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] h = digest.digest(pwd.getBytes(StandardCharsets.UTF_8));
                    hash = toHexString(h);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                //TODO Login handling
                System.out.println(NetFacade.login(nb, hash));
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentCreate = new Intent(Login.this, Create.class);
                startActivity(intentCreate);
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