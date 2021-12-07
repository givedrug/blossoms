package givedrug.cactus.creamcake;

import java.util.Map;

/**
 * @Author : wangjinjin
 * @Date : 2021/8/10 下午3:40
 */
public class StartCook {

    public static void main(String[] args) {
        /**
         * 制作奶油蛋糕
         * 保证奶油和蛋糕数量一致
         */
        Map<String, String> cp = Knife.getCP();
        cp.entrySet().stream().forEach(entry -> {
            String cream = entry.getKey();
            String cake = entry.getValue();
            String creamCake = getCreamCakePath(cream, cake);

            CreamCake.cook(cream, cake, creamCake);
            System.out.println(creamCake + " done!");
        });
    }

    private static String getCreamCakePath(String cream, String cake) {
        StringBuilder creamCake = new StringBuilder();
        String[] creamSplit = Knife.getLastSplitString(cream,"/").split("\\.");
        creamCake.append(Knife.OUT_PATH).append(creamSplit[0]);

        String[] cakeSplit = Knife.getLastSplitString(cake,"/").split("\\.");
        if (cakeSplit.length <= 1) {
            creamCake.append(Knife.FILE_TYPE_MAP.get(""));
        } else {
            creamCake.append(Knife.FILE_TYPE_MAP.get(cakeSplit[1]));
        }

        return creamCake.append(".").append(creamSplit[1]).toString();
    }

}
