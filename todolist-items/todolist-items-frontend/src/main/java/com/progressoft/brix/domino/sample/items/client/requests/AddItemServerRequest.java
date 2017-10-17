package com.progressoft.brix.domino.sample.items.client.requests;

import com.progressoft.brix.domino.api.client.annotations.Path;
import com.progressoft.brix.domino.api.client.annotations.Request;
import com.progressoft.brix.domino.api.client.request.ClientServerRequest;
import com.progressoft.brix.domino.sample.items.client.presenters.ItemsPresenter;
import com.progressoft.brix.domino.sample.items.shared.TodoItem;
import com.progressoft.brix.domino.sample.items.shared.request.AddItemRequest;
import com.progressoft.brix.domino.sample.items.shared.response.AddItemResponse;

@Request
@Path(AddItemRequest.PATH)
public class AddItemServerRequest extends ClientServerRequest<ItemsPresenter, AddItemRequest, AddItemResponse> {
    private final TodoItem item;

    public AddItemServerRequest(TodoItem item) {
        this.item = item;
    }

    @Override
    protected void process(ItemsPresenter presenter, AddItemRequest serverArgs, AddItemResponse response) {
        presenter.onItemAdded(item);
    }

    @Override
    public AddItemRequest buildArguments() {
        return new AddItemRequest(item);
    }
}
