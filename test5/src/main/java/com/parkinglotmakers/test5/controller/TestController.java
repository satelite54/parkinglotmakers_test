package com.parkinglotmakers.test5.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.parkinglotmakers.test5.service.TestService;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Controller
@AllArgsConstructor
public class TestController {
    private TestService boardService;

//    @RequestMapping(value = "/")
//    public String goIndex() {
//        return "index.jsp";
//    }
    @RequestMapping(value = "/getParkingAPI")
    public ModelAndView getParkingAPI() throws IOException {
        StringBuffer result = new StringBuffer();
        String type = "json"; //xml, json // 데이터 받는 타입 결정
        String urlstr = "http://openapi.seoul.go.kr:8088/sample/json/GetParkInfo/1/5/";
        URL url = new URL(urlstr);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));

        String returnLine;

        while((returnLine = br.readLine()) != null) {
            result.append(returnLine + "\n");
        }
        urlConnection.disconnect();

        ObjectMapper mapper = new ObjectMapper();

        Map<String, LinkedHashMap<String , Object>> map = mapper.readValue(result.toString(), Map.class);
        Map<String, Object> mapGetParkInfo = map.get("GetParkInfo");

        List<Map<String, String>> listMapRow = (List<Map<String, String>>) mapGetParkInfo.get("row");
        Integer intListTotalCnt = (Integer) mapGetParkInfo.get("list_total_count");
        Map<String, String> mapRESULT = (Map<String, String>) mapGetParkInfo.get("RESULT");
        Set<String> rowkeySet = listMapRow.get(0).keySet();


        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("listMapRow", listMapRow);
        modelAndView.addObject("intListTotalCnt", intListTotalCnt);
        modelAndView.addObject("mapRESULT", mapRESULT);
        modelAndView.addObject("rowkeySet", rowkeySet);
        return modelAndView;
    }
}