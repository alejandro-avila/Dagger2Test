package com.axa.dagger2example.domain.interactors;
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
import com.axa.dagger2example.domain.RestRepository;
import com.axa.dagger2example.domain.SchedulerFactory;
import com.axa.dagger2example.domain.interactors.base.UseCase;
import com.axa.dagger2example.domain.model.User;

import javax.inject.Inject;

import io.reactivex.Single;

@PerActivity
public class GetUser extends UseCase<User> {

    private RestRepository restRepository;
    private long id;

    @Inject
    public GetUser(RestRepository restRepository, SchedulerFactory schedulerFactory) {
        super(schedulerFactory);
        this.restRepository = restRepository;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Single<User> buildUseCase(Object... params) {
        return restRepository.getUser(id);
    }
}
