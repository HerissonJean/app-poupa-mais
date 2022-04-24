package com.example.poupamais;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.poupamais.Classes.Savings;

import java.util.List;

public class SavingsAdapter extends RecyclerView.Adapter<SavingsAdapter.MyViewHolder> {

    private List<Savings> lista;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_balance;
        TextView tv_month;
        TextView tv_fees;
        TextView tv_target;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_month = itemView.findViewById(R.id.tv_month);
            tv_balance = itemView.findViewById(R.id.tv_balance);
            tv_fees = itemView.findViewById(R.id.tv_fees);
            tv_target = itemView.findViewById(R.id.tv_target);
        }
    }

    public SavingsAdapter(List<Savings> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemLista = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_adapter,
                        viewGroup,
                        false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Savings savings = lista.get(i);

        myViewHolder.tv_month.setText(savings.getMonth());
        myViewHolder.tv_balance.setText(String.valueOf(savings.getBalance()));
        myViewHolder.tv_fees.setText(String.valueOf(savings.getFees()));
        myViewHolder.tv_target.setText(String.valueOf(savings.getMark()));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}

