package com.zeki.football_fixture.ui.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeki.football_fixture.R;
import com.zeki.football_fixture.network.Interface.TeamInterface;
import com.zeki.football_fixture.network.model.Team;
import com.zeki.football_fixture.ui.adapter.CustomListAdapter;
import com.zeki.football_fixture.ui.view_model.PageViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CurrentWeekFragment extends Fragment {

    private PageViewModel pageViewModel;
    RecyclerView recyclerView;
    CustomListAdapter adapter;
    List<Team> teamLists = new ArrayList<>();



    public CurrentWeekFragment() {
        // Required empty public constructor
    }


    public static CurrentWeekFragment newInstance(String param1, String param2) {
        CurrentWeekFragment fragment = new CurrentWeekFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        teamLists.clear();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_main, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fixtureList);

        //Getting current week data of this month as an arrayList
         String[] currentWeek = BaseActivity.currentWeek;

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

                        for (int i =0; i<currentWeek.length ; i++ ) {

                            if (currentWeek[i].equals(team.getTime())) {

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