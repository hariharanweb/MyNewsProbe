package com.hariharanweb;

import com.google.common.collect.Sets;

import java.util.Set;

public class TagCollisionScore {
    public float matchScore(Set<String> tags1, Set<String> tags2) {
        Sets.SetView<String> matches = Sets.intersection(tags1, tags2);
        return matches.size()/(tags1.size()+tags2.size());
    }
}
