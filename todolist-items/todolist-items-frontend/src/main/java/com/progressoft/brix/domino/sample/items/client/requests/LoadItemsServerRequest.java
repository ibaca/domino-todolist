package com.progressoft.brix.domino.sample.items.client.requests;

import com.progressoft.brix.domino.api.client.annotations.Path;
import com.progressoft.brix.domino.api.client.annotations.Request;
import com.progressoft.brix.domino.api.client.request.ClientServerRequest;
import com.progressoft.brix.domino.sample.items.shared.request.LoadItemsRequest;
import com.progressoft.brix.domino.sample.items.shared.response.LoadItemsResponse;

import javax.ws.rs.HttpMethod;

@Request
@Path(value = LoadItemsRequest.PATH, method = HttpMethod.GET)
public class LoadItemsServerRequest extends ClientServerRequest<LoadItemsRequest, LoadItemsResponse> {
}
