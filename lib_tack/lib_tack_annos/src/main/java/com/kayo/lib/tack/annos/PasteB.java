package com.kayo.lib.tack.annos;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Kayo
 * 2018/12/22
 * 00:17
 * byte
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface PasteB {
    String value() default "";
    byte defaultVar() default (byte)0;
}
