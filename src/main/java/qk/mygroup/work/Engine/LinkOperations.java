package qk.mygroup.work.Engine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import qk.mygroup.work.ImgGiverApplication;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Scanner;

public class LinkOperations {

    private String text;

    public LinkOperations(String text){
        textFormatter(text);
    }


    public String getImgSrc(){
        Elements imgTags = getImgTags();

        int begin = imgTags.get(0).getElementsByAttribute("src").get(0).toString().lastIndexOf("https");
        int end = imgTags.get(0).getElementsByAttribute("src").get(0).toString().length()-2;

        return imgTags.get(0).getElementsByAttribute("src").get(0).toString().substring(begin, end);
    }


    public Elements getImgTags() {
        String searchText = "https://www.google.ru/search?q=" + text + "&tbm=isch&ved=2ahUKEwi7vZqU6erzAhXUxSoKHWINBqcQ2-cCegQIABAA&oq=rebecca+holl&gs_lcp=CgNpbWcQAzIECAAQEzIECAAQEzIECAAQEzIECAAQEzIECAAQEzIECAAQEzIECAAQEzIECAAQEzIECAAQEzIECAAQEzoHCCMQ7wMQJzoFCAAQgAQ6BAgAEEM6CAgAEIAEELEDOggIABCxAxCDAToLCAAQgAQQsQMQgwE6BAgAEB5Q3EZYq1VgqldoAHAAeACAAUiIAY0GkgECMTOYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=z2R5YfuZPNSLqwHimpi4Cg&bih=888&biw=1645";
        Document doc =  Jsoup.parse(readURL(searchText));

        return doc.body().getElementsByClass("NZWO1b");
    }

    public static String readURL(String url) {

        String fileContents = "";
        String currentLine = "";

        try {
            URL link = new URL(url);
            URLConnection connection = (HttpURLConnection) link.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/4.0");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            fileContents = reader.readLine();
            while (currentLine != null) {
                currentLine = reader.readLine();
                fileContents += "\n" + currentLine;
            }
            reader.close();
            reader = null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error Message", JOptionPane.OK_OPTION);
            e.printStackTrace();

        }
        return fileContents;
    }

    public String getLink() {
        return text;
    }


    public void textFormatter(String text){
        String tmp = text.replace(" ", "+");
        this.text = tmp;
    }
}
