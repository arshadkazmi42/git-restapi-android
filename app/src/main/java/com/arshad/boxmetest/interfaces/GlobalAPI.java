package com.arshad.boxmetest.interfaces;


import com.arshad.boxmetest.model.RepoData;
import com.arshad.boxmetest.model.RepoResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by root on 1/1/17.
 */
public interface GlobalAPI{

    @GET("users/{username}/repos")
    Call<List<RepoData>> getRepos(@Path("username") String username);
}
