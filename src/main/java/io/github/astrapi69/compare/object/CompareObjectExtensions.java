/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.compare.object;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.BeanUtils;

import io.github.astrapi69.comparator.ComparatorExtensions;

/**
 * The class {@link CompareObjectExtensions} provide methods for compare an object with another
 * given object.
 */
public final class CompareObjectExtensions
{

	private CompareObjectExtensions()
	{
	}

	/**
	 * Compares the given two objects.
	 *
	 * @param sourceOjbect
	 *            the source ojbect
	 * @param objectToCompare
	 *            the object to compare
	 * @return true, if successful otherwise false
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 */
	@SuppressWarnings("rawtypes")
	public static boolean compare(final Object sourceOjbect, final Object objectToCompare)
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		if (sourceOjbect == null || objectToCompare == null
			|| !sourceOjbect.getClass().equals(objectToCompare.getClass()))
		{
			throw new IllegalArgumentException("Object should not be null and be the same type.");
		}
		final Map beanDescription = BeanUtils.describe(sourceOjbect);
		beanDescription.remove("class");
		final Map clonedBeanDescription = BeanUtils.describe(objectToCompare);
		clonedBeanDescription.remove("class");
		for (final Object key : beanDescription.keySet())
		{
			if (compareTo(sourceOjbect, objectToCompare, key.toString()) != 0)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Compares the given object over the given property.
	 *
	 * @param sourceOjbect
	 *            the source ojbect
	 * @param objectToCompare
	 *            the object to compare
	 * @param properties
	 *            properties to compare
	 * @return the resulted int value
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 */
	public static int compare(final Object sourceOjbect, final Object objectToCompare,
		final String... properties)
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		return compareTo(sourceOjbect, objectToCompare, new HashSet<>(Arrays.asList(properties)));
	}

	/**
	 * Compares the given two objects and returns the result as int.
	 *
	 * @param sourceOjbect
	 *            the source object
	 * @param objectToCompare
	 *            the object to compare
	 * @return the resulted int value
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int compareTo(final Object sourceOjbect, final Object objectToCompare)
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		final Integer nullCheck = ComparatorExtensions.nullCheck(sourceOjbect, objectToCompare);
		if (nullCheck != null)
		{
			return nullCheck;
		}
		if (!sourceOjbect.getClass().equals(objectToCompare.getClass()))
		{
			throw new IllegalArgumentException("Object should be the same type.");
		}
		if (sourceOjbect instanceof Comparable && objectToCompare instanceof Comparable)
		{
			return ((Comparable)sourceOjbect).compareTo(objectToCompare);
		}
		final Map beanDescription = BeanUtils.describe(sourceOjbect);
		beanDescription.remove("class");
		final Map clonedBeanDescription = BeanUtils.describe(objectToCompare);
		clonedBeanDescription.remove("class");
		int result = 0;
		for (final Object key : beanDescription.keySet())
		{
			result += compareTo(sourceOjbect, objectToCompare, key.toString());
		}
		return result;
	}

	/**
	 * Compares the given object over the given property.
	 *
	 * @param sourceOjbect
	 *            the source ojbect
	 * @param objectToCompare
	 *            the object to compare
	 * @param properties
	 *            the set of properties to compare
	 * @return the resulted int value
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 */
	public static int compareTo(final Object sourceOjbect, final Object objectToCompare,
		final Set<String> properties)
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		int result = 0;
		for (String property : properties)
		{
			result += compareTo(sourceOjbect, objectToCompare, property);
		}
		return result;
	}

	/**
	 * Compares the given object over the given property.
	 *
	 * @param sourceOjbect
	 *            the source ojbect
	 * @param objectToCompare
	 *            the object to compare
	 * @param property
	 *            the property
	 * @return the resulted int value
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int compareTo(final Object sourceOjbect, final Object objectToCompare,
		final String property)
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		final Integer nullCheck = ComparatorExtensions.nullCheck(
			BeanUtils.describe(sourceOjbect).get(property),
			BeanUtils.describe(objectToCompare).get(property));
		if (nullCheck != null)
		{
			return nullCheck;
		}
		return new BeanComparator(property).compare(sourceOjbect, objectToCompare);
	}

	/**
	 * Gets the compare to result.
	 *
	 * @param sourceOjbect
	 *            the source ojbect
	 * @param objectToCompare
	 *            the object to compare
	 * @return the compare to result
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Integer> getCompareToResult(final Object sourceOjbect,
		final Object objectToCompare)
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		if (sourceOjbect == null || objectToCompare == null
			|| !sourceOjbect.getClass().equals(objectToCompare.getClass()))
		{
			throw new IllegalArgumentException("Object should not be null and be the same type.");
		}
		final Map beanDescription = BeanUtils.describe(sourceOjbect);
		beanDescription.remove("class");
		final Map clonedBeanDescription = BeanUtils.describe(objectToCompare);
		clonedBeanDescription.remove("class");
		final Map<String, Integer> compareResult = new HashMap<>();
		for (final Object key : beanDescription.keySet())
		{
			compareResult.put(key.toString(),
				compareTo(sourceOjbect, objectToCompare, key.toString()));
		}
		return compareResult;
	}

}
