package id.ac.umn.dimas.week09_41281;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MahasiswaRepository {
    private final MahasiswaDAO daoMahasiswa;
    private final LiveData<List<Mahasiswa>> daftarMahasiswa;

    MahasiswaRepository(Application app) {
        MahasiswaRoomDatabase db = MahasiswaRoomDatabase.getDatabase(app);
        daoMahasiswa = db.daoMahasiswa();
        daftarMahasiswa = daoMahasiswa.getAllMahasiswa();
    }

    LiveData<List<Mahasiswa>> getDaftarMahasiswa() {
        return this.daftarMahasiswa;
    }

    public void insert(Mahasiswa mahasiswa) {
        new insertAsyncTask(daoMahasiswa).execute(mahasiswa);
    }

    public void deleteAll() {
        new deleteAllAsyncTask(daoMahasiswa).execute();
    }

    public void delete(Mahasiswa mahasiswa) {
        new deleteAsyncTask(daoMahasiswa).execute(mahasiswa);
    }

    public void update(Mahasiswa mahasiswa) {
        new updateAsyncTask(daoMahasiswa).execute(mahasiswa);
    }

    private static class insertAsyncTask extends AsyncTask<Mahasiswa, Void, Void> {
        private final MahasiswaDAO asyncDaoMahasiswa;

        insertAsyncTask(MahasiswaDAO dao) {
            this.asyncDaoMahasiswa = dao;
        }

        @Override
        protected Void doInBackground(final Mahasiswa... mahasiswas) {
            asyncDaoMahasiswa.insert(mahasiswas[0]);
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private final MahasiswaDAO asyncDaoMahasiswa;

        deleteAllAsyncTask(MahasiswaDAO dao) {
            this.asyncDaoMahasiswa = dao;
        }

        @Override
        protected Void doInBackground(final Void... voids) {
            asyncDaoMahasiswa.deleteAll();
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Mahasiswa, Void, Void> {
        private final MahasiswaDAO asyncDaoMahasiswa;

        deleteAsyncTask(MahasiswaDAO dao) {
            this.asyncDaoMahasiswa = dao;
        }

        @Override
        protected Void doInBackground(final Mahasiswa... mahasiswas) {
            asyncDaoMahasiswa.delete(mahasiswas[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Mahasiswa, Void, Void> {
        private final MahasiswaDAO asyncDaoMahasiswa;

        updateAsyncTask(MahasiswaDAO dao) {
            this.asyncDaoMahasiswa = dao;
        }

        @Override
        protected Void doInBackground(final Mahasiswa... mahasiswas) {
            asyncDaoMahasiswa.update(mahasiswas[0]);
            return null;
        }
    }
}
