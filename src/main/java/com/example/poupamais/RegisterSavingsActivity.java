package com.example.poupamais;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.poupamais.Classes.Savings;

import java.util.ArrayList;

public class RegisterSavingsActivity extends AppCompatActivity {

    private EditText et_mark, et_balance, et_fees, et_reminder;
    private Spinner spinnerMonth;
    private Button bt_save, bt_cancel;

    public static final String MODO = "MODO";
    public static final String MARK = "MARK";
    public static final String EXCLUDE = "EXCLUDE";
    public static final String BALANCE = "BALANCE";
    public static final String FEES = "FEES";
    public static final String REMINDER = "REMINDER";
    public static final String MONTH = "MONTH";

    public static final int NEW = 1;
    public static final int ALTER = 2;
//    public static final int EXCLUDE = 3;

    private int modo, aux = 0;

    public static void alterSaving(AppCompatActivity activity, Savings saving){

        Intent intent = new Intent(activity, RegisterSavingsActivity.class);

        intent.putExtra(MODO,  ALTER);
        intent.putExtra(MARK,  saving.getMark());
        intent.putExtra(BALANCE, saving.getBalance());
        intent.putExtra(FEES,  saving.getFees());
        intent.putExtra(REMINDER, saving.getReminder());
        intent.putExtra(MONTH, saving.getMonth());

        activity.startActivityForResult(intent, ALTER);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_savings);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        spinnerMonth = findViewById(R.id.spinner_month);
        et_mark = findViewById(R.id.et_mark);
        et_balance = findViewById(R.id.et_balance);
        et_fees = findViewById(R.id.et_fees);
        et_reminder = findViewById(R.id.et_reminder);
        bt_save = findViewById(R.id.bt_save);
        bt_cancel = findViewById(R.id.bt_cancel);

        if (bundle != null) {

            modo = bundle.getInt(MODO, NEW);

            if (modo == ALTER) {
                et_mark.setText(bundle.getString(MARK));
                et_balance.setText(bundle.getString(BALANCE));
                et_fees.setText(bundle.getString(FEES));
                et_reminder.setText(bundle.getString(REMINDER));
            }
        }
        setup();
        createSpinnerMonth();
    }

    public void setup(){

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel(view);
            }
        });
    }

    public void save(){
            String mark = et_mark.getText().toString();

            if (mark == null || mark.trim().isEmpty()) {
                Toast.makeText(RegisterSavingsActivity.this, R.string.insert_mark, Toast.LENGTH_SHORT).show();
                et_mark.requestFocus();
                return;
            }

            String balance = et_balance.getText().toString();

            if ( balance == null || balance.trim().isEmpty()) {
                Toast.makeText(RegisterSavingsActivity.this, R.string.insert_balance, Toast.LENGTH_SHORT).show();
                et_mark.requestFocus();
                return;
            }

            String fees = et_fees.getText().toString();

            if ( fees == null || fees.trim().isEmpty()) {
                fees = "0";
            }

            String reminder = et_reminder.getText().toString();
            String month = (String) spinnerMonth.getSelectedItem();

            Intent intent = new Intent();
            intent.putExtra(MARK, mark);
            intent.putExtra(BALANCE, balance);
            intent.putExtra(FEES, fees);
            intent.putExtra(REMINDER, reminder);
            intent.putExtra(MONTH, month);

            setResult(Activity.RESULT_OK, intent);

            finish();
        }

    public void createSpinnerMonth(){

        ArrayList<String> lista = new ArrayList<>();

        String[] month = getResources().getStringArray(R.array.month);
        for(int i = 0; i < month.length; i++){
            lista.add(month[i]);
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);

        spinnerMonth.setAdapter(adapter);
    }

    public void cancel(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.menuItemSalvar:
                save();
                return true;

            case R.id.menuItemExcluir:
//                modo = EXCLUDE;
                getIntent().putExtra(EXCLUDE,"exc");
                setResult(Activity.RESULT_OK, getIntent());

                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}