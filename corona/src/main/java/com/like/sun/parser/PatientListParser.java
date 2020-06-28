package com.like.sun.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientListParser {

    public Map<Integer, List<String>> getBasicInformation(Document doc) {
        Elements patientList = doc.select("tr.line_bg");

        Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();

        int number = 0;
        for (Element patient : patientList) {
            Elements patientInfoList = patient.select("td");
            List<String> list = new ArrayList<String>();
            list.add(patientInfoList.get(0).text());
            list.add(patientInfoList.get(1).text());
            list.add(patientInfoList.get(2).text());
            list.add(patientInfoList.get(3).text());
            list.add(patientInfoList.get(4).text());
            list.add(patientInfoList.get(5).text());

            map.put(number, list);
            number++;
        }

        return map;
    }
}