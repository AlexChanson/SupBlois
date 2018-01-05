package supblois.alexc.ovh.supblois;

import android.content.Intent;
import android.graphics.Color;
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
import java.sql.SQLException;
import java.util.ArrayList;

import supblois.alexc.ovh.supblois.dao.ConnectedAccount;
import supblois.alexc.ovh.supblois.dao.MyDbManager;
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
    private MyDbManager myDbManager;

    public void init() {
        loginImageView = findViewById(R.id.loginImageView);
        accountTextView = findViewById(R.id.accountLoginTextView);
        passwordTextView = findViewById(R.id.passwordLoginTextView);
        accountNotExists = findViewById(R.id.accountNotExistsTextView);
        accountEditText = findViewById(R.id.accountLoginEditText);
        passwordEditText = findViewById(R.id.passwordLoginEditText);
        loginButton = findViewById(R.id.loginButton);
        createAccountButton = findViewById(R.id.createAccountLoginButton);
        myDbManager = MyDbManager.getInstance(getBaseContext());
        try {
            myDbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();


        ArrayList<ConnectedAccount> connectedAccounts = myDbManager.getConnectedDAO()
                                                                    .getAllConnected();

        if (!connectedAccounts.isEmpty()){
            ConnectedAccount acc = connectedAccounts.get(0);
            System.out.println("Logged account found: "+acc.getNum());
            accountEditText.setText(acc.getNum(), TextView.BufferType.EDITABLE);
            passwordEditText.setText(acc.getPasswd(), TextView.BufferType.EDITABLE);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nb = accountEditText.getText().toString();
                String pwd =  passwordEditText.getText().toString();

                boolean login = NetFacade.login(nb, Utility.hashSHA256(pwd));
                if (login) {
                    NetFacade.registerFbToken(getApplicationContext());
                    System.out.println("logged successfully!");
                    intentLogin = new Intent (Login.this, Messages.class);
                    intentLogin.putExtra("id", nb);
                    myDbManager.getConnectedDAO().updatePswd(nb, pwd);
                    startActivity(intentLogin);
                }
                else {
                    accountNotExists.setText(R.string.login_failed_msg);
                    accountNotExists.setTextColor(Color.RED);
                    System.out.println("login failed!");
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