package com.axa.dagger2example.ui.details;
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


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.axa.dagger2example.R;
import com.axa.dagger2example.domain.model.User;
import com.axa.dagger2example.ui.Navigator;
import com.axa.dagger2example.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends BaseActivity implements DetailsView{

    @Inject
    DetailsPresenter presenter;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @BindView(R.id.tv_name_label)
    TextView tvNameLabel;
    @BindView(R.id.tv_phone_label)
    TextView tvPhoneLabel;

    long id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        getActivityComponent().inject(this);
        setBasePresenter(presenter);

        if(savedInstanceState==null) {
            id = getIntent().getLongExtra(Navigator.EXTRA_ID, -1);
        }else {
            id = savedInstanceState.getLong(Navigator.EXTRA_ID);
        }
    }

    @Override
    public void inflateUser(String labelName, String labelPhone,User user) {
        tvNameLabel.setText(labelName);
        tvPhoneLabel.setText(labelPhone);

        tvName.setText(user.getName());
        tvPhone.setText(user.getPhone());
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadUser(id);
    }
}
