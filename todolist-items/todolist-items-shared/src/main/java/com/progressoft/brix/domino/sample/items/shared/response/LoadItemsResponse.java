package com.progressoft.brix.domino.sample.items.shared.response;

import com.progressoft.brix.domino.api.shared.request.ServerResponse;
import com.progressoft.brix.domino.sample.items.shared.TodoItem;

import java.util.List;

public class LoadItemsResponse extends ServerResponse {

    private List<Item> items;

    public LoadItemsResponse() {
    }

    public LoadItemsResponse(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static class Item implements TodoItem{

        private boolean done;
        private String itemTitle;
        private String itemDescription;

        public Item() {
        }

        public Item(TodoItem todoItem){
            this.done=todoItem.isDone();
            this.itemTitle=todoItem.getItemTitle();
            this.itemDescription=todoItem.getItemDescription();
        }

        public Item(boolean done, String itemTitle, String itemDescription) {
            this.done = done;
            this.itemTitle = itemTitle;
            this.itemDescription = itemDescription;
        }

        @Override
        public boolean isDone() {
            return done;
        }

        public void setDone(boolean done) {
            this.done = done;
        }

        @Override
        public String getItemTitle() {
            return itemTitle;
        }

        public void setItemTitle(String itemTitle) {
            this.itemTitle = itemTitle;
        }

        @Override
        public String getItemDescription() {
            return itemDescription;
        }

        public void setItemDescription(String itemDescription) {
            this.itemDescription = itemDescription;
        }

        @Override
        public void toggle() {
            this.done=!this.done;
        }
    }
}