package io.github.astrapi69.comparator.factory;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class TestDataFactory
{
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
	public static <K> Map<K, Integer> newCounterMap(Map<K, Integer> counterMap,
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
