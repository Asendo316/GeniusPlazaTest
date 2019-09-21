package com.cordiscorp.geniustest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.cordiscorp.geniustest.R;
import com.cordiscorp.geniustest.adapter.RecyclerDivider;
import com.cordiscorp.geniustest.adapter.UserRecyclerAdapter;
import com.cordiscorp.geniustest.connectvity.response.CreateUserResponse;
import com.cordiscorp.geniustest.connectvity.response.Datum;
import com.cordiscorp.geniustest.connectvity.response.UsersResponse;
import com.cordiscorp.geniustest.contract.CreateUserContract;
import com.cordiscorp.geniustest.contract.GetUsersContract;
import com.cordiscorp.geniustest.presenter.UsersPresenter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, CreateUserContract.View, GetUsersContract.View {

    protected @BindView(R.id.swipe_refresh) SwipeRefreshLayout swipe_refresh;
    protected @BindView(R.id.usersRecycler) ShimmerRecyclerView usersRecycler;
    protected @BindView(R.id.add_btn) FloatingActionButton add_btn;
    protected @BindView(R.id.errorText) TextView errorText;
    protected @BindString(R.string.error1) String error1;
    protected @BindString(R.string.error2) String error2;
    protected @BindString(R.string.error3) String error3;

    protected @BindString(R.string.success1) String success1;
    private List<Datum> datumList =  new ArrayList<>();




    private UsersPresenter usersPresenter;
    private UserRecyclerAdapter userRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        usersPresenter = new UsersPresenter(this,this);
        swipe_refresh.setOnRefreshListener(this);
        add_btn.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        usersPresenter.getUsers();
    }

    @Override
    public void onRefresh() {
        usersPresenter.getUsers();
    }

    @Override
    public void onClick(View view) {
        displayCreateBottomSheet();
    }

    @Override
    public void showCreateUserProgress() {
        swipe_refresh.setRefreshing(true);
    }

    @Override
    public void hideCreateUserProgress() {
        swipe_refresh.setRefreshing(false);
    }
    @Override
    public void showGetUsersProgress() {
        swipe_refresh.setRefreshing(true);
    }

    @Override
    public void hideGetUsersProgress() {
        swipe_refresh.setRefreshing(false);
    }



    @Override
    public void showCreateUserUsers(CreateUserResponse usersResponse) {
        Toast.makeText(this,success1,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUsers(UsersResponse usersResponse) {
        errorText.setVisibility(View.GONE);

        datumList = usersResponse.getData();
        if (datumList != null) {
            usersRecycler.setItemAnimator(new DefaultItemAnimator());
            usersRecycler.setLayoutManager(new LinearLayoutManager(this));
            usersRecycler.addItemDecoration(new RecyclerDivider(this, LinearLayoutManager.VERTICAL, 0));
            userRecyclerAdapter = new UserRecyclerAdapter(this, datumList);
            usersRecycler.setAdapter(userRecyclerAdapter);
        }else{
            displayError(error3);
        }
    }



    @Override
    public void onCreateUserFailed(Throwable throwable) {
        displayError(throwable.getMessage());
    }
    @Override
    public void onGetUsersFailed(Throwable throwable) {
        displayError(throwable.getMessage());
    }
    @Override
    public void showCreateUserError(String message) {
        displayError(message);
    }
    @Override
    public void showGetUsersError(String message) {
        displayError(message);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        usersPresenter.onCreateUserDestroy();
        usersPresenter.onGetUsersDestroy();
    }


    public void displayCreateBottomSheet(){
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setCancelable(false);
        View sheetView = this.getLayoutInflater().inflate(R.layout.add_new_dialog, null);
        Button saveUserBtn;
        EditText nameEdit,jobEdit;
        saveUserBtn = sheetView.findViewById(R.id.saveUserBtn);
        nameEdit = sheetView.findViewById(R.id.nameEdit);
        jobEdit = sheetView.findViewById(R.id.jobEdit);

        saveUserBtn.setOnClickListener(view -> {
            mBottomSheetDialog.dismiss();
            if(validate(nameEdit,jobEdit)) {
                usersPresenter.createUser(nameEdit.getText().toString().trim(),
                        jobEdit.getText().toString().trim());
            }
        });

        mBottomSheetDialog.setContentView(sheetView);
        mBottomSheetDialog.show();
    }

    private boolean validate(EditText nameEdit, EditText jobEdit) {
        if(!nameEdit.getText().toString().trim().isEmpty()){
            if(!jobEdit.getText().toString().trim().isEmpty()){
                return true;
            }else{
                jobEdit.setError(error1);
                return  false;
            }
        }else{
            nameEdit.setError(error2);
            return  false;
        }
    }


    private void displayError(String message){
        errorText.setVisibility(View.VISIBLE);
        errorText.setText(message);
    }
}
