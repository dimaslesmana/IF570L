package id.ac.umn.dimas.week08b_41281;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String COUNTER_KEY = "counter";
    private final String WARNA_KEY = "warna";
    private SharedPreferences mPreferences;
    private String sharedPrefFile;
    private int mCount = 0;
    private int mWarna;
    private TextView tvCounter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        tvCounter = findViewById(R.id.tvCounter);

        /*if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt(COUNTER_KEY);
            mWarna = savedInstanceState.getInt(WARNA_KEY);

            if (mCount != 0) {
                tvCounter.setText(String.valueOf(mCount));
            }

            tvCounter.setBackgroundColor(mWarna);
        }*/

        sharedPrefFile = context.getPackageName();
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        mCount = mPreferences.getInt(COUNTER_KEY, 0);
        mWarna = mPreferences.getInt(WARNA_KEY, mWarna);

        tvCounter.setText(String.valueOf(mCount));
        tvCounter.setBackgroundColor(mWarna);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(COUNTER_KEY, mCount);
        preferencesEditor.putInt(WARNA_KEY, mWarna);
        preferencesEditor.apply();
    }

    /*@Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(COUNTER_KEY, mCount);
        outState.putInt(WARNA_KEY, mWarna);
    }*/

    public void gantiBackground(View view) {
        int warna = ((ColorDrawable) view.getBackground()).getColor();

        mWarna = warna;
        tvCounter.setBackgroundColor(warna);
    }

    public void tambahCounter(View view) {
        mCount++;
        tvCounter.setText(String.valueOf(mCount));
    }

    public void resetCounter(View view) {
        mCount = 0;
        tvCounter.setText(String.valueOf(mCount));
        mWarna = ContextCompat.getColor(context, R.color.gray);
        tvCounter.setBackgroundColor(mWarna);
    }
}