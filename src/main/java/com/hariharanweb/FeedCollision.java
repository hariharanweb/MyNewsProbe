package com.hariharanweb;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;

public class FeedCollision {

    private final List<String> feeds;

    public FeedCollision(MyFeeds myFeeds) {
        feeds = myFeeds.getFeeds();
    }

    public void getNews() {
        final FeedReader feedReader = new FeedReader();
        FluentIterable<List<Map<String, Object>>> feedAndContents = from(feeds).transform(new Function<String, List<Map<String, Object>>>() {
            @Override
            public List<Map<String, Object>> apply(String feed) {
                try {
                    return feedReader.getContents(feed);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return newArrayList();
            }
        });

        System.out.println(feedAndContents);
    }
}
