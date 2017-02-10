package com.example.helpers;

/**
 * Created by ducna on 2/7/2017.
 */
public class FunctionHelper {
    public static boolean isValidPhone(String phone){
        if (phone.length() == 0) return false;
        for (int i=0; i < phone.length(); i ++){
            if (!Character.isDigit(phone.charAt(i)) && phone.charAt(i) != '+'){
                return false;
            }
        }
        if (phone.equals("0123456789")) return false;
        return true;
    }

    public static boolean isValidVneId(String vneId) {
        return true;
    }

    public static boolean isValidProvince(String province){
        if (province.trim().length() > 0) return true;
        return false;
    }

    public static boolean isValidAddress(String address){
        if (address.trim().length() > 0) return true;
        return false;
    }

    public static boolean isValidDistrict(String district){
        if (district.trim().length() > 0) return true;
        return false;
    }
}
