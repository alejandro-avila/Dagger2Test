package com.axa.dagger2example.ui.test;
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
import com.axa.dagger2example.ui.base.BasePresenter;

import javax.inject.Inject;

@PerActivity
public class TestPresenterImpl extends BasePresenter<TestContract.TestView> implements TestContract.TestPresenter{

    @Inject
    public TestPresenterImpl(GetUsers getUsers){

    }
}
