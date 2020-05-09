package de.alpharogroup.comparators;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import de.alpharogroup.test.objects.Person;

public class BeanPropertyComparatorTest
{

	@Test
	public void testCompare()
	{
		int expected;
		int actual;

		final Person person = Person.builder().name("al").build();
		final Person otherPerson = Person.builder().name("bert").build();
		BeanPropertyComparator<Person> beanComparator = new BeanPropertyComparator<>("name");
		actual = beanComparator.compare(person, otherPerson);
		expected = -1;
		assertEquals(actual, expected);
	}
}
