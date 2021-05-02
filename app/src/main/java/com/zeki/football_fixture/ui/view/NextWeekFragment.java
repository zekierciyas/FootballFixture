package com.zeki.football_fixture.ui.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeki.football_fixture.R;
import com.zeki.football_fixture.network.Interface.TeamInterface;
import com.zeki.football_fixture.network.model.Team;
import com.zeki.football_fixture.ui.viewModel.WeekCalculator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NextWeekFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NextWeekFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NextWeekFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static NextWeekFragment newInstance(String param1, String param2) {
        NextWeekFragment fragment = new NextWeekFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_main, container, false);
        TextView textView = (TextView) view.findViewById(R.id.textView);

        //Getting next week of this month as an arraylist
        WeekCalculator t = new WeekCalculator();
        String[] nextWeek = BaseActivity.nextWeek;

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

                        for (int i =0; i<nextWeek.length ; i++ ) {

                            if (nextWeek[i].equals(team.getTime())) {
                                content += ("ID: " + team.getId() + "\n");
                                content += ("TEAM A:  " + team.getTeamA() + "\n");
                                content += ("TEAM B:  " + team.getTeamB() + "\n");
                                content += ("TIME:  " + team.getTime() + "\n");
                                textView.append(content);

                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {


                textView.setText(t.getMessage());

            }
        });

        return view;
    }
}