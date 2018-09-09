package com.mycompany.app.literx;

import com.mycompany.app.literx.domain.User;
import com.mycompany.app.literx.repository.ReactiveRepository;
import com.mycompany.app.literx.repository.ReactiveUserRepository;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Part10ReactiveToBlockingTest {

    Part10ReactiveToBlocking workshop = new Part10ReactiveToBlocking();
    ReactiveRepository<User> repository = new ReactiveUserRepository();

//========================================================================================

    @Test
    public void mono() {
        Mono<User> mono = repository.findFirst();
        User user = workshop.monoToValue(mono);
        assertEquals(User.SKYLER, user);
    }

//========================================================================================

    @Test
    public void flux() {
        Flux<User> flux = repository.findAll();
        Iterable<User> users = workshop.fluxToValues(flux);
        Iterator<User> it = users.iterator();
        assertEquals(User.SKYLER, it.next());
        assertEquals(User.JESSE, it.next());
        assertEquals(User.WALTER, it.next());
        assertEquals(User.SAUL, it.next());
        assertFalse(it.hasNext());
    }
}
