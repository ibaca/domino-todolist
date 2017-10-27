package com.progressoft.brix.domino.sample.items.client.requests;

import javax.validation.constraints.NotNull;

public @interface RequestGroup {
    @NotNull
    String value() default "";
}
