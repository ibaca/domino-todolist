package com.progressoft.brix.domino.sample.items.client.requests;

import com.progressoft.brix.domino.api.client.annotations.Path;
import com.progressoft.brix.domino.api.client.annotations.Request;
import com.progressoft.brix.domino.api.client.request.ClientServerRequest;
import com.progressoft.brix.domino.sample.items.client.presenters.ItemsPresenter;
import com.progressoft.brix.domino.sample.items.shared.request.RemoveRequest;
import com.progressoft.brix.domino.sample.items.shared.response.RemoveResponse;

import javax.ws.rs.HttpMethod;

@Request(classifier = RemoveRequest.CLEAR)
@Path(value = RemoveRequest.CLEAR, method = HttpMethod.GET)
public class ClearAllServerRequest extends ClientServerRequest<ItemsPresenter, RemoveRequest, RemoveResponse> {

    @Override
    protected void process(ItemsPresenter presenter, RemoveRequest serverArgs, RemoveResponse response) {
        presenter.onItemsCleared(response.isCleared());
    }

    @Override
    public RemoveRequest buildArguments() {
        return new RemoveRequest();
    }
}
