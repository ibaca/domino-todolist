package com.progressoft.brix.domino.sample.items.client.requests;

import com.progressoft.brix.domino.api.client.annotations.Path;
import com.progressoft.brix.domino.api.client.annotations.Request;
import com.progressoft.brix.domino.api.client.request.ClientServerRequest;
import com.progressoft.brix.domino.sample.items.shared.request.AddItemRequest;
import com.progressoft.brix.domino.sample.items.shared.response.AddItemResponse;

@Request
@Path(AddItemRequest.PATH)
public class AddItemServerRequest extends ClientServerRequest<AddItemRequest, AddItemResponse> {

}
