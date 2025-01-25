package ru.vsu.cs.dplatov.vvp.task12.utils;

import ru.vsu.cs.dplatov.vvp.task12.node.NodeWrapper;

public class Segment {
    private int startX;
    private int endX;
    private int yCord;

    public Segment(int startX, int endX, int yCord) {
        this.startX = startX;
        this.endX = endX;
        this.yCord = yCord;
    }

    public int getStartX() {
        return startX;
    }

    public int getEndX() {
        return endX;
    }

    public int getLength() {
        return endX - startX;
    }

    public int getyCord() {
        return yCord;
    }

    @Override
    public String toString() {
        return startX + " - " + endX;
    }

    /**
     * Divides and distributes nodeWrapper's segment to every child
     *
     * @param head - parent
     */
    public static void divideBigSegmentForChildren(NodeWrapper head) {
        Segment segment = head.getSegment();
        int extraSpace = segment.getLength() - head.getWrappedChildrenList().stream().map(NodeWrapper::getRequiredWidth).mapToInt(Integer::intValue).sum();
        int leftBorder = segment.startX;
        int newY = segment.yCord + 4;
        for (NodeWrapper child : head.getWrappedChildrenList()) {
            child.setSegment(new Segment(leftBorder, leftBorder + child.getRequiredWidth() + extraSpace / head.getWrappedChildrenList().size(), newY));
            leftBorder = leftBorder + child.getRequiredWidth();
        }
    }

    /**
     * Takes first and last child for nodeWrapper and finds segment between them centres (usage: printing -------)
     *
     * @param nodeWrapper - current drawing object
     * @return new Segment between the center of first child and center of the last child
     */
    public static Segment getSegmentBetweenFirstAndLastChild(NodeWrapper nodeWrapper) {
        NodeWrapper firstChild = nodeWrapper.getWrappedChildrenList().get(0);
        NodeWrapper lastChild = nodeWrapper.getWrappedChildrenList().get(nodeWrapper.getWrappedChildrenList().size() - 1);
        Segment segment = nodeWrapper.getSegment();
        return new Segment(firstChild.getSegment().startX + firstChild.getSegment().getLength() / 2, lastChild.getSegment().startX + lastChild.getSegment().getLength() / 2, segment.yCord + 2);
    }
}
