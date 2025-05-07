# vmlens lets you test multi-threaded, concurrent java

# Example

The following example shows how to test multi-threaded, concurrent java with vmlens:

```Java
import com.vmlens.api.AllInterleaving;
public class TestNonVolatileField {
    private int j = 0;
    @Test
    public void testUpdate() throws InterruptedException {
        try(AllInterleaving allInterleaving = new AllInterleaving("testNonVolatileField")) {
            while (allInterleaving.hasNext()) {
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
vmlens detects the data race and generates the following report:

<img style=" width: 100%; height: auto; margin-right : auto; margin-left : auto; min-width : 300px; border-radius: 10px; border: 2px solid #4198ff;" src="https://vmlens.com/img/dataRaceReportNew.png">

See [test-vmlens-maven-plugin](https://github.com/vmlens/vmlens/tree/master/test-vmlens-maven-plugin/src/test/java/com/vmlens/test/maven/plugin) for more examples.

# Installation

## maven

To use vmlens with maven, configure a plugin tag to tell maven that the vmlens plugin should be executed at the test phase. And include the jar com.vmlens.api as test dependency.

```XML
<project>
<!-- to include the class AllInterleaving into the test class path.  -->	
<dependency>
  <groupId>com.vmlens</groupId>
  <artifactId>api</artifactId>
  <version>1.2.0</version>
  <scope>test</scope>
</dependency>	
	
<build>
  <plugins>
<!-- to run the vmlens maven plugin during the maven test phase  -->	 
    <plugin>
        <groupId>com.vmlens</groupId>
        <artifactId>vmlens-maven-plugin</artifactId>
	<version>1.2.0</version>
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

# Questions? Problems? Suggestions?

Contact me at [thomas.krieger@vmlens.com](mailto:thomas.krieger@vmlens.com)


# License

[Apache License 2.0](https://github.com/vmlens/vmlens/blob/master/LICENSE)




