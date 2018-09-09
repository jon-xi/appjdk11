package com.mycompany.app.literx;

import com.mycompany.app.literx.domain.User;
import com.mycompany.app.literx.repository.BlockingUserRepository;
import com.mycompany.app.literx.repository.ReactiveRepository;
import com.mycompany.app.literx.repository.ReactiveUserRepository;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Part11BlockingToReactiveTest {

    Part11BlockingToReactive workshop = new Part11BlockingToReactive();

//========================================================================================

    @Test
    public void slowPublisherFastSubscriber() {
        BlockingUserRepository repository = new BlockingUserRepository();
        Flux<User> flux = workshop.blockingRepositoryToFlux(repository);
        assertEquals("The call to findAll must be deferred until the flux is subscribed", 0, repository.getCallCount());
        StepVerifier.create(flux)
                .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void fastPublisherSlowSubscriber() {
        ReactiveRepository<User> reactiveRepository = new ReactiveUserRepository();
        BlockingUserRepository blockingRepository = new BlockingUserRepository(new User[]{});
        Mono<Void> complete = workshop.fluxToBlockingRepository(reactiveRepository.findAll(), blockingRepository);
        assertEquals(0, blockingRepository.getCallCount());
        StepVerifier.create(complete)
                .verifyComplete();
        Iterator<User> it = blockingRepository.findAll().iterator();
        assertEquals(User.SKYLER, it.next());
        assertEquals(User.JESSE, it.next());
        assertEquals(User.WALTER, it.next());
        assertEquals(User.SAUL, it.next());
        assertFalse(it.hasNext());
    }

}
