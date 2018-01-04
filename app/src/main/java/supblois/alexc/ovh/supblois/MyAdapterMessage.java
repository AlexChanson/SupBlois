package supblois.alexc.ovh.supblois;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import supblois.alexc.ovh.supblois.dao.MyDB;
import supblois.alexc.ovh.supblois.dao.MyDbManager;
import supblois.alexc.ovh.supblois.dao.RegAccount;

/**
 * Created by Christopher on 02/01/2018.
 */

public class MyAdapterMessage extends ArrayAdapter<RegAccount> {
    private Context context;
    private List<RegAccount> regAccountsList = new ArrayList<>();
    TextView textViewFirstName;
    TextView textViewLastName;
    TextView textViewUnread;
    ImageButton deleteButton;
    Activity parentActivity;


    public MyAdapterMessage (Context context, int ressource,List<RegAccount> regAccountsList, Activity parentActivity) {
        super(context, ressource);
        this.regAccountsList = regAccountsList;
        this.context = context;
        this.parentActivity = parentActivity;
    }

    @Override
    public int getCount() {
        return regAccountsList.size();
    }

    @Override
    public RegAccount getItem(int pos) {
        return regAccountsList.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (getCount() <= 0){
            return view;
        }

        RegAccount regAccount = getItem(i);
        System.out.println("Drawing account : "+ regAccount.getNum() +" "+regAccount.getFirstName());
        View rowView = view;
        if (rowView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            rowView = layoutInflater.inflate(R.layout.layout_messages, viewGroup, false);
        }

        if (regAccount != null) {
            textViewFirstName = (TextView) rowView.findViewById(R.id.textViewFirstName);
            textViewLastName = (TextView) rowView.findViewById(R.id.textViewLastName);
            textViewUnread = (TextView) rowView.findViewById(R.id.unreadTextView);
            deleteButton = (ImageButton) rowView.findViewById(R.id.deleteAccountButton);
        }

        if (regAccount.getNum() == null || regAccount.getNum().equals("")){
            if (textViewFirstName != null){
                textViewFirstName.setText("NUMBER ERROR");
            }
        }
        else if (regAccount.getFirstName().equals("") && regAccount.getLastName().equals("")) {
            if (textViewFirstName != null) {
                textViewFirstName.setText(regAccount.getNum());
                textViewLastName.setText("");
            }
        }
        else {
            if (textViewFirstName != null) {
                textViewFirstName.setText(regAccount.getFirstName());
            }

            if (textViewLastName != null) {
                textViewLastName.setText(regAccount.getLastName());
            }

            if (textViewUnread != null) {
                int unreadMessages = regAccount.getUnreadMsg();
                if (unreadMessages > 0) {
                    textViewUnread.setText(String.valueOf(unreadMessages));
                } else {
                    textViewUnread.setText("");
                }
            }
        }

        View.OnClickListener lmda = textViewLmbda ->{
            Intent intent = new Intent(getContext(), Conversation.class);
            intent.putExtra("account", regAccount.getNum());
            intent.putExtra("firstname", regAccount.getFirstName());
            intent.putExtra("lastname", regAccount.getLastName());
            System.out.println("Going into conversation mode");
            Intent parentIntent = parentActivity.getIntent();
            intent.putExtra("id", parentIntent.getStringExtra("id"));
            parentActivity.startActivity(intent);
        };
        textViewFirstName.setOnClickListener(lmda);
        textViewLastName.setOnClickListener(lmda);

        if (deleteButton != null) {
            System.out.println("Addind button listener for "+regAccount.getNum()+" "+regAccount.getFirstName());
            deleteButton.setOnClickListener(view1 -> {
                Boolean result = MyDbManager.getInstance(context).getAccountDAO().deleteByNumber(regAccount.getNum());
                System.out.println("deleting "+regAccount.getNum()+ " : ");
                System.out.println(result);
                RegAccount deleted = regAccountsList.remove(i);
                System.out.println("deleted :" + deleted.getNum()+ " "+deleted.getFirstName());
                MyAdapterMessage.this.notifyDataSetChanged();
            });
        }
        else {
            System.out.println("strange, button is null...");
        }
        return rowView;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<RegAccount> getRegAccountsList() {
        return regAccountsList;
    }

    public void setRegAccountsList(List<RegAccount> regAccountsList) {
        this.regAccountsList = regAccountsList;
    }

    public TextView getTextViewFirstName() {
        return textViewFirstName;
    }

    public void setTextViewFirstName(TextView textViewFirstName) {
        this.textViewFirstName = textViewFirstName;
    }

    public TextView getTextViewLastName() {
        return textViewLastName;
    }

    public void setTextViewLastName(TextView textViewLastName) {
        this.textViewLastName = textViewLastName;
    }
}
