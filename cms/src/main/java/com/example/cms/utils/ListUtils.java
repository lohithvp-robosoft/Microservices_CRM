package com.example.cms.utils;

import java.util.*;

public class ListUtils {

    public static List<Object> createListFromObjects(Object... obj) {
        List<Object> list = new ArrayList<>();
        Collections.addAll(list, obj);
        return list;
    }

    public static List<Object> addObjectsToList(List<Object> list, Object... obj) {
        Collections.addAll(list, obj);

        return list;
    }

    private ListUtils() {

    }
}
