package com.progressoft.brix.domino.sample.items.shared.response;

import com.progressoft.brix.domino.api.shared.request.ServerResponse;

public class RemoveResponse extends ServerResponse {

    private boolean cleared;

    public RemoveResponse() {
    }

    public RemoveResponse(boolean cleared) {
        this.cleared = cleared;
    }

    public boolean isCleared() {
        return cleared;
    }

    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }
}
