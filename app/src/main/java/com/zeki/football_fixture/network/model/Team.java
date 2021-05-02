package com.zeki.football_fixture.network.model;

import com.google.gson.annotations.SerializedName;

public class Team {

    private String TeamA;
    private String TeamB;
    private int id;
    private String time;

    public Team() {}
    public Team(String TeamA, String TeamB, int id, String time){

        this.TeamA = TeamA;
        this.TeamB = TeamB;
        this.id = id;
        this.time = time;

    }

    public String getTeamA() {
        return TeamA;
    }

    public void setTeamA(String teamA) {
        TeamA = teamA;
    }

    public String getTeamB() { return TeamB; }

    public void setTeamB(String teamB) {
        TeamB = teamB;
    }

    public int getId() {
        return Integer.parseInt(String.valueOf(id));
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }

}
