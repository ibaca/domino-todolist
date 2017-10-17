package com.progressoft.brix.domino.sample.items.client.requests;

import com.progressoft.brix.domino.api.client.annotations.Path;
import com.progressoft.brix.domino.api.client.annotations.Request;
import com.progressoft.brix.domino.api.client.request.ClientServerRequest;
import com.progressoft.brix.domino.sample.items.client.presenters.ItemsPresenter;
import com.progressoft.brix.domino.sample.items.shared.TodoItem;
import com.progressoft.brix.domino.sample.items.shared.request.AddItemRequest;
import com.progressoft.brix.domino.sample.items.shared.request.ToggleItemRequest;
import com.progressoft.brix.domino.sample.items.shared.response.AddItemResponse;
import com.progressoft.brix.domino.sample.items.shared.response.ToggleItemResponse;

@Request
@Path(ToggleItemRequest.PATH)
public class ToggleItemServerRequest extends ClientServerRequest<ItemsPresenter, ToggleItemRequest, ToggleItemResponse> {
    private final String title;

    public ToggleItemServerRequest(String title) {
        this.title = title;
    }

    @Override
    protected void process(ItemsPresenter presenter, ToggleItemRequest serverArgs, ToggleItemResponse response) {
    }

    @Override
    public ToggleItemRequest buildArguments() {
        return new ToggleItemRequest(title);
    }
}
