package vget;

import java.io.File;
import java.net.URL;

import com.github.axet.vget.VGet;

public class vgetTest {

    public static void main(String[] args) {
        try {
            // ex: http://www.youtube.com/watch?v=Nj6PFaDmp6c
            String url = "https://www.youtube.com/watch?v=RRKJiM9Njr8";
            // ex: "/Users/axet/Downloads"
            String path = "D:\\video";
            VGet v = new VGet(new URL(url), new File(path));
            v.download();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}