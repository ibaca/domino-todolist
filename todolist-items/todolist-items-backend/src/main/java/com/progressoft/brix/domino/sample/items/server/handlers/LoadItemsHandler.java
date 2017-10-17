package com.progressoft.brix.domino.sample.items.server.handlers;

import com.progressoft.brix.domino.api.server.handler.Handler;
import com.progressoft.brix.domino.api.server.handler.RequestHandler;
import com.progressoft.brix.domino.sample.items.server.TodoItemsStore;
import com.progressoft.brix.domino.sample.items.shared.request.LoadItemsRequest;
import com.progressoft.brix.domino.sample.items.shared.response.LoadItemsResponse;

import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;

@Handler(LoadItemsRequest.PATH)
public class LoadItemsHandler implements RequestHandler<LoadItemsRequest, LoadItemsResponse> {
    private static final Logger LOGGER = Logger.getLogger(LoadItemsHandler.class.getName());

    @Override
    public LoadItemsResponse handleRequest(LoadItemsRequest loadItemsRequest) {
        return new LoadItemsResponse(TodoItemsStore.all().stream().map(LoadItemsResponse.Item::new).collect(toList()));
    }
}
