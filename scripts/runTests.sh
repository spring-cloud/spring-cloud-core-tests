#!/bin/bash

set -o errexit

REPO_URL="${REPO_URL:-https://github.com/spring-cloud-samples/tests.git}"
REPO_BRANCH="${REPO_BRANCH:-master}"
if [[ -d zuul-config-discovery ]]; then
  REPO_LOCAL="${REPO_LOCAL:-.}"
else 
  REPO_LOCAL="${REPO_LOCAL:-tests}"
fi
WAIT_TIME="${WAIT_TIME:-5}"
RETRIES="${RETRIES:-48}"
DEFAULT_VERSION="${DEFAULT_VERSION:-1.0.0.BUILD-SNAPSHOT}"

# Parse the script arguments
while getopts ":r" opt; do
    case $opt in
        r)
            RESET=0
            ;;
        \?)
            echo "Invalid option: -$OPTARG" >&2
            exit 1
            ;;
        :)
            echo "Option -$OPTARG requires an argument." >&2
            exit 1
            ;;
    esac
done

# Clone or update the repository
if [[ ! -e "${REPO_LOCAL}/.git" ]]; then
    git clone "${REPO_URL}" "${REPO_LOCAL}"
    cd "${REPO_LOCAL}"
else
    cd "${REPO_LOCAL}"
    if [[ $RESET ]]; then
        git reset --hard
        git pull "${REPO_URL}" "${REPO_BRANCH}"
    fi
fi

./mvnw --fail-at-end clean package
