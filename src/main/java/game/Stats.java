package game;

import java.util.ArrayList;

public class Stats  {
    private double floorTimer;
    private int floorEnemiesKilled;
    private int roomEnemiesKilled;
    private double roomTimer;
    private int totalEnemiesKilled;
    private double totalTimer;

    public void update(double deltaSeconds){
        floorTimer += deltaSeconds;
        totalTimer += deltaSeconds;
    }

    public void kill() {
        addKills(1);
    }

    public void addKills(int count) {
        if (count <= 0) {
            return;
        }
        floorEnemiesKilled += count;
        totalEnemiesKilled += count;
    }

    public void resetFloor(){
        floorEnemiesKilled = 0;
        floorTimer = 0;
    }
    public void resetRun() {
        floorEnemiesKilled = 0;
        floorTimer = 0;
        totalTimer = 0;
        totalEnemiesKilled = 0;
    }

    public double getFloortimer() {
        return floorTimer;
    }
    public int getFloorEnemiesKilled(){
            return floorEnemiesKilled;
    }

    public int getTotalEnemiesKilled(){
        return totalEnemiesKilled;
    }

    public double getTotalTime(){
        return totalTimer;
    }


}
