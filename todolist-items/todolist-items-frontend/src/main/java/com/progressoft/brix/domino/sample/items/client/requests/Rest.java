package com.progressoft.brix.domino.sample.items.client.requests;

public @interface Rest {
    String path();

    String method();

    String serviceRoot();
}
