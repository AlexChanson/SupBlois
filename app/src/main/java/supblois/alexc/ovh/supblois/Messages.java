package supblois.alexc.ovh.supblois;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import supblois.alexc.ovh.supblois.dao.MyDbManager;
import supblois.alexc.ovh.supblois.dao.RegAccount;
import supblois.alexc.ovh.supblois.network.NetTask;

public class Messages extends AppCompatActivity {
    private ListView listViewMessage;
    private MyAdapterMessage myAdapterMessage;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        init();
        listViewMessage = (ListView)findViewById(R.id.listViewMessage);
        List<RegAccount> regAccountsList = new ArrayList<>();
        regAccountsList = dbManager.getAccountDAO().getAll();
        myAdapterMessage = new MyAdapterMessage(this, R.layout.layout_messages, regAccountsList);
        listViewMessage.setAdapter(myAdapterMessage);
        myAdapterMessage.notifyDataSetChanged();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAdd = new Intent(getBaseContext(), Add.class);
                startActivity(intentAdd);
            }
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
