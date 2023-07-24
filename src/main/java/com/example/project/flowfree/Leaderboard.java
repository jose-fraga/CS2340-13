package com.example.project.flowfree;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard {


    public static Leaderboard board;
    private String filePath;

    private String highTimes;
    public ArrayList<Long> topTimes;
    public ArrayList<String> times;



    public Leaderboard()  {
        filePath = new File("flowfree/highTime").getAbsolutePath();
        topTimes = new ArrayList<Long>();
        times = new ArrayList<String>();
    }

    public static synchronized Leaderboard getInstance() {
        if (board == null) {
            board = new Leaderboard();
        }
        return board;
    }
    //a long goes in that addTime
    public void addTime(long secs) {
        topTimes.add(secs);
        Collections.sort(topTimes);
    }

    public void readTimes() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("highTime.txt"));

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                times.add(line);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeTimes() {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("highTime.txt"));
            for(int x = 0; x < 3; x++) {
                long l = this.topTimes.get(x);
                String str = l +"";
                writer.write(str);
                writer.newLine();
            }
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //  private void createSaveData(){
    //     FileWriter output = null;

    //   try{
    //     File f = new File(filePath, highTimes);
    //   output = new FileWriter(f);
    // BufferedWriter writer = new BufferedWriter(output);
    //          writer.newLine();
    //       writer.write(Integer.MAX_VALUE + "-" + Integer.MAX_VALUE + "-" + Integer.MAX_VALUE + "-" + Integer.MAX_VALUE + "-");
    //      writer.close();

    //} catch(Exception e) {
    //  e.printStackTrace();
    // }
    //  }

    public String getTopTime() { return this.times.get(0); }
    public String getNextTime() { return this.times.get(1); }
    public String getLastTime() { return this.times.get(2); }
    public void setFastestTime(long time) { this.topTimes.set(0, time); }

    public ArrayList<Long> getTopTimes() { return topTimes; }

    //public static void main(String[] args) throws IOException {
    //      Leaderboard test = new Leaderboard();
    //     BufferedReader reader = new BufferedReader(new FileReader("highTime.txt"));
    //     test.addTime(4000L);
    //      test.addTime(5000L);
    //     test.addTime(3000L);

    //     test.writeTimes();
    //     test.readTimes();

    //      System.out.println(test.getTopTime());
    //      System.out.println(test.getNextTime());
    //      System.out.println(test.getLastTime());

    //   System.out.println(test.getTopTime());
    //   System.out.println(test.getNextTime());
    //    System.out.println(test.getLastTime());
    //  }

}

