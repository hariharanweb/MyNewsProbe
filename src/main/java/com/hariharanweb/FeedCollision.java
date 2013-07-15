package com.hariharanweb;

import com.sun.syndication.io.FeedException;
import de.l3s.boilerpipe.BoilerpipeProcessingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

public class FeedCollision {

    public static final double TITLE_MATCH = 0.6;
    public static final double DESCRIPTION_MATCH = 0.4;
    public static final double PASS_SCORE = 0.5;
    private final List<String> feeds;

    public FeedCollision(MyFeeds myFeeds) {
        feeds = myFeeds.getFeeds();
    }

    public void getNews() throws BoilerpipeProcessingException, IOException, FeedException {
        final FeedReader feedReader = new FeedReader();
        ArrayList<Map<String, Object>> newsList = newArrayList();
        for (String feed : feeds) {
            newsList.addAll(feedReader.getContents(feed));
        }
        List<Map<String, Object>> newsInQueue = newArrayList();

        for (Map<String, Object> news : newsList) {
            double matchScore = matchScoreWithNewsInQueue(newsInQueue, news);
            if(matchScore == 0) newsInQueue.add(news);
        }

        System.out.println(newsInQueue);
    }

    private double matchScoreWithNewsInQueue(List<Map<String, Object>> newsInQueue, Map<String, Object> news) {
        TagCollisionScore collisionScore = new TagCollisionScore();
        for (Map<String, Object> queue : newsInQueue) {
            float titleMatchScore = collisionScore.matchScore((Set<String>) queue.get("titleTags"), (Set<String>) news.get("titleTags"));
            float descriptionMatchScore = collisionScore.matchScore((Set<String>) queue.get("descriptionTags"), (Set<String>) news.get("descriptionTags"));
            double articleScore = titleMatchScore * TITLE_MATCH + descriptionMatchScore * DESCRIPTION_MATCH;
            System.out.println(articleScore+" "+descriptionMatchScore+" "+titleMatchScore);
            if (articleScore > PASS_SCORE) {
                if (queue.containsKey("importanceScore")) {
                    queue.put("importanceScore",(Double)queue.get("importanceScore")+articleScore);
                } else {
                    queue.put("importanceScore", articleScore);
                }
                return articleScore;
            }
        }
        return 0;
    }
}
