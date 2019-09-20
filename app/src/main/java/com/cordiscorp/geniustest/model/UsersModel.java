package com.cordiscorp.geniustest.model;

import com.cordiscorp.geniustest.connectvity.GPTestClient;
import com.cordiscorp.geniustest.connectvity.GPTestNetworkInterface;
import com.cordiscorp.geniustest.connectvity.request.CreateUserRequest;
import com.cordiscorp.geniustest.connectvity.response.CreateUserResponse;
import com.cordiscorp.geniustest.connectvity.response.UsersResponse;
import com.cordiscorp.geniustest.contract.CreateUserContract;
import com.cordiscorp.geniustest.contract.GetUsersContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ibkunle Adeoluwa on 9/21/2019.
 */
public class UsersModel implements CreateUserContract.Model, GetUsersContract.Model {
    GPTestNetworkInterface gpTestNetworkInterface =
            GPTestClient.getClient().create(GPTestNetworkInterface.class);

    @Override
    public void createUser(CreateUserContract.Model.OnCompletedListener onCompletedListener, CreateUserRequest createUserRequest) {
        Call<CreateUserResponse> call = gpTestNetworkInterface.createUser(createUserRequest);

        call.enqueue(new Callback<CreateUserResponse>() {
            @Override
            public void onResponse(Call<CreateUserResponse> call, Response<CreateUserResponse> response) {
                onCompletedListener.onCreateUserSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CreateUserResponse> call, Throwable t) {
                onCompletedListener.onCreateUserFailed(t);
            }
        });


    }

    @Override
    public void getUser(GetUsersContract.Model.OnCompletedListener onCompletedListener) {
        Call<UsersResponse> call = gpTestNetworkInterface.getUsers();
        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                onCompletedListener.onGetUsersSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                onCompletedListener.onGetUsersFailed(t);
            }
        });
    }
}
