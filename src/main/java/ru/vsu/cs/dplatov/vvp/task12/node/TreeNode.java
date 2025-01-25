package ru.vsu.cs.dplatov.vvp.task12.node;

import java.util.ArrayList;
import java.util.List;

public class TreeNode implements Node {
    private String value;
    private final List<Node> childrenList = new ArrayList<>();

    public TreeNode(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public List<Node> getChildren() {
        return childrenList;
    }

    public void addChildren(TreeNode children) {
        this.childrenList.add(children);
    }

    public void addChildren(List<TreeNode> childrenList) {
        this.childrenList.addAll(childrenList);
    }

    @Override
    public String toString() {
        return "|" + value + "|";
    }
}

