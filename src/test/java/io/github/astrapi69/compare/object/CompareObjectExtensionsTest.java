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
import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.enumtype.Gender;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The unit test class for the class {@link CompareObjectExtensions}.
 */
public class CompareObjectExtensionsTest {

	private final static Logger log = Logger.getLogger(CompareObjectExtensionsTest.class.getName());

	/**
	 * Test method for {@link CompareObjectExtensions#compareTo(Object, Object, Function)}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" }) @Test public void testCompareToProperty() {
		int expected;
		int actual;
		Person obelix;
		Person asterix;
		Person miraculix;
		List<Person> persons;
		Comparator<String> comparator;
		Comparator defaultComparator;

		persons = new ArrayList<>();
		obelix = Person.builder().gender(Gender.MALE).name("obelix").build();
		asterix = Person.builder().gender(Gender.MALE).name("asterix").build();
		miraculix = Person.builder().gender(Gender.MALE).name("miraculix").build();
		// 1. scenario...
		actual = CompareObjectExtensions.compareTo(asterix, obelix, Person::getName);

		comparator = Comparator.naturalOrder(); // (o1, o2) -> o1.compareTo(o2);
		expected = comparator.compare(asterix.getName(), obelix.getName());

		assertEquals(expected, actual);

		persons.add(obelix);
		persons.add(asterix);
		persons.add(miraculix);

		assertEquals(persons.get(0), obelix);
		assertEquals(persons.get(1), asterix);
		assertEquals(persons.get(2), miraculix);

		log.log(Level.FINE, "Unsorted Persons:");
		log.log(Level.FINE, persons.toString());
		defaultComparator = ComparatorFactory.newBeanPropertyComparator(Person::getName);
		persons.sort(defaultComparator);

		log.log(Level.FINE, "Sorted Persons by name:");
		log.log(Level.FINE, persons.toString());

		assertEquals(persons.get(0), asterix);
		assertEquals(persons.get(1), miraculix);
		assertEquals(persons.get(2), obelix);

		Collections.reverse(persons);

		log.log(Level.FINE, "Sorted Persons by name reversed:");
		log.log(Level.FINE, persons.toString());
		assertEquals(persons.get(0), obelix);
		assertEquals(persons.get(1), miraculix);
		assertEquals(persons.get(2), asterix);
	}

	/**
	 * Test method for {@link CompareObjectExtensions} with {@link BeanTester}
	 */
	@Test public void testWithBeanTester() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(CompareObjectExtensions.class);
	}

}

