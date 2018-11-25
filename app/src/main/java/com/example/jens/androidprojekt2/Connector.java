package com.example.jens.androidprojekt2;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.pc.comm.NXTConnector;
import lejos.pc.comm.NXTInfo;
import lejos.pc.comm.NXTComm;

public class Connector{

    private NXTConnector conn = null;
    private DataOutputStream output = null;
    private DataInputStream input = null;
    private byte data[] = new byte[255];
    private boolean running = false;
    private int size = 0;

    public Connector(){}

    public boolean connect(NXTInfo ni) {
        conn = new NXTConnector();
        boolean connected = conn.connectTo(ni, NXTComm.PACKET);

        if(!connected){
            return false;
        }

        output = new DataOutputStream(conn.getOutputStream());
        input = new DataInputStream(conn.getInputStream());
        running = true;

        return running;
    }

    public void send(byte data[]) throws IOException{
        output.write(data);
        output.flush();
    }

    public void disconnect() throws IOException{
        running = false;
        input.close();
        output.close();
        conn.close();
    }

    public boolean isConnected(){
        return running;
    }

    public int readInt()throws IOException{
        return input.readInt();
    }

    public float readFloat()throws IOException{
        return input.readFloat();
    }
}




