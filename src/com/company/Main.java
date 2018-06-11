package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        int n =0;

        String filename = "src\\com\\company\\file1.txt";
        String filename1 = "src\\com\\company\\file2.txt";
        //FileInputStream in = null;
        try (FileInputStream in = new FileInputStream(filename)) {
        n = in.available();
        byte[] content = new byte[n];
            in.read(content);

            String string = new String(content, Charset.forName("windows-1251"));
            //System.out.println("байты в строку : " + string);


            String[] javaWords = {"for", "Scanner", "println", "while", "if", "Integer", "double"};
            String[] javaWordsOut = new String[javaWords.length];
            int[] counterJava = new int[javaWords.length];

            for (int i=0; i<javaWords.length; i++) {

                if (string.contains(javaWords[i])) {
                    System.out.println("есть" + javaWords[i]);
                    javaWordsOut[i] = javaWords[i];

                Pattern p = Pattern.compile(javaWords[i]);
                Matcher m = p.matcher(string);
                int counter = 0;
                while(m.find()) {
                    counter++;
                }
                if (counter!=0) {counterJava[i]=counter;}
                    System.out.println(counterJava[i]);
                }

            }

            String wordOutByteString = String.join(",", javaWordsOut);
            byte[] outWord = wordOutByteString.getBytes();
            String counteOutString = "";
            StringBuilder b = new StringBuilder();
            for (int i=0;i<counterJava.length;i++)
            {
                b.append(counterJava[i] + " ");
            }
            String lineSeparator = System.getProperty("line.separator");
            counteOutString = lineSeparator+b.toString();

            byte[] outCount = counteOutString.getBytes();

            try (FileOutputStream out = new FileOutputStream(filename1)) {
                out.write(outWord);
                out.write(outCount);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

