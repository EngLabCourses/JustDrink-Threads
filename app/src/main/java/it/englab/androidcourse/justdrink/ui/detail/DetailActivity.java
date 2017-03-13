package it.englab.androidcourse.justdrink.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import it.englab.androidcourse.justdrink.R;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private static final String DRINK_ID = DetailActivity.class.getPackage() +".DRINK_ID";

    public static Intent getDetailIntent(Context context,String drinkId) {
        Intent i = new Intent(context, DetailActivity.class);
        i.putExtra(DRINK_ID,drinkId);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String drinkId = getIntent().getStringExtra(DRINK_ID);

        getFragmentManager().beginTransaction().replace(R.id.detail_container,DetailFragment.newInstance(drinkId)).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
