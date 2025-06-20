#!/bin/bash


source "$HOME/.sdkman/bin/sdkman-init.sh"

# List of directories
JDKS=(
#   "8.0.452-amzn"
    "11.0.27-amzn"
    "17.0.12-graal"
    "21.0.7.fx-zulu"
    "23.0.2-graal"
    "23.0.1-open"
    "24-open"
#   "25.ea.26-open"
#   "26.ea.2-open"
)


runTests() {

for jdk in "${JDKS[@]}"; do
    if ! runTest "$jdk"; then
        echo "Aborting due to failure for $jdk"
        exit 1
    fi
done


}

runTest() {
sdk use java ""$1""
if ! mvn clean install; then
  return 1
fi
return 0
}

# Exit immediately if any command fails
set -e
# Change to target directory
cd test-vmlens-maven-plugin
runTests
# Go back to original directory
cd ..
cd test-cases
runTests
# Go back to original directory
cd ..
