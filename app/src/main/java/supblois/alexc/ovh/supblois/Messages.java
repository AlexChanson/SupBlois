package supblois.alexc.ovh.supblois;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.List;

import supblois.alexc.ovh.supblois.dao.MyDbManager;
import supblois.alexc.ovh.supblois.dao.RegAccount;

public class Messages extends AppCompatActivity {
    private ListView listViewMessage;
    private AdapterConvList adapterConvList;
    private MyDbManager dbManager;

    public void init() {
        dbManager = MyDbManager.getInstance(this);
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_messages);
        init();
        listViewMessage = (ListView)findViewById(R.id.listViewMessage);
        List<RegAccount> regAccountsList = dbManager.getAccountDAO().getAll();
        adapterConvList = new AdapterConvList(this, R.layout.layout_messages, regAccountsList, this);
        listViewMessage.setAdapter(adapterConvList);

        adapterConvList.notifyDataSetChanged();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intentAdd = new Intent(getBaseContext(), Add.class);
            startActivity(intentAdd);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_messages, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
