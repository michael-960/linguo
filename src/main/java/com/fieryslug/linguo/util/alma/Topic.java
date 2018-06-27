package com.fieryslug.linguo.util.alma;

import java.util.ArrayList;

public class Topic implements IWithName {

    public String name;
    public ArrayList<Group> groups;

    public Topic() {
        this("", new ArrayList<>());
    }

    public Topic(String name, ArrayList<Group> groups) {
        this.name = name;
        this.groups = groups;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
