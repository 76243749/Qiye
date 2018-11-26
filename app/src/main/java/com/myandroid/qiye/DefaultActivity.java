package com.myandroid.qiye;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DefaultActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnGoindex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        btnGoindex = (Button) findViewById(R.id.btn_goindex);
        btnGoindex.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_goindex:
                Intent intent = new Intent(DefaultActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
