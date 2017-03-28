package it.englab.androidcourse.justdrink.ui.home;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import it.englab.androidcourse.justdrink.R;
import it.englab.androidcourse.justdrink.listeners.DrinkListener;
import it.englab.androidcourse.justdrink.ui.detail.DetailActivity;
import it.englab.androidcourse.justdrink.ui.detail.DetailFragment;

public class MainActivity extends AppCompatActivity implements DrinkListener {

    private static final String TAG = MainActivity.class.getName();

    private MyAsyncTask myAsyncTask;
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
    protected void onStop() {
        if (myAsyncTask != null) {
            myAsyncTask.cancel(true);
        }
        super.onStop();
    }

    @Override
    public void onDrinkClick(String drinkId) {

        drinkIdSelected = drinkId;

        if (isHorizontal) {
            getFragmentManager().beginTransaction().replace(R.id.detail_container, DetailFragment.newInstance(drinkId)).commit();
        } else {

            //TODO - ANR esempio
            //anrExample();

            //TODO - Soluzione Thread
            //threadExample();

            //TODO - AsyncTask
            //asyncTaskExample();
        }
    }

    private void gotoDetails() {
        Intent i = DetailActivity.getDetailIntent(this, drinkIdSelected);
        startActivity(i);
    }

    private void anrExample() {
        try {
            Thread.sleep(20000);
            Toast.makeText(getApplicationContext(), "Il nostro fantastico codice ha finito!", Toast.LENGTH_SHORT).show();
            gotoDetails();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void threadExample() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20000);
                    Toast.makeText(getApplicationContext(), "Il nostro fantastico codice ha finito!", Toast.LENGTH_SHORT).show();
                    gotoDetails();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void asyncTaskExample() {
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(10);
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

    private class MyAsyncTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d(TAG, "Progress..." + values[0]);
        }

        @Override
        protected String doInBackground(Integer... params) {

            Integer numOfLoops = params[0];

            for (int i = 0; i < numOfLoops; i++) {
                try {

                    Thread.sleep(1000);
                    if (isCancelled()) break;
                    publishProgress(i * 1000);

                } catch (InterruptedException e) {
                    Log.i(TAG, "Thread interrupted!");
                }
            }
            return "ok";
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(MainActivity.this, "Il nostro fantastico codice ha finito!", Toast.LENGTH_SHORT).show();
            gotoDetails();
            myAsyncTask = null;
        }

        @Override
        protected void onCancelled() {
            myAsyncTask = null;
            super.onCancelled();
        }
    }

}
