package com.progressoft.brix.domino.sample.items.shared.request;

import com.progressoft.brix.domino.api.shared.request.ServerRequest;

public class ToggleItemRequest extends ServerRequest{

    public static final String PATH = "toggle";
    private String title;

    public ToggleItemRequest() {
    }

    public ToggleItemRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
