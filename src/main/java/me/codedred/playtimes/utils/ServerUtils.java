package me.codedred.playtimes.utils;

import org.bukkit.Bukkit;

public class ServerUtils {

    private static Boolean isNewerVersion = null;
    private static Boolean hasPAPI = null;

    private ServerUtils() {
        throw new IllegalStateException("Utility Class");
    }

    public static boolean isNewerVersion() {
        if (isNewerVersion == null) {
            try {
                Class.forName("org.bukkit.Material").getDeclaredMethod("matchMaterial", String.class, boolean.class);
                isNewerVersion = true;
            } catch (ClassNotFoundException | NoSuchMethodException ignored) {
                isNewerVersion = false;
            }
        }
        return isNewerVersion;
    }


    public static boolean hasPAPI() {
        if (hasPAPI == null) {
            hasPAPI = Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");
        }
        return hasPAPI;
    }


    public static boolean isRisenVersion() {
        String versionString = Bukkit.getServer().getVersion();
        String[] versionParts = versionString.split("[ .-]");

        if (versionParts.length < 2) {
            System.err.println("Unexpected version string format: " + versionString);
            return false; 
        }

        try {
            int majorVersion = Integer.parseInt(versionParts[1]);
            int minorVersion = (versionParts.length >= 3) ? Integer.parseInt(versionParts[2]) : 0;
            return majorVersion >= 17 && minorVersion >= 0;
        } catch (NumberFormatException e) {
            System.err.println("Failed to parse version number: " + e.getMessage());
            return false;
        }
    }
}
