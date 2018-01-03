package supblois.alexc.ovh.supblois;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import supblois.alexc.ovh.supblois.dao.Message;
import supblois.alexc.ovh.supblois.dao.MyDbManager;

public class Conversation extends AppCompatActivity {
    private EditText messageEditText;
    private Button sendButton;
    private MyDbManager dbManager;
    private Intent getMessageIntent;
    private MyAdapterConversation myAdapterConversation;

    public void init() {
        messageEditText = (EditText) findViewById(R.id.messageEditText);
        sendButton = (Button) findViewById(R.id.sendButton);
        dbManager = MyDbManager.getInstance(this);
        List<Message> messagesList = new ArrayList<>();
        messagesList = dbManager.getMessageDAO().getAll();
        String id = getMessageIntent.getStringExtra("id");
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
    }
}
