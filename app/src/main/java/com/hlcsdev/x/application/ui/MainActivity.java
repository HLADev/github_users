package com.hlcsdev.x.application.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.hlcsdev.x.application.R;
import com.hlcsdev.x.application.datamodels.User;
import com.hlcsdev.x.application.ui.details.DetailsFragment;
import com.hlcsdev.x.application.ui.mainlist.MainFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentCallback, DetailsFragment.DetailsFragmentCallback {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onItemClick(User user) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new DetailsFragment().newInstance(user))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onStartMainFragment(int title) {
        setTitle(title);

        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }


    @Override
    public void onStartDetailsFragment(int title) {
        setTitle(title);

        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
