#!/bin/bash

set -o errexit
set -o errtrace
set -o nounset
set -o pipefail

function bootVersion() {
    local minor="${1}"
    # FOR LATEST
    #BOOT_VERSION="\$( curl https://repo.spring.io/libs-snapshot-local/org/springframework/boot/spring-boot-starter/maven-metadata.xml | sed -ne '/<latest>/s#\s*<[^>]*>\s*##gp')"
    curl --silent https://repo.spring.io/libs-snapshot-local/org/springframework/boot/spring-boot-starter/maven-metadata.xml | grep "<version>${minor}." | tail -1 | sed -ne '/<version>/s#\s*<[^>]*>\s*##gp' | xargs
}

function cloudVersion() {
    local minor="${1}"
    #BOOT_VERSION="\$( curl https://repo.spring.io/libs-snapshot-local/org/springframework/cloud/spring-cloud-dependencies/maven-metadata.xml | sed -ne '/<latest>/s#\s*<[^>]*>\s*##gp')"
    curl --silent https://repo.spring.io/libs-snapshot-local/org/springframework/cloud/spring-cloud-dependencies/maven-metadata.xml | grep "<version>${minor}." | tail -1 | sed -ne '/<version>/s#\s*<[^>]*>\s*##gp' | xargs
}

CURRENT_BOOT_VERSION="${CURRENT_BOOT_VERSION:-}"
CURRENT_CLOUD_VERSION="${CURRENT_CLOUD_VERSION:-}"
[[ -z "${CURRENT_BOOT_VERSION}" ]] && CURRENT_BOOT_VERSION="$( bootVersion "2.2" )"
[[ -z "${CURRENT_CLOUD_VERSION}" ]] && CURRENT_CLOUD_VERSION="$( cloudVersion "Hoxton" )"

function build() {
    echo "Updating parent boot to [${CURRENT_BOOT_VERSION}]"
    ./mvnw versions:update-parent "-DparentVersion=(,${CURRENT_BOOT_VERSION}]" -DgenerateBackupPoms=false -DallowSnapshots=true
    echo "Running the build with cloud version [${CURRENT_CLOUD_VERSION}]"
    ./mvnw -s .settings.xml clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Dspring-cloud.version="${CURRENT_CLOUD_VERSION}" -U -P sonar -nsu --batch-mode -Dmaven.test.redirectTestOutputToFile=true -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn --fail-at-end
}

cat <<EOF
VERSIONS:

CURRENT_BOOT_VERSION="${CURRENT_BOOT_VERSION}"
CURRENT_CLOUD_VERSION="${CURRENT_CLOUD_VERSION}"

EOF

build
