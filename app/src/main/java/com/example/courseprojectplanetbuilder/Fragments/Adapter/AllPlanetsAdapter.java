package com.example.courseprojectplanetbuilder.Fragments.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseprojectplanetbuilder.Model.Planet;
import com.example.courseprojectplanetbuilder.R;

import java.util.ArrayList;

public class AllPlanetsAdapter extends RecyclerView.Adapter<AllPlanetsAdapter.ViewHolder> {
    final private OnListItemClickListener mOnListItemClickListener;
    private ArrayList<Planet> allPlanets;

    public AllPlanetsAdapter(OnListItemClickListener mOnListItemClickListener) {
        this.mOnListItemClickListener = mOnListItemClickListener;
        allPlanets = new ArrayList<>();
    }

    public void setAllPlanets(ArrayList<Planet> allPlanets) {
        this.allPlanets = allPlanets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.planet_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Planet actualPlanet = allPlanets.get(position);
        holder.planetName.setText(actualPlanet.getName());
        String sizeText = "Size: " + actualPlanet.getMaxSize();
        holder.planetSize.setText(sizeText);
        holder.planetAuthor.setText(actualPlanet.getAuthor());
        if (actualPlanet.isCompleted()) {
            holder.selectButton.setText(R.string.finished_button);
            holder.selectButton.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return allPlanets.size();
    }

    public interface OnListItemClickListener {
        void onButtonClicked(Planet planet);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView planetName;
        TextView planetSize;
        TextView planetAuthor;
        Button selectButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            planetName = itemView.findViewById(R.id.planet_list_item_name);
            planetSize = itemView.findViewById(R.id.planet_list_item_size);
            planetAuthor = itemView.findViewById(R.id.planet_list_item_author);
            selectButton = itemView.findViewById(R.id.planet_list_item_select_button);
            selectButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == selectButton.getId())
                mOnListItemClickListener.onButtonClicked(allPlanets.get(getAdapterPosition()));
        }
    }
}
