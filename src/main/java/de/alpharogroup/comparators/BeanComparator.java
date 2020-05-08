package de.alpharogroup.comparators;

import org.apache.commons.beanutils.PropertyUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

public class BeanComparator<T> implements Comparator<T>, Serializable {

    private String property;
    private final Comparator<?> comparator;

    public BeanComparator() {
        this( null );
    }

    public BeanComparator( final String property ) {
        this( property, SortOrderComparator.of() );
    }

    public BeanComparator( final String property, final Comparator<?> comparator ) {
        setProperty( property );
        if (comparator != null) {
            this.comparator = comparator;
        } else {
            this.comparator = SortOrderComparator.of();
        }
    }

    public void setProperty( final String property ) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public Comparator<?> getComparator() {
        return comparator;
    }

    public int compare( final T o1, final T o2 ) {

        if ( property == null ) {
            // compare the actual objects
            return internalCompare( o1, o2 );
        }

        try {
            final Object value1 = PropertyUtils.getProperty( o1, property );
            final Object value2 = PropertyUtils.getProperty( o2, property );
            return internalCompare( value1, value2 );
        }
        catch ( final IllegalAccessException iae ) {
            throw new RuntimeException( "IllegalAccessException: " + iae.toString() );
        }
        catch ( final InvocationTargetException ite ) {
            throw new RuntimeException( "InvocationTargetException: " + ite.toString() );
        } catch (NoSuchMethodException e) {
            throw new RuntimeException( "NoSuchMethodException: " + e.toString() );
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BeanComparator)) {
            return false;
        }

        final BeanComparator<?> beanComparator = (BeanComparator<?>) o;

        if (!comparator.equals(beanComparator.comparator)) {
            return false;
        }
        if (property != null)
        {
            if (!property.equals(beanComparator.property)) {
                return false;
            }
        }
        else
        {
            return (beanComparator.property == null);
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = comparator.hashCode();
        return result;
    }

    private int internalCompare(final Object val1, final Object val2) {
        @SuppressWarnings("rawtypes")
        final
        // to make the compiler happy
        Comparator internalComparator = comparator;
        return internalComparator.compare(val1, val2);
    }
}
