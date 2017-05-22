package com.axa.dagger2example.ui.main.adapters;
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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.axa.dagger2example.R;
import com.axa.dagger2example.domain.model.User;

import java.util.Collections;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{

    CompositeDisposable disposables;

    PublishSubject<User> publishSubject;
    List<User> users;
    OnClickListener onClickListener;

    LayoutInflater layoutInflater;

    public interface OnClickListener{
        void onClick(User user);
    }

    public UserAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
        users = Collections.emptyList();
        disposables = new CompositeDisposable();
        publishSubject = PublishSubject.create();
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater
                .inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {

        final User user = users.get(position);
        holder.bind(user);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publishSubject.onNext(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void subscribeOnClick(Consumer<User> consumer){
        Disposable disposable = publishSubject.subscribe(consumer);
        disposables.add(disposable);
    }

    public void dispose(){
        if(!disposables.isDisposed()) {
            disposables.clear();
        }
    }
}