package com.progressoft.brix.domino.sample.items.client.requests;

import com.progressoft.brix.domino.api.client.annotations.Path;
import com.progressoft.brix.domino.api.client.annotations.Request;
import com.progressoft.brix.domino.api.client.request.Response;
import com.progressoft.brix.domino.api.client.request.ServerRequest;
import com.progressoft.brix.domino.sample.items.shared.request.AddItemRequest;
import com.progressoft.brix.domino.sample.items.shared.request.LoadItemsRequest;
import com.progressoft.brix.domino.sample.items.shared.request.RemoveRequest;
import com.progressoft.brix.domino.sample.items.shared.request.ToggleItemRequest;
import com.progressoft.brix.domino.sample.items.shared.response.AddItemResponse;
import com.progressoft.brix.domino.sample.items.shared.response.LoadItemsResponse;
import com.progressoft.brix.domino.sample.items.shared.response.RemoveResponse;
import com.progressoft.brix.domino.sample.items.shared.response.ToggleItemResponse;

import javax.ws.rs.HttpMethod;

public class TodoItemsRequestFactory implements TodoItemsRequestGroup {

    public static final TodoItemsRequestFactory INSTANCE=new TodoItemsRequestFactory();

    @Request
    @Path(AddItemRequest.PATH)
    public class TodoItemsRequest_add extends ServerRequest<AddItemRequest, AddItemResponse> {
        TodoItemsRequest_add(AddItemRequest addItemRequest) {
            super(addItemRequest);
        }
    }

    @Request(classifier = RemoveRequest.CLEAR)
    @Path(value = RemoveRequest.CLEAR, method = HttpMethod.GET)
    public class TodoItemsRequest_clear extends ServerRequest<RemoveRequest, RemoveResponse> {
        TodoItemsRequest_clear(RemoveRequest requestBean) {
            super(requestBean);
        }
    }

    @Request(classifier = RemoveRequest.REMOVE_DONE)
    @Path(value = RemoveRequest.REMOVE_DONE, method = HttpMethod.GET)
    public class TodoItemsRequest_clearDone extends ServerRequest<RemoveRequest, RemoveResponse> {
        TodoItemsRequest_clearDone(RemoveRequest requestBean) {
            super(requestBean);
        }
    }

    @Request
    @Path(value = LoadItemsRequest.PATH, method = HttpMethod.GET)
    public class TodoItemsRequest_list extends ServerRequest<LoadItemsRequest, LoadItemsResponse> {
        TodoItemsRequest_list(LoadItemsRequest requestBean) {
            super(requestBean);
        }
    }

    @Request
    @Path(ToggleItemRequest.PATH)
    public class TodoItemsRequest_toggle extends ServerRequest<ToggleItemRequest, ToggleItemResponse> {
        TodoItemsRequest_toggle(ToggleItemRequest requestBean) {
            super(requestBean);
        }
    }

    @Override
    public Response<AddItemResponse> add(AddItemRequest addItemRequest){
        return new TodoItemsRequest_add(addItemRequest);
    }

    @Override
    public Response<RemoveResponse> clear(RemoveRequest removeRequest){
        return new TodoItemsRequest_clear(removeRequest);
    }

    @Override
    public Response<RemoveResponse> clearDone(RemoveRequest removeRequest){
        return new TodoItemsRequest_clearDone(removeRequest);
    }

    @Override
    public Response<LoadItemsResponse> list(LoadItemsRequest loadItemsRequest){
        return new TodoItemsRequest_list(loadItemsRequest);
    }

    @Override
    public Response<ToggleItemResponse> toggle(ToggleItemRequest toggleItemRequest){
        return new TodoItemsRequest_toggle(toggleItemRequest);
    }
}
