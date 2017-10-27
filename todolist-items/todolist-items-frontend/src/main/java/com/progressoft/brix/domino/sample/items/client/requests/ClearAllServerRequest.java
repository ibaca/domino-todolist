package com.progressoft.brix.domino.sample.items.client.requests;

import com.progressoft.brix.domino.api.client.annotations.Path;
import com.progressoft.brix.domino.api.client.annotations.Request;
import com.progressoft.brix.domino.api.client.request.ServerRequest;
import com.progressoft.brix.domino.sample.items.shared.request.RemoveRequest;
import com.progressoft.brix.domino.sample.items.shared.response.RemoveResponse;

import javax.ws.rs.HttpMethod;

@Request(classifier = RemoveRequest.CLEAR)
@Path(value = RemoveRequest.CLEAR, method = HttpMethod.GET)
public class ClearAllServerRequest extends ServerRequest<RemoveRequest, RemoveResponse> {
}
