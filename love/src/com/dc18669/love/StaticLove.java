package com.dc18669.love;

public class StaticLove {

    private static final double PI = Math.PI;

    public static void main(String[] args) {
        float x, y, a;
        for (y = 1.3f; y > -1.1f; y -= 0.06f) {   //这里的参数皆可以调，条件就是看着顺眼
            for (x = -1.2f; x <= 1.2f; x += 0.025f) {
                a = x * x + y * y - 1;
                System.out.print(a * a * a - x * x * y * y * y >= 0.0f ? ' ' : '*');
            }
            System.out.println();
        }

        System.out.println();
        System.out.println(" ==========================================");

        love();
    }

    public static void love() {
        double x, y, a;
        char s[] = new char[]{'I', ' ', 'l', 'o', 'v', 'e', ' ', 'y', 'o', 'u', '!'};
        int index = 0;
        for (y = 1.3f; y > -1.1f; y -= 0.06f) {
            index = 0;
            for (x = -1.1f; x <= 1.1f; x += 0.025f) {
                double result = x * x + Math.pow((5.0 * y / 4.0 - Math.sqrt(Math.abs(x))), 2);
                if (result <= 1) {
                    System.out.print((s[index]));
                    index = (index + 1) % 11;
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println(" ");
        }
    }
}
