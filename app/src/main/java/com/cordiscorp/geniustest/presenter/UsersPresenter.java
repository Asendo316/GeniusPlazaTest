package com.cordiscorp.geniustest.presenter;

import com.cordiscorp.geniustest.connectvity.response.CreateUserResponse;
import com.cordiscorp.geniustest.connectvity.response.UsersResponse;
import com.cordiscorp.geniustest.contract.CreateUserContract;
import com.cordiscorp.geniustest.contract.GetUsersContract;
import com.cordiscorp.geniustest.model.UsersModel;

/**
 * Created by Ibkunle Adeoluwa on 9/21/2019.
 */
public class UsersPresenter implements CreateUserContract.Presenter,
        GetUsersContract.Presenter,
        CreateUserContract.Model.OnCompletedListener,
        GetUsersContract.Model.OnCompletedListener {

    private CreateUserContract.View createView;
    private CreateUserContract.Model createModel;

    private GetUsersContract.View getView;
    private GetUsersContract.Model getModel;

    public UsersPresenter(CreateUserContract.View createView, GetUsersContract.View getView) {
        this.createView = createView;
        this.getView = getView;

        createModel = new UsersModel();
        getModel = new UsersModel();
    }


    @Override
    public void onCreateUserSuccess(CreateUserResponse usersResponse) {

    }

    @Override
    public void onCreateUserCreate(String message) {

    }

    @Override
    public void onCreateUserFailed(Throwable t) {

    }

    @Override
    public void onCreateUserDestroy() {

    }

    @Override
    public void onCreateUserStart() {

    }

    @Override
    public void getCreateUserUsers() {

    }

    @Override
    public void onGetUsersSuccess(UsersResponse usersResponse) {

    }

    @Override
    public void onGetUsersError(String message) {

    }

    @Override
    public void onGetUsersFailed(Throwable t) {

    }

    @Override
    public void onGetUsersDestroy() {

    }

    @Override
    public void onGetUsersStart() {

    }

    @Override
    public void getUsers() {

    }
}
