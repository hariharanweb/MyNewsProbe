package com.hariharanweb;


import org.junit.Test;

import java.io.IOException;
import java.util.Set;

public class FeedTagCalculatorTest {
    @Test
    public void shouldFetchTagsGivenContent() throws IOException {
        String testContent = "What Murthy is doing to get Infosys back on track, description=NDTV's Prashant Nair spoke to the Infosys top management to find out what Mr Murthy has been telling them over the last month and a half";
        FeedTagCalculator calculator = new FeedTagCalculator();
        Set<String> tags = calculator.getAllTags(testContent);
        System.out.println(tags);
    }
}
