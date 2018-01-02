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

import supblois.alexc.ovh.supblois.dao.RegAccount;

/**
 * Created by Christopher on 02/01/2018.
 */

public class MyAdapterMessage extends ArrayAdapter<RegAccount> {
    private Context context;
    private List<RegAccount> regAccountsList = new ArrayList<>();
    TextView textViewFirstName;
    TextView textViewLastName;

    public MyAdapterMessage (Context context, int ressource,List<RegAccount> regAccountsList) {
        super(context, ressource, regAccountsList);
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
        RegAccount regAccount = getItem(i);

        LayoutInflater layoutInflater;
        layoutInflater = LayoutInflater.from(getContext());
        View rowView = layoutInflater.inflate(R.layout.layout_messages, viewGroup, false);


        if (regAccount != null) {
            textViewFirstName = (TextView) view.findViewById(R.id.textViewFirstName);
            textViewLastName = (TextView) view.findViewById(R.id.textViewLastName);
        }

        if (textViewFirstName != null) {
            textViewFirstName.setText(regAccount.getFirstName());
        }
        else{
            System.out.println("pas de firstname");
        }

        if (textViewLastName != null) {
            textViewLastName.setText(regAccount.getLastName());
        }
        else{
            System.out.println("pas de lastname");
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
