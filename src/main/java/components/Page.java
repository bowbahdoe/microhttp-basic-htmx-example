package components;

import dev.mccue.html.Html;
import dev.mccue.html.HtmlEncodable;

import static dev.mccue.html.Html.HTML;

public record Page(HtmlEncodable body)
    implements HtmlEncodable {
    @Override
    public Html toHtml() {
        return HTML."""
                <html>
                  <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <script src="https://cdn.tailwindcss.com"></script>
                    <script src="https://unpkg.com/htmx.org@1.9.9"></script>
                  </head>
                  <body>
                    \{body}
                  </body>
                </html>
                """;
    }
}
