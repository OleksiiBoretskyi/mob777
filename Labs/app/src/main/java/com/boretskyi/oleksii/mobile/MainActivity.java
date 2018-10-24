package com.boretskyi.oleksii.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etSurname, etEmail, etPhone, etPassword, etPasswordConf;
    private Button btnSubmit;
    private Button btnChangeAct;
    private Pattern pName, pEmail, pPhone, pPassword;
    private Boolean name, surname, email, phone, password, password_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void saveInfo() {
        SharedPreferences mPrefs = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        User user = new User(etName.getText().toString(), etSurname.getText().toString(),
                etEmail.getText().toString(),etPhone.getText().toString());
        String json = gson.toJson(user);
        prefsEditor.putString("user", json);
        prefsEditor.commit();
    }

    private boolean validate_field_to_save(EditText et_field, Pattern regex) {
        if (et_field.getText().toString().matches(String.valueOf(regex))) {
            et_field.setBackgroundResource(R.drawable.norm);
            return true;
        } else {
            et_field.setBackgroundResource(R.drawable.error);
            return false;
        }
    }

    private void validation() {
        pName = Pattern.compile("[A-Z][a-z ,.'-]+$");
        pEmail = Pattern.compile("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$");
        pPhone = Pattern.compile("\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})");
        pPassword = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,}$");

        name = validate_field_to_save(etName, pName);
        surname = validate_field_to_save(etSurname, pName);
        email = validate_field_to_save(etEmail, pEmail);
        phone = validate_field_to_save(etPhone, pPhone);
        password = validate_field_to_save(etPassword, pPassword);
        password_confirm = validate_field_to_save(etPasswordConf, pPassword);
    }


    private void init() {
        etName = findViewById(R.id.et_name);
        etSurname = findViewById(R.id.et_last_name);
        btnSubmit = findViewById(R.id.btn_submit);
        btnChangeAct = findViewById(R.id.btn_change_act);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        etPasswordConf = findViewById(R.id.et_password_confr);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();

                if (name && surname && email && phone && password && password_confirm) {
                    Toast.makeText(getApplicationContext(), "Збережено", Toast.LENGTH_LONG).show();
                    saveInfo();
                } else
                    Toast.makeText(getApplicationContext(), "Заповніть всі поля", Toast.LENGTH_LONG).show();
            }
        });

        btnChangeAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(myIntent);
            }
        });
    }
}