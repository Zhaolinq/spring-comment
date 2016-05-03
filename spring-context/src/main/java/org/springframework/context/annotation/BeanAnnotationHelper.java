/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.annotation;

import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;

/**
 * Utilities for processing {@link Bean}-annotated methods.
 *
 * @author Chris Beams
 * @since 3.1
 */
class BeanAnnotationHelper {

	/**
	 * Return whether the given method is directly or indirectly annotated with
	 * the {@link Bean} annotation.
	 */
	public static boolean isBeanAnnotated(Method method) {
		return (AnnotationUtils.findAnnotation(method, Bean.class) != null);
	}

	public static String determineBeanNameFor(Method beanMethod) {
		// By default, the bean name is the name of the @Bean-annotated method
		String beanName = beanMethod.getName();

		// Check to see if the user has explicitly set a custom bean name...
		Bean bean = AnnotationUtils.findAnnotation(beanMethod, Bean.class);
		if (bean != null && bean.name().length > 0) {
			beanName = bean.name()[0];
		}

		return beanName;
	}

}