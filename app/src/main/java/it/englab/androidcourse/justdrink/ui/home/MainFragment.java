package it.englab.androidcourse.justdrink.ui.home;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import it.englab.androidcourse.justdrink.R;
import it.englab.androidcourse.justdrink.adapter.DrinkAdapter;
import it.englab.androidcourse.justdrink.listeners.ClickListener;
import it.englab.androidcourse.justdrink.listeners.DrinkListener;
import it.englab.androidcourse.justdrink.model.Drink;
import it.englab.androidcourse.justdrink.model.DrinkList;
import it.englab.androidcourse.justdrink.utils.JsonUtil;

/**
 * Created by Peppe on 07/02/2017.
 */

public class MainFragment extends Fragment implements ClickListener {

    private static final String HORIZONTAL_VALUE = MainFragment.class.getPackage() + ".HORIZONTAL_VALUE";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DrinkAdapter adapter;
    private DrinkListener listener;
    private boolean isHorizontal;

    public static MainFragment newInstance(boolean horizontal) {
        Bundle args = new Bundle();
        args.putBoolean(HORIZONTAL_VALUE, horizontal);

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isHorizontal = getArguments().getBoolean(HORIZONTAL_VALUE);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(R.string.app_name);
        adapter = new DrinkAdapter(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);

        if (isHorizontal) // conoscendo l'orientamento, possiamo differenziare il layout in portrait con il layout in landscape
            layoutManager = new LinearLayoutManager(mRecyclerView.getContext(), LinearLayoutManager.VERTICAL, false);
        else
            layoutManager = new GridLayoutManager(mRecyclerView.getContext(), 2, GridLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);

        String json = JsonUtil.loadJSONFromAsset(getActivity(),"list");
        DrinkList parsedJson = JsonUtil.fromJson(json, DrinkList.class);
        List<Drink> drinks = parsedJson.getDrinks();

        adapter.setList(drinks);
    }

    @Override
    public void onItemClick(String id) {
        listener.onDrinkClick(id);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_grid:
                mRecyclerView.setLayoutManager(new GridLayoutManager(mRecyclerView.getContext(),2,GridLayoutManager.VERTICAL,false));
                return true;
            case R.id.action_list:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attachListener(context);
    }

    /*Nonostante il metodo onAttach(Activity) sia deprecato, è necessario mantenerlo per ragioni
      di retrocompatibilità*/
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attachListener(activity);
    }

    /**
     *  Il metodo attachListener inizializza l'attributo listener, o lancia un'eccezione indicando
     *  che l'activity che contiene il MainFragment deve implementare l'interfaccia DrinkListener
     *
     * @param context Context
     */
    private void attachListener(Context context) {
        try {
            listener = (DrinkListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement DrinkListener");
        }
    }

}
