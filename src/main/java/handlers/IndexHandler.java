package handlers;

import components.Counter;
import components.Page;
import dev.mccue.microhttp.handler.RouteHandler;
import dev.mccue.microhttp.html.HtmlResponse;
import org.microhttp.Request;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dev.mccue.html.Html.HTML;

public final class IndexHandler extends RouteHandler {
    private final AtomicInteger count;

    public IndexHandler(AtomicInteger count) {
        super("GET", Pattern.compile("/"));
        this.count = count;
    }

    @Override
    protected HtmlResponse handleRoute(
            Matcher matcher,
            Request request
    ) {
        return new HtmlResponse(new Page(HTML."""
                <div id="counter">
                  \{ new Counter(count.get()) }
                </div>
               <button hx-post="/increment"
                        hx-swap="innerHTML"
                        hx-target="#counter"
                        class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded">
                  Increment
                </button>
                <button hx-post="/decrement"
                        hx-swap="innerHTML"
                        hx-target="#counter"
                        class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded">
                  Decrement
                </button>
                <button hx-post="/set_zero"
                        hx-swap="innerHTML"
                        hx-target="#counter"
                        class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded">
                  Set to zero
                </button>
                """));
    }
}
