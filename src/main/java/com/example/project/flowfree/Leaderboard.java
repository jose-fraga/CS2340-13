package com.example.project.flowfree;

import java.io.*;
import java.util.ArrayList;

public class Leaderboard {
    private static Leaderboard board;
    private String highTimes;
    private String filePath;

    public ArrayList<Long> topTimes;

    private Leaderboard()  {
        filePath = new File("").getAbsolutePath();
        topTimes = new ArrayList<Long>();
    }

    public static synchronized Leaderboard getInstance() {
        if (board == null) {
            board = new Leaderboard();
        }
        return board;
    }

    public void addTime(Long secs) {
        for (int x = 0; x < topTimes.size(); x++) {
            if (secs <= topTimes.get(x)) {
                topTimes.add(x, secs);
                topTimes.remove(topTimes.size() - 1);
                return;
            }
        }
    }

    public void loadTimes() {
        try {
            File f = new File(filePath, highTimes);
            if (!f.isFile()) {
                createSaveData();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

            topTimes.clear();
            String[] times = reader.readLine().split("-");


            for (int y = 0; y < times.length; y++) {
                topTimes.add(Long.parseLong(times[y]));
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveTimes() {
        FileWriter output = null;

        try{
            File f = new File(filePath, highTimes);
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);

            writer.newLine();
            writer.write(topTimes.get(0) + "-" + topTimes.get(1) + "-" + topTimes.get(2) + "-" +topTimes.get(3));
            writer.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void createSaveData(){
        FileWriter output = null;

        try{
            File f = new File(filePath, highTimes);
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);
            writer.newLine();
            writer.write(Integer.MAX_VALUE + "-" + Integer.MAX_VALUE + "-" + Integer.MAX_VALUE + "-" + Integer.MAX_VALUE + "-");
            writer.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public long getFastestTime() { return topTimes.get(0); }
    public void setFastestTime(long time) { topTimes.set(0, time); }
    public ArrayList<Long> getTopTimes() { return topTimes; }
}

