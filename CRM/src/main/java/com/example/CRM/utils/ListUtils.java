package com.example.CRM.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListUtils {

	public static <T> List<T> createListFromObjects(T... obj) {
		ArrayList<T> list = new ArrayList<>();
		Collections.addAll(list, obj);
		return list;
	}

	public static <T> List<T> addObjectsToList(List<T> list, T... obj) {
		Collections.addAll(list, obj);

		return list;
	}

	private ListUtils() {

	}
}
