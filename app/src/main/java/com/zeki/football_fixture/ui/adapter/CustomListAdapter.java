package com.zeki.football_fixture.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zeki.football_fixture.R;
import com.zeki.football_fixture.network.model.Team;

import java.util.List;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<Team> teams;

    public CustomListAdapter(Context ctx, List<Team> teams) {
        if (ctx!=null) {
            this.inflater = LayoutInflater.from(ctx);
            this.teams = teams;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_view, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textTeamA.setText(teams.get(position).getTeamA());
        holder.textTeamB.setText(teams.get(position).getTeamB());
        holder.textDate.setText(teams.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        TextView textTeamA, textDate, textTeamB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textTeamA  = (TextView) itemView.findViewById(R.id.textTeamA);
            textTeamB = (TextView) itemView.findViewById(R.id.textTeamB);
            textDate = (TextView) itemView.findViewById(R.id.matchDate);
        }
    }
}
