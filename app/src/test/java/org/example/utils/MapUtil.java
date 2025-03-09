package org.example.utils;

public class MapUtil {

    public static int[] findSymbolOnMap(String string, char target, int index) {

        String[] lines = string.split("\n");
        int foundTotal = 0;

        int y = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            line = line.replaceAll("\\.", "");
            if (line.isEmpty()) {
                continue;
            }

            char[] lineCharArray = line.toCharArray();
            int x = 0;
            for (char c : lineCharArray) {
                if (c == target) {
                    if (foundTotal == index) {
                        return new int[] {x, y};
                    }
                    foundTotal++;
                }
                x++;
            }
            y++;
        }

        return null;
    }
}
