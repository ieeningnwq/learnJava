package com.ieening.learnDynamicProxy.aop;

public class ServiceA {
	@SimpleInject
	ServiceB b;

	public void callB() {
		b.action();
	}

	public void callThrowException() {
		System.out.println("I am A, throw exception");
		throw new RuntimeException("call to throw exception");
	}

	public ServiceB getB() {
		return b;
	}
}
