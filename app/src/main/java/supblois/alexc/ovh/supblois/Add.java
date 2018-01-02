package supblois.alexc.ovh.supblois;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Add extends Activity {
    private TextView addUserTextView;
    private TextView phoneNumberTextView;
    private EditText phoneNumberEditText;
    private Button addUserButton;

    public void init() {
        addUserTextView = (TextView) findViewById(R.id.addUserTextView);
        phoneNumberTextView = (TextView) findViewById(R.id.phoneNumberTextView);
        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        addUserButton = (Button) findViewById(R.id.addUserButton);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO TEST
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_add);
        init();
    }
}
