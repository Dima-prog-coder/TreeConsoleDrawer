package ru.vsu.cs.dplatov.vvp.task12.utils;

import java.util.ArrayList;
import java.util.List;

public class Cord {
    public int x;
    public int y;

    public Cord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Cord calculateAvgCord(Cord cord1, Cord cord2) {
        return new Cord((cord1.x + cord2.x) / 2, (cord1.y + cord2.y) / 2);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public static List<List<Cord>> divideASegment(Cord borderLeft, Cord borderRight, int numberOfSegments) {
        if (numberOfSegments < 1) {
            throw new IllegalArgumentException("Number of parts must be greater than 0");
        }
        List<List<Cord>> bordersList = new ArrayList<>();

        int deltaX = (borderRight.x - borderLeft.x) / numberOfSegments;
        int deltaY = (borderRight.y - borderLeft.y) / numberOfSegments;

        for (int i = 0; i < numberOfSegments; i++) {
            Cord segmentStart = new Cord(borderLeft.x + i * deltaX, borderLeft.y + i * deltaY);
            Cord segmentEnd = new Cord(borderLeft.x + (i + 1) * deltaX, borderLeft.y + (i + 1) * deltaY);
            List<Cord> segment = new ArrayList<>();
            segment.add(segmentStart);
            segment.add(segmentEnd);
            bordersList.add(segment);
        }
        return bordersList;
    }
}
