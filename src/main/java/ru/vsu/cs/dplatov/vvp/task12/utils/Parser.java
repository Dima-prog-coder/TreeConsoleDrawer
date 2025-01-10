package ru.vsu.cs.dplatov.vvp.task12.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {
    private static final Pattern pattern1 = Pattern.compile("[^(),\\s]+");

    /**
     * The func converts (a,b,c) -> treeNodeObj (treeNodeObj.value = a && treeNodeObj.childrenList = [treeNodeObj(value b), treeNodeObj(value c)])
     *
     * @param string - string without nested parentheses
     * @return TreeNode object - head with included children
     */
    public static TreeNode makeBranch(String string) {
        String[] stringSplit = string.replaceAll("[()]", "").split(",");
        if (stringSplit.length == 0) {
            return null;
        }
        TreeNode head = new TreeNode(stringSplit[0]);
        for (int i = 1; i < stringSplit.length; i++) {
            head.addChildren(new TreeNode(stringSplit[i].strip()));
        }
        return head;
    }

    public static String getHeadElementValue(String string) {
        Matcher matcher = pattern1.matcher(string.substring(1, string.length() - 1));
        matcher.find();
        return matcher.group();
    }

    public static String removeHeadElementFromString(String string) {
        Matcher matcher = pattern1.matcher(string);
        return matcher.replaceFirst("");
    }

    /**
     * The func splits by comma if it is not inside brackets
     *
     * @param string input string like a "(g,(b,c),d)"
     * @return List of str elements
     */
    public static List<String> splitByComma(String string) {
        List<String> stringList = Arrays.stream(string.substring(1, string.length() - 1).split(",")).filter(str -> !str.isBlank()).map(String::strip).collect(Collectors.toList());
        List<String> outList = new ArrayList<>();
        int cntBracketsBefore = 0;
        int cntBracketsAfter = 0;
        StringBuilder currentString = new StringBuilder();
        for (int i = 0; i < stringList.size(); i++) {
            cntBracketsBefore += (int) Arrays.stream(stringList.get(i).chars().toArray()).filter(e -> e == '(').count();
            cntBracketsAfter += (int) Arrays.stream(stringList.get(i).chars().toArray()).filter(e -> e == ')').count();
            currentString.append(currentString.isEmpty() ? stringList.get(i) : "," + stringList.get(i));
            if (cntBracketsBefore == cntBracketsAfter) {
                cntBracketsBefore = 0;
                cntBracketsAfter = 0;
                outList.add(currentString.toString());
                currentString.setLength(0);
            }
        }
        return outList;
    }

    public static TreeNode buildTree(String string) {
        if (!string.substring(1, string.length() - 1).contains("(")) {
            return makeBranch(string);
        }
        TreeNode treeNode = new TreeNode(getHeadElementValue(string));
        string = removeHeadElementFromString(string);
        treeNode.addChildren(splitByComma(string).stream()
                .map(obj -> obj.contains("(") ? buildTree(obj) : new TreeNode(obj.strip()))
                .toList()
        );
        return treeNode;
    }
}
