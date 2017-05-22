package com.axa.dagger2example.data;
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


import com.axa.dagger2example.data.mapper.UserMapper;
import com.axa.dagger2example.data.model.UserResponse;
import com.axa.dagger2example.data.rest.RestApi;
import com.axa.dagger2example.domain.RestRepository;
import com.axa.dagger2example.domain.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

@Singleton
public class RestRepositoryImpl implements RestRepository {

    private RestApi datastore;
    private UserMapper mapper;

    @Inject
    public RestRepositoryImpl(RestApi datastore, UserMapper mapper){
        this.datastore = datastore;
        this.mapper = mapper;
    }
    @Override
    public Single<List<User>> getUsers() {
        return datastore.users()
                .flatMap(new Function<List<UserResponse>, SingleSource<List<User>>>() {
                    @Override
                    public SingleSource<List<User>> apply(List<UserResponse> userResponses) throws Exception {
                        return Observable.fromIterable(userResponses)
                                .map(new Function<UserResponse, User>() {
                                    @Override
                                    public User apply(UserResponse userResponse) throws Exception {
                                        return mapper.map(userResponse);
                                    }
                                }).toList();
                    }
                });
    }

    @Override
    public Single<User> getUser(long id) {
        return datastore.user(id)
                .map(new Function<UserResponse, User>() {
                    @Override
                    public User apply(UserResponse userResponse) throws Exception {
                        return mapper.map(userResponse);
                    }
                });
    }
}
