package supblois.alexc.ovh.supblois;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Create extends AppCompatActivity {
    private TextView createTextView;
    private TextView accountAlreadyExists;
    private TextView accountTextView;
    private EditText accountEditText;
    private TextView passwordTextView;
    private EditText passwordEditText;
    private Button createButton;


    public void init() {
        createTextView = (TextView) findViewById(R.id.createTextView);
        accountAlreadyExists = (TextView) findViewById(R.id.accountAlreadyExistsTextView);
        accountTextView = (TextView) findViewById(R.id.accountCreateTextView);
        accountEditText = (EditText) findViewById(R.id.accountCreateEditText);
        passwordTextView = (TextView) findViewById(R.id.passwordCreateTextView);
        passwordEditText = (EditText) findViewById(R.id.passwordCreateEditText);
        createButton = (Button) findViewById(R.id.createAccountButton);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        init();
    }
}
