package supblois.alexc.ovh.supblois;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

import supblois.alexc.ovh.supblois.dao.MyDbManager;
import supblois.alexc.ovh.supblois.dao.RegAccount;

public class Create extends AppCompatActivity {
    private TextView createTextView;
    private TextView accountAlreadyExists;
    private TextView accountTextView;
    private EditText accountEditText;
    private TextView passwordTextView;
    private EditText passwordEditText;
    private Button createButton;
    private MyDbManager dbManager;
    //TODO

    public void init() {
        createTextView = (TextView) findViewById(R.id.addUserTextView);
        accountAlreadyExists = (TextView) findViewById(R.id.accountAlreadyExistsTextView);
        accountTextView = (TextView) findViewById(R.id.accountCreateTextView);
        accountEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        passwordTextView = (TextView) findViewById(R.id.passwordCreateTextView);
        passwordEditText = (EditText) findViewById(R.id.passwordCreateEditText);
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
                ArrayList<RegAccount> accounts = dbManager.getAccountDAO().getAll();
                for (int i=0; i<accounts.size();i++) {
                    System.out.println(accounts.get(i).firstName);
                    System.out.println(accounts.get(i).lastName);
                    System.out.println(accounts.get(i).num);

                }
                finish();
            }
        });
    }
}
