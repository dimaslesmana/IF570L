package id.ac.umn.dimas.week09_41281;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MahasiswaListAdapter extends RecyclerView.Adapter<MahasiswaListAdapter.MahasiswaViewHolder> {
    private final LayoutInflater mInflater;
    private List<Mahasiswa> daftarMahasiswa;

    public MahasiswaListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.mhs_item_layout, parent, false);

        return new MahasiswaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        if (daftarMahasiswa != null) {
            Mahasiswa mahasiswa = daftarMahasiswa.get(position);

            holder.tvNim.setText(mahasiswa.getNim());
            holder.tvNama.setText(mahasiswa.getNama());
        } else {
            holder.tvNim.setText("Belum ada Mahasiswa");
        }
    }

    @Override
    public int getItemCount() {
        if (daftarMahasiswa != null) {
            return daftarMahasiswa.size();
        } else {
            return 0;
        }
    }

    public static class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNim;
        private final TextView tvNama;

        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNim = itemView.findViewById(R.id.tvItemNim);
            tvNama = itemView.findViewById(R.id.tvItemNama);
        }
    }

    public Mahasiswa getMahasiswaAtPosition(int position) {
        return daftarMahasiswa.get(position);
    }

    void setDaftarMahasiswa(List<Mahasiswa> listMahasiswa) {
        daftarMahasiswa = listMahasiswa;
        notifyDataSetChanged();
    }
}
