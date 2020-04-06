package com.bdshikkhok.dashboard.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

public class TutorsFragment extends Fragment {

    View fragment_view;
    @BindView(R.id.tutorRecyclerView)
    RecyclerView tutorRecyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<String> movieList = new ArrayList<String>();
    MyAdapter myAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment_view = inflater.inflate(R.layout.fragment_tutors_ist, container, false);
        ButterKnife.bind(this, fragment_view);
        tutorRecyclerView.setHasFixedSize(true);

        movieList.add(0,"Sara");
        movieList.add(1,"Rifat");
        movieList.add("Nazran");
        movieList.add("Ansar");
        movieList.add("Nargis");
        layoutManager = new LinearLayoutManager(getActivity());
        tutorRecyclerView.setLayoutManager(layoutManager);

        myAdapter = new MyAdapter(movieList);
        tutorRecyclerView.setAdapter(myAdapter);

        return fragment_view;
    }
}
