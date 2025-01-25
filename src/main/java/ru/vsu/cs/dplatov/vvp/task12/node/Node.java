package ru.vsu.cs.dplatov.vvp.task12.node;

import java.util.List;

/**
 * Base interface to implementing
 */
public interface Node {
    String getValue();

    List<Node> getChildren();
}
