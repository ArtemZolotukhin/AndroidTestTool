package com.example.rz.apptesttool.tools;

import com.example.rz.apptesttool.mvp.model.ReviewItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public <T> Set<T> toSet(List<T> reviewItems) {
        Set<T> set = new HashSet<>();
        if (reviewItems == null) {
            return set;
        }
        set.addAll(reviewItems);
        return set;
    }
}
