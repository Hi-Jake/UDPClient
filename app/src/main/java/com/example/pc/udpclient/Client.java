package com.example.pc.udpclient;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client implements Runnable{

    public static String SERVERIP = "127.0.0.1"; // 'Within' the emulator!
    public static final int SERVERPORT = 4444;

    @Override
    public void run() {
        try {
            // Retrieve the ServerName
            InetAddress serverAddr = InetAddress.getByName(SERVERIP);

            Log.d("UDP", "C: Connecting...");
            /* Create new UDP-Socket */
            DatagramSocket socket = new DatagramSocket();

            /* Prepare some data to be sent. */
            byte[] buf = ("Hello from Client").getBytes();

            /* Create UDP-packet with
             * data & destination(url+port) */
            DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, SERVERPORT);
            Log.d("UDP", "C: Sending: '" + new String(buf) + "'");

            /* Send out the packet */
            socket.send(packet);
            Log.d("UDP", "C: Sent.");
            Log.d("UDP", "C: Done.");

            socket.receive(packet);
            String line = new String(packet.getData(),0,packet.getLength());
            Log.d("UDP", "C: Received: '" + line + "'");

        } catch (Exception e) {
            Log.e("UDP", "C: Error", e);
        }

    }
}
