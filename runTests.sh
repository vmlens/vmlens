#!/bin/bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# List of directories
JDKS=(
     "11.0.27-amzn"
     "17.0.12-graal"
     "21.0.7.fx-zulu"
     "23.0.2-graal"
     "23.0.1-open"
     "24-open"
     "25-open"
 #   "26.ea.18-open"
)


runTests() {
for jdk in "${JDKS[@]}"; do
    if ! runTest "$jdk"; then
        echo "Aborting due to failure for $jdk"
        exit 1
    fi
done

sdk use java 17.0.12-graal
mvn install -DenableFailIfNoTests=true -DskipTests
}

runTest() {
sdk use java "$1"
if ! mvn clean install; then
  return 1
fi
return 0
}

runTests