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


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map:GoogleMap

    var sydney = LatLng(-34.15, 151.5590)
    var TamWorth = LatLng(-31.083332, 150.916672)
    var NewCastle = LatLng(-32.916668, 151.750000)
    var Brisbane = LatLng(-27.470125, 153.021072)

    var latLngArrayList: ArrayList<LatLng> = ArrayList()
    var locationNameArraylist: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        latLngArrayList!!.add(sydney);
        locationNameArraylist!!.add("Sydney");
        latLngArrayList!!.add(TamWorth);
        locationNameArraylist!!.add("TamWorth");
        latLngArrayList!!.add(NewCastle);
        locationNameArraylist!!.add("New Castle");
        latLngArrayList!!.add(Brisbane);
        locationNameArraylist!!.add("Brisbase");

        val mapFragment  = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }



    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        for (i in 0 until latLngArrayList.size) {
            map.addMarker(
                MarkerOptions().position(latLngArrayList.get(i))
                    .title("Marker in " + locationNameArraylist.get(i))
            )
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(sydney,10f),
                4000,
                null
            )
        }


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