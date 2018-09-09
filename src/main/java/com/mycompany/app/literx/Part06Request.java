package com.mycompany.app.literx;

import com.mycompany.app.literx.domain.User;
import com.mycompany.app.literx.repository.ReactiveRepository;
import com.mycompany.app.literx.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Part06Request {
    ReactiveRepository<User> repository = new ReactiveUserRepository();

//========================================================================================

    // TODO Create a StepVerifier that initially requests all values and expect 4 values to be received
    StepVerifier requestAllExpectFour(Flux<User> flux) {
        return StepVerifier.create(flux)
                .expectNextCount(4)
                .expectComplete(); // TO BE REMOVED
    }

//========================================================================================

    // TODO Create a StepVerifier that initially requests 1 value and expects User.SKYLER then requests another value and expects User.JESSE.
    StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
        return StepVerifier.create(flux, 1)
                .expectNext(User.SKYLER)
                .thenRequest(1)
                .expectNext(User.JESSE)
                .thenCancel(); // TO BE REMOVED
    }

//========================================================================================

    // TODO Return a Flux with all users stored in the repository that prints automatically logs for all Reactive Streams signals
    Flux<User> fluxWithLog() {
        return repository
                .findAll()
                .log(); // TO BE REMOVED
    }

//========================================================================================

    // TODO Return a Flux with all users stored in the repository that prints "Starring:" on subscribe, "firstname lastname" for all values and "The end!" on complete
    Flux<User> fluxWithDoOnPrintln() {
        return repository
                .findAll()
                .doOnSubscribe(s -> System.out.println("Starring:"))
                .doOnNext(p -> System.out.println(p.getFirstname() + " " + p.getLastname()))
                .doOnComplete(() -> System.out.println("The end!")); // TO BE REMOVED
    }
}