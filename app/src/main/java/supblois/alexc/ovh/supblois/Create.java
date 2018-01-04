package supblois.alexc.ovh.supblois;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import supblois.alexc.ovh.supblois.dao.Message;
import supblois.alexc.ovh.supblois.dao.MyDbManager;
import supblois.alexc.ovh.supblois.dao.RegAccount;
import supblois.alexc.ovh.supblois.network.NetFacade;

public class Create extends AppCompatActivity {
    private TextView createTextView;
    private TextView accountAlreadyExists;
    private TextView phoneNumberTextView;
    private EditText phoneNumberEditText;
    private TextView passwordTextView;
    private EditText passwordEditText;
    private TextView firstNameTextView;
    private EditText firstNameEditText;
    private TextView lastNameTextView;
    private EditText lastNameEditText;
    private Button createButton;
    private MyDbManager dbManager;

    public void init() {
        createTextView = (TextView) findViewById(R.id.addUserTextView);
        accountAlreadyExists = (TextView) findViewById(R.id.accountAlreadyExistsTextView);
        phoneNumberTextView = (TextView) findViewById(R.id.phoneNumberTextView);
        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        passwordTextView = (TextView) findViewById(R.id.passwordCreateTextView);
        passwordEditText = (EditText) findViewById(R.id.passwordCreateEditText);
        firstNameTextView = (TextView) findViewById(R.id.textViewFirstName);
        firstNameEditText = (EditText) findViewById(R.id.editTextFirstName);
        lastNameTextView = (TextView) findViewById(R.id.textViewLastName);
        lastNameEditText = (EditText) findViewById(R.id.editTextLastName);
        createButton = (Button) findViewById(R.id.createAccountButton);
        dbManager = MyDbManager.getInstance(this);
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        init();

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!phoneNumberEditText.getText().toString().equals("") && !passwordEditText.getText().toString().equals("") && !firstNameEditText.getText().toString().equals("") && !lastNameEditText.getText().toString().equals("")) {
                    NetFacade.createAccount(phoneNumberEditText.getText().toString(), firstNameEditText.getText().toString(), lastNameEditText.getText().toString(), passwordEditText.getText().toString());
                }
                finish();
            }
        });
    }
}
