package me.dakbutfly.springtest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by khk on 2017-01-17.
 */
public class TestSpringComponent {

    @Test
    public void testValidInvocation() throws Throwable {
        Method m = Object.class.getMethod("hashCode", (Class[]) null);
        Object proxy = new Object();
        final Object returnValue = new Object();
        List is = new LinkedList();
        MethodInterceptor real = new MethodInterceptor() {
            public Object invoke(MethodInvocation invocation) throws Throwable {
                return returnValue;
            }
        };
        is.add(real);
        ReflectiveMethodInvocation invocation = new ReflectiveMethodInvocation(proxy, null, //?
                m, null, null, is // list
        );
        Object rv = invocation.proceed();
        assertTrue("correct response", rv == returnValue);
    }

    private class TestBean {
    }
}
