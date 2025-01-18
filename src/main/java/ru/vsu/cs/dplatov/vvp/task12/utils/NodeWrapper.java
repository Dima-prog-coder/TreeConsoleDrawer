package ru.vsu.cs.dplatov.vvp.task12.utils;

import java.util.ArrayList;
import java.util.List;

public class NodeWrapper {
    private final Node node;
    private Segment segment;
    private final List<NodeWrapper> wrappedChildrenList = new ArrayList<>();
    private int requiredChildWidth = 0;

    public NodeWrapper(Node node) {
        this.node = node;
    }

    public void addAllNodesInWrappedChildrenList(List<Node> nodeList) {
        wrappedChildrenList.addAll(nodeList.stream()
                .map(DrawUtils::wrap)
                .toList());
        calcRequiredChildWidth();
    }

    public void calcRequiredChildWidth() {
        int childRequires = 0;
        for (NodeWrapper wrapper : wrappedChildrenList) {
            childRequires += wrapper.requiredChildWidth;
        }
        requiredChildWidth = Math.max(node.getValue().length() + 2, childRequires);
    }

    public List<Node> getChildren() {
        return node.getChildren();
    }

    public String getValue() {
        return node.getValue();
    }

    public List<NodeWrapper> getWrappedChildrenList() {
        return wrappedChildrenList;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public int getRequiredChildWidth() {
        return requiredChildWidth;
    }
}
