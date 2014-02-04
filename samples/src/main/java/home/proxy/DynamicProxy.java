/**
 * 
 */
package home.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author smrutis
 * 
 */
public class DynamicProxy implements InvocationHandler {

	private Object obj;

	public DynamicProxy(Object obj) {
		this.obj = obj;
	}

	static public Object newInstance(Object obj, Class[] interfaces) {
		return java.lang.reflect.Proxy.newProxyInstance(obj.getClass()
				.getClassLoader(), interfaces, new DynamicProxy(obj));
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		return null;
	}

}
