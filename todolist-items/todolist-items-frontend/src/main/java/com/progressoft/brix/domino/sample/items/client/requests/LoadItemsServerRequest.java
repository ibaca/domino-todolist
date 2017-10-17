package com.progressoft.brix.domino.sample.items.client.requests;

import com.progressoft.brix.domino.api.client.annotations.Path;
import com.progressoft.brix.domino.api.client.annotations.Request;
import com.progressoft.brix.domino.api.client.request.ClientServerRequest;
import com.progressoft.brix.domino.sample.items.client.presenters.ItemsPresenter;
import com.progressoft.brix.domino.sample.items.shared.request.LoadItemsRequest;
import com.progressoft.brix.domino.sample.items.shared.response.LoadItemsResponse;

import javax.ws.rs.HttpMethod;

@Request
@Path(value = LoadItemsRequest.PATH, method = HttpMethod.GET)
public class LoadItemsServerRequest extends ClientServerRequest<ItemsPresenter, LoadItemsRequest, LoadItemsResponse> {

    @Override
    protected void process(ItemsPresenter presenter, LoadItemsRequest serverArgs, LoadItemsResponse response) {
        presenter.onItemsLoaded(response.getItems());
    }

    @Override
    public LoadItemsRequest buildArguments() {
        return new LoadItemsRequest();
    }
}
