#!/usr/bin/env bash

set -e

regex="^([a-z_]+[/a-z_]*)/src.*"
modules=()

for file in $(git diff origin/"$BITRISEIO_GIT_BRANCH_DEST"...origin/"$BITRISE_GIT_BRANCH" --name-only --diff-filter=d)
do 
	if [[ $file =~ $regex ]]
	then 
		name="${BASH_REMATCH[1]}"
		modules+=( "${name//\//:}" )
	fi
done

count=0
command="./gradlew clean"
for module in $(printf "%s\n" "${modules[@]}" | sort -u)
do
	command+=" :$module:ktlint :$module:coverageReport"
	count+=1
done

# in case no module was found, run all the project
if [[ $count = 0 ]]
then
  command+=" ktlint coverageReport"
fi

set -x
eval "$command --parallel"
