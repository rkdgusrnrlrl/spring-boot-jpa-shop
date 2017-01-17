package me.dakbutfly.springtest;

/**
 * Created by khk on 2017-01-17.
 */
import org.aopalliance.intercept.MethodInterceptor;

import org.springframework.aop.MethodMatcher;

/**
 * Internal framework class, combining a MethodInterceptor instance
 * with a MethodMatcher for use as an element in the advisor chain.
 *
 * @author Rod Johnson
 */
class InterceptorAndDynamicMethodMatcher {

    final MethodInterceptor interceptor;

    final MethodMatcher methodMatcher;

    public InterceptorAndDynamicMethodMatcher(MethodInterceptor interceptor, MethodMatcher methodMatcher) {
        this.interceptor = interceptor;
        this.methodMatcher = methodMatcher;
    }

}
