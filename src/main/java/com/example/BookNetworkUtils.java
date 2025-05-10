package com.example;

import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.InetAddress;

public class BookNetworkUtils {
    public static void sendMulticastMessage(String message, InetAddress groupAddress) {
        try (MulticastSocket socket = new MulticastSocket()) {
            // Deprecated MulticastSocket methods
            socket.setInterface(InetAddress.getLocalHost());
            socket.setTTL((byte) 1);
            socket.joinGroup(groupAddress);
            socket.send(new DatagramPacket(message.getBytes(), message.length(), groupAddress, 5000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}