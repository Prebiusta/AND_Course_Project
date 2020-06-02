package com.example.courseprojectplanetbuilder.Repository;

import com.example.courseprojectplanetbuilder.DAO.Retrofit.CoronaVirusApi;
import com.example.courseprojectplanetbuilder.DAO.Retrofit.ServiceGenerator;
import com.example.courseprojectplanetbuilder.Model.CoronaModel.CoronaDailySummaryResponse;

import retrofit2.Call;

public class CoronaVirusRepository {
    private static final String TAG = "CoronaVirusRepository";
    private static CoronaVirusRepository instance;

    private CoronaVirusRepository() {

    }

    public static CoronaVirusRepository getInstance() {
        if (instance == null){
            instance = new CoronaVirusRepository();
        }
        return instance;
    }

    public Call<CoronaDailySummaryResponse> requestCoronaDailyData(){
        CoronaVirusApi coronaVirusApi = ServiceGenerator.getCoronaVirusApi();
        return coronaVirusApi.getDailyData();
    }
}
