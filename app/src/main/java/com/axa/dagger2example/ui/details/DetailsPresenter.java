package com.axa.dagger2example.ui.details;
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


import com.axa.dagger2example.R;
import com.axa.dagger2example.domain.ResourcesRepository;
import com.axa.dagger2example.domain.interactors.GetUser;
import com.axa.dagger2example.domain.model.User;
import com.axa.dagger2example.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

public class DetailsPresenter extends BasePresenter<DetailsView>{

    @Inject
    ResourcesRepository resourcesRespository;

    GetUser getUser;

    @Inject
    public DetailsPresenter(GetUser getUser){
        super(getUser);
        this.getUser = getUser;
    }

    public void loadUser(long id){
        getView().showLoader();
        getUser.setId(id);
        getUser.execute(new DisposableSingleObserver<User>() {
            @Override
            public void onSuccess(User user) {
                getView().dismissLoader();
                getView().inflateUser(resourcesRespository.getString(R.string.name),
                        resourcesRespository.getString(R.string.phone),
                        user);
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissLoader();
                getView().showError(e.getMessage());
            }
        },id);
    }
}
