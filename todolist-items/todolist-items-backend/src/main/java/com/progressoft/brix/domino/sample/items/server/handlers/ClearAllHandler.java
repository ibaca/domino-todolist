package com.progressoft.brix.domino.sample.items.server.handlers;

import com.progressoft.brix.domino.api.server.handler.Handler;
import com.progressoft.brix.domino.api.server.handler.RequestHandler;
import com.progressoft.brix.domino.sample.items.server.TodoItemsStore;
import com.progressoft.brix.domino.sample.items.shared.request.RemoveRequest;
import com.progressoft.brix.domino.sample.items.shared.response.RemoveResponse;

import java.util.logging.Logger;

@Handler(value = RemoveRequest.CLEAR, classifier = RemoveRequest.CLEAR)
public class ClearAllHandler implements RequestHandler<RemoveRequest, RemoveResponse> {
    private static final Logger LOGGER = Logger.getLogger(ClearAllHandler.class.getName());

    @Override
    public RemoveResponse handleRequest(RemoveRequest loadItemsRequest) {
        try {
            TodoItemsStore.clear();
            return new RemoveResponse(true);
        } catch (Exception e) {
            return new RemoveResponse(false);
        }
    }
}
