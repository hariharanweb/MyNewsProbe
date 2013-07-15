package com.hariharanweb;

import com.google.common.collect.Sets;

import java.util.Set;

public class TagCollisionScore {
    public float matchScore(Set<String> tags1, Set<String> tags2) {
        Sets.SetView<String> matches = Sets.intersection(tags1, tags2);


//        System.out.println(matches.size()+" "+tags1+" "+tags2);

        int denominator = Sets.union(tags1,tags2).size() == 0 ? 1:Sets.union(tags1,tags2).size();
        return matches.size()/ denominator;
    }
}
