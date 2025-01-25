package ru.vsu.cs.dplatov.vvp.task12.node;

import ru.vsu.cs.dplatov.vvp.task12.utils.DrawUtils;
import ru.vsu.cs.dplatov.vvp.task12.utils.Segment;

import java.util.ArrayList;
import java.util.List;

/**
 * Wraps Node object and contains extra attributes to easy draw it
 */
public class NodeWrapper {
    private final Node node; // ссылка на оборачиваемый элемент
    private String customValue; // поле, чтобы приводить четную длину исходного value к нечетной
    private Segment segment; // координаты начала и конца по x
    private final List<NodeWrapper> wrappedChildrenList = new ArrayList<>(); // список обернутых child
    private int requiredWidth = 0; // требуемое место для данной ветки в этот момент

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
            childRequires += wrapper.requiredWidth;
        }
        requiredWidth = Math.max(node.getValue().length() + 2, childRequires);
    }

    public List<Node> getChildren() {
        return node.getChildren();
    }

    public String getValue() {
        return customValue == null ? node.getValue() : customValue;
    }

    public void setValue(String customValue) {
        this.customValue = customValue;
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

    public int getRequiredWidth() {
        return requiredWidth;
    }
}
