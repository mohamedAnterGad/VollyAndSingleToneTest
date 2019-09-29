package com.example.vollytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends ArrayAdapter<Data> {

   // public static  ArrayList<Branches> branches ;

    public DataAdapter(@NonNull Context context, int resource, @NonNull List<Data> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_design, parent, false);

        TextView name = convertView.findViewById(R.id.name);
        TextView ID = convertView.findViewById(R.id.id);
        LinearLayout layout = convertView.findViewById(R.id.layout);

         final ArrayList<Branches> branches = getItem(position).getBranches();


        name.setText(getItem(position).getName());
        ID.setText(getItem(position).getId());

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent go = new Intent(getContext(),MapsActivity.class);

                Bundle bundle =new Bundle();
                bundle.putSerializable("branches",branches);

                go.putExtras(bundle);
                //branches=new ArrayList<>();
                //branches = getItem(position).getBranches();
                getContext().startActivity(go);

            }
        });


        return convertView;
    }
}
