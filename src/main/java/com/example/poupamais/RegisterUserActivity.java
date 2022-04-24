package com.example.poupamais;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.poupamais.Classes.User;

import java.util.ArrayList;

public class RegisterUserActivity extends AppCompatActivity {

    private Spinner state_spinner;
    private EditText et_name, et_email, et_password;
    private CheckBox checkBox_terms;
    private RadioGroup radioGroup;
    private RadioButton radioMas, radioFem;
    private Button bt_clear, bt_save;
    private ImageView bt_return;

    public static final String MODO = "MODO";
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static final String GENDER = "GENDER";
    public static final String STATE = "STATE";

    public int gender;
    public static final int NEW = 1;
    private int modo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        et_email = findViewById(R.id.et_email);
        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);
        state_spinner = findViewById(R.id.spinner_state);
        radioGroup = findViewById(R.id.RadioGroup);
        radioMas = findViewById(R.id.bt_radioMasc);
        radioFem = findViewById(R.id.bt_radioFem);
        checkBox_terms = findViewById(R.id.checkbox_terms);
        bt_return = findViewById(R.id.bt_return);
        bt_clear = findViewById(R.id.bt_clear);
        bt_save = findViewById(R.id.bt_save);

        setupSave();
        setupSpinner();
        setupButtonClear();
        setupReturn();
    }

    public static void novaPessoa(AppCompatActivity activity){

        Intent intent = new Intent(activity, RegisterUserActivity.class);
        intent.putExtra(MODO, NEW);
        activity.startActivityForResult(intent, NEW);
    }

    private void setupSave() {
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int res = verifica();

                if(res == 0){

                    String name = et_name.getText().toString();
                    String email = et_email.getText().toString();
                    String password = et_password.getText().toString();
                    String state  = (String) state_spinner.getSelectedItem();

                    int gender = 0;

                    switch (radioGroup.getCheckedRadioButtonId()) {
                        case R.id.bt_radioMasc:
                            gender = User.MASCULINE;
                            break;

                        case R.id.bt_radioFem:
                            gender = User.FEMALE;
                            break;
                    }

                    Intent intent = new Intent();
                    intent.putExtra(NAME, name);
                    intent.putExtra(EMAIL, email);
                    intent.putExtra(PASSWORD, password);
                    intent.putExtra(GENDER, gender);
                    intent.putExtra(STATE, state);
                    setResult(Activity.RESULT_OK, intent);

                    finish();
                }
            }
        });
    }

    private void setupSpinner(){
        ArrayList<String> lista = new ArrayList<>();

        String[] states = getResources().getStringArray(R.array.Array_states);
        for(int i = 0; i < states.length; i++){
            lista.add(states[i]);
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);

        state_spinner.setAdapter(adapter);
    }

    private void setupButtonClear(){
        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearData();
                Toast.makeText(RegisterUserActivity.this, getString(R.string.clear_data), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public int verifica(){
        if(et_name.getText().toString().trim().equals("")){
            et_name.requestFocus();
            Toast.makeText(RegisterUserActivity.this, getString(R.string.insert_name), Toast.LENGTH_SHORT).show();
            return 1;
        } if(et_email.getText().toString().trim().equals("")){
            Toast.makeText(RegisterUserActivity.this, getString(R.string.insert_email), Toast.LENGTH_SHORT).show();
            et_email.requestFocus();
            return 1;
        }  if(et_password.getText().toString().trim().equals("")){
            et_password.requestFocus();
            Toast.makeText(RegisterUserActivity.this, getString(R.string.insert_password), Toast.LENGTH_SHORT).show();
            return 1;
        }  if(!checkBox_terms.isChecked()){
            checkBox_terms.requestFocus();
            Toast.makeText(RegisterUserActivity.this, getString(R.string.terms), Toast.LENGTH_SHORT).show();
            return 1;
        }

        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.bt_radioMasc:
                Toast.makeText(RegisterUserActivity.this, getString(R.string.save_data), Toast.LENGTH_SHORT).show();
                break;

            case R.id.bt_radioFem:
                Toast.makeText(RegisterUserActivity.this, getString(R.string.save_data), Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(RegisterUserActivity.this, getString(R.string.insert_genero), Toast.LENGTH_SHORT).show();
                return 1;
        }
        return 0;
    }

    private void clearData(){
        et_email.setText("");
        et_name.setText("");
        et_password.setText("");
        radioMas.setChecked(false);
        radioFem.setChecked(false);
        checkBox_terms.setChecked(false);
        radioGroup.clearCheck();
        state_spinner.setSelection(0);
    }

    private void setupReturn(){
        bt_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterUserActivity.this, MainActivity.class);
                finish();
                startActivity(i);
            }
        });
    }
}