package ru.vsu.cs.dplatov.vvp.task12.utils;

import ru.vsu.cs.dplatov.vvp.task12.node.Node;
import ru.vsu.cs.dplatov.vvp.task12.node.NodeWrapper;

import static ru.vsu.cs.dplatov.vvp.task12.utils.Segment.divideBigSegmentForChildren;

public class DrawUtils {
    /**
     * Wraps all original nodes into wrapped tree. Also calculates requiredWidth(NodeWrapper attribute).
     * Saves original tree structure.
     *
     * @param head - the head of original tree
     * @return head of the wrapped tree
     */
    public static NodeWrapper wrap(Node head) {
        NodeWrapper nodeWrapper = new NodeWrapper(head);
        if (nodeWrapper.getValue().length() % 2 == 0) {
            nodeWrapper.setValue(nodeWrapper.getValue() + " ");
        }
        if (nodeWrapper.getChildren().isEmpty()) {
            nodeWrapper.calcRequiredChildWidth();
            return nodeWrapper;
        }
        nodeWrapper.addAllNodesInWrappedChildrenList(nodeWrapper.getChildren());
        return nodeWrapper;
    }

    /**
     * Cacls Segment for every wrapped node. It takes parent segment and divides it between children.
     *
     * @param wrappedHead - head of wrapped tree. The head attribute Segment must be not null!
     */
    public static void calcPositions(NodeWrapper wrappedHead) {
        if (wrappedHead.getWrappedChildrenList().isEmpty()) {
            return;
        }
        divideBigSegmentForChildren(wrappedHead);
        wrappedHead.getWrappedChildrenList().forEach(DrawUtils::calcPositions);
    }
}
