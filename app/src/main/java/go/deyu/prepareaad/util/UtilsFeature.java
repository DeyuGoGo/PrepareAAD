package go.deyu.prepareaad.util;

import android.os.Build;

/**
 * Created by gogolook on 2016/5/30.
 */
public class UtilsFeature {
    private static final String LOG_TAG = UtilsFeature.class.getSimpleName();

    public static boolean hasAndroidVersion(int andVersion) {
        return Build.VERSION.SDK_INT >= andVersion;
    }
    public static boolean hasDonut() {
        return hasAndroidVersion(Build.VERSION_CODES.DONUT);
    }

    public static boolean hasGingerBreadMr1() {
        return hasAndroidVersion(Build.VERSION_CODES.GINGERBREAD_MR1);
    }

    public static boolean hasHoneyComb() {
        return hasAndroidVersion(Build.VERSION_CODES.HONEYCOMB);
    }

    public static boolean hasHoneyCombMr1() {
        return hasAndroidVersion(Build.VERSION_CODES.HONEYCOMB_MR1);
    }

    public static boolean hasIceCreamSandWitch() {
        return hasAndroidVersion(Build.VERSION_CODES.ICE_CREAM_SANDWICH);
    }

    public static boolean preJellyBean() {
        return !hasJellyBean();
    }

    public static boolean hasJellyBean() {
        return hasAndroidVersion(Build.VERSION_CODES.JELLY_BEAN);
    }

    public static boolean hasJellyBeanMr1() {
        return hasAndroidVersion(Build.VERSION_CODES.JELLY_BEAN_MR1);
    }

    public static boolean preJellyBeanMr2() {
        return !hasJellyBeanMr2();
    }

    public static boolean hasJellyBeanMr2() {
        return hasAndroidVersion(Build.VERSION_CODES.JELLY_BEAN_MR2);
    }

    public static boolean preKitKat() {
        return !hasKitKat();
    }

    public static boolean hasKitKat() {
        return hasAndroidVersion(Build.VERSION_CODES.KITKAT);
    }

    public static boolean hasKitKatWatch() {
        return hasAndroidVersion(Build.VERSION_CODES.KITKAT_WATCH);
    }

    public static boolean hasLollipop() {
        return hasAndroidVersion(Build.VERSION_CODES.LOLLIPOP);
    }

    public static boolean preMarshMallow() {
        return !hasMarshMallow();
    }

    public static boolean hasMarshMallow() {
        return hasAndroidVersion(Build.VERSION_CODES.M);
    }

    public static boolean preNougat() {
        return !hasNougat();
    }

    public static boolean hasNougat() {
        return hasAndroidVersion(Build.VERSION_CODES.N);
    }

    public static boolean preOreo() {
        return !hasOreo();
    }

    public static boolean hasOreo() {
        return hasAndroidVersion(Build.VERSION_CODES.O);
    }

    public static boolean preP() {
        return !hasP();
    }

    public static boolean hasP() {
        return hasAndroidVersion(Build.VERSION_CODES.P);
    }

    public static boolean hasQ() {
        return hasAndroidVersion(Build.VERSION_CODES.Q);
    }

    public static boolean preQ() {
        return !hasQ();
    }
}
