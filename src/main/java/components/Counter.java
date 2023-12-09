package components;

import dev.mccue.html.Html;
import dev.mccue.html.HtmlEncodable;

import static dev.mccue.html.Html.HTML;

public record Counter(
        int value
) implements HtmlEncodable {
    @Override
    public Html toHtml() {
        return HTML."""
                <h2> The current count is \{value} </h2>
                """;
    }
}
