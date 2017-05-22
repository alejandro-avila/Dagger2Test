package com.axa.dagger2example.domain.interactors.base;
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


import com.axa.dagger2example.domain.SchedulerFactory;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public abstract class UseCase<T> {
    private CompositeDisposable compositeDisposable;
    private Scheduler mainThread;
    private Scheduler ioThread;

    public UseCase(SchedulerFactory schedulerFactory){
        this.mainThread = schedulerFactory.getMain();
        this.ioThread = schedulerFactory.getIO();

        compositeDisposable = new CompositeDisposable();
    }

    public abstract Single<T> buildUseCase(Object... params);

    public void execute(DisposableSingleObserver<T> observer, Object... params){
        buildUseCase(params)
                .subscribeOn(ioThread)
                .observeOn(mainThread)
                .subscribe(observer);

        compositeDisposable.add(observer);
    }

    private void addDisposable(Disposable disposable){
        compositeDisposable.add(disposable);
    }

    public void dispose(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
