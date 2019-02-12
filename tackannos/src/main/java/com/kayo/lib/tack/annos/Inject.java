package com.kayo.lib.tack.annos;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * KayoSun
 * 2019-01-29
 * 16:46
 * ----------
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Inject {
}
