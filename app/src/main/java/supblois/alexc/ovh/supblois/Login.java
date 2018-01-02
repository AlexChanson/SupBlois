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
    private Intent intentLogin;

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
                //TODO Login handling
                System.out.println(NetFacade.login(nb, Utility.hashSHA256(pwd)));
                boolean login = NetFacade.login(nb, Utility.hashSHA256(pwd));
                if (login) {
                    intentLogin = new Intent (Login.this, Messages.class);
                    startActivity(intentLogin);
                }
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


}