package com.boretskyi.oleksii.mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button clear, sayHello;
    private EditText name;
    private TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clear = findViewById(R.id.btn_clear);
        sayHello = findViewById(R.id.btn_show);
        name = findViewById(R.id.et_name);
        tvName = findViewById(R.id.tv_hello);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
            }
        });
        sayHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Введіть імя", Toast.LENGTH_SHORT).show();
                } else tvName.setText("Привіт " + name.getText().toString());
            }
        });
    }
}
