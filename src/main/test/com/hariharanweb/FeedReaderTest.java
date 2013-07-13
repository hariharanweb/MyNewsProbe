package com.hariharanweb;

import com.sun.corba.se.impl.orb.ParserTable;
import com.sun.syndication.io.FeedException;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FeedReaderTest {

    @Test
    public void shouldGiveContentGivenURL() throws IOException, FeedException, BoilerpipeProcessingException {
        FeedReader feedReader = new FeedReader();
        List<Map<String,Object>> contents = feedReader.getContents("http://feeds.feedburner.com/NdtvNews-TopStories?format=xml");
        System.out.println(contents);
    }

}
