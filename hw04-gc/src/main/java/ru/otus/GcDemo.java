package ru.otus;

import com.sun.management.GarbageCollectionNotificationInfo;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import javax.management.MBeanServer;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;

/*

1)  -Xms2048m
    -Xmx2048m

2)  -Xms4096m
    -Xmx4096m

-Xlog:gc=debug:file=./logs/gc-%p-%t.log:tags,uptime,time,level:filecount=5,filesize=10m
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=./logs/dump

GC:
Serial Collector -XX:+UseSerialGC
Parallel Collector -XX:+UseParallelGC
G1 -XX:+UseG1GC

*/

public class GcDemo {
    private static long beginTime;

    public static void main(String... args) {
        try {
            System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
            switchOnMonitoring();
            beginTime = System.currentTimeMillis();

            int size = 5 * 1000 * 1000;
            int loopCounter = 10;
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("ru.otus:type=Benchmark");

            Benchmark mbean = new Benchmark(loopCounter);
            mbs.registerMBean(mbean, name);
            mbean.setSize(size);
            mbean.run();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            long totalDuration = (System.currentTimeMillis() - beginTime);
            GcStatistics.printResult(totalDuration);
        }
    }

    private static void switchOnMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            System.out.println("GC name:" + gcbean.getName());
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    String gcName = info.getGcName();
                    String gcAction = info.getGcAction();
                    String gcCause = info.getGcCause();
                    GcEnum gcEnumName = GcEnum.getGcEnumName(gcName);
                    GcStatistics.setGcFullName(gcEnumName);
                    long startTime = info.getGcInfo().getStartTime();
                    long duration = info.getGcInfo().getDuration();

                    System.out.println("start:" + startTime + " Name:" + gcName + ", action:" + gcAction + ", gcCause:" + gcCause + "(" + duration + " ms)");
                    GcStatistics.logNewGCAction(gcEnumName, gcName, duration);
                }
            };
            emitter.addNotificationListener(listener, null, null);
        }
    }
}
