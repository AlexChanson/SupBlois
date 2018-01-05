package supblois.alexc.ovh.supblois;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import supblois.alexc.ovh.supblois.dao.Message;
import supblois.alexc.ovh.supblois.dao.MyDbManager;
import supblois.alexc.ovh.supblois.network.NetFacade;

public class Conversation extends AppCompatActivity {
    private EditText messageEditText;
    private Button sendButton;
    private ListView listViewConversation;
    private MyDbManager dbManager;
    private Intent getMessageIntent;
    private MyAdapterConversation myAdapterConversation;
    private List<Message> messagesList;

    public void init() {
        messageEditText = findViewById(R.id.editTextMessage);
        sendButton = findViewById(R.id.buttonSend);
        listViewConversation = findViewById(R.id.listViewConversation);
        dbManager = MyDbManager.getInstance(this);
        getMessageIntent = getIntent();
        messagesList = dbManager.getMessageDAO().getMsgFrom(getMessageIntent.getStringExtra("account"));

        String id = null;
        if (getMessageIntent != null){
            id = getMessageIntent.getStringExtra("id");
            System.out.println(id);
        }
        myAdapterConversation = new MyAdapterConversation(this, R.layout.activity_conversation, messagesList, id);
        listViewConversation.setAdapter(myAdapterConversation);
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate CALLED");

        setContentView(R.layout.activity_conversation);
        init();

        sendButton.setOnClickListener(view -> {
            if (!messageEditText.getText().toString().equals("")) {
                boolean result = NetFacade.pushMessage(messageEditText.getText().toString(), getMessageIntent.getStringExtra("account"));
                if (result){
                    messagesList.add(new Message(0,getMessageIntent.getStringExtra("id"),new Date(0),messageEditText.getText().toString()));
                    messageEditText.setText("");
                    myAdapterConversation.notifyDataSetChanged();
                    listViewConversation.setSelection(myAdapterConversation.getCount() - 1);
                }
            }
        });

        findViewById(R.id.buttonReceive).setOnClickListener(v -> {
            //TODO forcer rafraichir la conv
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);


            System.out.println("onNewIntent CALLED");

    }
}
