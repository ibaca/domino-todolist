package com.progressoft.brix.domino.sample.items.shared;

public interface TodoItem {

    String ROOT_PATH = "todo/";

    boolean isDone();

    String getItemTitle();

    String getItemDescription();

    void toggle();
}
