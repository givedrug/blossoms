package givedrug.cactus.creamcake;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public final class Knife {

    /**
     * compress and decompress
     */
    private static final int BUFFER_SIZE = 4 * 1024;

    public static byte[] compress(byte[] data, Level level) throws IOException {
        Deflater deflater = new Deflater();

        // set compression level
        deflater.setLevel(level.getLevel());
        deflater.setInput(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        deflater.finish();
        byte[] buffer = new byte[BUFFER_SIZE];
        while (!deflater.finished()) {
            // returns the generated
            int count = deflater.deflate(buffer);
            // code... index
            outputStream.write(buffer, 0, count);
        }
        byte[] output = outputStream.toByteArray();
        outputStream.close();
        return output;
    }

    public static byte[] decompress(byte[] data) throws IOException, DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[BUFFER_SIZE];
        while (!inflater.finished()) {
            int count = inflater.inflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        byte[] output = outputStream.toByteArray();
        outputStream.close();
        return output;
    }

    /**
     * Compression level
     */
    public static enum Level {

        /**
         * Compression level for no compression.
         */
        NO_COMPRESSION(0),

        /**
         * Compression level for fastest compression.
         */
        BEST_SPEED(1),

        /**
         * Compression level for best compression.
         */
        BEST_COMPRESSION(9),

        /**
         * Default compression level.
         */
        DEFAULT_COMPRESSION(-1);

        private int level;

        Level(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }
    }


    /**
     * directory and file
     */
    public static final String IN_PATH = "/Users/didi/Downloads/原始/";
    public static final String OUT_PATH = "/Users/didi/Downloads/筛选/";

    public static final Map<String, Integer> FILE_TYPE_MAP = new HashMap() {{
        put("", 0);
        put("mht", 1);
    }};

    public static final Map<Integer, String> FILE_TYPE_MAP_R = new HashMap() {{
        put(0, "");
        put(1, "mht");
    }};

    public static Map<String, String> getCP() {
        Map<String, String> map = new HashMap<>();
        List<String> pngList = new ArrayList<>();
        List<String> otherList = new ArrayList<>();
        try {
            Files.newDirectoryStream(Paths.get(IN_PATH), path -> path.toString().endsWith(".png"))
                    .forEach(file -> pngList.add(file.toString()));
            Files.newDirectoryStream(Paths.get(IN_PATH), path -> !path.toString().endsWith(".png"))
                    .forEach(file -> otherList.add(file.toString()));
            if (pngList.size() != otherList.size()) {
                throw new RuntimeException("数量不一致");
            } else {
                for (int i = 0; i < pngList.size(); i++) {
                    map.put(pngList.get(i), otherList.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static List<String> getAllFiles(String path){
        List<String> pngList = new ArrayList<>();
        try {
            Files.newDirectoryStream(Paths.get(path), file -> file.toString().endsWith(".png"))
                    .forEach(file -> pngList.add(file.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pngList;
    }

    public static String getLastSplitString(String str, String regex){
        String[] strings = str.split(regex);
        return strings[strings.length - 1];
    }

}
