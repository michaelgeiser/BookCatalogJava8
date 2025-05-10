package com.example;

import javax.management.ObjectName;
import javax.management.monitor.CounterMonitor;
import javax.management.monitor.GaugeMonitor;

public class BookManagementMonitor {
    public static void monitorBookSales(CounterMonitor monitor) {
        try {
            ObjectName observedObject = monitor.getObservedObject(); // Deprecated as of Java 9
            Number threshold = monitor.getThreshold(); // Deprecated as of Java 9
            Number derivedGauge = monitor.getDerivedGauge(); // Deprecated as of Java 9
            long derivedGaugeTimeStamp = monitor.getDerivedGaugeTimeStamp(); // Deprecated as of Java 9
            // ...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void monitorBookInventory(GaugeMonitor monitor) {
        try {
            ObjectName observedObject = monitor.getObservedObject(); // Deprecated as of Java 9
            Number derivedGauge = monitor.getDerivedGauge(); // Deprecated as of Java 9
            long derivedGaugeTimeStamp = monitor.getDerivedGaugeTimeStamp(); // Deprecated as of Java 9
            // ...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}