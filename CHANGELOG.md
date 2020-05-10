## Change log
----------------------

Version 3.6
-------------

ADDED:
 
- created new BeanPropertyComparator class 


Version 3.5
-------------

ADDED:
 
- added new methods that can compare over selected properties of the given objects

CHANGED:

- unit tests for new methods created
- code coverage increased to 100%

Version 3.4
-------------

ADDED:
 
- new test dependency commons-lang3  
- new unit tests created
- null check to compareTo method added
- created new idea run configuration for code coverage

CHANGED:

- upgrade gradle version to 6.3
- update of gradle plugin com.github.ben-manes.versions version to 0.28.0
- update of test dependency guava version to 29.0-jre
- update of test dependency test-objects version to 5.3
- update of test dependency testng version to 7.2.0

Version 3.3
-------------

ADDED:
 
- gradle as build system

CHANGED:

- removed maven related files
- changed project nature from maven to gradle nature
- removed lombok dependency
- removed all lombok dependent imports 

Version 3.2
-------------

ADDED:
 
- new factory class created for create custom Comparators
- added new test dependency guava in version 28.1-jre

CHANGED:

- update of parent version to 5.3
- update of dependency commons-beanutils version to 1.9.4
- update of test dependency test-objects version to 5.2

Version 3.1
-------------

ADDED:
 
- new enum CompareOrder created that encapsulates the possible return values of the compare method

Version 3
-------------

ADDED:
 
- this changelog file
- created PULL_REQUEST_TEMPLATE.md file
- created CODE_OF_CONDUCT.md file
- created CONTRIBUTING.md file
- provide package.html for the javadoc of packages
- moved classes from obsolet jobject-compare project


