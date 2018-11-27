package com.example.jens.androidprojekt2;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.UUID;

public class Connector {

    private static final String TAG = "Connector";

    private static final boolean BT_ON = true;
    private static final boolean BT_OFF = false;

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket bluetoothSocket;
    private String address;
    private OutputStream output;

    public Connector(String address) {
        this.address = address;
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void setBluetooth(boolean state) {
        if (state == Connector.BT_ON) {
            // Check if bluetooth is off
            if (!this.bluetoothAdapter.isEnabled()) {
                this.bluetoothAdapter.enable();
                while (!this.bluetoothAdapter.isEnabled())
                Log.d(Connector.TAG, "Bluetooth turned on");

            }

        }
        // Check if bluetooth is enabled
        else if (state == Connector.BT_OFF) {
            // Check if bluetooth is enabled
            if (this.bluetoothAdapter.isEnabled()) {
                this.bluetoothAdapter.disable();
                while (this.bluetoothAdapter.isEnabled())
                Log.d(Connector.TAG, "Bluetooth turned off");

            }

        }

    }

    public boolean connect() {

        boolean connected;
        BluetoothDevice nxt = this.bluetoothAdapter.getRemoteDevice(this.address);

        try {
            this.bluetoothSocket = nxt.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            this.bluetoothSocket.connect();
            connected = true;

        } catch (IOException e) {
            connected = false;

        }

        return connected;

    }

    public Integer readMessage() {
        Integer message;

        if (this.bluetoothSocket != null) {
            try {
                InputStreamReader input = new InputStreamReader(this.bluetoothSocket.getInputStream());
                message = input.read();
                Log.d(Connector.TAG, "Successfully read message");

            } catch (IOException e) {
                message = null;
                Log.d(Connector.TAG, "Couldn't read message");

            }
        } else {
            message = null;
            Log.d(Connector.TAG, "Couldn't read message");

        }

        return message;

    }

    public void sendMessage(int send) {
        if (this.bluetoothSocket != null) {
            try {
                OutputStreamWriter output = new OutputStreamWriter(this.bluetoothSocket.getOutputStream());
                output.write(send);
                output.flush();
                Log.d(Connector.TAG, "Successfully send message");

            } catch (IOException e) {
                Log.d(Connector.TAG, "Couldn't send message");

            }
        } else {
            Log.d(Connector.TAG, "Couldn't send message");

        }
    }

}




