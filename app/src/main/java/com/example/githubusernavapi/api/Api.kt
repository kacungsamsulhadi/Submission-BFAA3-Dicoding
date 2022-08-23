package com.example.githubusernavapi.api

import com.example.githubusernavapi.data.model.DetailUserRes
import com.example.githubusernavapi.data.model.User
import com.example.githubusernavapi.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Austhorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUserRes>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username")username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username")username: String
    ): Call<ArrayList<User>>
}