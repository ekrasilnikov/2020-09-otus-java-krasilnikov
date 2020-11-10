package ru.otus;

public class GcStatistics {
    private static GcEnum gcFullName;
    private static int youngGenCount = 0;
    private static int oldGenCount = 0;
    private static long youngGenDurationTotal = 0;
    private static long oldGenDurationTotal = 0;

    public static void logNewGCAction(GcEnum gcEnumName, String gcName, long duration) {
        if (GcEnum.getNameYoungGenerationFromGCName(gcEnumName).equals(gcName)) {
            youngGenCount++;
            youngGenDurationTotal += duration;
        } else if (GcEnum.getNameOldGenerationFromGCName(gcEnumName).equals(gcName)) {
            oldGenCount++;
            oldGenDurationTotal += duration;
        }
    }

    public static void printResult(Long totalDuration) {
        System.out.println("GC Name - " + gcFullName);
        System.out.println("GC Young Gen (count) - " + youngGenCount + ", duration - " + youngGenDurationTotal);
        System.out.println("GC Old Gen (count) - " + oldGenCount + ", duration - " + oldGenDurationTotal);
        System.out.println("Total duration - " + totalDuration);
    }

    public static void setGcFullName(GcEnum gcFullName) {
        GcStatistics.gcFullName = gcFullName;
    }
}
