package com.example.courseprojectplanetbuilder.Fragments.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseprojectplanetbuilder.Model.CoronaModel.CoronaDailySummaryResponse;
import com.example.courseprojectplanetbuilder.R;

import java.util.ArrayList;
import java.util.Locale;

public class CoronaDataAdapter extends RecyclerView.Adapter<CoronaDataAdapter.ViewHolder> {
    private ArrayList<CoronaDailySummaryResponse.Country> countryData;

    public CoronaDataAdapter() {
        countryData = new ArrayList<>();
    }

    public void setCountryData(ArrayList<CoronaDailySummaryResponse.Country> countryData) {
        this.countryData = countryData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.corona_summary_country_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CoronaDailySummaryResponse.Country currentCountry = countryData.get(position);

        holder.countryName.setText(currentCountry.Country);
        holder.countryNewCases.setText(String.format(Locale.getDefault(), "+ %d", currentCountry.NewConfirmed));
        holder.countryTotalCases.setText(String.format(Locale.getDefault(), "%d", currentCountry.TotalConfirmed));
        holder.countryNewDeaths.setText(String.format(Locale.getDefault(), "+ %d", currentCountry.NewDeaths));
        holder.countryTotalDeaths.setText(String.format(Locale.getDefault(), "%d", currentCountry.TotalDeaths));
    }

    @Override
    public int getItemCount() {
        return countryData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView countryName;
        TextView countryNewCases;
        TextView countryTotalCases;
        TextView countryNewDeaths;
        TextView countryTotalDeaths;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.corona_country_name);
            countryNewCases = itemView.findViewById(R.id.corona_country_new_cases);
            countryTotalCases = itemView.findViewById(R.id.corona_country_total_cases);
            countryNewDeaths = itemView.findViewById(R.id.corona_country_new_deaths);
            countryTotalDeaths = itemView.findViewById(R.id.corona_country_total_death);
        }
    }
}
