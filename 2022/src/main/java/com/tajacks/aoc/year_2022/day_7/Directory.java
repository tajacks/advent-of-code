package com.tajacks.aoc.year_2022.day_7;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Directory implements Comparable<Directory> {
    private final String name;
    private final List<File> files;
    private final List<Directory> directories;

    public Directory(String name) {
        this.name = name;
        this.files = new ArrayList<>();
        this.directories = new ArrayList<>();
    }

    public void addFileIfNotPresent(File file) {
        if (!containsFile(file)) {
            this.files.add(file);
        }
    }

    public void addDirIfNotPresent(Directory directory) {
        if (!containsDir(directory.getName())) {
            this.directories.add(directory);
        }
    }

    private boolean containsFile(File file) {
        return this.files.contains(file);
    }

    private boolean containsDir(String name) {
        return this.directories.stream().map(Directory::getName).anyMatch(n -> n.equals(name));
    }

    public String getName() {
        return name;
    }

    public Directory getDirByName(String name) {
        return this.directories.stream().filter(n -> n.getName().equals(name)).findAny().orElseThrow();
    }

    public List<Directory> getDirectories() {
        return new ArrayList<>(directories);
    }

    public List<Directory> getAllDownstreamDirectoriesIncludingSelf() {
        List<Directory> results = new ArrayList<>();
        results.add(this);
        for (Directory d: directories) {
            results.addAll(d.getAllDownstreamDirectoriesIncludingSelf());
        }
        return results;
    }

    public long totalSizeOfDownstreamLargerThan(long x) {
        return getAllDownstreamDirectoriesIncludingSelf()
                .stream()
                .filter(directory -> directory.size() <= x)
                .mapToLong(Directory::size)
                .sum();
    }

    public long sizeOfDirectoryLargeEnoughForUpdate(long total, long required) {
        long curAvail = total - this.size();
        long diff =  required - curAvail;
        return getAllDownstreamDirectoriesIncludingSelf()
                .stream()
                .filter(directory -> directory.size() >= diff)
                .sorted()
                .limit(1)
                .mapToLong(Directory::size)
                .boxed()
                .findAny()
                .orElseThrow();
    }

    public long size () {
        return files.stream().mapToLong(File::size).sum()
                + this.directories.stream().mapToLong(Directory::size).sum();
    }

    @Override
    public String toString() {
        return "Directory{" +
               "name='" + name + '\'' +
               ", files=" + files +
               ", directories=" + directories +
               '}';
    }

    @Override
    public int compareTo(Directory o) {
        long mySize = size();
        long yourSize = o.size();
        return Long.compare(mySize, yourSize);
    }
}
