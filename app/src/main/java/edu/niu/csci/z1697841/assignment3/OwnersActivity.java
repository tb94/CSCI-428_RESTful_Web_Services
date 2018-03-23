package edu.niu.csci.z1697841.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class OwnersActivity extends AppCompatActivity {

    private ArrayList<Owner> owners; // = new ArrayList<>();
    private RecyclerView recyclerView;
    private OwnersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_owners);
        recyclerView = findViewById(R.id.recycler_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            owners = new RESTfulGET().execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        adapter = new OwnersAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOwner();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void addOwner() {
        Intent intent = new Intent(this, AddOwnerActivity.class);
        startActivity(intent);
    }

    private class OwnersAdapter extends RecyclerView.Adapter<OwnersAdapter.OwnerViewHolder> {

        @NonNull
        @Override
        public OwnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.owner_list_row, parent, false);
            return new OwnerViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull OwnerViewHolder holder, int position) {
            Owner owner = owners.get(position);
            holder.bindView(owner);
        }

        @Override
        public int getItemCount() {
            return owners.size();
        }

        public class OwnerViewHolder extends RecyclerView.ViewHolder {
            TextView nameTV;
            TextView idTV;

            public OwnerViewHolder(View itemView) {
                super(itemView);
                nameTV = itemView.findViewById(R.id.owner_name);
                idTV = itemView.findViewById(R.id.owner_id);
            }

            public void bindView(Owner owner) {
                nameTV.setText(owner.getFirstName() + " " + owner.getLastName());
                idTV.setText(String.valueOf(owner.getOid()));
            }
        }
    }
}
