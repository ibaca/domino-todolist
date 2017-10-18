package com.progressoft.brix.domino.sample.items.client.requests;

import com.progressoft.brix.domino.api.client.annotations.Path;
import com.progressoft.brix.domino.api.client.annotations.Request;
import com.progressoft.brix.domino.api.client.request.ClientServerRequest;
import com.progressoft.brix.domino.sample.items.client.presenters.ItemsPresenter;
import com.progressoft.brix.domino.sample.items.shared.request.LoadItemsRequest;
import com.progressoft.brix.domino.sample.items.shared.response.LoadItemsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.HttpMethod;

@Request
@Path(value = LoadItemsRequest.PATH, method = HttpMethod.GET)
public class LoadItemsServerRequest extends ClientServerRequest<ItemsPresenter, LoadItemsRequest, LoadItemsResponse> {
    private static final Logger LOGGER=LoggerFactory.getLogger(LoadItemsServerRequest.class);

    @Override
    protected void process(ItemsPresenter presenter, LoadItemsRequest serverArgs, LoadItemsResponse response) {
        presenter.onItemsLoaded(response.getItems());
        LOGGER.info("Todo Items - Loaded items count is : "+response.getItems().size());
    }

    @Override
    public LoadItemsRequest buildArguments() {
        return new LoadItemsRequest();
    }
}
