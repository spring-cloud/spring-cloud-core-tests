# About
This project is a sample of using just the Hystrix Starter.  For more information see the
[Hystrix documentation](http://cloud.spring.io/spring-cloud-static/spring-cloud.html#_circuit_breaker_hystrix_clients).

# Usage
This simple app contains two endpoints both surrounded by Hystrix circuit breakers.
See [HystrixApplication.java](https://github.com/spring-cloud-samples/tests/blob/master/hystrix/src/main/java/demo/HystrixApplication.java) for
the endpoint definitions.

## /ok
The `/ok` endpoint just returns the String 'OK' and should actually never trip the circuit breaker since there is no
way for it to "fail".

## /fail
The `/fail` endpoint will throw a `RuntimeException` unless the header `X-NO-FAIL` is present.  If you hit the
`/fail` endpoint fast enough, the circuit will open and Hystrix will return the response from the fallback method
defined in [MyService.java](https://github.com/spring-cloud-samples/tests/blob/master/hystrix/src/main/java/demo/MyService.java#L28).
Once the circuit is open you will see the `/fail` endpoint return the string `from the fallback`.  The circuit will remain
open until a successful request is made to the fail endpoint.  This is where the `X-NO-FAIL` header comes in handy.  If
the header is present in the request made to `/fail`, the `RuntimeException` will not be thrown.  If you do this enough
while the circuit is open than the circuit will eventually close.

## Using The Hystrix Dashboard
You can use the [Hystrix Dashboard](https://github.com/spring-cloud-samples/hystrix-dashboard) sample to visualize
the state of the circuit breakers in this application.  To do this, follow the instructions in the README for the
Hystrix Dashboard sample to start the dashboard and then point it at `http://localhost:8080/hystrix.stream`.
