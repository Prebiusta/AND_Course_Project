package com.example.courseprojectplanetbuilder.Model.CoronaModel;

import java.util.ArrayList;
import java.util.Date;

public class RemoteCoronaDailySummary {
    public Global Global;
    public ArrayList<Country> countries;

    private class Global {
        public int NewConfirmed;
        public int TotalConfirmed;
        public int NewDeaths;
        public int TotalDeaths;
        public int NewRecovered;
        public int TotalRecovered;
    }

    private class Country {
        public String Country;
        public String CountryCode;
        public String Slug;
        public int NewConfirmed;
        public int TotalConfirmed;
        public int NewDeaths;
        public int TotalDeaths;
        public int NewRecovered;
        public int TotalRecovered;
        public Date date;
    }
}
