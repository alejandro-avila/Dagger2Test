package com.axa.dagger2example.ui;
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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.axa.dagger2example.di.qualifiers.ActivityContext;
import com.axa.dagger2example.di.scope.PerActivity;
import com.axa.dagger2example.ui.details.DetailsActivity;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

@PerActivity
public class Navigator {

    public static String EXTRA_ID = "EXTRA_ID";

    WeakReference<Context> contextWeakReference;

    @Inject
    public Navigator(@ActivityContext Context context){
        this.contextWeakReference = new WeakReference<>(context);
    }

    private Intent getIntent(Class<? extends Activity> activityClass){
        return new Intent(contextWeakReference.get(),activityClass);
    }

    public void goToDetails(long id){
        Intent intent = getIntent(DetailsActivity.class);
        intent.putExtra(EXTRA_ID,id);
        contextWeakReference.get().startActivity(intent);
    }
}
