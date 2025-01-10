package ru.vsu.cs.dplatov.vvp.task12.utils;

import java.util.List;

public interface Node {
    String getValue();

    List<Node> getChildren();
}
