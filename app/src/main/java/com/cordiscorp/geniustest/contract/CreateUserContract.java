package com.cordiscorp.geniustest.contract;


import com.cordiscorp.geniustest.connectvity.request.CreateUserRequest;
import com.cordiscorp.geniustest.connectvity.response.CreateUserResponse;

/**
 * Created by Ibkunle Adeoluwa on 9/20/2019.
 */

public class CreateUserContract {

    public interface Model {

        interface OnCompletedListener {
            void onCreateUserSuccess(CreateUserResponse usersResponse);

            void onCreateUserError(String message);

            void onCreateUserFailed(Throwable t);
        }

        void createUser(OnCompletedListener onCompletedListener, CreateUserRequest createUserRequest);

    }

    public interface View {
        void showCreateUserProgress();

        void hideCreateUserProgress();

        void showCreateUserUsers(CreateUserResponse usersResponse);

        void onCreateUserFailed(Throwable throwable);

        void showCreateUserError(String message);
    }

    public interface Presenter {

        void onCreateUserDestroy();

        void onCreateUserStart();

        void getCreateUserUsers();

    }
}
