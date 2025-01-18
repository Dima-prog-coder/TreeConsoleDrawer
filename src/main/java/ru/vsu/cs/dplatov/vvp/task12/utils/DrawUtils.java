package ru.vsu.cs.dplatov.vvp.task12.utils;

import static ru.vsu.cs.dplatov.vvp.task12.utils.Segment.divideBigSegmentForChildren;

public class DrawUtils {
    public static NodeWrapper wrap(Node head) {
        NodeWrapper nodeWrapper = new NodeWrapper(head);
        if (nodeWrapper.getChildren().isEmpty()) {
            nodeWrapper.calcRequiredChildWidth();
            return nodeWrapper;
        }
        nodeWrapper.addAllNodesInWrappedChildrenList(nodeWrapper.getChildren());
        return nodeWrapper;
    }

    public static void calcPositions(NodeWrapper wrappedHead) {
        if (wrappedHead.getWrappedChildrenList().isEmpty()) {
            return;
        }
        divideBigSegmentForChildren(wrappedHead);
        wrappedHead.getWrappedChildrenList().forEach(DrawUtils::calcPositions);
    }
}
