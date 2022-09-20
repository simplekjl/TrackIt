#!/bin/bash
# to be used on Mac env
#curl -L https://codeclimate.com/downloads/test-reporter/test-reporter-latest-darwin-amd64 > ./cc-test-reporter

# to be used on Linux env
curl -L https://codeclimate.com/downloads/test-reporter/test-reporter-0.10.1-linux-amd64 > ./cc-test-reporter
chmod +x ./cc-test-reporter

# Notify Code Climate that a test report is going to be sent
./cc-test-reporter before-build
