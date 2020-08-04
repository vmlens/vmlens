# vmlens, multi-threaded testing made easy

<img style="margin-right: 30px" src="https://vmlens.com/img/logo.png" >

# Why vmlens?

Running your tests with multiple threads does not work. Bugs depend on a specific thread interleaving, which is often impossible to reach by simply rerunning your test multiple times. And data races only occur on specific hardware architectures and JVMs.

Therefore vmlens uses the Java Memory Model to execute all possible thread interleavings and to check for data races in the program flow. [This blog post](https://vmlens.com/articles/cp/java_memory_model_enables_tests/) describes how vmlens uses the Java Memory Model to test all thread interleavings.

## Easy to use

Using vmlens is easy.

Surround your test with a while loop iterating over all thread interleavings using the class AllInterleaving. 

# Example

The following example shows how to write multi-threaded tests with vmlens:

```Java
import com.vmlens.api.AllInterleavings;
public class TestUpdateWrong {
    public void update(ConcurrentHashMap<Integer, Integer> map) {
        Integer result = map.get(1);
        if (result == null) {
            map.put(1, 1);
        } else {
            map.put(1, result + 1);
        }
    }
    @Test
    public void testUpdate() throws InterruptedException {
        try (AllInterleavings allInterleavings = 
                new AllInterleavings("TestUpdateWrong");) {
	// surround the test with a while loop, iterationg over
	// the class AllInterleavings
            while (allInterleavings.hasNext()) {
                final ConcurrentHashMap<Integer, Integer> map = 
                        new ConcurrentHashMap<Integer, Integer>();
                Thread first = new Thread(() -> {
                    update(map);
                });
                Thread second = new Thread(() -> {
                    update(map);
                });
                first.start();
                second.start();
                first.join();
                second.join();
                assertEquals(2,map.get(1).intValue());
            }
        }
    }
}
```
In your test method, you surround the code you want to test with a while loop iterating
over the class AllInterleavings. 
vmlens executes the block inside the while loop multiple times, for each thread interleaving once.
If the test fails vmlens shows the thread interleaving which led to the failure. If the test succeeds vmlens 
shows the last thread interleaving.  

The above example test fails, and vmlens reports the interleaving which led to the failed assertion:

<img style=" width: 100%; height: auto; margin-right : auto; margin-left : auto; min-width : 300px; border-radius: 10px; border: 2px solid #4198ff;" src="https://vmlens.com/img/readModifyWriteWithoutTitle.png">

In maven, you can see this report by clicking on the link TestUpdateWrong in the file target/interleave/elements.html.
In eclipse you can see the report by clicking on the link TestUpdateWrong in the view under Window -> Show View -> Other... -> vmlens -> vmlens Explorer.

The maven reports are [described here](https://vmlens.com/help/manual/#maven-reports). The eclipse views are [described here](https://vmlens.com/help/manual/#the-report).


## How to run the test
You can run the test in eclipse using the vmlens run short cut for JUnit. Right click on the JUnit class -> Run As -> JUnit Test traced with vmlens.

To run the test with maven put the vmlens interleave plugin in your maven pom.xml [as described here](https://vmlens.com/help/manual/#running-tests-maven).

## Next steps

[Read here more](https://vmlens.com/help/manual/#data_races_deadlocks) about how to use vmlens for testing multi-threaded software.


# Download

## Eclipse

<p>Install from marketplace:</p>
<ol>
	<li>Start Eclipse (version 4.4 or greater)</li>
	<li><a href="http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=825595" class="drag" title="Drag to your running Eclipse* workspace. *Requires Eclipse Marketplace Client">
	<img style="display: inline;" class="img-responsive" src="https://marketplace.eclipse.org/sites/all/themes/solstice/public/images/marketplace/btn-install.png"
	 alt="Drag to your running Eclipse* workspace. *Requires Eclipse Marketplace Client" /></a></li>
</ol>
<p>Or install directly from the update site:</p>
<ol>
	<li>Start Eclipse</li>
	<li>Select Help&gt;Install New Software…</li>
	<li>Work with:<strong> https://vmlens.com/download/site/</strong></li>
</ol>

To use the class AllInterleavings you need to include the jar api-1.0.15.jar into your classpath. You can download this jar from [maven central here](https://search.maven.org/remotecontent?filepath=com/vmlens/api/1.0.15/api-1.0.15.jar).

The usage of the eclipse plugin [is described here.](https://vmlens.com/help/manual/#run-eclipse)


## MAVEN

To use vmlens with maven, configure a plugin tag to tell maven that the vmlens plugin should be executed at the test phase. And include the jar com.vmlens.api as test dependency.

```XML
<project>
<!-- to include the class AllInterleavings into the test class path.  -->	
<dependency>
  <groupId>com.vmlens</groupId>
  <artifactId>api</artifactId>
  <version>1.0.15</version>
  <scope>test</scope>
</dependency>	
	
<build>
  <plugins>
<!-- to run the vmlens maven plugin during the maven test phase  -->	 
    <plugin>
    <groupId>com.vmlens</groupId>
    <artifactId>interleave</artifactId>
    <version>1.0.15</version>
    <executions>
      <execution>
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
The usage of the maven plugin [is described here.](https://vmlens.com/help/manual/#maven-plugin-configuration-1)


# Documentation

* [JavaDoc](https://vmlens.com/apidocs/api/1.0/) 
* [Manual](https://vmlens.com/help/manual/) 

# Support

Post an issue in our [issue tracker](https://github.com/vmlens/vmlens/issues/new) or send a message to our [mailing list](https://groups.google.com/forum/#!forum/vmlens-mailing-list).


# Stay in touch

Follow [@ThomasKrieger](https://twitter.com/-thomaskrieger_) and [join our mailing list](https://groups.google.com/forum/#!forum/vmlens-mailing-list).

# Build

To build vmlens, go to vmlens and run
```Shell
mvn clean install
```

You need JDK 11 or higher and a toolchains.xml containing a tag for JDK 8.
Example toolchains.xml:
```XML
<?xml version="1.0" encoding="UTF8"?>
<toolchains>
<!-- JDK toolchains -->
  <toolchain>
    <type>jdk</type>
      <provides>
        <version>1.8</version>
        <vendor>sun</vendor>
      </provides>
      <configuration>
        <jdkHome>/path/to/jdk/1.8</jdkHome>
      </configuration>
  </toolchain>
</toolchains>
```

# License

[Apache License 2.0](https://github.com/vmlens/vmlens/blob/master/LICENSE)




