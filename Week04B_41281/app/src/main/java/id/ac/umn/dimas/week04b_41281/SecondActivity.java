package id.ac.umn.dimas.week04b_41281;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {
    private EditText etTulisan;
    private Button btnGantiTulisan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etTulisan = findViewById(R.id.fragment_first_et_tulisan);
        btnGantiTulisan = findViewById(R.id.fragment_first_btn_berubah);

        btnGantiTulisan.setOnClickListener(v -> {
            SecondFragment secondFragment = (SecondFragment) getSupportFragmentManager().findFragmentById(R.id.second_fragment_2);
            secondFragment.setTextView(etTulisan.getText().toString());
        });
    }
}