# multi-threaded testing made easy

<img style="margin-right: 30px" src="https://vmlens.com/img/logo.png" >

# vmlens

## It works

Running your tests with multiple threads does not work. Bugs depend on a specific thread interleaving, which is often impossible to reach by simply rerunning your test multiple times. And data races only occur on specific hardware architectures and JVMs.

Therefore vmlens uses the Java Memory Model to execute all possible thread interleavings and to check for data races in the program flow.

## Even for complicated algorithms 

To test the put method of the class ConcurrentHashMap using two threads takes 353 iterations and less than 3 seconds on my Intel i5 3,40 GHz 4 core CPU.

And by using the @atomic annotation we can build complicated algorithms out of smaller pieces.

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
                assertEquals(2, 
                        map.get(1).intValue());
            }
        }
    }
}
```
In your test method, you surround the code you want to test with a while loop iterating
over the class AllInterleavings. 
vmlens executes the block inside the while loop multiple times, for each thread interleaving once.
If the test fails vmlens shows the thread interleaving which led to the failure. If the test succeeds vmlens 
shows the last thread interleaving.  The above example test fails, and vmlens reports the interleaving which led to the failed assertion:



<img style=" width: 100%; height: auto; margin-right : auto; margin-left : auto; min-width : 300px; border-radius: 10px; border: 2px solid #4198ff;" src="https://vmlens.com/img/readModifyWriteWithoutTitle.png">

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


## MAVEN

```XML
<project>
 
<build>
  <plugins>
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
# Documentation

[vmlens](https://vmlens.com) 


# License

[Apache License 2.0](https://github.com/vmlens/vmlens/blob/master/LICENSE)




