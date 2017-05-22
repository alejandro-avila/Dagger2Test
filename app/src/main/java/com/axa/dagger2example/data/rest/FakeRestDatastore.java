package com.axa.dagger2example.data.rest;
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


import com.axa.dagger2example.data.model.UserResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class FakeRestDatastore implements RestApi {

    List<UserResponse> list = new ArrayList<>();

    @Inject
    public FakeRestDatastore(){
        list.add(new UserResponse().id(1).name("alex1").phone("12345678"));
        list.add(new UserResponse().id(2).name("alex2").phone("23456789"));
        list.add(new UserResponse().id(3).name("alex3").phone("34567890"));
        list.add(new UserResponse().id(4).name("alex4").phone("45678901"));
        list.add(new UserResponse().id(5).name("alex5").phone("56789012"));
    }

    @Override
    public Single<List<UserResponse>> users() {
        return Single.just(list);
    }

    @Override
    public Single<UserResponse> user(long id) {
        return Single.just(list.get((int)id-1));
    }


}
