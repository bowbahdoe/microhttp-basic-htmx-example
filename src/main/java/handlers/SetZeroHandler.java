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

public final class SetZeroHandler extends RouteHandler {
    private final AtomicInteger count;

    public SetZeroHandler(AtomicInteger count) {
        super("POST", Pattern.compile("/set_zero"));
        this.count = count;
    }

    @Override
    protected HtmlResponse handleRoute(
            Matcher matcher,
            Request request
    ) {
        count.set(0);
        return new HtmlResponse(new Counter(0));
    }
}

