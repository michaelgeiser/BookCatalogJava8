package com.example;


import java.net.DatagramSocket;
import java.net.SocketException;

public class BookNetworkMonitor {
    public static void monitorDatagramSocket(DatagramSocket socket) {
        try {
            int receiveBufferSize = socket.getReceiveBufferSize(); // Use the standard method
            System.out.println("Receive buffer size: " + receiveBufferSize);

            boolean reuseAddr = socket.getReuseAddress(); // Use the standard method
            System.out.println("Reuse address: " + reuseAddr);

            // ...
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static void configureDatagramSocket(DatagramSocket socket) {
        try {
            socket.setReceiveBufferSize(65536); // Use the standard method
            socket.setReuseAddress(true); // Use the standard method
            // ...
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}