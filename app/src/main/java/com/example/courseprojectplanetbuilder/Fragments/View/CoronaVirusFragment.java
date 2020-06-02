package com.example.courseprojectplanetbuilder.Fragments.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseprojectplanetbuilder.Fragments.Adapter.CoronaDataAdapter;
import com.example.courseprojectplanetbuilder.Fragments.ViewModel.CoronaVirusViewModel;
import com.example.courseprojectplanetbuilder.Model.CoronaModel.CoronaDailySummaryResponse;
import com.example.courseprojectplanetbuilder.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoronaVirusFragment extends Fragment {

    private CoronaVirusViewModel mViewModel;

    private RecyclerView coronaAllCountriesRV;
    private CoronaDataAdapter coronaDataAdapter;

    public static CoronaVirusFragment newInstance() {
        return new CoronaVirusFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.corona_virus_fragment, container, false);

        coronaAllCountriesRV = root.findViewById(R.id.corona_all_countries);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CoronaVirusViewModel.class);

        coronaAllCountriesRV.hasFixedSize();
        coronaAllCountriesRV.setLayoutManager(new LinearLayoutManager(getContext()));

        coronaDataAdapter = new CoronaDataAdapter();
        coronaAllCountriesRV.setAdapter(coronaDataAdapter);

        mViewModel.requestCoronaDailyData().enqueue(new Callback<CoronaDailySummaryResponse>() {
            @Override
            public void onResponse(Call<CoronaDailySummaryResponse> call, Response<CoronaDailySummaryResponse> response) {
                if (response.code() == 200){
                    coronaDataAdapter.setCountryData(response.body().Countries);
                    coronaDataAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CoronaDailySummaryResponse> call, Throwable t) {

            }
        });
    }

}
