package com.example.courseprojectplanetbuilder.DAO.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit
            .Builder()
            .baseUrl("https://api.covid19api.com")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static CoronaVirusApi coronaVirusApi = retrofit.create(CoronaVirusApi.class);

    public static CoronaVirusApi getCoronaVirusApi() {
        return coronaVirusApi;
    }
}
