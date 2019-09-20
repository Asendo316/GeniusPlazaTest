package com.cordiscorp.geniustest.connectvity;


import com.cordiscorp.geniustest.connectvity.request.CreateUserRequest;
import com.cordiscorp.geniustest.connectvity.response.CreateUserResponse;
import com.cordiscorp.geniustest.connectvity.response.UsersResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Ibkunle Adeoluwa on 9/20/2019.
 */

public interface GPTestNetworkInterface {

    @GET("api/users")
    Call<UsersResponse> getUsers();

    @POST("api/users")
    Call<CreateUserResponse> createUser(@Body CreateUserRequest createUserRequest);
}
