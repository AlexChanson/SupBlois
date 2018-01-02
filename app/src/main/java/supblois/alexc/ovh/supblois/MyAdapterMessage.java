package supblois.alexc.ovh.supblois;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import supblois.alexc.ovh.supblois.dao.RegAccount;

/**
 * Created by Christopher on 02/01/2018.
 */

public class MyAdapterMessage extends BaseAdapter {
    private Context context;
    private List<RegAccount> regAccountList = new ArrayList<>();
    TextView textViewFirstName;
    TextView textViewLastName;

    @Override
    public int getCount() {
        return regAccountList.size();
    }

    @Override
    public RegAccount getItem(int pos) {
        return regAccountList.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RegAccount p = getItem(i);

        if (p != null) {
            textViewFirstName = (TextView) view.findViewById(R.id.textViewFirstName);
            textViewLastName = (TextView) view.findViewById(R.id.textViewLastName);
        }

        if (textViewFirstName != null) {
            textViewFirstName.setText(p.getFirstName());
        }

        if (textViewLastName != null) {
            textViewLastName.setText(p.getLastName());
        }

        return view;
    }
}
