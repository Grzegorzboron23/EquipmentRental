package com.API.EquipmentRental.utils;

public class QueryUtils {

    public static String stringToLikeQuery(String query) {
        return "%" + query + "%";
    }


}
