package supblois.alexc.ovh.supblois;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import supblois.alexc.ovh.supblois.dao.Message;
import supblois.alexc.ovh.supblois.dao.MyDbManager;
import supblois.alexc.ovh.supblois.network.NetFacade;

public class Conversation extends AppCompatActivity {
    private EditText messageEditText;
    private Button sendButton;
    private ListView listViewConversation;
    private MyDbManager dbManager;
    private Intent intent;
    private MyAdapterConversation myAdapterConversation;
    private List<Message> messagesList;

    public void scrollToBottom(){
        listViewConversation.setSelection(myAdapterConversation.getCount() - 1);
    }

    public void init() {
        dbManager = MyDbManager.getInstance(this);
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        messageEditText = findViewById(R.id.editTextMessage);
        sendButton = findViewById(R.id.buttonSend);
        listViewConversation = findViewById(R.id.listViewConversation);
        intent = getIntent();
        String number = intent.getStringExtra("account");
        String firstname = intent.getStringExtra("firstname");
        String lastname = intent.getStringExtra("lastname");
        messagesList = dbManager.getMessageDAO().getMsgFrom(number);

        String id = null;
        if (intent != null){
            id = intent.getStringExtra("id");
        }

        myAdapterConversation = new MyAdapterConversation(this, R.layout.activity_conversation, messagesList, id);
        listViewConversation.setAdapter(myAdapterConversation);
        scrollToBottom();

        ArrayList<Message> allMsg = dbManager.getMessageDAO().getMsgFrom(number);
        for (Message msg: allMsg){
            messagesList.add(msg);
        }

        myAdapterConversation.notifyDataSetChanged();

        messageEditText.setOnClickListener((View v) -> scrollToBottom());
        messageEditText.setOnFocusChangeListener((View v, boolean hasFocus) -> {
            if (hasFocus) scrollToBottom();
        });

        setTitle((firstname != null) ? firstname + " " + lastname.toUpperCase() : number);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate CALLED");

        setContentView(R.layout.activity_conversation);
        init();

        sendButton.setOnClickListener(view -> {
            if (!messageEditText.getText().toString().equals("")) {
                boolean result = NetFacade.pushMessage(messageEditText.getText().toString(), intent.getStringExtra("account"));
                if (result){
                    Message msg = new Message(0,
                            intent.getStringExtra("id"),
                            Calendar.getInstance().getTime(),
                            messageEditText.getText().toString());
                    messagesList.add(msg);
                    messageEditText.setText("");

                    dbManager.getMessageDAO().newMsg(msg);

                    myAdapterConversation.notifyDataSetChanged();
                    scrollToBottom();
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
