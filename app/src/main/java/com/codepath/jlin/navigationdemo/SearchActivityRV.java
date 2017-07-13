package com.codepath.jlin.navigationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.jlin.navigationdemo.helper.DemoHelper;
import com.codepath.jlin.navigationdemo.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class SearchActivityRV extends AppCompatActivity implements ContactAdapterRV.Listener {

    List<Contact> mData = new ArrayList<>();
    ContactAdapterRV mAdapter;
    RecyclerView mRecylerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_rv);

        //enable navigation back to parent activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mData = DemoHelper.createContactList();
        mAdapter = new ContactAdapterRV(this, mData);
        mRecylerView = (RecyclerView) findViewById(R.id.rvContacts);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecylerView.setLayoutManager(mLayoutManager);

        mRecylerView.setItemAnimator(new DefaultItemAnimator());
        mRecylerView.setAdapter(mAdapter);
    }

    private void finishActivityWithResult(int position) {
        Contact contact = mData.get(position);
        Intent data = new Intent();
        data.putExtra("contact", contact);
        data.putExtra("code", MainActivity.REQUEST_CODE);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onClickContact(int position) {
        finishActivityWithResult(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miAdd:
                Toast.makeText(this, "Add this feature", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
