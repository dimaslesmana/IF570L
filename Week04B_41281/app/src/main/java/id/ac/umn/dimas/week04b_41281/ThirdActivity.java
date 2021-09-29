package id.ac.umn.dimas.week04b_41281;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity implements IFragmentListener {
    private EditText etTulisan;
    private Button btnGantiTulisan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FirstFragment firstFragment = (FirstFragment) new FirstFragment();
        firstFragment.setFragmentListener(this);
        fragmentTransaction.replace(R.id.third_fragment_1, firstFragment);

        Fragment secondFragment = new SecondFragment();
        fragmentTransaction.replace(R.id.third_fragment_2, secondFragment);

        fragmentTransaction.commit();
    }

    @Override
    public void fragmentInitialized() {
        etTulisan = findViewById(R.id.fragment_first_et_tulisan);
        btnGantiTulisan = findViewById(R.id.fragment_first_btn_berubah);

        btnGantiTulisan.setOnClickListener(v -> {
            SecondFragment secondFragment = (SecondFragment) getSupportFragmentManager().findFragmentById(R.id.third_fragment_2);
            secondFragment.setTextView(etTulisan.getText().toString());
        });
    }
}