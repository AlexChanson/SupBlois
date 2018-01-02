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

        if (view == null) {
            LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.layout_messages, null);
        }

        if (regAccount != null) {
            textViewFirstName = (TextView) view.findViewById(R.id.textViewFirstName);
            textViewLastName = (TextView) view.findViewById(R.id.textViewLastName);
        }

        if (textViewFirstName != null) {
            textViewFirstName.setText(regAccount.getFirstName());
        }

        if (textViewLastName != null) {
            textViewLastName.setText(regAccount.getLastName());
        }

        return view;
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
