package ru.vsu.cs.dplatov.vvp.task12.utils;

public class Segment {
    public Cord startCord;
    public Cord endCord;

    public Segment(Cord startCord, Cord endCord) {
        this.startCord = startCord;
        this.endCord = endCord;
    }

    public double getStartX() {
        return startCord.x;
    }

    public double getStartY() {
        return startCord.y;
    }

    public double getEndX() {
        return endCord.x;
    }

    public double getEndY() {
        return endCord.y;
    }

    public double getWidth() {
        return endCord.x - startCord.x;
    }
}
