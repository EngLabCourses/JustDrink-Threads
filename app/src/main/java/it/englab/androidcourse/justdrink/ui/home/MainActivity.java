package it.englab.androidcourse.justdrink.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import it.englab.androidcourse.justdrink.R;
import it.englab.androidcourse.justdrink.listeners.DrinkListener;
import it.englab.androidcourse.justdrink.ui.detail.DetailActivity;
import it.englab.androidcourse.justdrink.ui.detail.DetailFragment;

public class MainActivity extends AppCompatActivity implements DrinkListener {

    private boolean isHorizontal;
    private String drinkIdSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FrameLayout detailContainer = (FrameLayout) findViewById(R.id.detail_container);
        if (detailContainer != null) { //detailContainer non è null, dunque stiamo utilizzando il layout land
            isHorizontal = true;
        }

        getFragmentManager().beginTransaction().replace(R.id.main_container, MainFragment.newInstance(isHorizontal)).commit();
    }

    @Override
    public void onDrinkClick(String drinkId) {

        drinkIdSelected = drinkId;

        if (isHorizontal) {
            getFragmentManager().beginTransaction().replace(R.id.detail_container, DetailFragment.newInstance(drinkId)).commit();
        } else {
            gotoDetails();
        }
    }

    private void gotoDetails() {
        Intent i = DetailActivity.getDetailIntent(this, drinkIdSelected);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_grid:
                return false;
            case R.id.action_list:
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

}
