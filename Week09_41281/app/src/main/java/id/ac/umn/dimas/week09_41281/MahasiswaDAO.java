package id.ac.umn.dimas.week09_41281;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MahasiswaDAO {
    @Query("SELECT * from tblMahasiswa")
    LiveData<List<Mahasiswa>> getAllMahasiswa();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Mahasiswa mahasiswa);

    @Delete
    void delete(Mahasiswa mahasiswa);

    @Update
    void update(Mahasiswa mahasiswa);

    @Query("DELETE FROM tblMahasiswa")
    void deleteAll();
}
