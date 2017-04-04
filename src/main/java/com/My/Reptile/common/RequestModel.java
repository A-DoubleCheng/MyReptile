package com.My.Reptile.common;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestModel {
    /**
     * The request parameter to bind to.
     */
    String value();
}
