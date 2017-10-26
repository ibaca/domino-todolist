package com.progressoft.brix.domino.sample.items.server.handlers;

import com.progressoft.brix.domino.api.server.handler.Handler;
import com.progressoft.brix.domino.api.server.handler.RequestHandler;
import com.progressoft.brix.domino.sample.items.server.TodoItemsStore;
import com.progressoft.brix.domino.sample.items.shared.request.RemoveRequest;
import com.progressoft.brix.domino.sample.items.shared.response.RemoveResponse;

import java.util.logging.Level;
import java.util.logging.Logger;


@Handler(value = RemoveRequest.REMOVE_DONE, classifier = RemoveRequest.REMOVE_DONE)
public class ClearDoneHandler implements RequestHandler<RemoveRequest, RemoveResponse> {
    private static final Logger LOGGER = Logger.getLogger(ClearDoneHandler.class.getName());

    @Override
    public RemoveResponse handleRequest(RemoveRequest loadItemsRequest) {
        try {
            TodoItemsStore.clearDone();
            return new RemoveResponse(true);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "could not clear done items", e);
            return new RemoveResponse(false);
        }
    }
}
