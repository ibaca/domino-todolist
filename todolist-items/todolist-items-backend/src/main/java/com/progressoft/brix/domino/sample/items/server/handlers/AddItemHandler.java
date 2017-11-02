package com.progressoft.brix.domino.sample.items.server.handlers;

import com.progressoft.brix.domino.api.server.handler.Handler;
import com.progressoft.brix.domino.api.server.handler.RequestHandler;
import com.progressoft.brix.domino.sample.items.server.TodoItemsStore;
import com.progressoft.brix.domino.sample.items.shared.TodoItem;
import com.progressoft.brix.domino.sample.items.shared.request.AddItemRequest;
import com.progressoft.brix.domino.sample.items.shared.response.AddItemResponse;

import java.util.logging.Logger;

@Handler(TodoItem.ROOT_PATH + AddItemRequest.PATH)
public class AddItemHandler implements RequestHandler<AddItemRequest, AddItemResponse> {
    private static final Logger LOGGER = Logger.getLogger(AddItemHandler.class.getName());

    @Override
    public AddItemResponse handleRequest(AddItemRequest addItemRequest) {
        TodoItemsStore.store(addItemRequest.getItemTitle(), addItemRequest);
        return new AddItemResponse(true);
    }
}
