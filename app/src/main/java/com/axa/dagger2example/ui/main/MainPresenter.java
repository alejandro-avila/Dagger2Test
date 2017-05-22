package com.axa.dagger2example.ui.main;
/*
 * Copyright (c) 2017 AXA Group Solutions.
 *
 * Licensed under the AXA Group Solutions License (the "License"); you
 * may not use this file except in compliance with the License.
 * A copy of the License can be found in the LICENSE.TXT file distributed
 * together with this file.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import com.axa.dagger2example.di.scope.PerActivity;
import com.axa.dagger2example.domain.interactors.GetUsers;
import com.axa.dagger2example.domain.model.User;
import com.axa.dagger2example.ui.Navigator;
import com.axa.dagger2example.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;

@PerActivity
public class MainPresenter extends BasePresenter<MainView> {

    @Inject
    Navigator navigator;
    GetUsers getUsers;

    @Inject
    public MainPresenter(GetUsers getUsers) {
        super(getUsers);
        this.getUsers = getUsers;
    }

    public void loadUsers() {

        getView().showLoader();

        getUsers.execute(new DisposableSingleObserver<List<User>>() {
            @Override
            public void onSuccess(List<User> users) {
                getView().dismissLoader();
                getView().inflateUsers(users);
                getView().subscribeOnClickUser(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        onSelectUser(user);
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissLoader();
                getView().showError(e.getMessage());
            }
        });

    }

    public void onSelectUser(User user) {
        navigator.goToDetails(user.getId());
    }
}
