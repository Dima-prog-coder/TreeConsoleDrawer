package ru.vsu.cs.dplatov.vvp.task12;


import ru.vsu.cs.dplatov.vvp.task12.utils.*;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import static ru.vsu.cs.dplatov.vvp.task12.utils.DrawUtils.calcPositions;
import static ru.vsu.cs.dplatov.vvp.task12.utils.Parser.buildTree;
import static ru.vsu.cs.dplatov.vvp.task12.utils.Segment.calcCenterPositionForWrappedNode;
import static ru.vsu.cs.dplatov.vvp.task12.utils.Segment.getSegmentBetweenFirstAndLastChild;

public class Drawer {
    public static void mainDraw(String input) {
        TreeNode treeNode = buildTree(input);
        AnsiConsole.systemInstall();
        System.out.print(Ansi.ansi().eraseScreen().cursor(1, 1));
        draw(treeNode);
        System.out.println();
        System.out.print(Ansi.ansi().cursor(30, 30).a("\n"));
        AnsiConsole.systemUninstall();
    }

    public static void draw(Node head) {
        NodeWrapper headWrapped = prepareToDraw(head);
        drawVisual(headWrapped);
    }

    public static NodeWrapper prepareToDraw(Node head) {
        NodeWrapper headWrapped = DrawUtils.wrap(head);
        headWrapped.setSegment(new Segment(0, headWrapped.getRequiredChildWidth(), 1));
        calcPositions(headWrapped);
        return headWrapped;
    }

    public static void drawVisual(NodeWrapper head) {
        Segment segment = head.getSegment();
        if (head.getWrappedChildrenList().isEmpty()) {
            System.out.print(Ansi.ansi().cursor(segment.getyCord() - 1, segment.getStartX() + calcCenterPositionForWrappedNode(head) + head.getValue().length() / 2).a("|"));
            System.out.print(Ansi.ansi().cursor(segment.getyCord(), segment.getStartX() + calcCenterPositionForWrappedNode(head)).a(head.getValue()));
            return;
        }
        if (segment.getyCord() != 1) {
            System.out.print(Ansi.ansi().cursor(segment.getyCord() - 1, segment.getStartX() + calcCenterPositionForWrappedNode(head) + head.getValue().length() / 2).a("|"));
        }
        System.out.print(Ansi.ansi().cursor(segment.getyCord(), segment.getStartX() + calcCenterPositionForWrappedNode(head)).a(head.getValue()));
        System.out.print(Ansi.ansi().cursor(segment.getyCord() + 1, segment.getStartX() + calcCenterPositionForWrappedNode(head) + head.getValue().length() / 2).a("|"));
        Segment line = getSegmentBetweenFirstAndLastChild(head);
        for (int xStart = line.getStartX(); xStart < line.getEndX() + 1; xStart++) {
            System.out.print(Ansi.ansi().cursor(line.getyCord(), xStart).a("-"));
        }
        head.getWrappedChildrenList().forEach(Drawer::drawVisual);
    }
}
