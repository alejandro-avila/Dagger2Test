package com.axa.dagger2example.ui.base;
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


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.axa.dagger2example.Application;
import com.axa.dagger2example.di.activity.ActivityComponent;
import com.axa.dagger2example.di.activity.ActivityModule;
import com.axa.dagger2example.di.activity.DaggerActivityComponent;
import com.axa.dagger2example.di.application.ApplicationComponent;

public class BaseActivity extends AppCompatActivity implements BaseView{

    private BasePresenter basePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        basePresenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        basePresenter.detachView();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showLoader() {

    }

    @Override
    public void dismissLoader() {

    }

    public ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    public ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return ((Application)getApplication()).getApplicationComponent();
    }

    public void setBasePresenter(BasePresenter basePresenter) {
        this.basePresenter = basePresenter;
    }
}
