# Deterministic Unit Tests for Multi-Threaded Java

# Example

The following example shows how to test multi-threaded, concurrent Java with vmlens:

```Java
import com.vmlens.api.AllInterleavings;
public class TestNonVolatileField {
    private int j = 0;
    @Test
    public void testUpdate() throws InterruptedException {
        try(AllInterleavings allInterleavings = new AllInterleavings("testNonVolatileField")) {
            while (allInterleavings.hasNext()) {
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        j++;
                    }
                };
                first.start();
                j++;
                first.join();
            }
        }
    }
}
```
VMLens detects the data race and generates the following report:

<img style=" width: 100%; height: auto; margin-right : auto; margin-left : auto; min-width : 300px; border-radius: 10px; border: 2px solid #4198ff;" src="https://vmlens.com/images/dataRaceReport.svg">

See [test-vmlens-maven-plugin](https://github.com/vmlens/vmlens/tree/master/test-vmlens-maven-plugin/src/test/java/com/vmlens/test/maven/plugin) for more examples.

# Installation

## Maven

To use vmlens with Maven, configure a plugin tag to tell Maven that the vmlens plugin should be executed at the test phase. And include the jar com.vmlens.api as test dependency.

```XML
<project>
<!-- to include the class AllInterleavings into the test class path.  -->	
<dependency>
  <groupId>com.vmlens</groupId>
  <artifactId>api</artifactId>
  <version>1.2.22</version>
  <scope>test</scope>
</dependency>	
	
<build>
  <plugins>
<!-- to run the vmlens maven plugin during the maven test phase  -->	 
    <plugin>
        <groupId>com.vmlens</groupId>
        <artifactId>vmlens-maven-plugin</artifactId>
        <version>1.2.22</version>
        <executions>
            <execution>
                <id>test</id>
                <goals>
                    <goal>test</goal>
                </goals>
            </execution>
        </executions>
	</plugin>
     ...
    </plugins>
</build>
      ...
</project>
```

See [pom.xml](https://github.com/vmlens/vmlens/blob/master/test-vmlens-maven-plugin/pom.xml) for an example.

## Gradle
To use VMLens with Gradle add the java agent as vm parameter for the test and process the events after the test run to create the VMLens Report:

```Java
import com.vmlens.gradle.VMLens
plugins {
  ...
}
repositories {
    mavenCentral()
}
dependencies {
    testImplementation("com.vmlens:api:1.2.22")
    ...
}
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.vmlens:standalone:1.2.22")
    }
}
tasks.register("vmlensReport") {
    doLast {
        VMLens().process(layout.buildDirectory.getAsFile().get());
    }
}
tasks.test {
    doFirst{
        jvmArgs(VMLens().setup(layout.buildDirectory.getAsFile().get()))
    }
    // VMLens currently does not work with jacoco
    jvmArgumentProviders.removeIf { it::class.java.simpleName == "JacocoAgent" }
    useJUnitPlatform()
    finalizedBy("vmlensReport")
}
```

See [build.gradle.kts](https://github.com/vmlens/vmlens-examples/blob/master/build.gradle.kts) for an example.

## Standalone

To use VMLens as a standalone tool:

1. Include com.vmlens.api from the [Maven Repository](https://repo1.maven.org/maven2/com/vmlens/api/1.2.22/) as a test jar in your project.
1. Download the jar standalone-1.2.22.jar from the [Maven Repository](https://repo1.maven.org/maven2/com/vmlens/standalone/1.2.22/) 
1. Run java -jar standalone-1.2.22.jar install. This creates the agent directory and prints the vm parameter to System.out
1. Add this vm parameter when you run your test
2. Run java -jar standalone-1.2.22.jar report. This checks for data races and creates the report


# Documentation

Read the documentation [here](https://vmlens.com/docs/).

# Questions? Problems? Suggestions?

Contact me at [thomas.krieger@vmlens.com](mailto:thomas.krieger@vmlens.com)


# License

[Apache License 2.0](https://github.com/vmlens/vmlens/blob/master/LICENSE)




