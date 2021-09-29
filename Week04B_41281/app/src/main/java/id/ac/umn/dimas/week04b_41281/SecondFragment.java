package id.ac.umn.dimas.week04b_41281;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment {
    private TextView tvTulisan;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        tvTulisan = view.findViewById(R.id.fragment_second_tv_tulisan);

        return view;
    }

    public void setTextView(String text) {
        if (!text.trim().isEmpty()) {
            tvTulisan.setText(text);
        } else {
            tvTulisan.setText("Initial TextView");
        }
    }
}