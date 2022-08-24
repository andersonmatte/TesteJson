package org.example;

import java.io.File;

/**
 * @author Anderson Matte
 */
public class Teste {

    public static void main(String[] args) {
        m3uParser();
    }

    public static void m3uParser() {
        try {
            M3U_Parser mpt = new M3U_Parser();
            mpt.parseFile(new File("C:\\Users\\atamborim\\Downloads\\us.m3u"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
