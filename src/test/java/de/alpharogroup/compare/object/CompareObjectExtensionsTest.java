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
package de.alpharogroup.compare.object;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.lang3.StringUtils;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import com.google.common.collect.Sets;

import de.alpharogroup.test.objects.Permission;
import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.enums.Gender;

/**
 * The unit test class for the class {@link CompareObjectExtensions}.
 */
public class CompareObjectExtensionsTest
{

	private final static Logger log = Logger.getLogger(CompareObjectExtensionsTest.class.getName());

	/**
	 * Test method for {@link CompareObjectExtensions#compare(Object, Object)}.
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 */
	@Test(enabled = true)
	public void testCompare()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		boolean expected;
		boolean actual;
		Person sourceOjbect;
		Person objectToCompare;
		// create a person...
		sourceOjbect = Person.builder().gender(Gender.MALE).name("obelix").build();
		// make a clone of it...
		objectToCompare = Person.builder().gender(Gender.MALE).name("obelix").build();
		// 1. scenario...
		// expected: the compare method should return true...
		// explanation of expected: compared object are equal
		expected = true;
		actual = CompareObjectExtensions.compare(sourceOjbect, objectToCompare);
		assertEquals(expected, actual);
		// 2. scenario...
		// expected: the compare method should return false...
		// explanation of expected: compared object are not equal

		// change the gender and compare again...
		objectToCompare.setGender(Gender.FEMALE);

		expected = false;
		actual = CompareObjectExtensions.compare(sourceOjbect, objectToCompare);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link CompareObjectExtensions#compare(Object, Object)} that throws an
	 * IllegalArgumentException
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 */
	@Test(enabled = true, expectedExceptions = IllegalArgumentException.class)
	public void testCompareThrowIllegalArgumentException01()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		Person sourceOjbect;
		Person objectToCompare;
		sourceOjbect = null;
		objectToCompare = Person.builder().gender(Gender.MALE).name("obelix").build();
		CompareObjectExtensions.compare(sourceOjbect, objectToCompare);
	}

	/**
	 * Test method for {@link CompareObjectExtensions#compare(Object, Object)} that throws an
	 * IllegalArgumentException
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 */
	@Test(enabled = true, expectedExceptions = IllegalArgumentException.class)
	public void testCompareThrowIllegalArgumentException02()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		Person sourceOjbect;
		Person objectToCompare;
		sourceOjbect = Person.builder().gender(Gender.MALE).name("obelix").build();
		objectToCompare = null;
		CompareObjectExtensions.compare(sourceOjbect, objectToCompare);
	}

	/**
	 * Test method for {@link CompareObjectExtensions#compare(Object, Object)} that throws an
	 * IllegalArgumentException
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 */
	@Test(enabled = true, expectedExceptions = IllegalArgumentException.class)
	public void testCompareThrowIllegalArgumentException03()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		Person sourceOjbect;
		String objectToCompare;
		sourceOjbect = Person.builder().gender(Gender.MALE).name("obelix").build();
		objectToCompare = "foo";
		CompareObjectExtensions.compare(sourceOjbect, objectToCompare);
	}

	/**
	 * Test method for {@link CompareObjectExtensions#compareTo(Object, Object)}
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 *
	 */
	@Test(enabled = true)
	public void testCompareTo()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		int expected;
		int actual;
		Permission sourceOjbect;
		Permission objectToCompare;
		// create a person...
		sourceOjbect = Permission.builder().name("read").build();
		// make a clone of it...
		objectToCompare = Permission.builder().name("read").build();

		expected = 0;
		actual = CompareObjectExtensions.compareTo(sourceOjbect, objectToCompare);
		assertEquals(expected, actual);

		sourceOjbect.setName("write");
		expected = 5;
		actual = CompareObjectExtensions.compareTo(sourceOjbect, objectToCompare);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link CompareObjectExtensions#compareTo(Object, Object, String)}.
	 *
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
	@Test(enabled = true)
	public void testCompareToProperty()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
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
		actual = CompareObjectExtensions.compareTo(asterix, obelix, "name");

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
		defaultComparator = new BeanComparator("name");
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
	 * Test method for {@link CompareObjectExtensions#compareTo(Object, Object, Set)}
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 *
	 */
	@Test(enabled = true)
	public void testCompareToSetProperties()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		int expected;
		int actual;
		Permission sourceOjbect;
		Permission objectToCompare;
		// create a person...
		sourceOjbect = Permission.builder().name("read").description("Permission for read files")
			.build();
		// make a clone of it...
		objectToCompare = Permission.builder().name("read").description("Permission for read files")
			.shortcut("R").build();
		// compare only name and description
		expected = 0;
		actual = CompareObjectExtensions.compareTo(sourceOjbect, objectToCompare,
			Sets.newHashSet("name", "description"));
		assertEquals(expected, actual);
		// compare all
		expected = -1;
		actual = CompareObjectExtensions.compareTo(sourceOjbect, objectToCompare,
			Sets.newHashSet("name", "description", "shortcut"));
		assertEquals(expected, actual);
		// alter name and compare only name and description
		sourceOjbect.setName("write");
		expected = 5;
		actual = CompareObjectExtensions.compareTo(sourceOjbect, objectToCompare,
			Sets.newHashSet("name", "description"));
		assertEquals(expected, actual);
		// compare only description
		expected = 0;
		actual = CompareObjectExtensions.compareTo(sourceOjbect, objectToCompare,
			Sets.newHashSet("description"));
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link CompareObjectExtensions#compareTo(Object, Object, Set)}
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 *
	 */
	@Test(enabled = true)
	public void testCompareToSetPropertiesWithComparableObjects()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		int expected;
		int actual;
		Person sourceOjbect;
		Person objectToCompare;
		// create a person...
		sourceOjbect = Person.builder().gender(Gender.MALE).name("obelix").build();
		// make a clone of it...
		objectToCompare = Person.builder().gender(Gender.MALE).name("obelix").build();

		expected = 0;
		actual = CompareObjectExtensions.compareTo(sourceOjbect, objectToCompare,
			Sets.newHashSet("gender", "name"));
		assertEquals(expected, actual);

		sourceOjbect.setName("asterix");
		expected = -14;
		actual = CompareObjectExtensions.compareTo(sourceOjbect, objectToCompare,
			Sets.newHashSet("gender", "name"));
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link CompareObjectExtensions#compareTo(Object, Object)} that throws an
	 * IllegalArgumentException
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 *
	 */
	@Test(enabled = true, expectedExceptions = IllegalArgumentException.class)
	public void testCompareToThrowIllegalArgumentException03()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		Person sourceOjbect;
		String objectToCompare;
		// create a person...
		sourceOjbect = Person.builder().gender(Gender.MALE).name("obelix").build();

		// make a clone of it...
		objectToCompare = "";

		CompareObjectExtensions.compareTo(sourceOjbect, objectToCompare);
	}

	/**
	 * Test method for {@link CompareObjectExtensions#compareTo(Object, Object)}
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 *
	 */
	@Test(enabled = true)
	public void testCompareToWithComparableObjects()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		int expected;
		int actual;
		Person sourceOjbect;
		Person objectToCompare;
		// create a person...
		sourceOjbect = Person.builder().gender(Gender.MALE).name("obelix").build();
		// make a clone of it...
		objectToCompare = Person.builder().gender(Gender.MALE).name("obelix").build();

		expected = 0;
		actual = CompareObjectExtensions.compareTo(sourceOjbect, objectToCompare);
		assertEquals(expected, actual);

		sourceOjbect.setName("asterix");
		expected = -14;
		actual = CompareObjectExtensions.compareTo(sourceOjbect, objectToCompare);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link CompareObjectExtensions#compareTo(Object, Object)} with
	 * objectToCompare as null
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 *
	 */
	@Test(enabled = true)
	public void testCompareToWithObjectToCompareIsNull()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		int expected;
		int actual;
		Person sourceOjbect;
		Person objectToCompare;
		// create a person...
		sourceOjbect = Person.builder().gender(Gender.MALE).name("obelix").build();

		// make a clone of it...
		objectToCompare = null;

		actual = CompareObjectExtensions.compareTo(sourceOjbect, objectToCompare);
		expected = 1;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link CompareObjectExtensions#compareTo(Object, Object)} with sourceObject
	 * as null
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 *
	 */
	@Test(enabled = true)
	public void testCompareToWithSourceObjectIsNull()
		throws IllegalAccessException, NoSuchMethodException, InvocationTargetException
	{
		int expected;
		int actual;
		Person sourceOjbect;
		Person objectToCompare;
		// create a person...
		sourceOjbect = null;
		// make a clone of it...
		objectToCompare = Person.builder().gender(Gender.MALE).name("obelix").build();

		actual = CompareObjectExtensions.compareTo(sourceOjbect, objectToCompare);
		expected = -1;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link StringUtils#compare(String, String)}
	 */
	@Test(enabled = true)
	public void testCompareWithStringUtilsCompare()
		throws IllegalAccessException, NoSuchMethodException, InvocationTargetException
	{
		int expected;
		int actual;
		String obelix;
		String asterix;

		obelix = null;
		asterix = null;
		actual = StringUtils.compare(asterix, obelix);
		expected = 0;
		assertEquals(expected, actual);

		actual = CompareObjectExtensions.compareTo(asterix, obelix);
		expected = 0;
		assertEquals(expected, actual);

		obelix = "foo";
		asterix = "foo";
		actual = StringUtils.compare(asterix, obelix);
		expected = 0;
		assertEquals(expected, actual);

		actual = CompareObjectExtensions.compareTo(asterix, obelix);
		expected = 0;
		assertEquals(expected, actual);

		obelix = "foo";
		asterix = null;
		actual = StringUtils.compare(asterix, obelix);
		expected = -1;
		assertEquals(expected, actual);

		actual = CompareObjectExtensions.compareTo(asterix, obelix);
		expected = -1;
		assertEquals(expected, actual);

		obelix = "foo";
		asterix = "bar";
		actual = StringUtils.compare(asterix, obelix);
		expected = -4;
		assertEquals(expected, actual);

		actual = CompareObjectExtensions.compareTo(asterix, obelix);
		expected = -4;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link CompareObjectExtensions#getCompareToResult(Object, Object)}.
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 */
	@Test
	public void testGetCompareToResult()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		int expected;
		int actual;
		Person obelix;
		Person asterix;

		obelix = Person.builder().gender(Gender.MALE).name("obelix").build();

		asterix = Person.builder().gender(Gender.MALE).name("asterix").build();
		Map<String, Integer> compareToResult = CompareObjectExtensions.getCompareToResult(asterix,
			obelix);

		assertNotNull(compareToResult);
		expected = 5;
		actual = compareToResult.size();

		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link CompareObjectExtensions#getCompareToResult(Object, Object)} that
	 * throws an IllegalArgumentException
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 */
	@Test(enabled = true, expectedExceptions = IllegalArgumentException.class)
	public void testGetCompareToResultThrowIllegalArgumentException01()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		Person obelix;
		Person asterix;

		obelix = null;

		asterix = Person.builder().gender(Gender.MALE).name("asterix").build();
		CompareObjectExtensions.getCompareToResult(asterix, obelix);
	}

	/**
	 * Test method for {@link CompareObjectExtensions#getCompareToResult(Object, Object)} that
	 * throws an IllegalArgumentException
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 */
	@Test(enabled = true, expectedExceptions = IllegalArgumentException.class)
	public void testGetCompareToResultThrowIllegalArgumentException02()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		Person obelix;
		Person asterix;

		obelix = Person.builder().gender(Gender.MALE).name("asterix").build();

		asterix = null;
		CompareObjectExtensions.getCompareToResult(asterix, obelix);
	}

	/**
	 * Test method for {@link CompareObjectExtensions#getCompareToResult(Object, Object)} that
	 * throws an IllegalArgumentException
	 *
	 * @throws IllegalAccessException
	 *             Thrown if this {@code Method} object is enforcing Java language access control
	 *             and the underlying method is inaccessible.
	 * @throws InvocationTargetException
	 *             Thrown if the property accessor method throws an exception
	 * @throws NoSuchMethodException
	 *             Thrown if a matching method is not found or if the name is "&lt;init&gt;"or
	 *             "&lt;clinit&gt;".
	 */
	@Test(enabled = true, expectedExceptions = IllegalArgumentException.class)
	public void testGetCompareToResultThrowIllegalArgumentException03()
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		String obelix;
		Person asterix;

		obelix = "foo";

		asterix = Person.builder().gender(Gender.MALE).name("asterix").build();
		CompareObjectExtensions.getCompareToResult(asterix, obelix);
	}

	/**
	 * Test method for {@link CompareObjectExtensions} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(CompareObjectExtensions.class);
	}

}

