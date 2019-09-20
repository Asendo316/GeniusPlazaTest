package com.cordiscorp.geniustest.contract;


import com.cordiscorp.geniustest.connectvity.response.UsersResponse;

/**
 * Created by Ibkunle Adeoluwa on 9/20/2019.
 */

public class GetUsersContract {

    public interface Model {

        interface OnCompletedListener {
            void onGetUsersSuccess(UsersResponse usersResponse);

            void onGetUsersError(String message);

            void onGetUsersFailed(Throwable t);
        }

        void getUser(OnCompletedListener onCompletedListener, UsersResponse usersResponse);

    }

    public interface View {
        void showGetUsersProgress();

        void hideGetUsersProgress();

        void showUsers(UsersResponse usersResponse);

        void onGetUsersFailed(Throwable throwable);

        void showGetUsersError(String message);
    }

    public interface Presenter {

        void onGetUsersDestroy();

        void onGetUsersStart();

        void getUsers();

    }
}
