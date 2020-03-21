package com.codepath.jlin.navigationdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.codepath.jlin.navigationdemo.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private Listener mListener;
    private List<Contact> contactList;

    public interface Listener {
        void onClickContact(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public View llContainer;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tvName);
            llContainer = view.findViewById(R.id.llContainer);
        }
    }

    public ContactAdapter(Listener listener, List<Contact> contactList) {
        this.contactList = contactList;
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Contact contact = contactList.get(position);
        holder.name.setText(contact.getName());
        holder.llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickContact(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}

