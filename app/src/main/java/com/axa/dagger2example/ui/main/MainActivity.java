package com.axa.dagger2example.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.axa.dagger2example.R;
import com.axa.dagger2example.di.scope.PerActivity;
import com.axa.dagger2example.domain.model.User;
import com.axa.dagger2example.ui.base.BaseActivity;
import com.axa.dagger2example.ui.main.adapters.UserAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

@PerActivity
public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.rv_users)
    RecyclerView rvUsers;

    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getActivityComponent().inject(this);
        setBasePresenter(mainPresenter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(adapter!=null) {
            adapter.dispose();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.loadUsers();
    }

    @Override
    public void inflateUsers(List<User> users) {
        if(adapter==null){

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

            adapter = new UserAdapter(this);

            rvUsers.setLayoutManager(linearLayoutManager);
            rvUsers.setAdapter(adapter);

        }

        adapter.setUsers(users);
    }

    @Override
    public void subscribeOnClickUser(Consumer<User> consumer){
        adapter.subscribeOnClick(consumer);
    }
}
