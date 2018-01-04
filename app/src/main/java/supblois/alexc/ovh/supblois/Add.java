package supblois.alexc.ovh.supblois;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.SQLException;

import supblois.alexc.ovh.supblois.dao.MyDbManager;
import supblois.alexc.ovh.supblois.network.Friend;
import supblois.alexc.ovh.supblois.network.NetFacade;

public class Add extends Activity {
    private TextView addUserTextView;
    private TextView phoneNumberTextView;
    private EditText phoneNumberEditText;
    private Button addUserButton;
    private MyDbManager dbManager;
    private Friend friend;

    public void init() {
        addUserTextView = (TextView) findViewById(R.id.addUserTextView);
        phoneNumberTextView = (TextView) findViewById(R.id.phoneNumberTextView);
        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        addUserButton = (Button) findViewById(R.id.addUserButton);

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
        setContentView(R.layout.acitivity_add);
        init();

        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phoneNumberEditText.getText().toString() != null) {
                    friend = NetFacade.addFriend(phoneNumberEditText.getText().toString());
                    if (friend != null) {
                        dbManager.getAccountDAO().addAccount(friend.phone, friend.prenom, friend.nom);
                    }
                }
            }
        });
    }
}
