package com.sabkayar.praveen.twoactivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.sabkayar.praveen.twoactivities.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    private static final String EXTRA_MESSAGE = SecondActivity.class.getName() + "extra.MESSAGE";

    public static Intent getIntent(Context context, String message) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        return intent;
    }

    private ActivitySecondBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_MESSAGE)) {
            mBinding.textViewMessage.setText(intent.getStringExtra(EXTRA_MESSAGE));
            mBinding.textHeader.setVisibility(View.VISIBLE);
        }


    }

    public void returnReply(View view) {
        String reply = mBinding.editTextReply.getText().toString();
        if (!TextUtils.isEmpty(reply)) {
            setResult(RESULT_OK, MainActivity.getIntent(reply));
            finish();
        } else {
            Snackbar.make(mBinding.buttonReply, "Enter some reply to proceed!", Snackbar.LENGTH_SHORT).show();
        }
    }
}