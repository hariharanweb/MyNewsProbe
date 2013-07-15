package com.hariharanweb;


import com.sun.syndication.io.FeedException;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import org.junit.Test;

import java.io.IOException;

public class FeedCollisionTest {
    @Test
    public void shouldFEtchNewsWithScore() throws BoilerpipeProcessingException, IOException, FeedException {
        FeedCollision feedCollision = new FeedCollision(new MyFeeds());
        feedCollision.getNews();
    }

}
