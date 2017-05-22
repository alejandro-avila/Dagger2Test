package com.axa.dagger2example.di.application;
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

import android.content.Context;

import com.axa.dagger2example.data.ResourcesRepositoryImpl;
import com.axa.dagger2example.data.RestRepositoryImpl;
import com.axa.dagger2example.data.mapper.UserMapper;
import com.axa.dagger2example.data.rest.FakeRestDatastore;
import com.axa.dagger2example.data.rest.RestApi;
import com.axa.dagger2example.di.qualifiers.ApplicationContext;
import com.axa.dagger2example.domain.ResourcesRepository;
import com.axa.dagger2example.domain.RestRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    Context context;
    public ApplicationModule(Context context){
        this.context = context;
    }

    @Provides
    @ApplicationContext
    public Context providesContext(){
        return this.context;
    }

    @Provides
    public RestApi providesDatastore(){

        //return new RestDatastore();
        return new FakeRestDatastore();
    }

    @Provides
    public RestRepository providesRepository(RestApi restApi, UserMapper userMapper){
        return new RestRepositoryImpl(restApi,userMapper);
    }

    @Provides
    public ResourcesRepository providesResources(){
        return new ResourcesRepositoryImpl(context);
    }
}
