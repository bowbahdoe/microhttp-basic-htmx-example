import dev.mccue.microhttp.handler.DelegatingHandler;
import dev.mccue.microhttp.html.HtmlResponse;
import handlers.DecrementHandler;
import handlers.IncrementHandler;
import handlers.IndexHandler;
import handlers.SetZeroHandler;
import org.microhttp.EventLoop;
import org.microhttp.Options;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static dev.mccue.html.Html.HTML;

public class Main {
    public static void main(String[] args) throws Exception {
        var error = new HtmlResponse(500, HTML."Internal Error");
        var notFound = new HtmlResponse(404, HTML."Not Found");

        var count = new AtomicInteger();

        var handlers = List.of(
                new IndexHandler(count),
                new IncrementHandler(count),
                new DecrementHandler(count),
                new SetZeroHandler(count)
        );

        var rootHandler = new DelegatingHandler(handlers, notFound);

        var eventLoop = new EventLoop(
                Options.builder()
                        .withPort(6655)
                        .build(),
                (request, consumer) -> {
                    try {
                        consumer.accept(
                                rootHandler.handle(request)
                                        .intoResponse()
                        );
                    } catch (Exception e) {
                        e.printStackTrace();
                        consumer.accept(error.intoResponse());
                    }
                }
        );
        eventLoop.start();
        eventLoop.join();
    }
}
