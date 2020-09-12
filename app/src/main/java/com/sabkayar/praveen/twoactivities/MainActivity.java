package com.sabkayar.praveen.twoactivities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.sabkayar.praveen.twoactivities.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mBinding;

    private static final String EXTRA_REPLY =
            MainActivity.class.getName() + ".extra.REPLY";

    private static final int RC_MESSAGE = 1;

    public static Intent getIntent(String reply) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_REPLY, reply);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    public void launchSecondActivity(View view) {
        Log.d(TAG, "Button clicked!");
        String message = mBinding.editTextMain.getText().toString();
        if (!TextUtils.isEmpty(message)) {
            startActivityForResult(SecondActivity.getIntent(this, message), RC_MESSAGE);
        } else {
            Snackbar.make(mBinding.buttonMain, "Enter some message to proceed!", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_MESSAGE && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra(EXTRA_REPLY)) {
                mBinding.textViewMessage.setText(data.getStringExtra(EXTRA_REPLY));
                mBinding.textViewHeader.setVisibility(View.VISIBLE);
            }
        }
    }
}