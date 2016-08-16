## Integration test projects for Spring Cloud
[![CircleCI](https://circleci.com/gh/spring-cloud-samples/tests.svg?style=svg)](https://circleci.com/gh/spring-cloud-samples/tests)

Not necessarily full demo or sample apps, but each one implementing a single scenario or small set of scenarios. Very useful for testing classpath combinations (e.g. making sure that optional depednencies really are optional).

### Running

`./mvnw --fail-at-end --update-snapshots clean package`
