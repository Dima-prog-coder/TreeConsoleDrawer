package ru.vsu.cs.dplatov.vvp.task12.utils;

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

    public static void divideBigSegmentForChildren(NodeWrapper head) {
        Segment segment = head.getSegment();
        int extraSpace = segment.getLength() - head.getWrappedChildrenList().stream().map(NodeWrapper::getRequiredChildWidth).mapToInt(Integer::intValue).sum();
        int leftBorder = segment.startX;
        int newY = segment.yCord + 4;
        for (NodeWrapper child : head.getWrappedChildrenList()) {
            child.setSegment(new Segment(leftBorder, leftBorder + child.getRequiredChildWidth() + extraSpace / head.getWrappedChildrenList().size(), newY));
            leftBorder = leftBorder + child.getRequiredChildWidth();
        }
    }

    public static int calcCenterPositionForWrappedNode(NodeWrapper nodeWrapper) {
        int noSymbolsLength = nodeWrapper.getSegment().getLength() - nodeWrapper.getValue().length();
        return noSymbolsLength / 2 - (nodeWrapper.getValue().length() % 2 == 0 ? 1 : 0);
    }

    public static Segment getSegmentBetweenFirstAndLastChild(NodeWrapper nodeWrapper) {
        NodeWrapper firstChild = nodeWrapper.getWrappedChildrenList().getFirst();
        NodeWrapper lastChild = nodeWrapper.getWrappedChildrenList().getLast();
        Segment segment = nodeWrapper.getSegment();
        if (firstChild == lastChild) {
            return new Segment(segment.getStartX() + calcCenterPositionForWrappedNode(nodeWrapper) + nodeWrapper.getValue().length() / 2, segment.getStartX() + calcCenterPositionForWrappedNode(nodeWrapper) + nodeWrapper.getValue().length() / 2, segment.yCord + 2);
        }
        return new Segment(firstChild.getSegment().startX + calcCenterPositionForWrappedNode(firstChild) + firstChild.getValue().length() / 2, lastChild.getSegment().startX + calcCenterPositionForWrappedNode(lastChild) + lastChild.getValue().length() / 2, segment.yCord + 2);
    }
}
