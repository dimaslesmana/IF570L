package id.ac.umn.dimas.week09_41281;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MahasiswaViewModel extends AndroidViewModel {
    private final MahasiswaRepository mhsRepository;
    private final LiveData<List<Mahasiswa>> daftarMahasiswa;

    public MahasiswaViewModel(@NonNull Application application) {
        super(application);
        this.mhsRepository = new MahasiswaRepository(application);
        this.daftarMahasiswa = mhsRepository.getDaftarMahasiswa();
    }

    LiveData<List<Mahasiswa>> getDaftarMahasiswa() {
        return this.daftarMahasiswa;
    }

    public void insert(Mahasiswa mahasiswa) {
        mhsRepository.insert(mahasiswa);
    }

    public void deleteAll() {
        mhsRepository.deleteAll();
    }

    public void delete(Mahasiswa mahasiswa) {
        mhsRepository.delete(mahasiswa);
    }

    public void update(Mahasiswa mahasiswa) {
        mhsRepository.update(mahasiswa);
    }
}
