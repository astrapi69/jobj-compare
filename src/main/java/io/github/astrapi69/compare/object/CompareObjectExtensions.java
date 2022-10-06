/**
 * The MIT License
 * <p>
 * Copyright (C) 2015 Asterios Raptis
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.compare.object;

import io.github.astrapi69.comparator.factory.ComparatorFactory;

import java.util.Comparator;
import java.util.function.Function;

/**
 * The class {@link CompareObjectExtensions} provide methods for compare an object with another
 * given object.
 */
public final class CompareObjectExtensions {

	private CompareObjectExtensions() {
	}

	/**
	 * Compares the given object over the given property.
	 *
	 * @param sourceOjbect
	 *            the source ojbect
	 * @param objectToCompare
	 *            the object to compare
	 * @param beanPropertyFunction
	 *            the function used to extract the bean property and create the {@link Comparator}
	 * @return the resulted int value
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" }) public static <T, U extends Comparable<? super U>> int compareTo(final T sourceOjbect,
			final T objectToCompare, final Function<? super T, ? extends U> beanPropertyFunction) {
		return ComparatorFactory.newBeanPropertyComparator(beanPropertyFunction).compare(sourceOjbect, objectToCompare);
	}

}
