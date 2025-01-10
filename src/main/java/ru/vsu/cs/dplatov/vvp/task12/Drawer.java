package ru.vsu.cs.dplatov.vvp.task12;


import org.fusesource.jansi.Ansi;
import ru.vsu.cs.dplatov.vvp.task12.utils.Cord;
import ru.vsu.cs.dplatov.vvp.task12.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Drawer {


















    public static void main(String[] args) {
        // Перемещаем курсор в строку 5, колонку 10
        System.out.print(Ansi.ansi().cursor(5, 10).fg(Ansi.Color.RED).a("Hello, World!"));

        // Перемещаем курсор в строку 7, колонку 5 и выводим другой текст
        System.out.print(Ansi.ansi().cursor(7, 5).fg(Ansi.Color.GREEN).a("This is green text"));
    }
//    public static void mainDrawing(TreeNode treeNode, GraphicsContext gc) {
//        calculatePositions(treeNode, new Cord(0, 20), new Cord(800, 20));
//        drawTree(treeNode, gc, (int) gc.getFont().getSize());
//    }
//
//    public static void drawTree(TreeNode treeNode, GraphicsContext gc, int fontSize) {
//        gc.fillText(treeNode.getValue(), treeNode.getFxCord().x, treeNode.getFxCord().y);
//        treeNode.getChildren().forEach(child -> {
//            drawTree(child, gc, fontSize - 2);
//            drawLine(treeNode.getFxCord().x, treeNode.getFxCord().y, child.getFxCord().x, child.getFxCord().y, gc);
//        });
//    }

    public static void calculatePositions(TreeNode currentHead, Cord cordStart, Cord cordStop) {
        if (currentHead.getChildren().isEmpty()) {
            currentHead.setFxCord(Cord.calculateAvgCord(cordStart, cordStop));
        }
        currentHead.setFxCord(Cord.calculateAvgCord(cordStart, cordStop));
        cordStart.y += 60;
        cordStop.y += 60;
        currentHead.getChildren().forEach(treeNode -> {
            List<List<Cord>> bordersList = divideASegment(cordStart, cordStop, currentHead.getChildren().size());
            calculatePositions((TreeNode) treeNode, bordersList.get(currentHead.getChildren().indexOf(treeNode)).get(0), bordersList.get(currentHead.getChildren().indexOf(treeNode)).get(1));
        });
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
