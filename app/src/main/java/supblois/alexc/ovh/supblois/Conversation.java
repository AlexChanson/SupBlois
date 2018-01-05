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
import java.time.Instant;
import java.util.Date;
import java.util.List;

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
    private TextView convFirstNameView;
    private TextView convLastNameView;

    public void init() {
        messageEditText = (EditText) findViewById(R.id.editTextMessage);
        sendButton = (Button) findViewById(R.id.buttonSend);
        listViewConversation = (ListView) findViewById(R.id.listViewConversation);
        dbManager = MyDbManager.getInstance(this);
        getMessageIntent = getIntent();
        messagesList = dbManager.getMessageDAO().getMsgFrom(getMessageIntent.getStringExtra("account"));
        convFirstNameView = findViewById(R.id.convFirstNameTextView);
        convLastNameView = findViewById(R.id.convLastNameTextView);


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
        setContentView(R.layout.activity_conversation);
        init();


        String firstname = getMessageIntent.getStringExtra("firstname");
        String lastname = getMessageIntent.getStringExtra("lastname");

        if (firstname.equals("") && lastname.equals("")){
            convFirstNameView.setText(getMessageIntent.getStringExtra("account"));
        }
        else{
            convFirstNameView.setText(firstname);
            convLastNameView.setText(lastname);
        }

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
    }
}
