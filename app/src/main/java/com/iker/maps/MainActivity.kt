package com.iker.maps

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.Map


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map:GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment  = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }



    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
        map.setOnMarkerClickListener(OnMarkerClickListener { marker -> // on marker click we are getting the title of our marker
            // which is clicked and displaying it in a toast message.
            val markerName = marker.title
            Toast.makeText(this@MainActivity, "Clicked location is $markerName", Toast.LENGTH_SHORT)
                .show()
            false
        })
    }

    private fun createMarker() {
        val coordinates = LatLng(40.403108, -3.706084)
        val marker = MarkerOptions().position(coordinates).title("Prueba")
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates,18f),
            4000,
            null
        )
    }
}