package com.example.poupamais;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poupamais.Classes.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView bt_register;
    private Button  bt_login;
    private CheckBox check_terms;
    private EditText ed_email, ed_password;

    private ArrayList<User> users = new ArrayList<>();
    public static final int NEW    = 1;
    public int i ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_register = findViewById(R.id.bt_register);
        bt_login = findViewById(R.id.bt_login);
        check_terms = findViewById(R.id.checkbox_terms);

        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);

        users.add(new User("",""));

        setup();
    }

    private void setup(){

        bt_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this, RegisterUserActivity.class);
                startActivityForResult(i, NEW);
            }
        });

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_terms.isChecked()) {
                    login();
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.terms), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        if (resultCode == Activity.RESULT_OK) {

            Bundle bundle = data.getExtras();
            String  name = bundle.getString(RegisterUserActivity.NAME);
            String  email = bundle.getString(RegisterUserActivity.EMAIL);
            String  password = bundle.getString(RegisterUserActivity.PASSWORD);
            String  state = bundle.getString(RegisterUserActivity.STATE);
            int  gender = bundle.getInt(RegisterUserActivity.GENDER);

            User user = new User(name,email, password, state,gender);
            users.add(user);
        }
    }


    private void login(){

        String email = ed_email.getText().toString();
        String password = ed_password.getText().toString();
        User userAux = new User("","","","",1);
        i = 0;

        for(; i < users.size(); i++){
          userAux= users.get(i);

          if(email.equals(userAux.getEmail().toString()) && password.equals(userAux.getPassword().toString())){
              Intent intent = new Intent(this, HomeActivity.class);
              startActivity(intent);
              break;
          }
        }
        if( i == users.size()){
            Toast.makeText(this, R.string.error_login, Toast.LENGTH_SHORT).show();
        }
    }
}
