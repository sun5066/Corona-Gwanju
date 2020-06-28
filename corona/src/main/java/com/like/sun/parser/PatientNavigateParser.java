package com.like.sun.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientNavigateParser {

    public Map<Integer, List<String>> getBasicInformation(Document doc) {
        Elements navigateInfoList = doc.select("tbody tr");

        Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        int cnt = 0;

        for (Element infoItem : navigateInfoList) {
            Elements navigateItemList = infoItem.select("table.in_default");
            int size = navigateItemList.size();

            if (size >= 2) {
                Elements navigateList = navigateItemList.get(1).select("tbody tr");
                List<String> list = new ArrayList<String>();

                for (Element navigate : navigateList) {
                    String content = navigate.select("td").text();
                    list.add(content);
                }

                if (!list.isEmpty()) {
                    map.put(cnt, list);
                    cnt++;
                }
            }
        }

        return map;
    }
}
