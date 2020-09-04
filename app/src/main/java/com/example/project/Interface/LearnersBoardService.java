package com.example.project.Interface;

import com.example.project.Model.Learners;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface LearnersBoardService {
   @GET("/api/hours")
    Call<List<Learners>>  getLearners();


}
