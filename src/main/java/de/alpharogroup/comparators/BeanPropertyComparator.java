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
package de.alpharogroup.comparators;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * The class {@link BeanPropertyComparator} compares two beans by a given name of a bean property.
 * It is inspired from the <code>BeanComparator</code> from the library commons-beanutils
 *
 * @param <T>
 *            the generic type
 */
public class BeanPropertyComparator<T> implements Comparator<T>, Serializable
{

	/** The comparator. */
	private final Comparator<?> comparator;

	/** The property. */
	private String property;

	/**
	 * Instantiates a new {@link BeanPropertyComparator}
	 */
	public BeanPropertyComparator()
	{
		this(null);
	}

	/**
	 * Instantiates a new {@link BeanPropertyComparator} from the given property
	 *
	 * @param property
	 *            the property
	 */
	public BeanPropertyComparator(final String property)
	{
		this(property, SortOrderComparator.of());
	}

	/**
	 * Instantiates a new {@link BeanPropertyComparator} from the given property and the given
	 * comparator
	 *
	 * @param property
	 *            the property
	 * @param comparator
	 *            the comparator
	 */
	public BeanPropertyComparator(final String property, final Comparator<?> comparator)
	{
		setProperty(property);
		if (comparator != null)
		{
			this.comparator = comparator;
		}
		else
		{
			this.comparator = SortOrderComparator.of();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compare(final T o1, final T o2)
	{

		if (property == null)
		{
			// compare the actual objects
			return internalCompare(o1, o2);
		}

		try
		{
			final Object value1 = PropertyUtils.getProperty(o1, property);
			final Object value2 = PropertyUtils.getProperty(o2, property);
			return internalCompare(value1, value2);
		}
		catch (final IllegalAccessException iae)
		{
			throw new RuntimeException("IllegalAccessException: " + iae.toString());
		}
		catch (final InvocationTargetException ite)
		{
			throw new RuntimeException("InvocationTargetException: " + ite.toString());
		}
		catch (NoSuchMethodException e)
		{
			throw new RuntimeException("NoSuchMethodException: " + e.toString());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof BeanPropertyComparator))
		{
			return false;
		}

		final BeanPropertyComparator<?> beanComparator = (BeanPropertyComparator<?>)o;

		if (!comparator.equals(beanComparator.comparator))
		{
			return false;
		}
		if (property != null)
		{
			if (!property.equals(beanComparator.property))
			{
				return false;
			}
		}
		else
		{
			return (beanComparator.property == null);
		}

		return true;
	}

	/**
	 * Gets the comparator.
	 *
	 * @return the comparator
	 */
	public Comparator<?> getComparator()
	{
		return comparator;
	}

	/**
	 * Gets the property.
	 *
	 * @return the property
	 */
	public String getProperty()
	{
		return property;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int result;
		result = comparator.hashCode();
		return result;
	}

	@SuppressWarnings("unchecked")
	private int internalCompare(final Object val1, final Object val2)
	{
		@SuppressWarnings("rawtypes")
		final
		// to make the compiler happy
		Comparator internalComparator = comparator;
		return internalComparator.compare(val1, val2);
	}

	/**
	 * Sets the property.
	 *
	 * @param property
	 *            the new property
	 */
	public void setProperty(final String property)
	{
		this.property = property;
	}
}
