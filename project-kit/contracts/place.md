# Standard Place

A Place requires an actual numeric height map and a texture map describing the same physical extent.
Declare units, axes/handedness, origin, bounds and alignment. Additional layers refine this foundation.
An intentionally flat Place has explicit numeric flat height data; missing height or texture is a content
gap. Never fabricate missing samples, substitute an unrelated palette or present a preview as source.

Record height encoding, sample dimensions/spacing, vertical scale and offset, no-data semantics,
interpolation and edge policy. Record texture dimensions, orientation, mapping/repeat scale and media
identity/revision/digest. Physical size is independent of raster resolution. Check corner/interior
heights and texture landmarks against the same coordinates. Caves/overhangs need separately supported
geometry; a heightmap is single-valued.

## Display and composition

List layers top-first: overlays/masks, texture, height, background. Declare mask targets and blending;
list position alone does not establish composition semantics. Height defines geometry, not a color
overlay. Viewing existing content must not require locking, saving or editing it.

Place is a well-known domain view over Morph content and links: spatial bounds, layers, metadata,
instances and gameplay queries retain their typed authority. A Place can be held/rendered through Morph
composition and a stage Metamorph; it does not require a separate geometry/motion language. Preserve
explicit identity/revision links between the domain projection and content. Physical storage mappings
are implementation choices, not a requirement that Place be a creature/Factory row.

The numeric-height/aligned-texture minimum above applies to the standard terrain-surface representation;
Place-scale content may also contain arbitrary geometry, nested buildings, water and non-heightfield
forms through the common language. Missing adapters remain explicit dependencies, not exceptions that
permit a private lookalike dialect. See [Morph](morph.md) and [Metamorph](metamorph.md).

See [native authoring and admission](native-content.md) and [migration](../docs/MIGRATION-0.1.1.md).

## Future physical world prerequisite

Before physics integration, pin the full world contract: units, axes/handedness, origin/bounds, collision
and navigation surfaces/volumes, gravity/fields, material regions, fixed-step clock and admitted active
region. See [future physics boundary](physics.md). A numeric height/texture pair alone does not establish
that complete contract; navigation/collision/render projections need explicit supported relationships.
