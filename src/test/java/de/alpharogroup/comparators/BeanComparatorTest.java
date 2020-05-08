package de.alpharogroup.comparators;

import de.alpharogroup.test.objects.Person;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertTrue;

public class BeanComparatorTest {

    @Test
    public void testCompare() {
        int actual;

        final Person person = Person.builder().name("al").build();
        final Person otherPerson = Person.builder().name("bert").build();
        BeanComparator beanComparator = new BeanComparator("name");
        actual = beanComparator.compare(person, otherPerson);
        assertTrue(actual == -1);
    }
}
