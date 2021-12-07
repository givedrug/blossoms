package givedrug.cactus.creamcake;

/**
 * @Author : wangjinjin
 * @Date : 2021/8/10 下午3:40
 */
public class StartDining {
    public static void main(String[] args) {
        /**
         * 吃蛋糕
         */
        Knife.getAllFiles(Knife.OUT_PATH).stream()
                .forEach(file -> {
                    String fileName = Knife.getLastSplitString(file, "/").split("\\.")[0];
                    int type = Integer.parseInt(fileName.substring(fileName.length() - 1));
                    String typeString = Knife.FILE_TYPE_MAP_R.get(type);
                    CreamCake.dining(file, Knife.OUT_PATH + fileName.substring(0, fileName.length() - 1) + "." + typeString);
                    System.out.println(file + " done!");
                });
    }
}
