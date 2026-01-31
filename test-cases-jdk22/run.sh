#!/bin/bash

set -e

for i in {1..30}
do
  mvn test
done
