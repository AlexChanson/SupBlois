package supblois.alexc.ovh.supblois;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import supblois.alexc.ovh.supblois.dao.Message;

/**
 * Created by Christopher on 02/01/2018.
 */

public class MyAdapterConversation extends ArrayAdapter<Message> {
    private Context context;
    private List<Message>  messagesList = new ArrayList<>();
    private TextView textViewMe;
    private TextView textViewOther;
    private String id;

    public MyAdapterConversation (Context context, int ressource, List<Message> messagesList, String id) {
        super(context, ressource);
        this.context = context;
        this.messagesList = messagesList;
        this.id = id;
    }

    @Override
    public int getCount() {
        return messagesList.size();
    }

    @Override
    public Message getItem(int pos) {
        return messagesList.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Message message = getItem(i);
        View rowView = view;

        if (rowView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            rowView = layoutInflater.inflate(R.layout.layout_conversation, viewGroup, false);
        }

        if (message != null) {
            textViewMe = (TextView) rowView.findViewById(R.id.textViewMe);
            textViewOther = (TextView) rowView.findViewById(R.id.textViewOther);
        }
        System.out.println(message.getSenderId());
        if (message.getSenderId().equals(id)) {

            if (textViewMe != null) {
                textViewMe.setText(message.getContent());
                textViewOther.setText("");
                textViewOther.setBackground(null);
            } else {
                if (textViewOther != null) {
                    textViewOther.setText(message.getContent());
                    textViewMe.setText("");
                    textViewMe.setBackground(null);
                }
            }
        }
        return rowView;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Message> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(List<Message> messagesList) {
        this.messagesList = messagesList;
    }

    public TextView getTextViewMe() {
        return textViewMe;
    }

    public void setTextViewMe(TextView textViewMe) {
        this.textViewMe = textViewMe;
    }

    public TextView getTextViewOther() {
        return textViewOther;
    }

    public void setTextViewOther(TextView textViewOther) {
        this.textViewOther = textViewOther;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
