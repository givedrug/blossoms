package givedrug.cactus.creamcake;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.zip.DataFormatException;

/**
 * @Author : wangjinjin@didiglobal.com
 * @Date : 2021/8/9 上午11:34
 */
public class CreamCake {

    public static void cook(String cream, String cake, String creamCake) {
        try {
            FileOutputStream creamCakeFOS = new FileOutputStream(creamCake);
            //存cream
            BufferedInputStream creamBIS = new BufferedInputStream(new FileInputStream(cream));
            ByteArrayOutputStream creamBOS = new ByteArrayOutputStream(1024);
            int size = 0;
            byte[] temp = new byte[1024];
            while ((size = creamBIS.read(temp)) != -1) {
                creamBOS.write(temp, 0, size);
            }
            creamBIS.close();
            byte[] creamData = creamBOS.toByteArray();
            creamBOS.close();
            creamCakeFOS.write(creamData);

            //存cake(compress)
            BufferedInputStream cakeBIS = new BufferedInputStream(new FileInputStream(cake));
            ByteArrayOutputStream cakeBOS = new ByteArrayOutputStream(1024);
            while ((size = cakeBIS.read(temp)) != -1) {
                cakeBOS.write(temp, 0, size);
            }
            cakeBIS.close();
            byte[] cakeData = cakeBOS.toByteArray();
            byte[] cakeDataCompress = Knife.compress(cakeData, Knife.Level.BEST_COMPRESSION);
            cakeBOS.close();
            creamCakeFOS.write(cakeDataCompress);
            creamCakeFOS.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dining(String creamCake, String cake) {
        try {
            BufferedInputStream creamCakeBIS = new BufferedInputStream(new FileInputStream(creamCake));
            ByteArrayOutputStream creamCakeBOS = new ByteArrayOutputStream(1024);
            byte[] iend = DatatypeConverter.parseHexBinary("0000000049454e44ae426082");
            byte[] temp = new byte[1024];
            while (creamCakeBIS.read(temp) != -1) {
                int loc = KMPSearch(temp, iend);
                if (loc == -1) {
                    // TODO: 2021/8/10 截断情况
                } else {
                    int remainLen = 1024 - (loc + iend.length);
                    byte[] remain = new byte[remainLen];
                    System.arraycopy(temp, loc + iend.length, remain, 0, remainLen);
                    creamCakeBOS.write(remain);
                    while (creamCakeBIS.read(temp) != -1) {
                        creamCakeBOS.write(temp);
                    }
                }
            }
            creamCakeBIS.close();

            byte[] creamCakeData = creamCakeBOS.toByteArray();
            creamCakeBOS.close();
            byte[] creamCakeDataDecompress = Knife.decompress(creamCakeData);

            FileOutputStream cakeFOS = new FileOutputStream(cake);
            cakeFOS.write(creamCakeDataDecompress);
            cakeFOS.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataFormatException e) {
            e.printStackTrace();
        }
    }

    private static int KMPSearch(byte[] source, byte[] pattern) {
        return KMPSearch(source, pattern, 0);
    }

    private static int KMPSearch(byte[] source, byte[] pattern, int pos) {
        int length = pattern.length;
        int[] next = BuildKMP(pattern);
        int j = 0;
        for (int i = pos; i < source.length; i++) {
            while (j > 0 && source[i] != pattern[j]) {
                j = next[j - 1];
            }
            if (source[i] == pattern[j]) {
                j++;
            }
            if (j == length) {
                return i - length + 1;
            }
        }
        return -1;
    }

    private static int[] BuildKMP(byte[] pattern) {
        int[] next = new int[pattern.length];
        next[0] = 0;
        int j = 0;
        for (int i = 1; i < pattern.length; i++) {
            while (j > 0 && pattern[j] != pattern[i]) {
                j = next[j - 1];
            }
            if (pattern[j] == pattern[i]) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
