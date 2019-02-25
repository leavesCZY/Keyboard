package leavesc.hello.keyboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(leavesc.hello.keyboard.R.layout.activity_main);
        findViewById(leavesc.hello.keyboard.R.id.btn_unresolved).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UnresolvedActivity.class));
            }
        });
        findViewById(leavesc.hello.keyboard.R.id.btn_resolved).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ResolvedActivity.class));
            }
        });
    }

}
