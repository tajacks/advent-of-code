package com.tajacks.aoc.year_2022.day_7;

import java.util.*;
import java.util.stream.Stream;

public enum FileStructureNavigator {
    INSTANCE;


    public static Directory navigateFilesystem(String fileSystem) {
        String[] commands = fileSystem.split("\\r?\\n");
        Deque<Directory> pwd = new LinkedList<>();
        Directory startingDirectory = new Directory("/");
        pwd.add(startingDirectory);


        for (int i = 1; i < commands.length; i++) {
            String line = commands[i];
            String[] lineParts = line.split(" ");
            if ("$".equals(lineParts[0])) {
                pwd = processCommand(pwd, lineParts);
            } else {
                pwd = processDirectoryListing(pwd, lineParts);
            }
        }
        return startingDirectory;
    }

    public static Deque<Directory> processCommand (Deque<Directory> queue, String[] command) {
        Deque<Directory> workingCopy = new LinkedList<>(queue);

        if ("cd".equals(command[1])) {
            if ("..".equals(command[2])) {
                workingCopy.poll();
            } else {
                Optional.ofNullable(workingCopy.peek()).map(dir -> dir.getDirByName(command[2])).ifPresent(workingCopy::addFirst);
            }
        }
        return workingCopy;
    }

    private static Deque<Directory> processDirectoryListing(Deque<Directory> queue, String[] command) {
        Deque<Directory> workingCopy = new LinkedList<>(queue);
        if ("dir".equals(command[0])) {
            Directory newDir = new Directory(command[1]);
            Optional.ofNullable(workingCopy.peek()).ifPresent(dir -> dir.addDirIfNotPresent(newDir));
        } else {
            File file = new File(command[1], Integer.parseInt(command[0]));
            Optional.ofNullable(workingCopy.peek()).ifPresent(dir -> dir.addFileIfNotPresent(file));
        }
        return workingCopy;
    }
}
