package com.example.rz.apptesttool.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by rz on 5/15/18.
 */

public class CollectionUtils {
    public <T> List<T> toList(Collection<T> collection) {
        if (collection == null) {
            return new ArrayList<>();
        }
        if (collection.size() == 0) {
            return new ArrayList<>();
        }
        ArrayList<T> result = new ArrayList<>(collection.size());
        result.addAll(collection);
        return result;
    }
}
