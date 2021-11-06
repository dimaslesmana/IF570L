package id.ac.umn.dimas.week09_41281;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MAHASISWA = "extra_mahasiswa";
    private EditText etNim, etNama, etEmail, etNomorHp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etNomorHp = findViewById(R.id.etNomorHp);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_MAHASISWA)) {
            Mahasiswa mahasiswa = (Mahasiswa) intent.getSerializableExtra(EXTRA_MAHASISWA);

            etNim.setText(mahasiswa.getNim());
            etNama.setText(mahasiswa.getNama());
            etEmail.setText(mahasiswa.getEmail());
            etNomorHp.setText(mahasiswa.getNomorhp());
            etNim.setEnabled(false);
        } else {
            etNim.setEnabled(true);
        }
    }

    public void simpanData(View view) {
        String mNim = etNim.getText().toString();
        String mNama = etNama.getText().toString();
        String mEmail = etEmail.getText().toString();
        String mNomorHp = etNomorHp.getText().toString();

        if (mNim.length() <= 0 || mNama.length() <= 0 || mEmail.length() <= 0 || mNomorHp.length() <= 0) {
            Toast.makeText(this, "Semua harus Diisi", Toast.LENGTH_LONG).show();
        } else {
            Intent intentJawab = new Intent();
            Mahasiswa mahasiswa = new Mahasiswa(mNim, mNama, mEmail, mNomorHp);

            intentJawab.putExtra(EXTRA_MAHASISWA, mahasiswa);
            setResult(RESULT_OK, intentJawab);
            finish();
        }
    }

    public void batal(View view) {
        Intent intentJawab = new Intent();
        setResult(RESULT_CANCELED, intentJawab);
        finish();
    }
}