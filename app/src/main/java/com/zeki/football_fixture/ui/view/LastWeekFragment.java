package com.zeki.football_fixture.ui.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeki.football_fixture.R;
import com.zeki.football_fixture.network.Interface.TeamInterface;
import com.zeki.football_fixture.network.model.Team;
import com.zeki.football_fixture.ui.Adapter.CustomListAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LastWeekFragment extends Fragment {


    RecyclerView recyclerView;
    CustomListAdapter adapter;
    List<Team> teamLists = new ArrayList<>();


    public LastWeekFragment() {
        // Required empty public constructor
    }

    public static LastWeekFragment newInstance(String param1, String param2) {
        LastWeekFragment fragment = new LastWeekFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        teamLists.clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_main, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fixtureList);


        //Gettin Last week of month as an arrayList
         String[] lastWeek = BaseActivity.lastWeek;

        //Cleaning the previous data for not be doubled.
        teamLists.clear();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TeamInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TeamInterface teamInterface = retrofit.create(TeamInterface.class);

        Call<List<Team>> call = teamInterface.getTeamJsons();

        call.enqueue(new Callback<List<Team>>() {
            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {

                if (response.isSuccessful()) {

                    List<Team> teams = response.body();

                    for (Team team : teams) {
                        String content= "";

                        for (int i =0; i<lastWeek.length ; i++ ) {

                            if (lastWeek[i].equals(team.getTime())) {

                                Team t = new Team();
                                t.setId(team.getId());
                                t.setTeamA(team.getTeamA());
                                t.setTeamB(team.getTeamB());
                                t.setTime(team.getTime());
                                teamLists.add(t);
                            }
                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        adapter = new CustomListAdapter(getActivity(), teamLists);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {
                Log.d("TAG", "onFailure: API call error");
            }
        });

        return view;
    }
}