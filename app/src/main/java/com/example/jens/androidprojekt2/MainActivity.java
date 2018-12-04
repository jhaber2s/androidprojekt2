package com.example.jens.androidprojekt2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Connector conn = new Connector("00:16:53:07:1D:00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button conbutton = findViewById(R.id.connect);
        final Button qualbutton = findViewById(R.id.qualification);
        final Button disconnect = findViewById(R.id.disconnect);
        final TextView context = findViewById(R.id.connection);
        final TextView received = findViewById(R.id.received);
        conbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(conn.connect()){
                    context.setText("Verbunden");
                }
            }
        });
        qualbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                conn.sendMessage(99);
            }
        });
        disconnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                conn.sendMessage(50);
            }
        });
    }

}
