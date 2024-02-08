package com.example.ex2evibaialvarez.Fragments;

import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;


import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ex2evibaialvarez.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Mapa_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Mapa_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Mapa_Fragment newInstance(String param1, String param2) {
        Mapa_Fragment fragment = new Mapa_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private MapView mapa;
    IMapController controlMapa;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mapa_, container, false);

        //Se coloca el mapa
        mapa = view.findViewById(R.id.mapaGPS);
        mapa.setTileSource(TileSourceFactory.MAPNIK);

        //Inicilizamos marcador en mapa
        controlMapa = mapa.getController();
        controlMapa.setZoom(15.65);
        GeoPoint startPoint = new GeoPoint(40.5, -1.5);
        controlMapa.setCenter(startPoint);
        Configuration.getInstance().load(getContext(), PreferenceManager.getDefaultSharedPreferences(getContext()));

        //Zoom al mapa y config
        mapa.setBuiltInZoomControls(true);
        mapa.setMultiTouchControls(true);

        //Marcador inicial
        Marker marcador = new Marker(mapa);
        GeoPoint markerPosi = new GeoPoint(40.5, -1.5);
        marcador.setPosition(markerPosi);
        marcador.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        //Imagen del marker
        Drawable de = ResourcesCompat.getDrawable(getResources(), R.drawable.marker, null);
        Bitmap bitmapElorrieta = ((BitmapDrawable) de).getBitmap();
        Drawable dre = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmapElorrieta, (int) (15f * getResources().getDisplayMetrics().density), (int) (15 * getResources().getDisplayMetrics().density), true));
        marcador.setIcon(dre);

        //Listener del marcador
        marcador.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                //Cambio de fragment
                Detalles_Fragment d_fragment = new Detalles_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, d_fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                return false;
            }
        });

        mapa.getOverlays().add(marcador);

        return view;
    }
}