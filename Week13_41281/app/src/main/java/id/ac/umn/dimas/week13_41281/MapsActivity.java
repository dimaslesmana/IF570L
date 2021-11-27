package id.ac.umn.dimas.week13_41281;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import id.ac.umn.dimas.week13_41281.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private UiSettings mUiSettings;
    static LatLng currentLocation;
    private List<MarkerAndShape> daftarMarkerAndShape = new ArrayList<>();
    private List<LatLng> lokalTitiks = new ArrayList<>();
    private Button btnTambah;
    static final int REQUEST_TAMBAH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnTambah = findViewById(R.id.btnTambah);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tambahIntent = new Intent(MapsActivity.this, TambahObject.class);
                startActivityForResult(tambahIntent, REQUEST_TAMBAH);
            }
        });

        currentLocation = new LatLng(-6.2574591,106.6183484);
        lokalTitiks.add(currentLocation);
        daftarMarkerAndShape.add(new MarkerAndShape("Marker", "Universitas Multimedia Nusantara", 0.0, lokalTitiks));
        daftarMarkerAndShape.add(new MarkerAndShape("Circle", "Area 500m dari UMN", 500.0, lokalTitiks));

        lokalTitiks.clear();
        lokalTitiks.add(new LatLng(-6.256718, 106.618209));
        lokalTitiks.add(new LatLng(-6.255982, 106.618434));
        lokalTitiks.add(new LatLng(-6.256061, 106.621020));
        lokalTitiks.add(new LatLng(-6.254611, 106.622085));
        lokalTitiks.add(new LatLng(-6.254752, 106.622383));

        daftarMarkerAndShape.add(new MarkerAndShape("PolyLine", "Jalur UMN ke Bethsaida",0.0, lokalTitiks));

        lokalTitiks.clear();
        lokalTitiks.add(new LatLng(-6.256302, 106.617534));
        lokalTitiks.add(new LatLng(-6.256099, 106.619744));
        lokalTitiks.add(new LatLng(-6.256558, 106.619851));
        lokalTitiks.add(new LatLng(-6.259374, 106.618639));
        lokalTitiks.add(new LatLng(-6.258659, 106.616740));

        daftarMarkerAndShape.add(new MarkerAndShape("Polygon", "Area Kampus UMN",0.0, lokalTitiks));

        lokalTitiks.clear();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_TAMBAH) {
            String jenis = data.getStringExtra("jenis");
            String keterangan = data.getStringExtra("keterangan");
            double radius = data.getDoubleExtra("radius", 0.0);
            double[] dTitiks = data.getDoubleArrayExtra("titiks");
            List<LatLng> mTitiks = new ArrayList<>();

            for (int i = 0; i < dTitiks.length / 2; i++) {
                mTitiks.add(new LatLng(dTitiks[2*i], dTitiks[2*i + 1]));
            }

            MarkerAndShape markerAndShape = new MarkerAndShape(jenis, keterangan, radius, mTitiks);
            daftarMarkerAndShape.add(markerAndShape);

            if (mMap != null) {
                mMap.clear();
                gambarMarkerAndShape();
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mUiSettings = mMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 16));

        gambarMarkerAndShape();
    }

    protected void gambarMarkerAndShape() {
        for (int i = 0; i < daftarMarkerAndShape.size(); i++) {
            MarkerAndShape ms = daftarMarkerAndShape.get(i);

            if (ms.getJenis().equalsIgnoreCase("Marker")) {
                ms.setObjekPeta(mMap.addMarker(new MarkerOptions()
                        .position(ms.getTitiks().get(0))
                        .title(ms.getKeterangan())
                ));
            } else if (ms.getJenis().equalsIgnoreCase("Circle")) {
                ms.setObjekPeta(mMap.addCircle(new CircleOptions()
                        .center(ms.getTitiks().get(0))
                        .radius(ms.getRadius())
                        .strokeColor(Color.YELLOW)
                        .fillColor(Color.argb(30, 255, 255, 0))
                ));
            } else if (ms.getJenis().equalsIgnoreCase("PolyLine")) {
                PolylineOptions pl = new PolylineOptions();

                for (int j = 0; j < ms.getJumlahTitiks(); j++) {
                    pl.add(ms.getTitiks().get(j));
                }

                pl.color(Color.RED).width(10.0f);
                ms.setObjekPeta(mMap.addPolyline(pl));
            } else if (ms.getJenis().equalsIgnoreCase("Polygon")) {
                PolygonOptions pg = new PolygonOptions();

                for (int j = 0; j < ms.getJumlahTitiks(); j++) {
                    pg.add(ms.getTitiks().get(j));
                }

                pg.add(ms.getTitiks().get(0))
                        .strokeColor(Color.BLUE)
                        .strokeWidth(10.0f)
                        .fillColor(Color.argb(20, 0, 255, 255));

                ms.setObjekPeta(mMap.addPolygon(pg));
            }
        }
    }
}