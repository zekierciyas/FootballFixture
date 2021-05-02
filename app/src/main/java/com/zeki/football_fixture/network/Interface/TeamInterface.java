package com.zeki.football_fixture.network.Interface;

import com.zeki.football_fixture.network.model.Team;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TeamInterface {

   String BASE_URL = "https://608daf56fe2e9c00171e1e8b.mockapi.io/api/v1/";

   @GET("matches")
   Call<List<Team>> getTeamJsons();
}
