package ru.otus;

public enum GcEnum {
    SERIALGC("Copy", "MarkSweepCompact"),
    PARALLELGC("PS Scavenge", "PS MarkSweep"),
    G1GC("G1 Young Generation", "G1 Old Generation");
    // ZGC("ZGC", "");

    private String nameYoungGeneration;
    private String nameOldGeneration;

    GcEnum(String nameYoungGeneration, String nameOldGeneration) {
        this.nameYoungGeneration = nameYoungGeneration;
        this.nameOldGeneration = nameOldGeneration;
    }

    public String getNameYoungGeneration() {
        return nameYoungGeneration;
    }

    public String getNameOldGeneration() {
        return nameOldGeneration;
    }

    public static GcEnum getGcEnumName(String gcName) {
        GcEnum result = null;
        if (gcName.equals(SERIALGC.getNameYoungGeneration()) || gcName.equals(SERIALGC.getNameOldGeneration()))
            result = SERIALGC;
        else if (gcName.equals(PARALLELGC.getNameYoungGeneration()) || gcName.equals(PARALLELGC.getNameOldGeneration()))
            result = PARALLELGC;
        else if (gcName.equals(G1GC.getNameYoungGeneration()) || gcName.equals(G1GC.getNameOldGeneration()))
            result = G1GC;
        return result;
    }

    public static String getNameYoungGenerationFromGCName(GcEnum gcEnumName) {
        String result = "";
        switch (gcEnumName) {
            case SERIALGC:
                result = SERIALGC.getNameYoungGeneration();
                break;
            case PARALLELGC:
                result = PARALLELGC.getNameYoungGeneration();
                break;
            case G1GC:
                result = G1GC.getNameYoungGeneration();
                break;
        }
        return result;
    }

    public static String getNameOldGenerationFromGCName(GcEnum gcEnumName) {
        String result = "";
        switch (gcEnumName) {
            case SERIALGC:
                result = SERIALGC.getNameOldGeneration();
                break;
            case PARALLELGC:
                result = PARALLELGC.getNameOldGeneration();
                break;
            case G1GC:
                result = G1GC.getNameOldGeneration();
                break;
        }
        return result;
    }
}
