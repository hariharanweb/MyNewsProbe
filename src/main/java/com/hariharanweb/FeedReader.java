package com.hariharanweb;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FeedReader {

    private final ArticleExtractor articleExtractor;
    private final FeedTagCalculator feedTagCalculator;

    public FeedReader(){
        articleExtractor = ArticleExtractor.getInstance();
        feedTagCalculator = new FeedTagCalculator();
    }

    public List<Map<String, Object>> getContents(String url) throws IOException, FeedException, BoilerpipeProcessingException {

        SyndFeedInput input = new SyndFeedInput();
        URL inputURL = new URL(url);
        SyndFeed feed = input.build(new XmlReader(inputURL));
        List<SyndEntry> entries = feed.getEntries();
        ArrayList<Map<String, Object>> feeds = Lists.newArrayList();
        for (SyndEntry entry : entries) {
            String description = articleExtractor.getText(entry.getDescription().getValue());

            feeds.add(ImmutableMap.<String, Object>builder()
                    .put("title", entry.getTitle())
                    .put("description", description)
                    .put("titleTags",feedTagCalculator.getAllTags(entry.getTitle()))
                    .put("descriptionTags",feedTagCalculator.getAllTags(description))
                    .build());
        }
        return feeds;
    }
}
