package com.hariharanweb;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class MyFeeds {

    public List<String> getFeeds(){
        return ImmutableList.<String>builder()
                .add("http://feeds.feedburner.com/NdtvNews-TopStories?format=xml")
                .add("http://ibnlive.in.com/ibnrss/top.xml")
                .add("http://toi.timesofindia.indiatimes.com/rssfeedstopstories.cms")
                .build();
    }
}
