package com.vmlens.maven.plugin;

import static org.apache.maven.plugin.surefire.SurefireHelper.reportExecution;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.surefire.AbstractSurefireMojo;
import org.apache.maven.plugin.surefire.SurefireReportParameters;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.surefire.suite.RunResult;

import com.anarsoft.config.DefaultValues;
import com.anarsoft.config.MavenMojo;
import com.vmlens.api.callback.APICallback;
import com.vmlens.api.callback.ApiCallbackParallize;
import com.vmlens.api.callback.ApiCallbackRegressionTest;
import com.vmlens.api.callback.ExtractAgentAndCheckLicence;
import com.vmlens.api.callback.IssuesFoundException;
import com.vmlens.api.internal.reports.ReportFacade$;

public abstract class AbstractMavenPlugin extends AbstractSurefireMojo implements SurefireReportParameters {

	private APICallback callback = null;

	protected boolean hasExecutedBefore() {
		return false;
	}

	public void execute() throws MojoExecutionException, MojoFailureException {

		if (this.skipTests) {
			return;
		}

		try {

			try {

				agentDirectory.mkdirs();
				getReportsDirectory().mkdirs();

				FileUtils.deleteQuietly(new File(agentDirectory.getAbsoluteFile() + "/vmlens"));

				String testDirectory = null;

				for (Object obj : this.getProject().getTestResources()) {
					Resource r = (Resource) obj;
					testDirectory = r.getDirectory();

					if (regresssionTestFolder != null) {
						testDirectory = testDirectory + "/" + regresssionTestFolder;
					}

				}

				MavenMojo mavenMojo = new MavenMojoImpl(this, testDirectory);

				String source = (new ExtractAgentAndCheckLicence())
						.extractAndCheckAndSetPropertiesInRunProperties(agentDirectory, mavenMojo);

				processAll(source, mavenMojo);

			} catch (IssuesFoundException issuesFoundException) {
				if (isTestFailureIgnore()) {
					getLog().error(issuesFoundException.getMessage());
				} else {
					throw new MojoFailureException(issuesFoundException.getMessage());
				}
			}

		} catch (Throwable e) {
			getLog().error(e);

			throw e;

		}

	}

	private boolean noTestRun(String source, MavenMojo mavenMojo) {
		File eventDir = new File(source);

		if (eventDir.exists()) {
			if (eventDir.listFiles().length > 0) {
				return false;
			}

		}

		ReportFacade$.MODULE$.saveNoTestToRunReport2File(mavenMojo.getReportDir());

		return true;

	}

	private void processAll(String source, MavenMojo mavenMojo)
			throws MojoFailureException, MojoExecutionException, IssuesFoundException {


		if (callback == null) {

			if (callbackType == 1) {
				callback = new ApiCallbackParallize();
			} else if (callbackType == 2) {
				callback = new ApiCallbackRegressionTest(true, false);
			} else if (callbackType == 3) {
				callback = new ApiCallbackRegressionTest(false, false);
			} else if (callbackType == 4) {
				callback = new ApiCallbackRegressionTest(true, true);
			}

			if (callback != null) {
				callback.onStartup(source, mavenMojo);

				boolean run = true;

				while (run) {

					try {

						super.execute();

						if (noTestRun(source, mavenMojo)) {
							return;
						}
						
						if(!deadlockAndDataRaceDetection) {
							
							ReportFacade$.MODULE$.saveDeadlockAndDataRaceDetectionFalse(mavenMojo.getReportDir());
							
							return;
						}
						

						run = callback.prozess(source, mavenMojo, new MavenProgressMonitor(getLog()));

					} catch (MojoFailureException exp) {
						callback.prozessTestError(source, mavenMojo, new MavenProgressMonitor(getLog()));

						run = false;

						if (callback.ignoreTestErrors()) {

							getLog().info(exp);
						} else {
							throw exp;
						}

					}

				}

			}
		}
	}

	protected abstract Mode getMode();

	// For Test

	@Parameter(defaultValue = "1")
	int callbackType;

	@Parameter(defaultValue = "")
	private String regresssionTestFolder;

	@Parameter
	boolean agentLog;

	@Parameter
	boolean agentLogPerformance;

	@Parameter
	boolean disableAgentExceptionLog;

	@Parameter
	boolean disableTimeoutCheck;

	@Parameter
	boolean disableTimeoutWarningCheck;

	// For Prod

	protected abstract DefaultValues defaults();

	@Parameter
	List<String> trace = defaults().getOnlyTraceIn();

	@Parameter
	List<String> doNotTrace = defaults().getDoNotTraceIn();

	@Parameter
	List<String> excludeFromStackTrace = defaults().getExcludeFromTrace();

	@Parameter
	List<String> suppress;

	@Parameter(defaultValue = "true")
	boolean deadlockAndDataRaceDetection;

	@Parameter(defaultValue = "${project.build.directory}/vmlens-agent")
	private File agentDirectory;

	protected int getEffectiveForkCount() {
		return 1;
	}

	public String getArgLine() {

		// System.out.println( "super.getArgLine() " + super.getArgLine() );

		String additionalArgs = "";

		if (super.getArgLine() != null) {
			additionalArgs = super.getArgLine();
		}

		return "-javaagent:\"" + agentDirectory.getAbsolutePath() + "/agent.jar\" " + additionalArgs;

		// return
		// "-javaagent:/home/thomas/git-repo/workspace/com.anarsoft.plugin.sync.bug/agent_lib/agent.jar=/home/thomas/git-repo/workspace/com.anarsoft.plugin.sync.bug.test/agent_properties/run.properties";
	}

	public boolean isReuseForks() {
		return true;
	}

	/// Copied

	/**
	 * The directory containing generated classes of the project being tested. This
	 * will be included after the test classes in the test classpath.
	 */
	@Parameter(defaultValue = "${project.build.outputDirectory}")
	private File classesDirectory;

	/**
	 * Set this to "true" to ignore a failure during testing. Its use is NOT
	 * RECOMMENDED, but quite convenient on occasion.
	 */
	@Parameter(property = "maven.test.failure.ignore", defaultValue = "false")
	private boolean testFailureIgnore;

	/**
	 * Base directory where all reports are written to.
	 */

	@SuppressWarnings("checkstyle:linelength")
	/**
	 * Specify this parameter to run individual tests by file name, overriding the
	 * parameter {@code includes} and {@code excludes}. Each pattern you specify
	 * here will be used to create an include pattern formatted like
	 * <code>**{@literal /}${test}.java</code>, so you can just type
	 * {@code -Dtest=MyTest} to run a single test called "foo/MyTest.java". The test
	 * patterns prefixed with a <em>!</em> will be excluded. <br>
	 * This parameter overrides the parameter {@code includes}, {@code excludes},
	 * and the TestNG parameter {@code suiteXmlFiles}. <br>
	 * Since 2.7.3, you can execute a limited number of methods in the test by
	 * adding <i>#myMethod</i> or <i>#my*ethod</i>. For example,
	 * {@code -Dtest=MyTest#myMethod}. This is supported for junit 4.x and
	 * TestNg.<br>
	 * <br>
	 * Since 2.19 a complex syntax is supported in one parameter (JUnit 4, JUnit
	 * 4.7+, TestNG):
	 * 
	 * <pre>
	 * <code>"-Dtest=???Test, !Unstable*, pkg{@literal /}**{@literal /}Ci*leTest.java, *Test#test*One+testTwo?????, #fast*+slowTest"</code>
	 * </pre>
	 * 
	 * or e.g.
	 * 
	 * <pre>
	 * <code>"-Dtest=Basic*, !%regex[.*.Unstable.*], !%regex[.*.MyTest.class#one.*|two.*], %regex[#fast.*|slow.*]"</code>
	 * </pre>
	 * 
	 * <br>
	 * The Parameterized JUnit runner {@code describes} test methods using an index
	 * in brackets, so the non-regex method pattern would become:
	 * {@code #testMethod[*]}. If using
	 * <code>@Parameters(name="{index}: fib({0})={1}")</code> and selecting the
	 * index e.g. 5 in pattern, the non-regex method pattern would become
	 * {@code #testMethod[5:*]}.
	 */
	@Parameter(property = "test")
	private String test;

	/**
	 * Option to print summary of test suites or just print the test cases that have
	 * errors.
	 */
	@Parameter(property = "surefire.printSummary", defaultValue = "true")
	private boolean printSummary;

	/**
	 * Selects the formatting for the test report to be generated. Can be set as
	 * "brief" or "plain". Only applies to the output format of the output files
	 * (target/surefire-reports/testName.txt)
	 */
	@Parameter(property = "surefire.reportFormat", defaultValue = "brief")
	private String reportFormat;

	/**
	 * Option to generate a file test report or just output the test report to the
	 * console.
	 */
	@Parameter(property = "surefire.useFile", defaultValue = "true")
	private boolean useFile;

	/**
	 * Set this to "true" to cause a failure if the none of the tests specified in
	 * -Dtest=... are run. Defaults to "true".
	 *
	 * @since 2.12
	 */
	@Parameter(property = "surefire.failIfNoSpecifiedTests")
	private Boolean failIfNoSpecifiedTests;

	/**
	 * Attach a debugger to the forked JVM. If set to "true", the process will
	 * suspend and wait for a debugger to attach on port 5005. If set to some other
	 * string, that string will be appended to the argLine, allowing you to
	 * configure arbitrary debuggability options (without overwriting the other
	 * options specified through the {@code argLine} parameter).
	 *
	 * @since 2.4
	 */
	@Parameter(property = "maven.surefire.debug")
	private String debugForkedProcess;

	/**
	 * Kill the forked test process after a certain number of seconds. If set to 0,
	 * wait forever for the process, never timing out.
	 *
	 * @since 2.4
	 */
	@Parameter(property = "surefire.timeout")
	private int forkedProcessTimeoutInSeconds;

	/**
	 * Forked process is normally terminated without any significant delay after
	 * given tests have completed. If the particular tests started non-daemon
	 * Thread(s), the process hangs instead of been properly terminated by
	 * {@code System.exit()}. Use this parameter in order to determine the timeout
	 * of terminating the process. <a href=
	 * "http://maven.apache.org/surefire/maven-surefire-plugin/examples/shutdown.html">see
	 * the documentation:
	 * http://maven.apache.org/surefire/maven-surefire-plugin/examples/shutdown.html</a>
	 * Turns to default fallback value of 30 seconds if negative integer.
	 *
	 * @since 2.20
	 */
	@Parameter(property = "surefire.exitTimeout", defaultValue = "30")
	private int forkedProcessExitTimeoutInSeconds;

	/**
	 * Stop executing queued parallel JUnit tests after a certain number of seconds.
	 * <br>
	 * Example values: "3.5", "4"<br>
	 * <br>
	 * If set to 0, wait forever, never timing out. Makes sense with specified
	 * {@code parallel} different from "none".
	 *
	 * @since 2.16
	 */
	@Parameter(property = "surefire.parallel.timeout")
	private double parallelTestsTimeoutInSeconds;

	/**
	 * Stop executing queued parallel JUnit tests and {@code interrupt} currently
	 * running tests after a certain number of seconds. <br>
	 * Example values: "3.5", "4"<br>
	 * <br>
	 * If set to 0, wait forever, never timing out. Makes sense with specified
	 * {@code parallel} different from "none".
	 *
	 * @since 2.16
	 */
	@Parameter(property = "surefire.parallel.forcedTimeout")
	private double parallelTestsTimeoutForcedInSeconds;

	@SuppressWarnings("checkstyle:linelength")
	/**
	 * A list of &lt;include&gt; elements specifying the tests (by pattern) that
	 * should be included in testing. When not specified and when the {@code test}
	 * parameter is not specified, the default includes will be
	 * 
	 * <pre>
	 * <code>
	 * {@literal <includes>}
	 *     {@literal <include>}**{@literal /}Test*.java{@literal </include>}
	 *     {@literal <include>}**{@literal /}*Test.java{@literal </include>}
	 *     {@literal <include>}**{@literal /}*Tests.java{@literal </include>}
	 *     {@literal <include>}**{@literal /}*TestCase.java{@literal </include>}
	 * {@literal </includes>}
	 * </code>
	 * </pre>
	 * 
	 * Each include item may also contain a comma-separated sub-list of items, which
	 * will be treated as multiple &nbsp;&lt;include&gt; entries.<br>
	 * Since 2.19 a complex syntax is supported in one parameter (JUnit 4, JUnit
	 * 4.7+, TestNG):
	 * 
	 * <pre>
	 * <code>
	 *
	 * </code>
	 * </pre>
	 * 
	 * {@literal <include>}%regex[.*[Cat|Dog].*], Basic????,
	 * !Unstable*{@literal </include>} {@literal <include>}%regex[.*[Cat|Dog].*],
	 * !%regex[pkg.*Slow.*.class],
	 * pkg{@literal /}**{@literal /}*Fast*.java{@literal </include>} <br>
	 * This parameter is ignored if the TestNG {@code suiteXmlFiles} parameter is
	 * specified.<br>
	 * <br>
	 * <b>Notice that</b> these values are relative to the directory containing
	 * generated test classes of the project being tested. This directory is
	 * declared by the parameter {@code testClassesDirectory} which defaults to the
	 * POM property {@code ${project.build.testOutputDirectory}}, typically
	 * <code>{@literal src/test/java}</code> unless overridden.
	 */
	@Parameter
	private List<String> includes;

	/**
	 * Option to pass dependencies to the system's classloader instead of using an
	 * isolated class loader when forking. Prevents problems with JDKs which
	 * implement the service provider lookup mechanism by using the system's
	 * ClassLoader.
	 *
	 * @since 2.3
	 */
	@Parameter(property = "surefire.useSystemClassLoader", defaultValue = "true")
	private boolean useSystemClassLoader;

	/**
	 * By default, Surefire forks your tests using a manifest-only JAR; set this
	 * parameter to "false" to force it to launch your tests with a plain old Java
	 * classpath. (See the <a href=
	 * "http://maven.apache.org/plugins/maven-surefire-plugin/examples/class-loading.html">
	 * http://maven.apache.org/plugins/maven-surefire-plugin/examples/class-loading.html</a>
	 * for a more detailed explanation of manifest-only JARs and their benefits.)
	 * <br>
	 * Beware, setting this to "false" may cause your tests to fail on Windows if
	 * your classpath is too long.
	 *
	 * @since 2.4.3
	 */
	@Parameter(property = "surefire.useManifestOnlyJar", defaultValue = "true")
	private boolean useManifestOnlyJar;

	/**
	 * (JUnit 4+ providers) The number of times each failing test will be rerun. If
	 * set larger than 0, rerun failing tests immediately after they fail. If a
	 * failing test passes in any of those reruns, it will be marked as pass and
	 * reported as a "flake". However, all the failing attempts will be recorded.
	 */
	@Parameter(property = "surefire.rerunFailingTestsCount", defaultValue = "0")
	private int rerunFailingTestsCount;

	/**
	 * (TestNG) List of &lt;suiteXmlFile&gt; elements specifying TestNG suite xml
	 * file locations. Note that {@code suiteXmlFiles} is incompatible with several
	 * other parameters of this plugin, like {@code includes} and
	 * {@code excludes}.<br>
	 * This parameter is ignored if the {@code test} parameter is specified
	 * (allowing you to run a single test instead of an entire suite).
	 *
	 * @since 2.2
	 */
	@Parameter(property = "surefire.suiteXmlFiles")
	private File[] suiteXmlFiles;

	/**
	 * Defines the order the tests will be run in. Supported values are
	 * {@code alphabetical}, {@code reversealphabetical}, {@code random},
	 * {@code hourly} (alphabetical on even hours, reverse alphabetical on odd
	 * hours), {@code failedfirst}, {@code balanced} and {@code filesystem}. <br>
	 * <br>
	 * Odd/Even for hourly is determined at the time the of scanning the classpath,
	 * meaning it could change during a multi-module build. <br>
	 * <br>
	 * Failed first will run tests that failed on previous run first, as well as new
	 * tests for this run. <br>
	 * <br>
	 * Balanced is only relevant with parallel=classes, and will try to optimize the
	 * run-order of the tests reducing the overall execution time. Initially a
	 * statistics file is created and every next test run will reorder classes. <br>
	 * <br>
	 * Note that the statistics are stored in a file named
	 * <b>.surefire-XXXXXXXXX</b> beside <i>pom.xml</i> and should not be checked
	 * into version control. The "XXXXX" is the SHA1 checksum of the entire surefire
	 * configuration, so different configurations will have different statistics
	 * files, meaning if you change any configuration settings you will re-run once
	 * before new statistics data can be established.
	 *
	 * @since 2.7
	 */
	@Parameter(property = "surefire.runOrder", defaultValue = "filesystem")
	private String runOrder;

	/**
	 * A file containing include patterns. Blank lines, or lines starting with # are
	 * ignored. If {@code includes} are also specified, these patterns are appended.
	 * Example with path, simple and regex includes:
	 * 
	 * <pre>
	 * <code>
	 * *{@literal /}test{@literal /}*
	 * **{@literal /}NotIncludedByDefault.java
	 * %regex[.*Test.*|.*Not.*]
	 * </code>
	 * </pre>
	 */
	@Parameter(property = "surefire.includesFile")
	private File includesFile;

	/**
	 * A file containing exclude patterns. Blank lines, or lines starting with # are
	 * ignored. If {@code excludes} are also specified, these patterns are appended.
	 * Example with path, simple and regex excludes:<br>
	 * 
	 * <pre>
	 * <code>
	 * *{@literal /}test{@literal /}*
	 * **{@literal /}DontRunTest.*
	 * %regex[.*Test.*|.*Not.*]
	 * </code>
	 * </pre>
	 */
	@Parameter(property = "surefire.excludesFile")
	private File excludesFile;

	/**
	 * Set to error/failure count in order to skip remaining tests. Due to race
	 * conditions in parallel/forked execution this may not be fully guaranteed.<br>
	 * Enable with system property {@code -Dsurefire.skipAfterFailureCount=1} or any
	 * number greater than zero. Defaults to "0".<br>
	 * See the prerequisites and limitations in documentation:<br>
	 * <a href=
	 * "http://maven.apache.org/plugins/maven-surefire-plugin/examples/skip-after-failure.html">
	 * http://maven.apache.org/plugins/maven-surefire-plugin/examples/skip-after-failure.html</a>
	 *
	 * @since 2.19
	 */
	@Parameter(property = "surefire.skipAfterFailureCount", defaultValue = "0")
	private int skipAfterFailureCount;

	/**
	 * After the plugin process is shutdown by sending <i>SIGTERM signal
	 * (CTRL+C)</i>, <i>SHUTDOWN command</i> is received by every forked JVM. <br>
	 * By default ({@code shutdown=testset}) forked JVM would not continue with new
	 * test which means that the current test may still continue to run. <br>
	 * The parameter can be configured with other two values {@code exit} and
	 * {@code kill}. <br>
	 * Using {@code exit} forked JVM executes {@code System.exit(1)} after the
	 * plugin process has received <i>SIGTERM signal</i>. <br>
	 * Using {@code kill} the JVM executes {@code Runtime.halt(1)} and kills itself.
	 *
	 * @since 2.19
	 */
	@Parameter(property = "surefire.shutdown", defaultValue = "testset")
	private String shutdown;

	@Override
	protected int getRerunFailingTestsCount() {
		return rerunFailingTestsCount;
	}

	@Override
	protected void handleSummary(RunResult summary, Exception firstForkException)
			throws MojoExecutionException, MojoFailureException {
		getLog().error(firstForkException);
		reportExecution(this, summary, getConsoleLogger(), firstForkException);
	}

	@Override
	protected boolean isSkipExecution() {
		return isSkip() || isSkipTests() || isSkipExec();
	}

	@Override
	protected String getPluginName() {
		return "vmlens";
	}

	@Override
	protected String[] getDefaultIncludes() {
		return new String[] { "**/Test*.java", "**/*Test.java", "**/*Tests.java", "**/*TestCase.java" };
	}

	@Override
	protected String getReportSchemaLocation() {
		return "https://maven.apache.org/surefire/maven-surefire-plugin/xsd/surefire-test-report.xsd";
	}

	// now for the implementation of the field accessors

	public boolean isSkipTests() {
		return skipTests;
	}

	public void setSkipTests(boolean skipTests) {
		this.skipTests = skipTests;
	}

	public boolean isSkipExec() {
		return skipExec;
	}

	public void setSkipExec(boolean skipExec) {
		this.skipExec = skipExec;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public boolean isTestFailureIgnore() {
		return testFailureIgnore;
	}

	public void setTestFailureIgnore(boolean testFailureIgnore) {
		this.testFailureIgnore = testFailureIgnore;
	}

	public File getBasedir() {
		return basedir;
	}

	public void setBasedir(File basedir) {
		this.basedir = basedir;
	}

	public File getTestClassesDirectory() {
		return testClassesDirectory;
	}

	public void setTestClassesDirectory(File testClassesDirectory) {
		this.testClassesDirectory = testClassesDirectory;
	}

	public File getClassesDirectory() {
		return classesDirectory;
	}

	public void setClassesDirectory(File classesDirectory) {
		this.classesDirectory = classesDirectory;
	}

	public String getTest() {
		return test;
	}

	public boolean isUseSystemClassLoader() {
		return useSystemClassLoader;
	}

	public void setUseSystemClassLoader(boolean useSystemClassLoader) {
		this.useSystemClassLoader = useSystemClassLoader;
	}

	public boolean isUseManifestOnlyJar() {
		return useManifestOnlyJar;
	}

	public void setUseManifestOnlyJar(boolean useManifestOnlyJar) {
		this.useManifestOnlyJar = useManifestOnlyJar;
	}

	public Boolean getFailIfNoSpecifiedTests() {
		return failIfNoSpecifiedTests;
	}

	public void setFailIfNoSpecifiedTests(boolean failIfNoSpecifiedTests) {
		this.failIfNoSpecifiedTests = failIfNoSpecifiedTests;
	}

	public int getSkipAfterFailureCount() {
		return skipAfterFailureCount;
	}

	public String getShutdown() {
		return shutdown;
	}

	public boolean isPrintSummary() {
		return printSummary;
	}

	public void setPrintSummary(boolean printSummary) {
		this.printSummary = printSummary;
	}

	public String getReportFormat() {
		return reportFormat;
	}

	public void setReportFormat(String reportFormat) {
		this.reportFormat = reportFormat;
	}

	public boolean isUseFile() {
		return useFile;
	}

	public void setUseFile(boolean useFile) {
		this.useFile = useFile;
	}

	public String getDebugForkedProcess() {
		return debugForkedProcess;
	}

	public void setDebugForkedProcess(String debugForkedProcess) {
		this.debugForkedProcess = debugForkedProcess;
	}

	public int getForkedProcessTimeoutInSeconds() {
		return forkedProcessTimeoutInSeconds;
	}

	public void setForkedProcessTimeoutInSeconds(int forkedProcessTimeoutInSeconds) {
		this.forkedProcessTimeoutInSeconds = forkedProcessTimeoutInSeconds;
	}

	public int getForkedProcessExitTimeoutInSeconds() {
		return forkedProcessExitTimeoutInSeconds;
	}

	public void setForkedProcessExitTimeoutInSeconds(int forkedProcessExitTimeoutInSeconds) {
		this.forkedProcessExitTimeoutInSeconds = forkedProcessExitTimeoutInSeconds;
	}

	public double getParallelTestsTimeoutInSeconds() {
		return parallelTestsTimeoutInSeconds;
	}

	public void setParallelTestsTimeoutInSeconds(double parallelTestsTimeoutInSeconds) {
		this.parallelTestsTimeoutInSeconds = parallelTestsTimeoutInSeconds;
	}

	public double getParallelTestsTimeoutForcedInSeconds() {
		return parallelTestsTimeoutForcedInSeconds;
	}

	public void setParallelTestsTimeoutForcedInSeconds(double parallelTestsTimeoutForcedInSeconds) {
		this.parallelTestsTimeoutForcedInSeconds = parallelTestsTimeoutForcedInSeconds;
	}

	public void setTest(String test) {
		this.test = test;
	}

	@Override
	public List<String> getIncludes() {
		return includes;
	}

	@Override
	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

	@Override
	public File[] getSuiteXmlFiles() {
		return suiteXmlFiles.clone();
	}

	@Override
	@SuppressWarnings("UnusedDeclaration")
	public void setSuiteXmlFiles(File[] suiteXmlFiles) {
		this.suiteXmlFiles = suiteXmlFiles.clone();
	}

	@Override
	public String getRunOrder() {
		return runOrder;
	}

	@Override
	@SuppressWarnings("UnusedDeclaration")
	public void setRunOrder(String runOrder) {
		this.runOrder = runOrder;
	}

	@Override
	public File getIncludesFile() {
		return includesFile;
	}

	@Override
	public File getExcludesFile() {
		return excludesFile;
	}

	@Override
	protected final List<File> suiteXmlFiles() {
		return hasSuiteXmlFiles() ? Arrays.asList(suiteXmlFiles) : Collections.<File>emptyList();
	}

	@Override
	protected final boolean hasSuiteXmlFiles() {
		return suiteXmlFiles != null && suiteXmlFiles.length != 0;
	}

}