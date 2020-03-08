package com.blueradix.android.aitimetable;

import android.os.AsyncTask;
import android.util.Log;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scrape extends AsyncTask<Void, Void, Void> {
    private static final String URL = "https://jivi.ait.nsw.edu.au/mobile/timetable.php";



    @Override
    protected Void doInBackground(Void... voids) {
//        try {
/*
            Document doc = Jsoup.connect(URL).get();
            String title = doc.title();

            Element idElement = doc.getElementById("id");
            idElement.text("6808");

            Element pwdElement = doc.getElementById("pw");
            pwdElement.text("forMyAlex");
*/
//            Element subElement = doc.getElementById("hawinputsubmit");
//            subElement.

/*
            Document doc = Jsoup.connect(URL)
                    .followRedirects(true)
                    .data("id", "6808")
                    .data("pw", "forMyAlex")
                    .post();
//                    .method(Connection.Method.POST).get();

            List<Element> list = new ArrayList<Element>();

            Element element = doc.getElementById("hawtextbox");
*/




//            Log.i("TAG", element.html());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return null;

    }
}
