package com.hariharanweb;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.apache.lucene.analysis.standard.StandardAnalyzer.STOP_WORDS_SET;

public class FeedTagCalculator {

    public Set<String> getAllTags(String content) throws IOException {

        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_43, STOP_WORDS_SET);
        TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content));
        tokenStream.reset();

        HashSet<String> tags = Sets.newHashSet();
        while (tokenStream.incrementToken()) {
            tags.add(tokenStream.getAttribute(CharTermAttribute.class).toString());
        }
        tokenStream.end();
        tokenStream.close();

        return tags;
    }
}
