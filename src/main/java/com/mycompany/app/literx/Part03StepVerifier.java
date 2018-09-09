package com.mycompany.app.literx;

import com.mycompany.app.literx.domain.User;
import java.time.Duration;
import java.util.function.Supplier;
import org.assertj.core.api.Assertions;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Part03StepVerifier {

    // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then completes successfully.
    void expectFooBarComplete(Flux<String> flux) {
        StepVerifier.create(flux)
                .expectNext("foo", "bar")
                .verifyComplete(); // TO BE REMOVED
    }

//========================================================================================

    // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then a RuntimeException error.
    void expectFooBarError(Flux<String> flux) {
        StepVerifier.create(flux)
                .expectNext("foo", "bar")
                .verifyError(RuntimeException.class); // TO BE REMOVED
    }

//========================================================================================

    // TODO Use StepVerifier to check that the flux parameter emits a User with "swhite"username
    // and another one with "jpinkman" then completes successfully.
    void expectSkylerJesseComplete(Flux<User> flux) {
        StepVerifier.create(flux)
                .expectNextMatches(user -> user.getUsername().equals("swhite"))
                .assertNext(user -> Assertions.assertThat(user.getUsername()).isEqualToIgnoringCase("jpinkman"))
                .verifyComplete(); // TO BE REMOVED
    }

//========================================================================================

    // TODO Expect 10 elements then complete and notice how long the test takes.
    void expect10Elements(Flux<Long> flux) {
        StepVerifier.create(flux)
                .expectNextCount(10)
                .verifyComplete(); // TO BE REMOVED
    }

//========================================================================================

    // TODO Expect 3600 elements at intervals of 1 second, and verify quicker than 3600s
    // by manipulating virtual time thanks to StepVerifier#withVirtualTime, notice how long the test takes
    void expect3600Elements(Supplier<Flux<Long>> supplier) {
        StepVerifier.withVirtualTime(supplier)
                .thenAwait(Duration.ofHours(1))
                .expectNextCount(3600)
                .verifyComplete(); // TO BE REMOVED
    }

    private void fail() {
        throw new AssertionError("workshop not implemented");
    }

}
