package com.example.project.Interface;

import com.example.project.Model.Skill;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillBoardService {
    @GET("/api/skilliq")
    Call<List<Skill>> getSkill();
}
