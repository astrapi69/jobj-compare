# Overview

<div style="text-align: center">

[![Java CI with Gradle](https://github.com/astrapi69/jobj-compare/actions/workflows/gradle.yml/badge.svg)](https://github.com/astrapi69/jobj-compare/actions/workflows/gradle.yml)
[![Coverage Status](https://codecov.io/gh/astrapi69/jobj-compare/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/jobj-compare)
[![Open Issues](https://img.shields.io/github/issues/astrapi69/jobj-compare.svg?style=flat)](https://github.com/astrapi69/jobj-compare/issues)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/jobj-compare)](https://central.sonatype.com/artifact/io.github.astrapi69/jobj-compare)
[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/jobj-compare.svg)](http://www.javadoc.io/doc/io.github.astrapi69/jobj-compare)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)

</div>

Utility library for simply compare java objects.

# Support this project

> Please support this project by simply putting a Github <!-- Place this tag where you want the button to render. -->
<a class="github-button" href="https://github.com/astrapi69/jobj-compare" data-icon="octicon-star" aria-label="Star astrapi69/jobj-compare on GitHub">Star ⭐</a>
>
> Share this library with friends on Twitter and everywhere else you can
>
> If you love this project [![donation](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)
>
> or for more donation options go the [donations section](#Donations)

## Maven dependency

The library is on Maven Central. Check out [Maven Central](https://central.sonatype.com/artifact/io.github.astrapi69/jobj-compare) for the latest release.

Add the following maven dependency to your project `pom.xml` if you want to import the core functionality of jobj-compare:

Than you can add the dependency to your dependencies:

    <properties>
            ...
        <!-- JOBJ-COMPARE version -->
        <jobj-compare.version>11.1</jobj-compare.version>
            ...
    </properties>
            ...
        <dependencies>
            ...
            <!-- JOBJ-COMPARE DEPENDENCY -->
            <dependency>
                <groupId>io.github.astrapi69</groupId>
                <artifactId>jobj-compare</artifactId>
                <version>${jobj-compare.version}</version>
            </dependency>
            ...
        </dependencies>


## gradle dependency

You can first define the version in the ext section and add than the following gradle dependency to
your project `build.gradle` if you want to import the core functionality of jobj-compare:

define version in file gradle.properties

```
jobjCompareVersion=11.1
```

or in build.gradle ext area

```
ext {
            ...
    jobjCompareVersion = "11.1"
            ...
}
```

and than add the dependency to the dependencies area

```
dependencies {
            ...
    implementation("io.github.astrapi69:jobj-compare:$jobjCompareVersion")
            ...
}
```

## Support this project

If jobj-compare is useful to you, a star on [GitHub](https://github.com/astrapi69/jobj-compare) helps others find it.
Sharing it or reporting a bug helps just as much.

If you would like to contribute financially, the channels are on the repository's Sponsor button, which
reads [.github/FUNDING.yml](.github/FUNDING.yml): GitHub Sponsors, Liberapay, Ko-fi and PayPal.

## Note

No animals were harmed in the making of this library.

## License

The source code comes under the liberal MIT License, making jobj-compare great for all types of applications.

## Semantic Versioning

The versions of jobj-compare are maintained with the Simplified Semantic Versioning guidelines.

Release version numbers will be incremented in the following format:

`<major>.<minor>.<patch>`

For detailed information on versioning for this project you can visit this [wiki page](https://github.com/lightblueseas/mvn-parent-projects/wiki/Simplified-Semantic-Versioning).

## Want to Help and improve it? ###

The source code for jobj-compare are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/jobj-compare/fork](https://github.com/astrapi69/jobj-compare/fork)

To share your changes, [submit a pull request](https://github.com/astrapi69/jobj-compare/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contacting the Developers

Do not hesitate to contact the jobj-compare developers with your questions, concerns, comments, bug reports, or feature requests.
- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/astrapi69/jobj-compare/issues).

## Credits

|**Maven Central**|
|     :---:      |
|[![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/jobj-compare?style=for-the-badge)](https://central.sonatype.com/artifact/io.github.astrapi69/jobj-compare)|
|Special thanks to [sonatype repository](https://www.sonatype.com) for providing a free maven repository service for open source projects|
|     <img width=1000/>     |

|**javadoc.io**|
|     :---:      |
|[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/jobj-compare.svg)](http://www.javadoc.io/doc/io.github.astrapi69/jobj-compare)|
|Special thanks to [javadoc.io](http://www.javadoc.io) for providing a free javadoc documentation for open source projects|
|     <img width=1000/>     |
