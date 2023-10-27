package com.ilestegor.lab3.utils;

public class AreaChecker {

    public static String checkArea(double x, double y, double r) {
        if (checkFirstArea(x, y, r)) {
            return HitType.FIRST_AREA.getHitArea();
        } else if (checkSecondArea(x, y, r)) {
            return HitType.SECOND_AREA.getHitArea();
        } else if (checkThirdArea(x, y, r)) {
            return HitType.THIRD_AREA.getHitArea();
        } else {
            return HitType.MISS.getHitArea();
        }
    }

    private static boolean checkFirstArea(double x, double y, double r) {
        return (y <= r) && (x >= 0 && x <= r / 2) && y >= 0;
    }

    private static boolean checkSecondArea(double x, double y, double r) {
        return (x * x + y * y <= (r / 2) * (r / 2)) && x <= 0 && y >= 0;
    }

    private static boolean checkThirdArea(double x, double y, double r) {
        return (y >= -r - (x * x)) && x <= 0 && y <= 0;
    }
}
