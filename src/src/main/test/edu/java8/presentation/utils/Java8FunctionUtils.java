package edu.java8.presentation.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Java8FunctionUtils {

    public static <T> List<T> filter( List<T> in, Predicate<T> pred) {
        List<T> out = new ArrayList<>();
        for (T elem : in) {
            if(pred.test(elem)) {
                out.add(elem);
            }
        }
        return out;
    }

    public static <T, R> List<R> function( List<T> in, Function<T, R> fun) {
        List<R> out = new ArrayList<>();
        for (T elem : in) {
           out.add(fun.apply(elem));
        }
        return out;
    }
}
