## Change log
----------------------

Version 11.2-SNAPSHOT
-------------

CHANGED:

- tagged class BeanPropertyComparator as deprecated
- update of gradle-plugin dependency 'com.diffplug.spotless:spotless-plugin-gradle' to new minor version 6.11.0
- update of dependency comparator-extensions to new minor version 1.4
- update of test dependency junit.jupiter.api to new patch version 5.9.1
- update of test dependency test-object to new version 7.2

Version 11.1
-------------

CHANGED:

- update gradle to new version 7.5.1
- update of dependency comparator-extensions to new minor version 1.2
- replaced test dependency testng with junit.jupiter.api
- update of test dependency test-object to new version 7

Version 11
-------------

ADDED:

- created new factory method for create a comparator with a defined order
- new gradle plugin 'org.ajoberstar.grgit:grgit-gradle' in major version 5.0.0
- new gradle plugin dependency 'com.diffplug.spotless:spotless-plugin-gradle' for formatting source code with gradle build task

CHANGED:

- update to jdk version 11
- update gradle to new version 7.5
- update of com.github.ben-manes.versions.gradle.plugin to new minor version 0.42.0
- changed to new major version 8 because of move of existing classes to new packages
- moved all comparator classes from package comparator.* to new module comparator-extensions
- update of test dependency test-objects to new version 6.1
- update of test dependency guava version to 31.1-jre
- update of test dependency testng version to 7.6.1

Version 3.9
-------------

CHANGED:

- update gradle to new version 7.2
- update gradle-plugin dependency of gradle.plugin.com.hierynomus.gradle.plugins:license-gradle-plugin to new version 0.16.1
- new factory method for create a random order of a given list
- new factory method for create a random order of a given list with a given SecureRandom object
- update of test dependency test-objects to new version 5.5
- update of test dependency guava version to 31.0.1-jre

Version 3.8
-------------

CHANGED:

- changed to new package io.github.astrapi69
- upgrade gradle version to 6.9
- update of test dependency test-objects to new version 5.4
- update of com.github.ben-manes.versions.gradle.plugin to new version 0.39.0
- update of test dependency commons-lang3 version to 3.12.0
- update of test dependency guava version to 30.1.1-jre
- update of test dependency testng version to 7.4.0

Version 3.7
-------------

ADDED:

- created new factory methods for create a comparator from the values of a map
- created new factory methods for create a random comparator from the values of a map

CHANGED:

- upgrade gradle version to 6.7
- update of gradle plugin com.github.ben-manes.versions version to 0.33.0
- update of test dependency guava version to 30.0-jre
- update of test dependency commons-lang3 version to 3.11
- update of test dependency testng version to 7.3.0

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
