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
                <p class="block text-gray-500 font-bold md:text-left mb-1 md:mb-0 pr-4">
                  The current count is \{value}
                </p>
                """;
    }
}
