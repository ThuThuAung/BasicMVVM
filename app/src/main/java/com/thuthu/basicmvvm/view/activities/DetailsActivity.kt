package com.thuthu.basicmvvm.view.activities

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.thuthu.basicmvvm.R
import com.thuthu.basicmvvm.data.model.UserResponse
import com.thuthu.basicmvvm.databinding.DetailLayoutBinding
import com.thuthu.basicmvvm.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*

class DetailsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var viewModel: DetailViewModel

    private lateinit var user: UserResponse
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<DetailLayoutBinding>(this, R.layout.detail_layout)
        intent?.extras?.getParcelable<UserResponse>("userDetailData")?.let {

            user = it
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding.viewModel = viewModel


        viewModel.onSetUpData(user)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        val lat = user.address?.geo?.lat
        val lng = user.address?.geo?.lng
        val loc = LatLng(lat!!.toDouble(), lng!!.toDouble())
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.setOnMarkerClickListener(this)
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        googleMap.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->

            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }

        googleMap.addMarker(MarkerOptions().position(loc)
            .title(user.address?.city))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc))

    }

    override fun onMarkerClick(p0: Marker?) = false

}