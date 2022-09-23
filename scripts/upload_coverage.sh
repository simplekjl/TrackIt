#!/bin/bash

TEST_COVERAGE_TASK="coverageReport"
regex="\./([a-z_]+[/a-z_]*)/build.*"
exclude="interfaces"

for d in $(find . -type d | grep "build/reports/jacoco$" | grep -Ev "$exclude"); do
  if [[ $d =~ $regex ]]; then
    module="${BASH_REMATCH[1]}"

    JACOCO_COVERAGE_FILE="${module}/build/reports/jacoco/${TEST_COVERAGE_TASK}/${TEST_COVERAGE_TASK}.xml"
    CC_COVERAGE_FILE=coverage/codeclimate.${module//\//-}.json

    # Fix file paths inside Jacoco report files
    #sed -i -e "s/app\/choco/${module//\//\\/}\/src\/main\/java\/app\/choco/g" "${JACOCO_COVERAGE_FILE}"

    # Generate Code Climate test reports from Jacoco test coverage reports
    ./cc-test-reporter format-coverage "${JACOCO_COVERAGE_FILE}" -o "${CC_COVERAGE_FILE}" -t jacoco

    reportFileCount=$((reportFileCount+1))
  fi
done

# Unify all Code Climate test coverage reports in one file
./cc-test-reporter sum-coverage coverage/codeclimate.*.json -p ${reportFileCount}

# Print code coverage percentage
sed "24q;d" 'coverage/codeclimate.json'

./cc-test-reporter upload-coverage -r "3b545cb2bd8e831b401989bb763447278bb2218a734b34304142f87fae2d4e78"
