package com.ieening.learnInputOutStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class TestHTML {
    @Test
    public void testHTMLParse() throws IOException {
        Document document = Jsoup.parse(new File("src\\main\\resources\\html\\javaCharapterTitles.html"));
        Elements elements = document.select("#cnblogs_post_body p a");
        try (PrintWriter writer = new PrintWriter(new FileWriter("src\\main\\resources\\html\\titleUrls.csv"))) {
            writer.println("title, href");
            for (Element element : elements) {
                writer.println(element.text() + ", " + element.attr("href"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
