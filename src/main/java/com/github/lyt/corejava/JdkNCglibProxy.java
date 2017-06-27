package com.github.lyt.corejava;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import net.sf.cglib.proxy.CallbackHelper;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * Created by Yueting on 6/18/2017.
 */
public class JdkNCglibProxy {

  private static class DebugArrayListJDK implements InvocationHandler {

    private Object originalList;

    private DebugArrayListJDK(Object list) {
      this.originalList = list;
    }

    public static Object newInstance(Object list) {
      return Proxy
          .newProxyInstance(list.getClass().getClassLoader(), list.getClass().getInterfaces(),
              new DebugArrayListJDK(list));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      Object result = null;

      if ("add".equals(method.getName())) {
        System.out.println(
            "\"add\" method called on " + originalList.getClass().getSimpleName() + " with " + args
                .toString());
        result = method.invoke(originalList, args);
        System.out.println("\"add\" method call succeeded.");
      }

      return result;
    }
  }

  public static void jdkProxyExample() {
    List<String> list = new ArrayList<>();
    list.add("One");
    list.add("Two");

    List<String> proxy = (List<String>) DebugArrayListJDK.newInstance(list);
    proxy.add("Three");

    System.out.println("[jdkProxyExample]: " + list.toString());
  }

  private static class DebugArrayListCGLIB implements net.sf.cglib.proxy.InvocationHandler {

    private Object originalList;

    private DebugArrayListCGLIB(Object list) {
      this.originalList = list;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      Object result = null;

      if ("add".equals(method.getName())) {
        System.out.println(
            "\"add\" method called on " + originalList.getClass().getSimpleName() + " with " + args
                .toString());
        result = method.invoke(originalList, args);
        System.out.println("\"add\" method call succeeded.");
      }
      return result;
    }
  }

  public static void cglibProxyExample() {
    List<String> list = new ArrayList<>();
    list.add("One");
    list.add("Two");

    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(list.getClass());

    CallbackHelper helper = new CallbackHelper(list.getClass(), new Class[0]) {

      @Override
      protected Object getCallback(Method method) {
        if ("add".equals(method.getName())) {
          return new DebugArrayListCGLIB(list);
        }
        return NoOp.INSTANCE;
      }
    };
    enhancer.setCallbackFilter(helper);
    enhancer.setCallbacks(helper.getCallbacks());

    List<String> enhancedArrayList = (List<String>) enhancer.create();
    enhancedArrayList.add("Three");

    System.out.println("[cglibProxyExample]: " + list.toString());
  }

  public static void main(String[] args) {
    System.out.println("========== Running JDK proxy example. ==========");
    JdkNCglibProxy.jdkProxyExample();
    System.out.println("================================================");

    System.out.println("========== Running CGLIB proxy example. ==========");
    JdkNCglibProxy.cglibProxyExample();
    System.out.println("==================================================");
  }

}
