package id.ac.umn.dimas.week09_41281;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.umn.dimas.week09_41281.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private MahasiswaViewModel mhsVM;
    private static final int REQUEST_TAMBAH = 1;
    private static final int REQUEST_EDIT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(view -> {
            Intent addIntent = new Intent(MainActivity.this, DetailActivity.class);

            startActivityForResult(addIntent, REQUEST_TAMBAH);
        });

        final MahasiswaListAdapter adapter = new MahasiswaListAdapter(this);
        rv = findViewById(R.id.rvMahasiswa);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        mhsVM = ViewModelProviders.of(this).get(MahasiswaViewModel.class);
        mhsVM.getDaftarMahasiswa().observe(this, mahasiswas -> adapter.setDaftarMahasiswa(mahasiswas));

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Mahasiswa mahasiswa = adapter.getMahasiswaAtPosition(position);

                if (direction == ItemTouchHelper.LEFT) {
                    mhsVM.delete(mahasiswa);
                    Toast.makeText(MainActivity.this, "Mahasiswa dengan NIM = " + mahasiswa.getNim() + " dihapus", Toast.LENGTH_LONG).show();
                } else if (direction == ItemTouchHelper.RIGHT) {
                    Intent editIntent = new Intent(MainActivity.this, DetailActivity.class);

                    editIntent.putExtra(DetailActivity.EXTRA_MAHASISWA, mahasiswa);
                    startActivityForResult(editIntent, REQUEST_EDIT);
                }
            }
        });

        helper.attachToRecyclerView(rv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Mahasiswa mahasiswa = (Mahasiswa) data.getSerializableExtra(DetailActivity.EXTRA_MAHASISWA);

            if (requestCode == REQUEST_TAMBAH) {
                mhsVM.insert(mahasiswa);
            } else if (requestCode == REQUEST_EDIT) {
                mhsVM.update(mahasiswa);
            }
        }

        rv.getAdapter().notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            mhsVM.deleteAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }
}