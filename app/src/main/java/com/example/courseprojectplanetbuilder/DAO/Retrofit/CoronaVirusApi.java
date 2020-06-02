package com.example.courseprojectplanetbuilder.DAO.Retrofit;

import com.example.courseprojectplanetbuilder.Model.CoronaModel.CoronaDailySummaryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoronaVirusApi {
    @GET("summary")
    Call<CoronaDailySummaryResponse> getDailyData();
}
