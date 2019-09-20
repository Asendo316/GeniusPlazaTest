package com.cordiscorp.geniustest.presenter;

import com.cordiscorp.geniustest.connectvity.request.CreateUserRequest;
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
        createView.showCreateUserUsers(usersResponse);
        if (createView != null) {
            createView.hideCreateUserProgress();
        }
    }
    @Override
    public void onGetUsersSuccess(UsersResponse usersResponse) {
        getView.showUsers(usersResponse);
        if (getView != null) {
            getView.hideGetUsersProgress();
        }
    }





    @Override
    public void onCreateUserStart() {

    }

    @Override
    public void getCreateUserUsers() {

    }



    @Override
    public void onGetUsersError(String message) {
        getView.showGetUsersError(message);
        if (getView != null) {
            getView.hideGetUsersProgress();
        }
    }
    @Override
    public void onCreateUserError(String message) {
        createView.showCreateUserError(message);
        if (createView != null) {
            createView.hideCreateUserProgress();
        }
    }



    @Override
    public void onCreateUserFailed(Throwable t) {
        createView.onCreateUserFailed(t);
        if (createView != null) {
            createView.hideCreateUserProgress();
        }
    }
    @Override
    public void onGetUsersFailed(Throwable t) {
        getView.onGetUsersFailed(t);
        if (getView != null) {
            getView.hideGetUsersProgress();
        }
    }



    @Override
    public void onGetUsersDestroy() {
        this.getModel = null;
    }
    @Override
    public void onCreateUserDestroy() {
        this.createModel = null;
    }



    @Override
    public void onGetUsersStart() {
        refreshUsers();
    }

    @Override
    public void getUsers() {
        refreshUsers();
    }


    private void refreshUsers(){
        if (getModel != null) {
            getView.showGetUsersProgress();
        }
        getModel.getUser(this);
    }

    public void createUser(String name, String job){
        if (createModel != null) {
            createView.showCreateUserProgress();
        }
        createModel.createUser(this,new CreateUserRequest(name,job));
    }

}
