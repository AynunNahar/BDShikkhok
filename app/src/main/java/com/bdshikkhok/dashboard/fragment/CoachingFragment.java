package com.bdshikkhok.dashboard.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdshikkhok.R;
import com.bdshikkhok.dashboard.Adapter.MyAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoachingFragment extends Fragment {

    @BindView(R.id.tutorRecyclerView)
    RecyclerView tutorRecyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<String> movieList = new ArrayList<String>();
    MyAdapter myAdapter;
    
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment_view = inflater.inflate(R.layout.fragment_tutors_ist, container, false);
        ButterKnife.bind(this, fragment_view);
        //tutorRecyclerView.setHasFixedSize(true);

        movieList.add("Sara");
        movieList.add("Rifat");
        movieList.add("Nazran");
        movieList.add("Ansar");
        movieList.add("Nargis");
        movieList.add("Lu");
        movieList.add("To");
        movieList.add("Ja");
        movieList.add("Sha");
        movieList.add("Na");
        movieList.add("fa");
        movieList.add("3");
        movieList.add("6");
        movieList.add("8");
        movieList.add("0");
        movieList.add("Lu");
        movieList.add("To");
        movieList.add("Ja");
        movieList.add("Sha");
        movieList.add("Na");
        layoutManager = new LinearLayoutManager(getActivity());
        tutorRecyclerView.setLayoutManager(layoutManager);

        myAdapter = new MyAdapter(movieList);
        tutorRecyclerView.setAdapter(myAdapter);

        return fragment_view;
    }
}
