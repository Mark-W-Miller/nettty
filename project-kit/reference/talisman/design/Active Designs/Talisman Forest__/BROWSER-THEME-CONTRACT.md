# Talisman Forest browser theme v1

TF-04 publishes a static, opt-in shared theme. Forest owns colors and typography. Consumers retain
composition, semantic state, commands, focus movement, enablement, subscriptions and persistence.
The bundle contains no JavaScript, lifecycle worker, storage, service call or provider route.

## Public consumption

```html
<link rel="stylesheet" href="/assets/talisman-forest/v1/forest.css">
<section data-talisman-theme="forest">
  <h2 class="tf-heading">Places</h2>
  <p class="tf-narrative">The road follows the river.</p>
</section>
```

Place the data attribute on `html` for a whole page or on an owning container for an embedded surface.
The stylesheet scopes tokens and typography beneath that opt-in. It has no global reset, `:root`, body,
Shelf, Box, button, or form-layout rules. Font-face declarations are the only global registrations.
Existing page-local typography rules can override inheritance; consumers explicitly bind their owning
controls to `var(--tf-font-ui)` where needed. Never import specimen.css into a product screen.

- Public stylesheet: `/assets/talisman-forest/v1/forest.css`.
- Specimen: `/forest/specimen/`.
- Font files: `/assets/talisman-forest/v1/fonts/` with exact names in font-provenance.json.
- Licences: `/assets/talisman-forest/v1/licenses/` with each original OFL text retained.
- Provenance: `/assets/talisman-forest/v1/font-provenance.json`.
- Source bundle: `src/main/resources/app/talisman-forest/v1/`.

These are registered source routes. Landing does not prove the running Application Server binary has
loaded the new font media allowlist or CSP; its owner controls runtime adoption without a Forest launch.
The asset host retains its existing no-store policy. Offline-capable means no public internet/font CDN
is required when these bundled assets are available; it does not promise a disconnected PWA cache.

## Semantic color names

All names below carry the `--tf-` prefix. Base and accent values mirror the landed Java Forest palette;
the focused test extracts that palette directly and checks equality. Change both representations and
proof together. Consumers use tokens rather than copying RGB literals.

| Concern | Names |
| --- | --- |
| Surfaces | `surface`, `surface-raised`, `surface-recessed`, `input` |
| Text | `text`, `text-muted`, `text-disabled` |
| Controls | `control`, `control-hover`, `control-border` |
| Borders/focus | `border`, `border-dark`, `highlight`, `focus` |
| Selection | `selection`, `selection-text`, `selection-accent` |
| Ownership | `owner`, `shelf`, `box`, `related-group` |
| Status | `active`, `waiting`, `complete`, `error`, `warning`, `success` |
| Role classes | `role-primary`, `role-secondary`, `role-tertiary` |

Use `text` on hover surfaces; reserve `text-muted` for the base/raised/recessed and ordinary control
surfaces checked by the contrast proof. `text-disabled` is only for unavailable controls, never active
operational copy. Bright accents are labeled emphasis/indicators/borders on the base surface, not
unvalidated full-fill button backgrounds. Labels, shapes, borders and accessible state carry meaning
when color is unavailable. Browser forced-color mode retains native adaptation and a visible focus ring.

## Typography and offline assets

| Token | Role | Shipped face / weight |
| --- | --- | --- |
| `--tf-font-display` | Sparse Gothic identity | Bundled Morris Roman Black; UnifrakturMaguntia 400 fallback |
| `--tf-font-heading` | Serif headings | Inknut Antiqua 700 |
| `--tf-font-narrative` | Narrative, document, editor copy | Forest Junicode 400, derived from Junicode |
| `--tf-font-ui` | Controls, navigation, status, forms, tables | Inter 400 / 600 / 700 |
| `--tf-font-mono` | Code and exact identifiers | System monospace |

Optional classes `tf-display`, `tf-heading`, `tf-narrative`, and `tf-ui` select these roles. Do not put
decorative faces on dense operational controls. Narrative currently ships regular only; additional
italic/bold source faces require a coherent font-bundle update, not an accidental remote dependency.

Fontsource 5.3.0 supplies unchanged Inter, Inknut Antiqua and UnifrakturMaguntia WOFF2 distributions.
Junicode comes from its official repository at the exact revision recorded in font-provenance.json.
FontTools 4.60.2 pins the Junicode variable axes to their defaults (weight 400), subsets Latin and
punctuation, and renames the derived face Forest Junicode. The original Junicode OFL is retained.
All eleven WOFF2 files total 308,664 bytes; exact bytes and SHA-256 values are part of the checked manifest.
Mark explicitly selected Morris Roman Black and authorized web and Java packaging on 2026-09-08.
The original installed OTF is retained byte-for-byte for Java; its WOFF2 is a container conversion,
without glyph or embedded-name changes. Original copyright, trademark and informal licence notices
are retained in morris-roman-black-NOTICES.txt and the font metadata. Morris is not represented as OFL.
The browser family alias Morris Roman Black Web uses only the packaged URL, so local font installation
is unnecessary. UnifrakturMaguntia remains the shipped OFL Gothic fallback.

Inter and Inknut ship Latin plus Latin-extended subsets; UnifrakturMaguntia ships its supplied Latin
subset. Junicode covers U+0020–024F, U+2000–206F, euro and dotted circle where present in the source.
Other scripts fall through to local system fonts; this is not a promise of full multilingual coverage.
Each web face uses `font-display: swap` and relative same-origin WOFF2 URLs. Metric-adjusted local
Arial/Times aliases use x-height size matching, ascent/descent overrides and zero line gap; where
those faces or descriptors are unavailable, readable generic/system fallbacks remain in the stack.
Font loading never locks text visibility or control width. Use `rem`, content-driven height, wrapping
and bounded input widths; retain browser zoom and test 200% text enlargement.

## Host boundary and focused proof

The existing ApplicationServerManifestStore owns path admission, immutable bytes and content digests.
TF-04 adds only font/woff2 and licence text media types, one static manifest page, and `font-src 'self'`
to the existing HTML content policy. It creates no capability, authentication bypass or service owner.
Consumer pages served under a different host must explicitly allow their same-origin fonts in CSP.

Focused commands (Node with Playwright available for the second command, local Chrome installed):

```text
node --test src/test/js/talisman-forest-browser.test.mjs
node --test src/test/js/talisman-forest-browser-visual.test.mjs
JAVA_HOME=$(/usr/libexec/java_home -v 21) ./gradlew --offline --no-daemon :test \
  --tests '*ApplicationServerForestAssetsTest' \
  --tests '*productionManifestRegistersExactOwnerBundlesAndSafeMigrationContract'
JAVA_HOME=$(/usr/libexec/java_home -v 21) ./gradlew --offline --no-daemon validateDevelopmentArchitectureAtlas
```

The browser fixture intercepts only specimen assets and rejects external requests. It checks desktop,
iPad-sized and narrow widths, 200% text, missing fonts, forced colors, keyboard focus and editable
sample input; it is not physical iPad Safari acceptance. The Java fixture binds an ephemeral loopback
port, proves exact static bytes/MIME/CSP, and fails on any feature-gateway invocation. It never launches
Talisman or the managed Application Server process.

## Source licences

- Inter: https://github.com/rsms/inter/blob/master/LICENSE.txt
- Inknut Antiqua: https://github.com/clauseggers/Inknut-Antiqua
- Junicode: https://github.com/psb1558/Junicode-font/blob/master/OFL.txt
- UnifrakturMaguntia: https://github.com/google/fonts/blob/main/ofl/unifrakturmaguntia/OFL.txt
- Self-hosted distribution: https://fontsource.org/docs/getting-started/introduction

## Java display role and shared design direction

TalismanForestTypography loads the original classpath OTF and supplies Talisman.font.display.
The Forest installer exposes this display role and the Java gallery uses it for its major title.
Morris is reserved for Gothic identity; it does not replace button or dense operational text.
The shared target is Morris Roman Black for display, Inknut Antiqua for headings, Junicode for narrative,
and Inter for controls. Full Java adoption of the other three roles and matching control composition
remain later work; this body does not claim whole-application web/Java visual parity.
Automated proof is deferred for this visual design pass. Browser font-use assertions and the static-host
font count are updated for the next authorized focused verification, without claiming they have run.

## Gallery typography-only adoption

Load /assets/talisman-forest/v1/forest.css and place data-talisman-typography="forest" on the
Gallery container. Apply tf-display only to the Gallery heading, tf-heading to creature names/titles,
and tf-ui to controls. This opt-in sets font roles and chosen face weights only; it supplies no colors,
backgrounds, sizes, line heights, layout, borders, focus styling or component behavior. Existing page
CSS remains the composition owner. Use the shared --tf-font-* variables in owner-scoped rules where
existing selectors are more specific. Do not add data-talisman-theme="forest" for this limited adoption.
The original broad theme opt-in remains unchanged. LAN deployment belongs to Tassy; Gallery changes
belong to Fergus. Source publication alone does not establish deployed font bytes or visible adoption.
