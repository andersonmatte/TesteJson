package org.example;

import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class M3U_Parser {

    public M3U_Parser() throws Exception {

    }

    public String convertStreamToString(InputStream is) {
        try {
            return new java.util.Scanner(is).useDelimiter("\\A").next();
        } catch (java.util.NoSuchElementException e) {
            return "";
        }
    }

    public void parseFile(File f) throws FileNotFoundException {
        List<PlayList> playLists = new ArrayList<>();
        if (f.exists()) {
            String stream = convertStreamToString(new FileInputStream(f));
            stream = stream.replaceAll("#EXTM3U", "").trim();
            String[] arr = stream.split("#EXTINF.*,");
            String urls = "", data = "";
            // clean
            {
                for (int n = 1; n < arr.length; n++) {
                    String nome = null;
                    String url = null;
                    if (!arr[n].contains("EXTVLCOPT")) {
                        PlayList playList = new PlayList();
                        playList.setLogo("https://image.png");
                        playList.setCountry("USA");
                        nome = arr[n].substring(0, arr[n].indexOf("\n"));
                        System.out.println(nome);
                        playList.setName(nome);
                        url = arr[n].substring(arr[n].indexOf("\n"), arr[n].length());
                        System.out.println(url);
                        playList.setUrl(url);
                        playLists.add(playList);
                    }
                }
                convertToJson(playLists);

            }

        }

    }

    public void convertToJson(List<PlayList> playLists){
        try{
            FileWriter writeFile = null;
            writeFile = new FileWriter("novoTime.json");

            for (PlayList playList: playLists) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", playList.getName());
                jsonObject.put("logo", playList.getLogo());
                jsonObject.put("country", playList.getCountry());
                jsonObject.put("url", playList.getUrl());
                writeFile.write(jsonObject.toJSONString());
            }
            writeFile.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


}
