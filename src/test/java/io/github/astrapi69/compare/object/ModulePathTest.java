/**
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
package io.github.astrapi69.compare.object;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Test that a consumer compiles against this library together with comparator-extensions, which it
 * depends on (#26): a module on the module path that requires both, and a class on the class path.
 * This library has no module descriptor, so on the module path its jar is the automatic module
 * named after the file: {@code jobj.compare} for a released jar such as
 * {@code jobj-compare-12.jar}. A snapshot jar such as {@code jobj-compare-13-SNAPSHOT.jar} gives no
 * module name at all, so the test copies the built jar to {@value #RELEASED_JAR_NAME} first.
 * comparator-extensions declares its module and exports {@code io.github.astrapi69.comparator}, so
 * a class of this library in that package makes a split package, which javac and the JVM refuse on
 * the module path. Gradle's test task hands over the built jar in the system property
 * {@value #JAR_PROPERTY} and its runtime class path in {@value #RUNTIME_CLASSPATH_PROPERTY}
 * (gradle/testing.gradle)
 */
public class ModulePathTest
{

	/**
	 * The system property with the built jar
	 */
	private static final String JAR_PROPERTY = "module.path.test.jar";

	/**
	 * The system property with the runtime class path of the built jar, separated by the platform's
	 * path separator
	 */
	private static final String RUNTIME_CLASSPATH_PROPERTY = "module.path.test.runtime.classpath";

	/**
	 * The file name the built jar is copied to, the name of a released jar without its version,
	 * from which the automatic module name {@code jobj.compare} derives
	 */
	private static final String RELEASED_JAR_NAME = "jobj-compare.jar";

	/**
	 * A class of the consumer that uses this library and comparator-extensions
	 */
	private static final String CONSUMER_CLASS = String.join("\n", "package consumer.check;", "",
		"import io.github.astrapi69.compare.object.CompareObjectExtensions;",
		"import io.github.astrapi69.comparator.ComparatorExtensions;", "", "public class Compare",
		"{", "	public static boolean compare() throws ReflectiveOperationException", "	{",
		"		return ComparatorExtensions.compare(\"a\", \"b\") < 0",
		"			&& CompareObjectExtensions.compare(\"a\", \"a\");", "	}", "}", "");

	/**
	 * The module descriptor of the consumer module, which requires this library and
	 * comparator-extensions
	 */
	private static final String CONSUMER_MODULE_INFO = String.join("\n", "module consumer.check",
		"{", "	requires io.github.astrapisixtynine.comparator.extensions;",
		"	requires jobj.compare;", "}", "");

	/**
	 * The result of a compilation
	 *
	 * @param compiled
	 *            whether the sources compiled
	 * @param messages
	 *            the messages of the compiler, each with its diagnostic code first, which does not
	 *            depend on the locale
	 */
	record Compilation(boolean compiled, String messages) {
	}

	/**
	 * Test that a consumer module that requires this library and comparator-extensions compiles on
	 * the module path. Today it does not: both modules hold the package
	 * {@code io.github.astrapi69.comparator}
	 *
	 * @param directory
	 *            the directory for the sources and the classes of the consumer
	 * @throws IOException
	 *             if a source file cannot be written
	 */
	@Test
	public void testConsumerModuleCompiles(@TempDir final Path directory) throws IOException
	{
		final Compilation compilation = compile(directory, "--module-path",
			Map.of("module-info.java", CONSUMER_MODULE_INFO, "consumer/check/Compare.java",
				CONSUMER_CLASS));
		assertFalse(compilation.compiled(), compilation.messages());
		assertTrue(compilation.messages().contains("compiler.err.package.clash.from.requires: "),
			compilation.messages());
		assertTrue(compilation.messages().contains("io.github.astrapi69.comparator"),
			compilation.messages());
	}

	/**
	 * Test that the same consumer class compiles on the class path, where a split package does not
	 * show
	 *
	 * @param directory
	 *            the directory for the sources and the classes of the consumer
	 * @throws IOException
	 *             if a source file cannot be written
	 */
	@Test
	public void testConsumerOnTheClassPathCompiles(@TempDir final Path directory) throws IOException
	{
		final Compilation compilation = compile(directory, "--class-path",
			Map.of("consumer/check/Compare.java", CONSUMER_CLASS));
		assertTrue(compilation.compiled(), compilation.messages());
	}

	/**
	 * Compiles the given sources against the built jar and its runtime class path, on the path the
	 * given option names
	 *
	 * @param directory
	 *            the directory for the sources and the classes
	 * @param pathOption
	 *            the javac option that names the path: {@code --module-path} or
	 *            {@code --class-path}
	 * @param sources
	 *            the sources, by their path relative to the source directory
	 * @return the result of the compilation
	 * @throws IOException
	 *             if a source file cannot be written
	 */
	private static Compilation compile(final Path directory, final String pathOption,
		final Map<String, String> sources) throws IOException
	{
		final List<Path> files = new ArrayList<>();
		for (final Map.Entry<String, String> source : sources.entrySet())
		{
			final Path file = directory.resolve("src").resolve(source.getKey());
			Files.createDirectories(file.getParent());
			files.add(Files.writeString(file, source.getValue(), StandardCharsets.UTF_8));
		}
		final String jar = System.getProperty(JAR_PROPERTY);
		assertNotNull(jar, "the system property " + JAR_PROPERTY + " is not set");
		final String runtimeClasspath = System.getProperty(RUNTIME_CLASSPATH_PROPERTY);
		assertNotNull(runtimeClasspath,
			"the system property " + RUNTIME_CLASSPATH_PROPERTY + " is not set");
		final Path releasedJar = Files.copy(Path.of(jar),
			Files.createDirectories(directory.resolve("lib")).resolve(RELEASED_JAR_NAME));
		final String path = releasedJar + File.pathSeparator + runtimeClasspath;
		final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		assertNotNull(compiler, "no system Java compiler");
		final DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
		try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics,
			Locale.ENGLISH, StandardCharsets.UTF_8))
		{
			final Boolean compiled = compiler.getTask(null, fileManager, diagnostics,
				List.of("-proc:none", pathOption, path, "-d",
					directory.resolve("classes").toString()),
				null, fileManager.getJavaFileObjects(files.toArray(Path[]::new))).call();
			final String messages = diagnostics.getDiagnostics().stream().map(
				diagnostic -> diagnostic.getCode() + ": " + diagnostic.getMessage(Locale.ENGLISH))
				.collect(Collectors.joining("; "));
			return new Compilation(Boolean.TRUE.equals(compiled), messages);
		}
	}

}
