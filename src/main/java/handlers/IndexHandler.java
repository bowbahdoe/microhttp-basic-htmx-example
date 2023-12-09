package handlers;

import components.Counter;
import dev.mccue.microhttp.handler.IntoResponse;
import dev.mccue.microhttp.handler.RouteHandler;
import dev.mccue.microhttp.html.HtmlResponse;
import org.jspecify.annotations.Nullable;
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
    protected @Nullable IntoResponse handleRoute(
            Matcher matcher,
            Request request
    ) {
        return new HtmlResponse(HTML."""
                <html>
                  <head>
                    <script src="https://unpkg.com/htmx.org@1.9.9"></script>
                  </head>

                  <body>
                    <div id="counter">
                      \{ new Counter(count.get()) }
                    </div>
                    <button hx-post="/increment" hx-swap="innerHTML" hx-target="#counter"> Increment </button>
                    <button hx-post="/decrement" hx-swap="innerHTML" hx-target="#counter"> Decrement </button>
                    <button hx-post="/set_zero" hx-swap="innerHTML" hx-target="#counter"> Set to zero </button>
                  </body>
                </html>

                """);
    }
}
