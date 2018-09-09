package com.mycompany.app.literx;

import com.mycompany.app.literx.domain.User;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Part07Errors {

//========================================================================================

    // TODO Return a Mono<User> containing User.SAUL when an error occurs in the input Mono, else do not change the input Mono.
    Mono<User> betterCallSaulForBogusMono(Mono<User> mono) {
        return mono.onErrorResume(e -> Mono.just(User.SAUL)); // TO BE REMOVED
    }

//========================================================================================

    // TODO Return a Flux<User> containing User.SAUL and User.JESSE when an error occurs in the input Flux, else do not change the input Flux.
    Flux<User> betterCallSaulAndJesseForBogusFlux(Flux<User> flux) {
        return flux.onErrorResume(e -> Flux.just(User.SAUL, User.JESSE)); // TO BE REMOVED
    }

//========================================================================================

    // TODO Implement a method that capitalizes each user of the incoming flux using the
    // #capitalizeUser method and emits an error containing a GetOutOfHereException error
    Flux<User> capitalizeMany(Flux<User> flux) {
        return flux.map(user -> {
            try {
                return capitalizeUser(user);
            }
            catch (GetOutOfHereException e) {
                throw Exceptions.propagate(e);
            }
        }); // TO BE REMOVED
    }

    User capitalizeUser(User user) throws GetOutOfHereException {
        if (user.equals(User.SAUL)) {
            throw new GetOutOfHereException();
        }
        return new User(user.getUsername(), user.getFirstname(), user.getLastname());
    }

    protected final class GetOutOfHereException extends Exception {
    }

}
