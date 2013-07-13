package com.hariharanweb;


import org.junit.Test;

public class FeedCollisionTest {
    @Test
    public void shouldFEtchNewsWithScore(){
        FeedCollision feedCollision = new FeedCollision(new MyFeeds());
        feedCollision.getNews();
    }

}
