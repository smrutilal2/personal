package home.patterns;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * The ancient one. Dumb, Lazy, nobody wants it.
 */
public class Singleton implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3968995646437778396L;

	private static Singleton instance;

	int count;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (null == instance)
			instance = new Singleton();
		return instance;
	}

	// this function must not be called other than by the deserialization
	// runtime
	private Object readResolve() throws ObjectStreamException {
		return instance;
	}

}

/**
 * Eagerly loaded. performs better.
 */
class Singleton_Eager {
	private static Singleton_Eager instance;
	static {
		instance = new Singleton_Eager();
	}

	private Singleton_Eager() {
	}

	public static Singleton_Eager getInstance() {
		return instance;
	}
}

/**
 * Lazy with double check. Thread safe as opposed to the ancient one.
 */
class Singleton_DoubleCheck {
	private static Singleton_DoubleCheck instance;

	private Singleton_DoubleCheck() {
	}

	public static Singleton_DoubleCheck getInstance() {
		if (null == instance)
			synchronized (Singleton_DoubleCheck.class) {
				if (null == instance)
					instance = new Singleton_DoubleCheck();
			}
		return instance;
	}
}

/**
 * Maintains a single instance across multiple class loaders.
 */
class AbsoluteSingleton {

	/**
	 * This is effectively an instance of this class (although actually it may
	 * be instead a java.lang.reflect.Proxy wrapping an instance from the
	 * original classloader).
	 */
	public static AbsoluteSingleton instance = null;

	/**
	 * Retrieve an instance of AbsoluteSingleton from the original classloader.
	 * This is a true Singleton, in that there will only be one instance of this
	 * object in the virtual machine, even though there may be several copies of
	 * its class file loaded in different classloaders.
	 */
	public synchronized static AbsoluteSingleton getInstance() {
		ClassLoader myClassLoader = AbsoluteSingleton.class.getClassLoader();
		if (instance == null) {
			// The root classloader is sun.misc.Launcher package. If we are not
			// in a sun package,
			// we need to get hold of the instance of ourself from the class in
			// the root classloader.
			if (!myClassLoader.toString().startsWith("sun.")) {
				try {
					// So we find our parent classloader
					ClassLoader parentClassLoader = AbsoluteSingleton.class
							.getClassLoader().getParent();
					// And get the other version of our current class
					Class otherClassInstance = parentClassLoader
							.loadClass(AbsoluteSingleton.class.getName());
					// And call its getInstance method - this gives the correct
					// instance of ourself
					Method getInstanceMethod = otherClassInstance
							.getDeclaredMethod("getInstance", new Class[] {});
					Object otherAbsoluteSingleton = getInstanceMethod.invoke(
							null, new Object[] {});
					// But, we can't cast it to our own interface directly
					// because classes loaded from
					// different classloaders implement different versions of an
					// interface.
					// So instead, we use java.lang.reflect.Proxy to wrap it in
					// an object that *does*
					// support our interface, and the proxy will use reflection
					// to pass through all calls
					// to the object.

					instance = (AbsoluteSingleton) Proxy
							.newProxyInstance(myClassLoader,
									new Class[] { AbsoluteSingleton.class },
									new PassThroughProxyHandler(
											otherAbsoluteSingleton));

					// And catch the usual tedious set of reflection exceptions
					// We're cheating here and just catching everything - don't
					// do this in real code
				} catch (Exception e) {
					e.printStackTrace();
				}
				// We're in the root classloader, so the instance we have here
				// is the correct one
			} else {
				instance = new AbsoluteSingleton();
			}
		}

		return instance;
	}

}

class PassThroughProxyHandler implements InvocationHandler {
	private final Object delegate;

	public PassThroughProxyHandler(Object delegate) {
		this.delegate = delegate;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Method delegateMethod = delegate.getClass().getMethod(method.getName(),
				method.getParameterTypes());
		return delegateMethod.invoke(delegate, args);
	}
}
