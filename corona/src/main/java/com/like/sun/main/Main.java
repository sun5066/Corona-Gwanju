package com.like.sun.main;

import com.like.sun.parser.PatientInfoListParser;
import com.like.sun.parser.PatientListParser;
import com.like.sun.parser.PatientNavigateParser;
import com.like.sun.utils.CoronaUtil;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        CoronaUtil coronaUtil = new CoronaUtil("https://www.gwangju.go.kr/c19/c19/contentsView.do?pageId=coronagj2");
        Document doc = coronaUtil.getDocument();

        Map<Integer, List<String>> map;

        PatientListParser patientListParser = new PatientListParser();
        map = patientListParser.getBasicInformation(doc);
        printListParser(map);

        int key = 0;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.printf("번호를 입력하세요 : ");
            key = scanner.nextInt() - 1;

            int size = map.size() - 1;
            if (key < 0 || key > size) System.out.println("[ WARNING ] 범위가 잘못 되었습니다!");
            else break;
        }
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

        PatientInfoListParser patientInfoListParser = new PatientInfoListParser();
        map = patientInfoListParser.getBasicInformation(doc);
        printInfoListParser(map, key);

        PatientNavigateParser patientNavigateParser = new PatientNavigateParser();
        map = patientNavigateParser.getBasicInformation(doc);
        printNavigateParser(map, key);
    }

    private static void printListParser(Map<Integer, List<String>> map) {
        int cnt = map.size() - 1;
        for (int i = cnt; i >= 0; i--) {
            System.out.println(String.format("번호 : %d", (i + 1)));
            System.out.println(String.format("확진자번호 : %s", map.get(i).get(0)));
            System.out.println(String.format("인적사항 : %s", map.get(i).get(1)));
            System.out.println(String.format("확진경위 : %s", map.get(i).get(2)));
            System.out.println(String.format("확진일 : %s", map.get(i).get(3)));
            System.out.println(String.format("접촉 : %s", map.get(i).get(4)));
            System.out.println(String.format("격리시설 : %s\n", map.get(i).get(5)));
        }
    }

    private static void printInfoListParser(Map<Integer, List<String>> map, int key) {
        System.out.println("■ 확진자 기본정보");
        System.out.println(String.format(
                "\t기본사항 : %s\n" +
                        "\t감염경로 : %s\n" +
                        "\t증상발현일 : %s\n" +
                        "\t검체채취일 : %s\n" +
                        "\t확진일 : %s\n" +
                        "\t현재상황 : %s\n" +
                        "\t접촉자 세부현황 : %s\n",
                map.get(key).get(1), map.get(key).get(2),
                map.get(key).get(3), map.get(key).get(4),
                map.get(key).get(5), map.get(key).get(6),
                map.get(key).get(7)));
    }

    private static void printNavigateParser(Map<Integer, List<String>> map, int key) {
        int mapSize = map.get(key).size();
        System.out.println("■ 조사대상 기간 이동경로");
        for (int i = 0; i < mapSize; i++) {
            System.out.println(String.format("\t%s", map.get(key).get(i)));
        }
    }
}
