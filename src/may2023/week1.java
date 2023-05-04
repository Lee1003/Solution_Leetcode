package may2023;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class week1 {

    //970. 强整数
    public static List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> set = new HashSet<>();
        int index1 = 0, index2 = 0;
        if (x == 1) {
            index1 = 1;
        } else {
            while ((int)Math.pow(x, index1) <= bound) {
                index1++;
            }
        }
        if (y == 1) {
            index2 = 1;
        } else {
            while ((int)Math.pow(y, index2) <= bound) {
                index2++;
            }
        }
        for (int i = 0; i < index1; i++) {
            for (int j = 0; j < index2; j++) {
                int res = (int)Math.pow(x, i) + (int)Math.pow(y, j);
                if (res <= bound) {
                    set.add(res);
                }
            }
        }
        return new ArrayList<>(set);
    }


    //1003. 检查替换后的词是否有效
    public boolean isValid(String s) {
        StringBuilder stk = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            stk.append(c);
            if (stk.length() >= 3 && stk.substring(stk.length() - 3).equals("abc")) {
                stk.delete(stk.length() - 3, stk.length());
            }
        }
        return stk.length() == 0;
    }



    public static void main(String[] args) {
        System.out.println(powerfulIntegers(1, 1, 10));
    }
}
