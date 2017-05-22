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


import com.axa.dagger2example.domain.interactors.base.UseCase;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePresenter <T extends BaseView>{

    private T view;
    private List<UseCase> useCases;

    public BasePresenter(UseCase... useCases){
        this.useCases = new ArrayList<>();
        addUseCases(useCases);
    }

    public void attachView(T view){
        this.view = view;
    }

    protected T getView(){
        return view;
    }

    public void detachView(){
        dispose();
        view = null;
    }

    public void addUseCases(UseCase... useCases){

        for(UseCase useCase : useCases){
            this.useCases.add(useCase);
        }

    }

    public void dispose(){
        for(UseCase useCase : useCases){
            useCase.dispose();
        }
    }


}
