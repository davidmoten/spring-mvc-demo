package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.reactivex.Observable;

@RestController
public class GreetingRestController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/rest")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return create(name);
    }

    private Greeting create(String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping(value = "/rest2", method = RequestMethod.GET)
    public Observable<Greeting> greeting2(
            @RequestParam(value = "name", defaultValue = "World") String name) {
        return Observable.just(create(name), create(name));
    }
}