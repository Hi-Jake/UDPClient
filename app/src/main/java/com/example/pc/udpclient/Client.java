package com.example.pc.udpclient;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client extends Thread{

    public static String SERVERIP = "192.168.0.56"; // 'Within' the emulator!
    public static final int SERVERPORT = 4444;

    public Client() {
        super();
    }

    @Override
    public void run() {
        try {
            // Retrieve the ServerName
            InetAddress serverAddr = InetAddress.getByName(SERVERIP);

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

            /* Ready Receive buffer */
            byte[] recbuf = new byte[256];

            /* Ready Receive Packet */
            DatagramPacket receivePacket = new DatagramPacket(recbuf,recbuf.length);

            /* Receive from Server */
            socket.receive(receivePacket);
            String line = new String(receivePacket.getData(),0,receivePacket.getLength());
            Log.d("UDP", "C: Received: '" + line + "'");

        } catch (Exception e) {
            Log.e("UDP", "C: Error", e);
        }

    }
}
