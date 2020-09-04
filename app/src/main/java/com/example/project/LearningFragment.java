package com.example.project;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Adapter.LearnersRecyclerAdapter;
import com.example.project.Interface.LearnersBoardService;
import com.example.project.Model.Learners;
import com.example.project.Services.ServiceBuilder;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningFragment extends Fragment {
    RecyclerView mRecyclerView;
    LearnersRecyclerAdapter mLearnersRecyclerAdapter;
    private Context mContext;
    private ProgressBar mLoading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
      View rootView =  inflater.inflate(R.layout.learners_fragment, container, false);
    mRecyclerView = (RecyclerView) rootView.findViewById(R.id.learners_list);
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//    mRecyclerView.setAdapter(mLearnersRecyclerAdapter);
    mLoading = (ProgressBar)rootView.findViewById(R.id.progressBar);
    getLearner();
    return rootView;
    }


    public void getLearner() {

        LearnersBoardService mLearnersBoardService = ServiceBuilder.buildService(LearnersBoardService.class);
        Call<List<Learners>> request = mLearnersBoardService.getLearners();
        request.enqueue(new Callback<List<Learners>>() {
            @Override
            public void onResponse(Call<List<Learners>> call, Response<List<Learners>> response) {

                if (response.isSuccessful()) {
                    mRecyclerView.setAdapter(new LearnersRecyclerAdapter(getActivity().getBaseContext(), response.body()));
                    mRecyclerView.setVisibility(View.VISIBLE);
                } else if (response.code() == 401) {
                    Toast.makeText(getActivity().getApplicationContext(), "Your session has expired ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Failed to retrieve items", Toast.LENGTH_LONG).show();
                }
                mLoading.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<List<Learners>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(getActivity().getApplicationContext(), "A connection error occurred", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Failed to retrieve", Toast.LENGTH_LONG).show();
                }
               mLoading.setVisibility(View.GONE);
            }
            });

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    interface OnFragmentInteractionListener {
    }
}
