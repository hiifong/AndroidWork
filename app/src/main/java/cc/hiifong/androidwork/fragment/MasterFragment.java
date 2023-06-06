package cc.hiifong.androidwork.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

import cc.hiifong.androidwork.R;
import cc.hiifong.androidwork.activity.DetailActivity;
import cc.hiifong.androidwork.model.Country;
import cc.hiifong.androidwork.model.Resort;

public class MasterFragment extends ListFragment {
    boolean isDualPane;
    int position;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<String> countryNames = new ArrayList<String>();
        for (Country country: Resort.COUNTRIES){
            countryNames.add(country.getName());
        }
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                countryNames
                ));
        View detailFrame = getActivity().findViewById(R.id.detail);
        isDualPane = detailFrame != null && detailFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null){
            position = savedInstanceState.getInt("position", 0);
        }
        if (isDualPane){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetail(position);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        showDetail(position);
    }
    void showDetail(int position){
        this.position = position;
        if (isDualPane){
            getListView().setItemChecked(position, true);
            DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detail);
            if (detailFragment == null || detailFragment.getIndex() != position){
                detailFragment = new DetailFragment(position);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.detail, detailFragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }
        }else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), DetailActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    }
}
