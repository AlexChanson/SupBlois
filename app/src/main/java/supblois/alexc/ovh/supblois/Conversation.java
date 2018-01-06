package supblois.alexc.ovh.supblois;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import supblois.alexc.ovh.supblois.dao.Message;
import supblois.alexc.ovh.supblois.dao.MyDbManager;
import supblois.alexc.ovh.supblois.network.NetFacade;

public class Conversation extends AppCompatActivity {
    private EditText messageEditText;
    private ListView listViewConversation;
    private MyDbManager dbManager;
    private Intent intent;
    private MyAdapterConversation myAdapterConversation;
    private List<Message> messagesList;
    String number;

    public void scrollToBottom(){
        listViewConversation.setSelection(myAdapterConversation.getCount() - 1);
    }

    public void init() {
        setContentView(R.layout.activity_conversation);
        intent = getIntent();
        dbManager = MyDbManager.getInstance(this);
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        number = intent.getStringExtra("account");
        messagesList = dbManager.getMessageDAO().getMsgFrom(number);
        messageEditText = findViewById(R.id.editTextMessage);
        listViewConversation = findViewById(R.id.listViewConversation);

        //listViewConversation.setOnScrollChangeListener(this);


        String firstname = intent.getStringExtra("firstname");
        String lastname = intent.getStringExtra("lastname");

        String id = intent != null ? intent.getStringExtra("id") : null;

        myAdapterConversation = new MyAdapterConversation(this, R.layout.activity_conversation, messagesList, id);
        listViewConversation.setAdapter(myAdapterConversation);
        scrollToBottom();


        messageEditText.setOnClickListener((View v) -> scrollToBottom());
        messageEditText.setOnFocusChangeListener((View v, boolean hasFocus) -> {
            if (hasFocus) scrollToBottom();
        });

        setTitle((firstname != null) ? firstname + " " + lastname.toUpperCase() : number);

        findViewById(R.id.buttonSend).setOnClickListener(view -> {
            String toSend = messageEditText.getText().toString();
            if (!toSend.equals("")) {
                if (NetFacade.pushMessage(toSend, number)){
                    Date time = Calendar.getInstance().getTime();
                    Message msg = new Message(0,
                            number,
                            time,
                            true,
                            toSend);
                    messageEditText.setText("");
                    dbManager.getMessageDAO().newMsg(msg);
                    messagesList.add(msg);
                    myAdapterConversation.notifyDataSetChanged();
                    scrollToBottom();
                }
            }
        });

        findViewById(R.id.buttonReceive).setOnClickListener(v -> {
            messagesList.clear();
            messagesList.addAll(dbManager.getMessageDAO().getMsgFrom(number));
            myAdapterConversation.notifyDataSetChanged();
        });

        myAdapterConversation.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);


            System.out.println("onNewIntent CALLED");

    }

}
