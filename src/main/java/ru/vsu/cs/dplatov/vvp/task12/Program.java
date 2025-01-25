package ru.vsu.cs.dplatov.vvp.task12;

import org.apache.commons.cli.*;

import static ru.vsu.cs.dplatov.vvp.task12.Drawer.mainDraw;
import static ru.vsu.cs.dplatov.vvp.task12.node.Parser.buildTree;

public class Program {
    public static final String PROGRAM_NAME_IN_HELP = "program (-h | -i <valid string for tree>)";
    public static final String TASK_CONDITION = "Узлы дерева задаются в текстовом виде следующим образом: каждый узел дерева - описывается в круглых скобках, где первый элемент - вершина (очевидно, описываться в скобках не может), остальные - потомки. Необходимо нарисовать в виде дерева";

    public static void main(String[] args) throws Exception {
        Options cmdLineOptions = new Options();
        cmdLineOptions.addOption("h", "help", false, "Show help");
        cmdLineOptions.addOption("i", "input-string", true, "Input string");
        cmdLineOptions.addOption("t", "task-info", false, "Task condition");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmdLine = null;
        try {
            cmdLine = parser.parse(cmdLineOptions, args);
        } catch (Exception e) {
            new HelpFormatter().printHelp(PROGRAM_NAME_IN_HELP, cmdLineOptions);
            System.exit(1);
        }

        if (cmdLine.getOptions().length == 0) {
            new HelpFormatter().printHelp(PROGRAM_NAME_IN_HELP, cmdLineOptions);
            System.exit(2);
        }

        if (cmdLine.hasOption("h")) {
            new HelpFormatter().printHelp(PROGRAM_NAME_IN_HELP, cmdLineOptions);
            System.exit(0);
        }

        if (cmdLine.hasOption("t")) {
            System.out.println(TASK_CONDITION);
            System.exit(0);
        }

        if (cmdLine.hasOption("i")) {
            try {
                mainDraw(buildTree(cmdLine.getOptionValue("i")));
            } catch (Exception e) {
                new HelpFormatter().printHelp(PROGRAM_NAME_IN_HELP, cmdLineOptions);
                System.exit(2);
            }
        }

    }
}
