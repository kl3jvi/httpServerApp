package com.kl3jvi.localwebserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.angads25.toggle.model.ToggleableView;
import com.github.angads25.toggle.widget.LabeledSwitch;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.myswitch);
        item.setActionView(R.layout.switchery);

        LabeledSwitch labeledSwitch = item.getActionView().findViewById(R.id.switchKey);
        labeledSwitch.setOnToggledListener((toggleableView, isOn) -> {
            Server server = new Server(8080);
            if(isOn){
                try {
                    server.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"It's oFFF babe"+e,Toast.LENGTH_SHORT).show();
                }
            } else {
                if(server.wasStarted()){
                    server.stop();
                }
            }
        });
        return true;
    }


}