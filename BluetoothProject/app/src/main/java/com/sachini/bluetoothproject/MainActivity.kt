package com.sachini.bluetoothproject

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import entity.DataEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viewModel.DataAdapter
import viewModel.DataViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var btnShowDetails: Button
    private lateinit var btnViewPrevious: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: DataViewModel
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnShowDetails = findViewById(R.id.btnShowDetails)
        btnViewPrevious = findViewById(R.id.btnViewPrevious)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this)[DataViewModel::class.java]

        btnShowDetails.setOnClickListener { fetchDataFromDevice() }
        btnViewPrevious.setOnClickListener { viewPreviousDetails() }

        viewModel.getAllData().observe(this) { data ->
            recyclerView.adapter = DataAdapter(data)
        }
    }

    private fun fetchDataFromDevice() {
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled) {
            // Prompt user to enable Bluetooth
            return
        }

        val device: Unit = if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        } else {

        }
        bluetoothAdapter.bondedDevices.firstOrNull {
            it.name == "ELM327" // Replace with your device's name
        }

        device?.let {
            val intentFilter = IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED)
            registerReceiver(object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    // Simulate receiving data from device
                    val data = "Sample data from ELM 327"
                    CoroutineScope(Dispatchers.IO).launch {
                        val parsedData = DataEntity(System.currentTimeMillis(), data)
                        viewModel.insertData(parsedData)
                    }
                }
            }, intentFilter)
        }
    }

    private fun viewPreviousDetails() {
        // Navigate to a new screen or update the current list
    }
}
