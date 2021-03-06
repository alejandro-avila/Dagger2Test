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

import com.axa.dagger2example.di.qualifiers.ApplicationContext;
import com.axa.dagger2example.domain.ResourcesRepository;
import com.axa.dagger2example.domain.RestRepository;
import com.axa.dagger2example.domain.SchedulerFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    RestRepository repository();
    SchedulerFactory schedulerFactory();
    ResourcesRepository resourcesRespository();

}
