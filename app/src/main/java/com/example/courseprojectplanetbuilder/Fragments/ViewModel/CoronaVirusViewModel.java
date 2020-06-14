package com.example.courseprojectplanetbuilder.Fragments.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.courseprojectplanetbuilder.Model.CoronaModel.CoronaDailySummaryResponse;
import com.example.courseprojectplanetbuilder.Repository.CoronaVirusRepository;

import retrofit2.Call;

public class CoronaVirusViewModel extends ViewModel {
    private final CoronaVirusRepository coronaVirusRepository;

    public CoronaVirusViewModel() {
        this.coronaVirusRepository = CoronaVirusRepository.getInstance();
    }

    public Call<CoronaDailySummaryResponse> requestCoronaDailyData() {
        return coronaVirusRepository.requestCoronaDailyData();
    }
}
