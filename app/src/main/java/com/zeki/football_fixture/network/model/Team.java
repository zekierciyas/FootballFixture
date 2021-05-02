package com.zeki.football_fixture.network.model;

import com.google.gson.annotations.SerializedName;

public class Team {

    private String TeamA;
    private String TeamB;
    private int id;
    private String time;


    public String getTeamA() {
        return TeamA;
    }

    public String getTeamB() { return TeamB; }

    public int getId() {
        return Integer.parseInt(String.valueOf(id));
    }

    public String getTime() {
        return time;
    }


}
