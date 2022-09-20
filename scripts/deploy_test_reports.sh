#!/usr/bin/env bash
# fail if any commands fails
set -e

regex="\/([/a-z_]*)/build.*"

for d in $(find . -type d | grep "build/test-results/test[a-zA-Z]*$")

do
    if [[ $d =~ $regex ]]
    then
        name="${BASH_REMATCH[1]}"
        test_run_dir="$BITRISE_TEST_RESULT_DIR/${name//\//_}"

        rm -rf "$test_run_dir"
        mkdir -p "$test_run_dir"

        cp -a "$d/." $test_run_dir

        rm -rf "$test_run_dir/binary"

        echo $test_run_dir
        echo "{ \"test-name\" : \"${name//\// }\" }" >> "$test_run_dir/test-info.json"
    fi
done
