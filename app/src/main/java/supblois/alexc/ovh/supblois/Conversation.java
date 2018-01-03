package supblois.alexc.ovh.supblois;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import supblois.alexc.ovh.supblois.dao.Message;
import supblois.alexc.ovh.supblois.dao.MyDbManager;
import supblois.alexc.ovh.supblois.network.NetFacade;

public class Conversation extends AppCompatActivity {
    private EditText messageEditText;
    private Button sendButton;
    private MyDbManager dbManager;
    private Intent getMessageIntent;
    private MyAdapterConversation myAdapterConversation;

    public void init() {
        messageEditText = (EditText) findViewById(R.id.editTextMessage);
        sendButton = (Button) findViewById(R.id.buttonSend);
        dbManager = MyDbManager.getInstance(this);
        List<Message> messagesList = dbManager.getMessageDAO().getAll();
        String id = null;
        if (getMessageIntent != null){
            id = getMessageIntent.getStringExtra("id");
        }
        myAdapterConversation = new MyAdapterConversation(this, R.layout.activity_conversation, messagesList, id);
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        init();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (messageEditText.getText().toString() != null) {
                    //TODO Envoyer message
                }
            }
        });
    }
}
