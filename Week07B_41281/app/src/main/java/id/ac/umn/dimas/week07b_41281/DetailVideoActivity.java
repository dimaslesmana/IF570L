package id.ac.umn.dimas.week07b_41281;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

public class DetailVideoActivity extends AppCompatActivity {
    private VideoView vvDetail;
    private EditText etJudul;
    private EditText etKeterangan;
    private EditText etUri;
    private Button btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video);

        vvDetail = findViewById(R.id.vvDetail);
        etJudul = findViewById(R.id.etJudul);
        etKeterangan = findViewById(R.id.etKeterangan);
        etUri = findViewById(R.id.etUri);
        btnKembali = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        SumberVideo sumberVideo = (SumberVideo) bundle.getSerializable("DetailVideo");

        etJudul.setText(sumberVideo.getJudul());
        etKeterangan.setText(sumberVideo.getKeterangan());
        etUri.setText(sumberVideo.getVideoURI());

        vvDetail.setVideoURI(Uri.parse(sumberVideo.getVideoURI()));
        vvDetail.seekTo(100);
        vvDetail.start();

        MediaController mediaController = new MediaController(this);
        mediaController.setMediaPlayer(vvDetail);
        vvDetail.setMediaController(mediaController);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}