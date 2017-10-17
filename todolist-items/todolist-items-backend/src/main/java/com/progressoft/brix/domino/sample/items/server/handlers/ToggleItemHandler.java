package com.progressoft.brix.domino.sample.items.server.handlers;

import com.progressoft.brix.domino.api.server.handler.Handler;
import com.progressoft.brix.domino.api.server.handler.RequestHandler;
import com.progressoft.brix.domino.sample.items.server.TodoItemsStore;
import com.progressoft.brix.domino.sample.items.shared.TodoItem;
import com.progressoft.brix.domino.sample.items.shared.request.AddItemRequest;
import com.progressoft.brix.domino.sample.items.shared.request.ToggleItemRequest;
import com.progressoft.brix.domino.sample.items.shared.response.AddItemResponse;
import com.progressoft.brix.domino.sample.items.shared.response.ToggleItemResponse;

import java.util.logging.Logger;

@Handler(ToggleItemRequest.PATH)
public class ToggleItemHandler implements RequestHandler<ToggleItemRequest, ToggleItemResponse> {
    private static final Logger LOGGER = Logger.getLogger(ToggleItemHandler.class.getName());

    @Override
    public ToggleItemResponse handleRequest(ToggleItemRequest toggleItemRequest) {
        TodoItem todoItem = TodoItemsStore.get(toggleItemRequest.getTitle());
        todoItem.toggle();
        return new ToggleItemResponse(todoItem.isDone());
    }
}
