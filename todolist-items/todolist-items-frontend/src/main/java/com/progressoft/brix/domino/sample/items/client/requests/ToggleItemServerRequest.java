package com.progressoft.brix.domino.sample.items.client.requests;

import com.progressoft.brix.domino.api.client.annotations.Path;
import com.progressoft.brix.domino.api.client.annotations.Request;
import com.progressoft.brix.domino.api.client.request.ServerRequest;
import com.progressoft.brix.domino.sample.items.shared.request.ToggleItemRequest;
import com.progressoft.brix.domino.sample.items.shared.response.ToggleItemResponse;

@Request
@Path(ToggleItemRequest.PATH)
public class ToggleItemServerRequest extends ServerRequest<ToggleItemRequest, ToggleItemResponse> {
}
