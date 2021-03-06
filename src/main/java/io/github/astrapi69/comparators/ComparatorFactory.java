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
package io.github.astrapi69.comparators;

import java.security.SecureRandom;
import java.util.*;

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
		return Comparator.comparing(definedOrder::indexOf);
	}

	/**
	 * Factory method for create a comparator for random sort of map values
	 *
	 * @param <K>
	 *            the generic type of the key
	 * @param <V>
	 *            the generic type of the value
	 * @param map
	 *            the map
	 * @param secureRandom
	 *            the secure random object for random generation
	 * @return the comparator with random sort of map values
	 */
	public static<K, V> Comparator<V> newRandomMapValuesComparator(
			Map<K, V> map, SecureRandom secureRandom)
	{
		List<V> numberCounterValues = new ArrayList<>(new TreeSet<>(map.values()));
		Collections.shuffle(numberCounterValues, secureRandom);
		return ComparatorFactory.newComparator(numberCounterValues);
	}

	/**
	 * Factory method for create a comparator for sort with the map values
	 *
	 * @param <K>
	 *            the generic type of the key
	 * @param <V>
	 *            the generic type of the value
	 * @param map
	 *            the map
	 * @return the comparator for sort with the map values
	 */
	public static<K, V> Comparator<V> newMapValuesComparator(Map<K, V> map)
	{
		List<V> numberCounterValues = new ArrayList<>(new TreeSet<>(map.values()));
		return ComparatorFactory.newComparator(numberCounterValues);
	}

}
