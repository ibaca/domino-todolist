package com.progressoft.brix.domino.sample.items.client.requests;

import com.progressoft.brix.domino.api.client.annotations.Path;
import com.progressoft.brix.domino.api.client.annotations.RequestFactory;
import com.progressoft.brix.domino.api.client.request.Response;
import com.progressoft.brix.domino.sample.items.shared.request.AddItemRequest;
import com.progressoft.brix.domino.sample.items.shared.request.LoadItemsRequest;
import com.progressoft.brix.domino.sample.items.shared.request.RemoveRequest;
import com.progressoft.brix.domino.sample.items.shared.request.ToggleItemRequest;
import com.progressoft.brix.domino.sample.items.shared.response.AddItemResponse;
import com.progressoft.brix.domino.sample.items.shared.response.LoadItemsResponse;
import com.progressoft.brix.domino.sample.items.shared.response.RemoveResponse;
import com.progressoft.brix.domino.sample.items.shared.response.ToggleItemResponse;

import javax.ws.rs.HttpMethod;

@RequestFactory("todo/")
public interface TodoItemsRequests {

    @Path(AddItemRequest.PATH)
    Response<AddItemResponse> add(AddItemRequest addItemRequest);

    @Path(RemoveRequest.CLEAR)
    Response<RemoveResponse> clear(RemoveRequest removeRequest);

    @Path(RemoveRequest.REMOVE_DONE)
    Response<RemoveResponse> clearDone(RemoveRequest removeRequest);

    @Path(value = LoadItemsRequest.PATH, method = HttpMethod.GET)
    Response<LoadItemsResponse> list(LoadItemsRequest loadItemsRequest);

    @Path(ToggleItemRequest.PATH)
    Response<ToggleItemResponse> toggle(ToggleItemRequest toggleItemRequest);

}
