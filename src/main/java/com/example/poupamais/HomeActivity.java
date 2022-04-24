package com.example.poupamais;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.poupamais.Classes.Savings;
import com.example.poupamais.uteis.RecyclerItemClickListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private Button bt_about, bt_addSavings;
    private RecyclerView recyclerHome;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Savings> savingsList;
    private SavingsAdapter savingsAdapter;
    private AlertDialog alert;

    private int positionSelected = -1;
    public static final int NEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bt_about = findViewById(R.id.bt_about);
        bt_addSavings = findViewById(R.id.bt_addSavings);
        recyclerHome = findViewById(R.id.recycler_home);

        layoutManager = new LinearLayoutManager(this);
        recyclerHome.setLayoutManager(layoutManager);
        recyclerHome.setHasFixedSize(true);
        recyclerHome.addItemDecoration(new DividerItemDecoration(this,
                LinearLayout.VERTICAL));

        savingsList = new ArrayList<>();
        savingsAdapter = new SavingsAdapter(savingsList);
        recyclerHome.setAdapter(savingsAdapter);

        setupButtons();
        setupClickRecycler();
    }

    private void setupButtons(){

        bt_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AboutActivity.class);
                startActivity(intent);
            }
        });

        bt_addSavings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), RegisterSavingsActivity.class);
                startActivityForResult(i,NEW);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        if (resultCode == Activity.RESULT_OK) {

            Bundle bundle = data.getExtras();
            String mark = bundle.getString(RegisterSavingsActivity.MARK);
            String exclude = bundle.getString(RegisterSavingsActivity.EXCLUDE);
            String balance = bundle.getString(RegisterSavingsActivity.BALANCE);
            String fees = bundle.getString(RegisterSavingsActivity.FEES);
            String reminder = bundle.getString(RegisterSavingsActivity.REMINDER);
            String month = bundle.getString(RegisterSavingsActivity.MONTH);

            Log.d("TAG", "tag" + bundle.toString());
            Log.d("TAG", "tag req " + requestCode);
            Log.d("TAG", "tag res" + resultCode);

            if(requestCode == RegisterSavingsActivity.ALTER){

                if(!exclude.isEmpty()){
                    savingsList.remove(positionSelected);
                }else{
                    Savings savings = savingsList.get(positionSelected);
                    savings.setFees(fees);
                    savings.setMark(mark);
                    savings.setReminder(reminder);
                    savings.setBalance(balance);
                    savings.setMonth(month);
                }
            } else{
                Savings saving = new Savings( mark, balance, fees, reminder, month);
                savingsList.add(saving);
            }
            savingsAdapter.notifyDataSetChanged();
        }
    }

    private void setupClickRecycler(){
        recyclerHome.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(),
                        recyclerHome,
                        new RecyclerItemClickListener.OnItemClickListener() {

                            @Override
                            public void onItemClick(View view, int position) {

                                Savings savings = HomeActivity.this.savingsList.get(position);
                                positionSelected = position;
                                alterSaving(savings);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                                Savings savings = HomeActivity.this.savingsList.get(position);

                                    AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                                    builder.setTitle(R.string.msg_delete);
                                    builder.setMessage(R.string.exclude + savings.getMonth() + R.string.Mark + savings.getMark());

                                    builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface arg0, int arg1) {
                                            savingsList.remove(position);
                                            savingsAdapter.notifyDataSetChanged();
                                        }
                                    });

                                    builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface arg0, int arg1) {
                                        }
                                    });
                                    alert = builder.create();
                                    alert.show();
                            }
                        }
                ));
    }

    private void alterSaving(Savings savings){
        RegisterSavingsActivity.alterSaving(this, savings);
    }
}