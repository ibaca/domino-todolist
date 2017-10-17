package com.progressoft.brix.domino.sample.items.shared.response;

import com.progressoft.brix.domino.api.shared.request.ServerResponse;

public class AddItemResponse extends ServerResponse {

    private boolean added;

    public AddItemResponse() {
    }

    public AddItemResponse(boolean added) {
        this.added = added;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }
}
