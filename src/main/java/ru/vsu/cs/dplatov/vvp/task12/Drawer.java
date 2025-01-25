package ru.vsu.cs.dplatov.vvp.task12;


import ru.vsu.cs.dplatov.vvp.task12.node.Node;
import ru.vsu.cs.dplatov.vvp.task12.node.NodeWrapper;
import ru.vsu.cs.dplatov.vvp.task12.utils.*;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import static ru.vsu.cs.dplatov.vvp.task12.utils.DrawUtils.calcPositions;
import static ru.vsu.cs.dplatov.vvp.task12.utils.Segment.getSegmentBetweenFirstAndLastChild;

public class Drawer {
    /**
     * Main drawing func. Cleans some field and draws the tree in terminal
     *
     * @param input - head node object
     */
    public static void mainDraw(Node input) {
        AnsiConsole.systemInstall();
        System.out.print(Ansi.ansi().eraseScreen().cursor(1, 1));
        draw(input);
        System.out.println();
        System.out.print(Ansi.ansi().cursor(30, 30).a("\n"));
        AnsiConsole.systemUninstall();
    }

    /**
     * Connects preparing(wrapping) and drawing. Responsible only for drawing
     *
     * @param head - head Node object
     */
    public static void draw(Node head) {
        NodeWrapper headWrapped = prepareToDraw(head);
        drawVisual(headWrapped);
    }

    /**
     * Connects wrapping and calculating positions. Fully prepares to drawing
     *
     * @param head - head Node object
     * @return head nodeWrapper object
     */
    public static NodeWrapper prepareToDraw(Node head) {
        NodeWrapper headWrapped = DrawUtils.wrap(head);
        headWrapped.setSegment(new Segment(1, headWrapped.getRequiredWidth(), 1));
        calcPositions(headWrapped);
        return headWrapped;
    }

    /**
     * Recursion func to draw all the wrappedNodes
     *
     * @param head - head wrappedNode object
     */
    public static void drawVisual(NodeWrapper head) {
        if (head.getWrappedChildrenList().isEmpty()) {
            printGraphics(head, true);
            return;
        }
        printGraphics(head, false);
        head.getWrappedChildrenList().forEach(Drawer::drawVisual);
    }

    /**
     * Gets from nodeWrapper object attributes and by them draws visual graphic
     *
     * @param head - current head nodeWrapper object
     * @param isLast - flag to know does the current head nodeWrapper object have any child
     */
    public static void printGraphics(NodeWrapper head, boolean isLast) {
        Segment segment = head.getSegment();
        if (segment.getyCord() != 1) {
            System.out.print(Ansi.ansi().cursor(segment.getyCord() - 1, segment.getStartX() + head.getSegment().getLength() / 2).a("|"));
        }
        System.out.print(Ansi.ansi().cursor(segment.getyCord(), segment.getStartX() + head.getSegment().getLength() / 2 - head.getValue().length() / 2).a(head.getValue()));
        if (isLast) {
            return;
        }
        System.out.print(Ansi.ansi().cursor(segment.getyCord() + 1, segment.getStartX() + head.getSegment().getLength() / 2).a("|"));
        Segment line = getSegmentBetweenFirstAndLastChild(head);
        for (int xStart = line.getStartX(); xStart < line.getEndX() + 1; xStart++) {
            System.out.print(Ansi.ansi().cursor(line.getyCord(), xStart).a("-"));
        }
    }
}
