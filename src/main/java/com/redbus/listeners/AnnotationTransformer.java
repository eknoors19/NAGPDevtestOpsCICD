package com.redbus.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class AnnotationTransformer implements  IAnnotationTransformer
{	
	@Override
	public void transform(ITestAnnotation annotation, @SuppressWarnings("rawtypes") Class testclass, @SuppressWarnings("rawtypes") Constructor testConstructor, Method met) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}

}
