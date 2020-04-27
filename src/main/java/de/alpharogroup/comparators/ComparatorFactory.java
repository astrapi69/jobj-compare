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

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * A factory for creating custom {@link Comparator} objects
 */
public class ComparatorFactory
{

	/**
	 * Factory method for create a new {@link Comparator} from the given {@link List} with the
	 * defined order
	 *
	 * @param <T>
	 *            the generic type of the elements
	 * @param definedOrder
	 *            the defined order
	 * @return the comparator
	 */
	public static <T> Comparator<T> newComparator(final List<T> definedOrder)
	{
		Objects.requireNonNull(definedOrder);
		return Comparator.comparing(e -> definedOrder.indexOf(e));
	}

}
