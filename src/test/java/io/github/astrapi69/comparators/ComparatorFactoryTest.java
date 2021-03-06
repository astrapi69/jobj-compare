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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;

/**
 * The unit test class for the class {@link ComparatorFactory}
 */
public class ComparatorFactoryTest
{

	/**
	 * Test for method {@link ComparatorFactory#newComparator(List)}
	 */
	@Test
	public void testNewComparator()
	{
		List<Integer> values;
		List<Integer> actual;
		List<Integer> expected;
		// new scenario...
		values = Lists.newArrayList(1, 2, 3, 4, 5);
		// change list to random sort order
		Collections.shuffle(values);
		// create the custom Comparator from the given list
		Comparator<Integer> customComparator = ComparatorFactory.newComparator(values);
		// create a new list to sort with the custom Comparator
		actual = Lists.newArrayList(1, 2, 3, 4, 5);
		// sort with the custom Comparator
		Collections.sort(actual, customComparator);
		// now the actual list have to be sorted as the values list
		expected = values;
		assertEquals(actual, expected);
	}

	/**
	 * Test for method {@link ComparatorFactory#newMapValuesComparator(Map)}
	 */
	@Test
	public void testNewMapValuesComparator() {

		List<Integer> values;
		List<Integer> actual;
		List<Integer> expected;
		// new scenario...
		values = Lists.newArrayList(1, 2, 3, 4, 5);
		Map<Integer, Integer> numberCounterMap;
		numberCounterMap = newCounterMap(new HashMap<>(), values);
		Comparator<Integer> integerComparator = ComparatorFactory.newMapValuesComparator(numberCounterMap);
		assertNotNull(integerComparator);
	}

	/**
	 * Test for method {@link ComparatorFactory#newRandomMapValuesComparator(Map, SecureRandom)}
	 */
	@Test
	public void testNewRandomMapValuesComparator() throws NoSuchAlgorithmException {

		List<Integer> values;
		List<Integer> actual;
		List<Integer> expected;
		// new scenario...
		values = Lists.newArrayList(1, 2, 3, 4, 5);
		Map<Integer, Integer> numberCounterMap;
		numberCounterMap = newCounterMap(new HashMap<>(), values);
		Comparator<Integer> integerComparator = ComparatorFactory.newRandomMapValuesComparator(numberCounterMap, SecureRandom.getInstanceStrong());
		assertNotNull(integerComparator);
	}


	/**
	 * Factory method for create a map for and count elements of the given collection
	 *
	 * @param <K>
	 *            the generic type of the elements
	 * @param counterMap
	 *            the counter Map
	 * @param elements
	 *            the elements
	 * @return the new map ready to count elements
	 */
	private static <K> Map<K, Integer> newCounterMap(Map<K, Integer> counterMap,
													Collection<K> elements)
	{
		Objects.requireNonNull(counterMap);
		for (K element : elements)
		{
			if (counterMap.containsKey(element))
			{
				counterMap.merge(element, 1, Integer::sum);
				continue;
			}
			counterMap.put(element, 0);
		}
		return counterMap;
	}
}
