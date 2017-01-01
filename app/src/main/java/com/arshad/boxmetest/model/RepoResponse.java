package com.arshad.boxmetest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 1/1/17.
 */
public class RepoResponse {

    @SerializedName("id")
    @Expose
    private List<RepoData> repos;

    public RepoResponse(List<RepoData> repos) {
        this.repos = repos;
    }

    public void setRepos(List<RepoData> repos) {
        this.repos = repos;
    }

    public List<RepoData> getRepos() {
        return this.repos;
    }



}
