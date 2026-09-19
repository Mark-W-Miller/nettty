# Talisman Object Factory

## Active Metamorph consolidation — 2026-09-13

[METAMORPH-GALLERY.md](METAMORPH-GALLERY.md) is the controlling design for the shared Gallery and
in-place edit experience now assigned to the existing `__Slice` Patcher. Everything is the default catalog
scope, with a Slice only checkbox; selection opens a read-only card and explicit Edit replaces that card
without replacing the Gallery. Slice remains the game/playtest consumer. The Talisman application badge
and Slice editor control target the same native-JavaScript workspace. This supersedes conflicting older
separate Gallery destinations/default-edit directions only; it is a design-only patch, not implemented
behavior, a Java UI port, a store migration or a replacement of the service authorities below.

## End-to-End Product and Implementation Specification

Date: 2026-09-04
Status: Morph Design added; OF-UNIFIED-VIEWS-43 remains the current implementation body
Revision: 9
Owner: permanent **Object Sing & Dance Factory** task

This document consolidates the complete Object Factory direction established in the design conversation
and the read-only Cet and Dwarf War audits. It is intended to be the durable basis for implementation
planning and for dividing the initiative into independently landable bodies of work.

The words **MUST**, **MUST NOT**, **SHOULD**, and **MAY** are normative. Items explicitly marked
**proposal** are recommended initial choices that may be changed before their implementation body begins.
Items marked **proven** describe current source or audited assets. Items marked **future** reserve an
architectural boundary without requiring the feature in the first release.

Repository implementation is gated. It MUST NOT begin from this projectless task, `talisman-main`, or a
generated Codex checkout. See [Implementation gate](#29-implementation-gate).

---

## Navigation

- [Product intent, decisions, goals, and vocabulary](#1-product-statement)
- [Fixed Core Morph objects, copy-then-modify, workshop, and deferred synchronization](MORPH-DESIGN.md)
- [Unified Morph gallery, Observe/Edit detail, and bulk database exchange](UNIFIED-MORPH-WORKBENCH-HANDOFF.md)
- [Architecture, authority, identity, coordinates, and procedural geometry](#6-end-to-end-architecture)
- [Editor, references, UVs, atlas artifacts, and appearance](#11-body-form-editor)
- [Import and conversion](#16-general-import-and-convert-service)
- [Rig, pose authoring, Puppeteer, and behavior](#17-rig-control-point-and-skeleton-contract)
- [Persistence, service routes, ownership, and natural-language boundary](#23-persistence-derivation-and-object-lifecycle)
- [Future signed-language compatibility and nonfunctional requirements](#27-future-body-language-and-signed-language-compatibility)
- [Permanent implementation gate and audited evidence](#29-implementation-gate)
- [Predicted implementation bodies](#31-predicted-implementation-bodies)
- [Verification and overall definition of success](#32-verification-specification)

## 1. Product statement

Object Factory lets an ordinary user construct, texture, rig, pose, teach, save, derive, and reuse simple
three-dimensional objects and characters without requiring Blender or another professional modeling tool.

Its central strategy is deliberately bounded:

> The program owns form and mapping. Artificial intelligence may paint controlled views.

Talisman programmatically constructs known, controlled, low-poly geometry. It owns topology, component
identity, dimensions, seams, UV islands, atlas placement, texel density, material regions, skeletons,
constraints, mapping landmarks, deterministic texture baking, and behavior compatibility. An image
generator may paint controlled orthographic source views or a tightly bounded repair mask, but it does not
invent or revise the mesh, mapping, or UV contract.

This makes one verified body form reusable as many characters. One dragon form can become a frost dragon,
a desert dragon, an ancient scarred dragon, or a friendly green dragon by changing appearance assets while
retaining the same geometry, UV contract, rig, poses, and behaviors.

Import and conversion are also part of the long-term product. They are a general application capability,
not the critical path for the first procedural character. External files enter through a shared checked
pipeline and become independent, versioned native Talisman objects.

## 2. Confirmed product decisions

The following decisions are established direction, not open design questions.

1. **Geometry is program-owned.** Body shaping and low-poly topology are produced by deterministic code and
   explicit user-controlled parameters. AI is not asked to generate arbitrary character meshes.
2. **UV structure is program-owned.** Code chooses seams, islands, packing, density, material regions, and
   semantic surface identities.
3. **AI is only a bounded appearance painter.** The primary route projects six controlled orthographic
   views through a program-owned mapping and bakes them into a fixed Atlas Contract. AI may produce those
   reviewed views or repair a tightly bounded unresolved mask. It cannot change topology, landmarks,
   rigging, UVs, region identities, or dimensions.
4. **One form supports many appearances.** Appearance changes MUST NOT invalidate mechanical motion
   compatibility. They may invalidate presentation-dependent accessibility or language-review evidence;
   those review dependencies are tracked separately.
5. **Users do not manage MTL files.** Talisman generates and maintains them automatically.
6. **`regions.json` is a Talisman sidecar.** It is not part of the OBJ or MTL standards. It communicates
   semantic surface ownership to Talisman, people, and an image-generation request assembler.
7. **OBJ, MTL, and PNG are the first static interchange artifacts.** They are renderable derivatives, not
   the complete future authority for rigs and behavior.
8. **The central armature view is named `Skeleton`.** It displays joints and bones, not polygon edges.
   Existing mesh-edge `Wireframe` remains a separate diagnostic presentation.
9. **Canonical distances are feet.** Stored physical distances use feet at the domain boundary. The editor
   may present feet, feet-and-inches, inches, metric, or another explicit display conversion without changing
   canonical values.
10. **Control points establish local spaces.** Every Control Point is `(0,0,0)` for its own parent-relative
    space. Any number of geometric shapes and child spaces may attach beneath it. Translation, rotation, or
    scale of that space affects every attached shape and descendant through ordinary transform composition.
11. **Motion constraints are declared, not universal.** A conventional Rig may lock local translation and
    scale so a limb behaves as fixed-length. Another Rig may permit a Control Point to translate or scale
    relative to its parent. The Animation Channel/Deformer Profile states which local transform components
    are admitted; the Rig owns distance, scale, angular, and other kinematic constraints. Together they state
    which behavior is legal. Object Factory does not impose one hard-and-fast immovable-skeleton rule.
12. **Body forms are absorbed snapshots, not live inheritance.** Save As creates a distinct object from the
    exact source revision. Later source changes do not propagate.
13. **Puppeteer dots are complete poses.** A dot on the 2D Puppeteer field is not a joint. It stores the
    current state of all channels governed by that field. Moving the cursor between dots blends poses.
14. **Pose authoring is separate from Puppeteer performance.** Joints and semantic controls are manipulated
    on the rig to create poses; those poses are then placed on the Puppeteer field.
15. **Import is a general application service.** Object Factory is one consumer and target-contract owner;
    it MUST NOT create a private, competing import store.
16. **Dwarf War is evidence, not a dependency.** Portable algorithmic ideas may be independently ported.
17. **Signed-language support is a future capability.** The motion architecture must leave room for it now,
    but language content, translation, and linguistic approval do not belong in the first implementation.
18. **The production-target workbench has one root and one depth-one Shelf.** Batch, Gallery, and Creature
    are a fixed `SINGLE_CURRENT` Destination Shelf whose current Box owns the complete lower route. The
    Creature route has one reorderable `MULTI_OPEN` Shelf whose Shader, Puppeteer, and Object items toggle
    retained Boxes. Current, open, and keyboard focus are separate truths. Puppeteer owns Form/Skeleton/
    stance/motion editing; Shader owns mapping/Appearance; Object presents their shared result only. The
    last exact Creature route key is a bounded browser-session navigation hint, never Creature or selection
    authority; direct keyless routes remain empty and typed detail reads remain authoritative. Object's
    Surface visibility is independent of Wireframe, Skeleton, Control Points, camera, and Form truth.
19. **Puppeteer composes explicitly bounded Shelves.** Creature contains Puppeteer. Puppeteer contains one
    Body Form label/Box pair and exposes the current Stance in its label bar because that state affects both
    Shader and Object. Body Form contains Type, Proportions, and Transformation. These are named Shelf
    Contract v1 instances, not an unbounded recursive grammar. Parent close hides descendants but retains
    admitted child state and returns focus to the parent label.
20. **Manufacture precedes texture.** A user chooses or derives the Body Form Type, adjusts admitted
    proportions and rest layout, and reviews stances before Shader adoption. Texture observations never
    mutate the Skeleton or become geometry authority.
21. **Creature coverage uses morphotypes plus reviewed adoption.** The compendium defines reusable bipedal,
    quadrupedal, winged, axial, and radial families. The later 235-monster pass proposes exact type,
    proportions, and stance per exact Creature revision; it does not create 235 unrelated hand-built rigs
    or silently mutate live data.
22. **Image painting uses a constrained flat guide.** Each canonical face request starts from an exact
    program-rendered orthographic silhouette/mask with projected semantic Control Points, Skeleton segments,
    regions, camera, Form, stance, and Atlas revisions. A provider may paint admitted pixels but cannot move
    the guide, invent geometry, or redefine mapping. Projection and render-back validation use that same
    exact camera/surface contract.
23. **Core Morphs are fixed complete objects; editing begins with a copy.** Core Morph source is the sole
    authoring authority and the database is mandatory materialization of complete immutable revisions. Every
    Morph owns its complete Points, Joints, simple geometry/profile attachments, exactly `mapping` and
    `resting`, and its linked motion pairs. A user cannot edit a fixed Core Morph in place: selection copies
    the complete object under a new identity, and only that independent copy may change. Family and copy
    provenance are descriptive only. There is no Morph inheritance, parent-chain resolution, variant stack,
    or propagation from a source object into a copy. No image or silhouette is Morph payload.

## 3. Goals

Object Factory MUST eventually provide:

- A small language of deterministic primitive body parts and attachment rules.
- Reusable form families such as humanoids, quadrupeds, winged quadrupeds, and dragons.
- Simple visual controls for dimensions, proportions, part placement, and facet count.
- Optional free reference images for ordinary construction and a standard six-face orthographic Token
  mapping deck for mapped creatures, without treating Icon as another face.
- Reusable Mapping Body Types whose fixed skeleton, simple geometry, UVs, semantic regions, mapping pose,
  and six default landmark planes can be fitted to many compatible creatures.
- Stable, understandable flattened UV layouts with named semantic regions.
- Atlas inspection beside the corresponding textured 3D object.
- Appearance creation by importing, projecting, manually editing, or AI-painting a PNG.
- Automatic OBJ and MTL production for static viewing and interchange.
- A hierarchical Control Point/Rig system with declared transform channels, constraints, forward
  kinematics, and inverse kinematics.
- Direct pose authoring through joint and semantic end-effector controls.
- A 2D Puppeteer pose field that blends saved whole poses and records performances.
- Reusable behaviors such as idle, walking, striking, waving, and dancing.
- Layered behavior composition, such as walking while waving.
- Durable Save New, Save, Save As, reload, versioning, and provenance.
- A shared import-and-convert route that produces the same native contracts.
- Later runtime consumption by Viewer, GM, Player, and Scripted Walkthrough features without making those
  consumers start gates.

## 4. Non-goals

The initial initiative does not attempt to:

- Reproduce Blender, Maya, Hero Forge, or a general digital-content-creation suite.
- Generate arbitrary topology with an image or language model.
- Guarantee realistic organic skin deformation in the first rigged fixture.
- Automatically infer a trustworthy skeleton, semantics, or behavior library from any imported mesh.
- Depend on Hero Forge source formats, services, licensing, or continued availability.
- Link Talisman at runtime to Dwarf War or Blender.
- Treat one Front image as complete evidence for hidden sides and back surfaces.
- Treat a generated Token view as authoritative 3D joint truth or accept an inferred Pose without review.
- Treat polygon decimation as equivalent to intentional planarization or faceting.
- Equate an English word with a sign or automatically publish generated gestures as signed language.
- Make paid provider calls without an explicit, reviewed user action.

## 5. Canonical vocabulary

| Term | Exact meaning |
|---|---|
| **Template** | A versioned program-owned family definition: allowable parts, parameters, rig pattern, semantic surfaces, and atlas policy. |
| **Core Morph** | A fixed, source-authoritative, complete skeletal object: stable Points, Joints, simple geometry/profile attachments, exactly Mapping and Resting, linked motion pairs, and derivation options. |
| **Materialized Morph** | One exact complete Core Morph source revision stored in the project database for application reads; it is never independent authoring authority. |
| **User Morph** | A complete independent object created by copying a Core Morph or another complete object. It never inherits from, resolves through, or receives later changes from its source. |
| **Workshop comparison** | A temporary complete review state used to compare possible forms before one is flattened into a complete Core Morph revision or independent object. |
| **Body Form** | A complete immutable snapshot produced by copying or constructing one object. Despite the name, it may describe a dragon, creature, prop, or other constructed object. |
| **Mapping Body Type** | Reusable geometry, UV, regions, mapping pose, and six landmark planes. |
| **Space Node / Control Point** | A stable node whose parent-relative transform establishes a local coordinate space with its own `(0,0,0)`. It may own zero or more Parts and zero or more child Space Nodes. In the editor, an articulated or directly manipulated Space Node is presented as a Control Point. |
| **Part** | One semantic geometric shape instance attached beneath a Space Node, with shape-local transform, primitive parameters, surfaces, and material assignment. Several Parts may share one Space Node. |
| **Surface Region** | A stable semantic subset of triangles, such as `head.face`, `torso.belly`, or `wing.left.upper`. |
| **Material Region** | A stable material assignment. It may contain several Surface Regions. |
| **UV Island** | One connected piece of the flattened surface in atlas coordinates. |
| **Atlas Contract** | The immutable combination of atlas dimensions, UV coordinates, islands, regions, masks, gutter policy, and identity. |
| **Appearance** | Texture maps and material values compatible with one exact Atlas Contract. |
| **Rig** | The authoritative articulation of Space Nodes: hierarchy, rest local transforms, axes, limits, distance/scale and other kinematic constraints, and semantic capabilities. |
| **Skeleton** | The user-facing visualization of the Rig as joints and bones. |
| **Binding** | The rule by which geometry follows articulated Space Nodes. Version 1 attaches each complete Part rigidly beneath one Space Node. |
| **Pose Control / Pose Handle** | A user-facing pose target such as a wrist target, gaze target, elbow pole, or joint rotation handle. The domain may call it a Pose Control; the UI calls it a Pose Handle so it cannot be confused with a construction Control Point. It need not be a joint. |
| **Control Profile** | Independently versioned semantic Controls and their mappings into one compatible Rig/channel/solver combination. |
| **Landmark / Semantic Site** | A named body-relative point, oriented frame, contact surface, or tolerance volume such as `chin`, `chest.center`, or `hand.primary.palm`. It MUST NOT be merely a raw mesh index. |
| **Semantic Site Set** | Independently versioned Semantic Sites, their locator methods, coordinate frames, dependencies, and qualification evidence. |
| **Animation Channel/Deformer Profile** | The complete skeletal and nonskeletal channel schema, per-component admission policy (locked, directly animatable, solver-controlled, or derived), defaults, ranges, composition rules, and target-specific deformation mapping. Rig-owned kinematic constraints are referenced rather than duplicated here. |
| **Capability Offer / Requirement** | Structured, versioned declarations of what a target provides and what a behavior needs. Eligibility is requirement satisfaction, not exact hash equality. |
| **Pose Snapshot** | The complete saved state of the animation channels governed by a pose or field. |
| **Puppeteer Dot** | A Pose Snapshot placed at a stable coordinate in a two-dimensional pose-blending field. |
| **Puppeteer Field** | A set of complete-pose dots plus deterministic spatial interpolation. It owns no composition mask or additive reference. Its coordinates have no anatomical or time meaning. |
| **Performance Path** | A time-stamped cursor path through a Puppeteer Field. |
| **Behavior Intent** | Retargetable targets, constraints, paths, timing, layers, and semantic events. |
| **Solved Clip** | Dense, rig-specific complete permitted parent-local TRS/channel values, scene-local root transforms, and deformation values produced by a named solver version. |
| **Character Object** | A saved snapshot joining exact Form, Appearance, Rig, and selected Behavior revisions. |
| **Source Asset** | Immutable imported bytes plus declared metadata and digest. |
| **Conversion Recipe** | A normalized, versioned sequence that transforms a Source Asset into a native candidate. |
| **Native Object Package** | The logical collection of authoritative records and derived artifacts admitted as one versioned Talisman object. It need not be one physical archive file. |
| **Review Record** | Immutable evidence that named reviewers evaluated exact dependency revisions for a declared method, audience, context, and result. |

## 6. End-to-end architecture

Object Factory has two construction entrances that converge before editing and saving:

```text
Programmatic Template + parameters -----------------------+
                                                          |
External file -> checked import -> conversion recipe -----+--> Native Form Candidate
                                                                  |
                                  +-------------------------------+------------------+
                                  |                  |             |                  |
                             Geometry/UV          Skeleton       Appearance         Provenance
                                  |                  |             |                  |
                                  +------------------+-------------+------------------+
                                                                  |
                                                        save immutable revision
                                                                  |
                                              pose -> Puppeteer -> behavior library
                                                                  |
                                             Viewer / GM / Player / Walkthrough consumers
```

The native candidate boundary is the architectural convergence point. Downstream editors MUST NOT need to
know whether a form began as procedural geometry or an imported file. Capabilities that do not exist on an
imported form, such as semantic regions or a rig, remain explicitly absent until an Object Factory operation
adds and validates them.

### 6.1 Normal user path

The preferred procedural workflow is:

1. Choose a Template, such as `dragon.basic`.
2. Place Control Points/local spaces, attach one or more shapes beneath each, and adjust program-owned
   parameters while viewing the Solid or Skeleton representation.
3. Optionally register Front, Side, or Back reference images and fit the form against them.
4. Compile deterministic geometry, semantic surfaces, UVs, atlas masks, OBJ, and MTL.
5. Inspect the flattened atlas beside the 3D form.
6. Create an Appearance by importing a PNG, projecting registered views, editing, or requesting an
   AI-painted atlas.
7. Review the textured form and unresolved/low-confidence areas.
8. Pose and, when desired, teach behaviors through the rig and Puppeteer tools.
9. Save New or Save As through the existing checked Factory Object application-service seam.

### 6.2 Imported-object path

The general import workflow is:

1. Select an external file or package and state its intended target kind.
2. Inspect and decode it without committing application state.
3. Normalize units, axes, handedness, names, and supported materials.
4. Optionally apply an explicit conversion recipe such as repair, reduction, faceting, or UV rebuilding.
5. Preview the source, candidate, diagnostics, lost capabilities, and provenance receipt.
6. On acceptance, commit immutable source bytes and the native derived object in one checked operation.
7. Continue through the same Object Factory editors as a procedural candidate.

## 7. Domain authority and artifact authority

OBJ must not become the canonical definition of a future animated object. The authoritative logical model
is a set of versioned structured records; OBJ, MTL, PNG, and later GLB are downstream artifacts.

The logical Native Object Package contains these independently versioned components:

| Component | Required initially | Authority |
|---|---:|---|
| Object manifest | Yes | Exact component identities, revisions, hashes, capabilities, and provenance links. |
| Form recipe | Yes | Template identity/version, canonical-foot parameters, Space Nodes, parent-relative transforms, Parts, sockets, and compiler version. |
| Geometry artifact | Yes | Immutable indexed mesh and canonical static OBJ derivative. |
| Atlas contract | Yes | UVs, regions, island identities, masks, dimensions, and packer policy. |
| Material definition | Yes | Stable material identities and automatically generated MTL. |
| Appearance | Yes for Textured state | Diffuse PNG first; additional maps later. |
| Rig | Required for articulated forms | Articulated Space Node hierarchy, complete rest local TRS, axes, limits, complete optional distance/scale and other kinematic constraints, and mechanically meaningful joint/bone roles. |
| Binding | Required for articulated forms | Zero-to-many rigid Part attachments per Space Node first; weights later. |
| Control Profile | Required for pose-controlled forms | Semantic handles and exact solver/channel mappings. |
| Semantic Site Set | Required by semantic contacts or retargeting | Points, oriented frames, contact surfaces/volumes, locators, dependencies, and tolerances. |
| Animation Channel/Deformer Profile | Required for motion | Complete skeletal/nonskeletal channel admission policies, defaults, ranges, composition, and deformation mappings. |
| Pose library | No | Named Pose Snapshots compatible with one Rig revision. |
| Puppeteer fields | No | Complete-pose dots, field layout, and interpolation version. |
| Behaviors | No | Performance Paths, Behavior Intent, and/or Solved Clips. |
| Source asset and conversion receipt | Imported forms only | Original bytes, digest, rights metadata, adapter and recipe history. |

Every saved Character Object MUST reference exact immutable revisions. Updating a Template, Rig, Appearance,
or Behavior creates a new revision; it never silently alters an already saved Character Object.

## 8. Identity, revision, and compatibility

Stable semantic identifiers are required independently of list order and mesh indices.

Each present component MUST own stable IDs. Optional components are represented as explicitly absent in the
Native Object Package; a static prop or unrigged import does not invent bones or behaviors. IDs include, when
their owning component exists:

- Template and Form revision
- Space Nodes, Parts, and component shells
- Parent-relative spaces, Part-local transforms, and attachment sockets
- Surface Regions and Material Regions
- UV islands and Atlas Contract
- Bones and joints
- Controls, Semantic Sites, and animation/deformation channels
- Pose dots, fields, behavior layers, and events

The following compatibility signatures MUST be computable:

| Signature | Includes | Permitted reuse |
|---|---|---|
| **Topology signature** | Ordered semantic parts, connectivity, vertex/triangle ownership, seam expansion, and compiler version, excluding positions | Index correspondence and topology comparisons only |
| **Geometry/Bind signature** | Topology signature, exact Part shape-local translation/rotation/scale and positions, Space Node rest transforms, Part attachments or weights, and binding version | Position-sensitive Binding and geometry-dependent artifact reuse |
| **Atlas signature** | Dimensions, UV coordinates, islands, Surface Regions, masks, and material assignments | Appearance reuse only when exact |
| **Rig component signature** | Space Node hierarchy; rest translation/rotation/scale; local axes; complete angular, distance, scale, and other kinematic constraints; preferred poles; root-motion policy; and transform/quaternion conventions | Exact Rig identity and structural compatibility evidence |
| **Exact motion signature** | Rig component signature plus complete Channel/Deformer schema, admission policies/defaults, constraint/limit-projector and solver conventions that affect evaluation, and target-specific deformation mapping used by the saved artifact | Exact Pose Snapshot, Puppeteer Field, Performance Path, and Solved Clip reuse |
| **Rig-family signature** | Required semantic roles and capabilities while allowing declared proportion changes | Behavior Intent retargeting |
| **Control Profile signature** | Exact Control semantics, target mappings, solvers, and dependencies | Control-layout and pose-authoring reuse |
| **Semantic Site Set signature** | Exact sites, shapes, locators, coordinate spaces, dependencies, and tolerances | Contact/retargeting qualification |
| **Channel/Deformer signature** | Exact skeletal and nonskeletal channel schema; per-component admission policies; defaults; ranges; composition; deformation mapping; and exact referenced Rig/channel-target dependencies where applicable | Complete Pose and solved-output compatibility |
| **Capability Offer identity** | Versioned roles, cardinalities, parameters, tolerances, dependencies, and qualification evidence | Content identity; behavior eligibility is evaluated by requirement satisfaction |

A target is behavior-eligible only when its structured Capability Offer satisfies the behavior's Capability
Requirements, including explicit role binding where needed. A qualified four-manipulator creature may satisfy
a behavior requiring two manipulators; exact capability-hash equality is neither necessary nor sufficient.
Declared, mechanically validated, naturalness-reviewed, and language-reviewed capability states remain
separate evidence.

For concise references, `exactMotionSignature` is the domain-separated composite of the exact Rig,
Channel/Deformer Profile, every constraint/limit-projector and solver convention that affects evaluation,
and any deformation mapping required by the saved motion artifact. Pose Snapshots, Fields, Paths, and Solved
Clips use that composite, not a hierarchy-only hash.

Continuous shape changes such as cylinder radius or ellipsoid width SHOULD preserve topology and Atlas
signatures. A topology-changing choice, including facet-count changes or adding a wing, MUST create a new
explicit Form/Atlas revision. Existing Appearance bytes may be deliberately migrated or projected, but they
MUST NOT be declared compatible without conversion evidence.

## 9. Coordinate, unit, and orientation contract

The native coordinate convention follows the current Factory contract:

- Right-handed coordinates
- `+X` is right
- `+Y` is up
- `+Z` is forward
- Distances are stored in **feet** at the domain boundary
- A UI or adapter may explicitly display feet-and-inches, inches, metric, or another unit, but conversion is
  presentation/input handling and never changes the canonical stored unit
- Every Space Node/Control Point is the `(0,0,0)` origin of one local space and stores one parent-relative
  translation in feet, normalized quaternion rotation, and positive per-axis scale
- A Space Node may own any number of geometric Parts and child Space Nodes
- Every Part stores a shape-local transform inside its owning Space Node; every child stores its transform
  inside its parent Space Node
- A parent Space Node's translation, rotation, and scale affect all attached Parts and descendants
- Each Rig has an explicit root frame and canonical rest pose
- Front is data, never inferred from the current camera

The normative transform equations are:

```text
worldMatrix(node) = worldMatrix(parentNode) * TRS(node.localTransform)
worldMatrix(part) = worldMatrix(ownerNode) * TRS(part.shapeLocalTransform)
```

The root's parent matrix is identity. `TRS` means translation, then normalized-quaternion rotation, then
positive scale under one versioned matrix convention. Nonuniform ancestral scales can make a descendant's
composed world basis non-orthogonal; the composed matrix remains authoritative and MUST NOT be silently
decomposed and rewritten. Shape-local topology, normals, UVs, regions, and material identity remain defined
in the Part's own space and are transformed only for world presentation or a derivative static bake.

A Rig and its Animation Channel/Deformer Profile declare which components of each Space Node local transform
are locked, directly animatable, solver-controlled, or derived. Updating an allowed local translation or
scale is ordinary articulated motion relative to the parent; it is not, by itself, a topology change.

Quaternions are stored as normalized `(x, y, z, w)` values using a documented multiplication order. Euler
angles MAY be shown as editing conveniences but MUST NOT be the persisted motion authority. Negative scale
MUST NOT be used as a mirroring mechanism for rigs; mirroring uses an explicit semantic mapping and proper
rotation/translation conversion.

Imported adapters MUST declare their source unit, axes, handedness, and normalization transform. Unknown
units or orientation remain a visible unresolved decision; the system MUST NOT silently guess and commit.

## 10. Deterministic procedural geometry contract

### 10.1 Primitive grammar

The initial program-owned grammar SHOULD contain:

- Faceted cylinder and tapered cylinder/frustum
- Sphere and ellipsoid, including flattened ellipsoids
- Box and rectangular/tapered prism
- Disc and bounded planar patch
- Cone and curved or segmented horn
- Segmented chain for tail, neck, tentacle, or spine-like structures
- Bounded wing surface assembled from named spars and planar/faceted membranes

Every primitive definition MUST include:

- Stable Part ID and primitive type/version
- Owning Space Node ID and optional attachment socket
- Shape-local translation, normalized rotation, and positive scale
- Dimensions in canonical feet
- Discrete facet parameters
- Declared local Front, Back, Up, and longitudinal axis
- Surface Region assignments
- Material Region assignment
- UV policy identity
- Binding target, when articulated

### 10.2 First rigid construction style

The first articulated forms use closed, rigid low-poly Shape Instances attached beneath named Space Nodes:

- Upper arm: faceted cylinder
- Elbow: sphere or ellipsoid
- Forearm: faceted cylinder
- Hand: flattened ellipsoid

Small overlaps hide articulation gaps. This avoids skin weights while proving the complete relationship among
geometry, rig, UV, appearance, and posing. Each shell remains finite, outward-wound, non-degenerate, closed,
and two-manifold. Several shells may share one Space Node, and a single OBJ may therefore contain several
closed connected components.

Smooth skin and weighted deformation are a later Binding type. Adding them MUST NOT change the Rig,
Behavior Intent, or Atlas ownership boundaries.

### 10.3 Shape, space, and structural variation

Three kinds of variation are distinct:

1. **Shape variation** changes primitive radii, width, flattening, shape-local placement, or other dimensions
   inside one owning Space Node. It SHOULD preserve topology and UVs when the Template declares it compatible.
2. **Space-layout variation** changes a Space Node's rest local translation, rotation, or scale while retaining
   the same hierarchy and channel schema. It changes geometry/bind identity. If that node is articulated, it
   MUST also change exact Rig identity; an unarticulated node need not. It need not change topology or the
   Atlas Contract.
3. **Structural variation** adds/removes/reparents Space Nodes, Parts, sockets, or semantic roles. It creates a
   new Form and, when articulated, Rig revision. Behavior Intent may retarget through semantic Controls; exact
   Solved Clips may not.

“Fat,” “thin,” “broad,” “shorter forearm,” and “longer neck” may be either shape or Space-layout changes,
depending on which Transform Node the Template intentionally exposes. Scaling a parent space also scales and
repositions all attached shapes and descendants. “Additional tail segment” is structural because it changes
the hierarchy.

### 10.4 Determinism and validation

Given the same normalized Form recipe and compiler versions, the pre-appearance compiler MUST produce
byte-identical structured records, indexed geometry, OBJ, MTL, initial `diffuse.png`, guide, masks, and region
manifest.

The compiler MUST reject:

- Non-finite coordinates or transforms
- Invalid or non-normalized persisted rotations
- Non-positive local scale or forbidden negative mirroring scale
- Cyclic Space Node parentage, missing node owners, or invalid shape-to-node attachments
- Zero-area or inward-facing triangles
- Open or non-manifold shells where the declared primitive requires closure
- Duplicate semantic identities
- Missing parent frames, sockets, surfaces, materials, or bindings
- Unsupported or unbounded facet counts
- UVs outside `[0,1]`
- Atlas overlaps not explicitly declared as mirrored sharing

Stable construction order MUST be defined by semantic identity and recipe order, never hash-map iteration or
platform locale. Floating-point text output MUST use a fixed locale and fixed canonical formatting.
World-space normal realization under nonuniform affine scale MUST use the inverse-transpose normal matrix and
renormalize; zero/singular scale is rejected before realization.

## 11. Body Form editor

The editor is a simple authoring environment over the structured Body Form; it is not a general polygon
modeler. The machine-readable recipe language SHOULD remain almost entirely invisible to ordinary users.

### 11.1 Presentation modes

The central 3D editor MUST distinguish these presentations:

- **Skeleton** — joints, bones, semantic controls, sockets, pins, and selected constraints.
- **Solid** — the low-poly geometric shells with simple material colors.
- **Textured** — the current Appearance on the same geometry.
- **Mesh Wireframe** — optional diagnostic triangle edges, clearly separated from Skeleton.
- **UV/Regions** — flattened atlas and semantic-region correspondence, normally beside Solid or Textured.

The product MUST NOT label the armature view `Wireframe`.

### 11.2 Authoring operations

The first editor SHOULD permit:

- Add, remove, select, name, and reparent a permitted Control Point/Space Node.
- Attach any number of permitted primitive Parts beneath a selected Space Node or named socket.
- Edit the selected node's allowed position, rotation, and positive scale relative to its parent.
- Edit a Part's shape-local transform and length, radius, width, height, taper, flattening, or facet count
  where the Template permits them.
- Reparent with an explicit choice to preserve current world placement or preserve current local values.
- Select a Control Point, Pose Handle/joint, Part, Surface Region, or UV island from either 3D or atlas view.
- Change permitted dimensions numerically or through bounded visual handles.
- View Front, Side, Back, and free-orbit cameras without changing the declared object orientation.
- Undo and redo transient editing commands.
- Preview validation and compatibility consequences before accepting a topology-changing operation.

Changing a continuous parameter SHOULD update geometry interactively while retaining stable semantic IDs.
Changing a discrete topology parameter MUST visibly declare that it will create a new topology/atlas
revision and may require appearance migration.

### 11.3 Reference-image registration

The editor MUST define canonical Front, Side, and Back slots, with Front as the first supported view. All
reference images are optional; a programmatic Form can be authored without one. The editor MAY also hold
multiple independently registered references for size, age, style, or proportion comparisons. Every present
reference has:

- Immutable source image identity and digest
- Declared view identity
- Independent visibility and opacity
- Translation, scale, and optional rotation registration
- Camera calibration and projection type
- Optional crop and confidence boundary
- Reset and remove operations

The new Body Form reference editor initially uses an orthographic camera. This is a chosen extension: the
current proven Talisman projection renderer uses a fixed 36-degree perspective camera. Registering an image
MUST NOT change its bytes. Adjusting the form while viewing the image is an explicit user operation; the
image itself does not silently deform the body.

OF-SHADER-CAGE-50 separates that immutable source registration from one derived Shader sampling
registration. Each canonical view owns a browser-local snapshot keyed by the view and accepted source-image
identity. Its revisioned cage combines the current visible image observations, their confidence, explicitly
unresolved controls, fixed canvas-boundary anchors, and bounded user offsets. The source pixels, digest, and
accepted asset identity never change. Replacing the image rejects the prior cage by identity; switching views
selects that view's independent snapshot; changing the live Mapping controls or surface geometry derives a
fresh cage from the current geometry rather than rewriting the stored source.

A screen coordinate contains only two spatial dimensions. A Front drag therefore preserves current depth
unless the active edit tool declares another rule. Depth may later be changed through a Side view, depth handle,
named plane, or free 3D manipulation. The persisted result is always canonical 3D state; a 2D view is only a
projection and editing interface.

#### 11.3.1 Standard six-face Token mapping deck

OF-12A consumes a read-only **mapping deck** of the six exact managed Token views for one exact Creature
identity and revision. The deck supersedes the earlier three-slot reference assumption for mapped creatures
and becomes the normal deterministic Appearance input. It may also supply later pose evidence, but no image
observation is authoritative 3D geometry or motion truth.

| Face | Managed role | Asset label | Canonical orthographic camera basis |
|---|---|---|---|
| Front | `token` | Token | camera on `+Z`, rays toward `-Z`, image right `+X`, image up `+Y` |
| Back | `token_back` | Token Back | camera on `-Z`, rays toward `+Z`, image right `-X`, image up `+Y` |
| Left | `token_left` | Token Left | camera on `-X`, rays toward `+X`, image right `+Z`, image up `+Y` |
| Right | `token_right` | Token Right | camera on `+X`, rays toward `-X`, image right `-Z`, image up `+Y` |
| Top | `token_top` | Token Top | camera on `+Y`, rays toward `-Y`, image right `-X`, image up `+Z` |
| Bottom | `token_bottom` | Token Bottom | camera on `-Y`, rays toward `+Y`, image right `+X`, image up `+Z` |

`icon` remains a separate presentation role and MUST NOT be admitted as a mapping face. The current accepted
`token` is the authoritative **reference lineage** for the deck: it fixes creature, appearance, framing,
and the exact accepted Front asset from which every non-front role was generated. It is not authoritative
3D geometry, joint placement, depth, or Pose truth.

Every admitted non-front observation MUST name the same Creature identity/revision and the exact accepted
Front asset identity, revision, and SHA-256. Replacing Front makes observations derived from an older Front
stale even when their Creature identity is unchanged. Cross-creature or mixed-Front decks fail closed.
Object Factory reads one immutable settled snapshot; it does not enqueue, promote, regenerate, cancel, or
reorder Critter Image Creation work. If Front is missing, queued, or in flight, deck adoption reports
`WAITING_FOR_FRONT` and starts no mapping bake. Critter Image Creation retains its existing prerequisite,
shared-lane, direct-priority, and serialized-writer authorities.

All six roles assume one common canvas, subject orientation, root/center registration, and uniform
subject-space scale inherited from Front. Every image is strict orthographic mapping evidence: whole
creature visible, head at image top, tail at image bottom, neutral fully splayed limbs, tail straight from
the pelvis, no weapon, no perspective, and no foreshortening. The common scale is normalized visual scale
until explicitly calibrated against a known Form dimension; it MUST NOT be mislabeled as feet. A role may
carry an explicit bounded corrective translation or uniform-scale registration, but mirroring, arbitrary
roll, independent axis stretch, or an undocumented crop invalidates canonical-deck status and lowers or
rejects observation confidence rather than being silently normalized.

The corrective Shader cage does not relax that admission rule. Global axis fit and local piecewise-affine
warp are reviewable browser presentation used to sample already admitted evidence; they do not make a
noncanonical image canonical and do not rewrite the settled deck. Registration recovery is accepted only for
the same canonical view and source-image identity. Its derived mesh is additionally keyed by current surface
geometry, global image transform, viewport registration, orientation, and cage revision, so stale cached work
is discarded instead of being applied to a changed view or body.

The six 2D landmark maps are independent image observations. Moving a landmark on one face changes only
that face's mapping evidence and MUST NOT mutate the 3D body, another face, or a reusable body-type default.
The recommended calibration order is Front, Back, Left, Right, Top, Bottom. Copying the opposite face may
seed a new mapping by the declared camera transform, but the copy immediately becomes independent and MUST
NOT change its source face, image, or geometry. The current direct-command rule permits the explicit Copy
Opposite action to replace the active target without a second permission dialog.

Anatomical Left and Right always name the creature, not the viewer. In Front, anatomical Left appears on
image right; in Back it appears on image left. Left and Right faces expose only their respective near-side
limb chains for direct mapping; far-side joints remain part of the 3D body but do not masquerade as separate
visible evidence. Top and Bottom remain independent maps even though they share two world axes.

Front is required to form a deck, but the other five roles are individually optional. Missing or occluded
views remain explicit unresolved evidence, reduce affected surface confidence, and never become synthesized
observations. Contradictory silhouettes or landmarks remain per-view residuals/outliers; the system MUST
NOT average them into false certainty.

Each present observation carries bounded provenance and confidence: Creature identity/revision, role,
managed asset identity/revision/digest, exact Front-parent identity/revision/digest, pixel dimensions,
camera-basis version, common-registration version, generation/request evidence reference, per-landmark
coordinates/visibility/confidence, and residual summary. Bytes remain with the owning Assets authority.
Object Factory retains resolvable identities and the exact immutable deck snapshot used by one mapping
revision or later Pose candidate.

#### 11.3.2 Mapping Body Types

A **Mapping Body Type** is a reusable, deliberately practical mapping family. It owns:

- a fixed joint graph rooted at Hips and stable anatomical names;
- one fully splayed mapping pose and one or more stance angle sets, beginning with Standing;
- simple closed primitive geometry and cross-sections around the skeleton;
- six default 2D landmark layouts in the camera bases above;
- fixed mesh topology, UV islands, seams, semantic regions, and region-ID colors; and
- body-type identity, revision, compatible parameter bounds, and migration rules.

The first OF-12A body type is **Lizard**, calibrated with Giant Crocodile. It is intentionally narrower than
the landed generic quadruped Template: long trunk, four lateral limbs, low neck/head chain, and a straight
segmented tail. Later Mapping Body Types may include mammalian quadruped, heavy/bear, humanoid, digitigrade
biped, winged dragon, bird, serpentine, aquatic, arthropod, and amorphous/tentacled families. They are an
engineering taxonomy, not a scientific claim that all creatures fit one biological tree.

Selecting a Mapping Body Type creates a creature-owned mapping revision. Creature edits MUST NOT mutate the
reusable default. An explicit specialist action may save reviewed geometry, mapping defaults, and region
truth as a new default body-type revision. A normal reset returns to the exact selected default. Once a
creature mapping diverges, changing its body-type identity requires an explicit reset or migration rather
than silently reinterpreting the edited work.

### 11.4 Reference projection and coverage

Registered color references may be projected onto the known form. For every atlas texel, Talisman computes
its exact 3D surface point, projects it through each calibrated camera, depth-tests visibility, and chooses
or blends only valid front-facing evidence. When a view has an OF-SHADER-CAGE-50 registration, Shader first
inverse-warps the immutable source through the revisioned piecewise-affine cage into one derived registered
canvas. Object preserves its canonical first-claim UVs and samples that canvas. A point outside an admitted
cage triangle, or in a degenerate or folded triangle, remains unresolved during the bake rather than falling
through to invented coverage.

The OF-12A route fits the Lizard skeleton and simple geometry to six independent mapping planes, projects
each accepted image only onto compatible visible surfaces, and bakes the result into the body type's fixed
UV layout. Surface identity, camera basis, landmark placement, cage registration, visibility, and confidence
decide which view may contribute. Shader presentation and Object's cached first-claim surface use this one
sampling function; camera orbit, stance playback, and motion do not recompute or substitute another view.
Incompatible or unobserved coverage remains explicit. The initial deterministic bake uses no provider call.

The completed Object Factory extension MUST distinguish per texel:

- Directly observed texels
- Bounded locally propagated texels
- Unresolved texels
- Confidence by contributing view

Current Talisman proves projection and aggregate direct/propagated/unresolved counts, but not this complete
per-texel classification. Its bounded propagation follows connected UV coverage rather than semantic-region
boundaries. OF-04 must add per-texel evidence and prevent propagation across a Surface Region boundary unless
that Atlas Contract explicitly permits continuity.

One Front image cannot truthfully paint hidden sides or the back. Unresolved coverage MUST remain visible
until filled by another view, an explicit mirroring/completion rule, manual work, or a reviewed generator
request. The system MUST NOT silently present invented back coverage as observed evidence.

The existing Talisman projection pipeline is a **proven** foundation for this behavior; see section 30.

### 11.5 UI experience-design gate

No Object Factory UI implementation body may begin from prose requirements alone. The non-code
**OF-UI-00** body must first identify every screen/workspace, its sections and actions, shared application
chrome, required dialogs, empty/busy/error/stale/unsaved/incompatible states, user-facing terminology,
keyboard behavior, accessible non-color-only signals, and expected-width layouts.

Its primary evidence is a labeled image/mockup set, not ASCII wireframes. At minimum, the Build, Reference,
Skin Map, Skeleton Setup, Pose, Puppeteer, Behavior Mixer, and Appearance Review inventions receive full
screen plates. Home/library, Create Form, Character Variants, Save/History, Import/Convert, Export,
Provenance, runtime publication, and future language-review surfaces are also inventoried at appropriate
fidelity. A clickable or equivalent reviewed journey connects Build -> Reference -> Skin -> Skeleton -> Pose
-> Puppeteer -> Save.

The UI design is accepted only after explicit product review. Later implementation may refine ordinary
spacing or platform-native control details, but it MUST NOT silently change workspace ownership, primary
actions, persistence boundaries, paid-action review, terminology, or safety states without revising the
approved design.

## 12. UV mapping contract

### 12.1 Face-corner correspondence

Every triangle corner has a 3D position, normal, and UV coordinate. The initial canonical OBJ derivative
uses one expanded index for all three, matching the existing Factory writer:

```obj
f positionIndex/uvIndex/normalIndex ...
```

In canonical output the three indices are numerically equal for each corner. When one physical 3D point
lies on a seam, the compiler duplicates its exported vertex so each copy can own a different UV. This is
how a face on the 3D object is mapped to an exact part of the flat image; no later discovery step exists.

OBJ UV coordinates use bottom-left origin. For an atlas of width `W` and height `H`, continuous raster
coordinates are:

```text
pixelX = u * W
pixelY = (1 - v) * H
```

The center of PNG texel `(x,y)` corresponds to:

```text
u = (x + 0.5) / W
v = 1 - (y + 0.5) / H
```

### 12.2 Exact cylinder policy

For a cylinder whose local axis is `+Y`, local Front is `+Z`, and Back is `-Z`:

```text
theta = atan2(localX, localZ), in (-pi, pi]
sideU = uMin + ((theta + pi) / (2*pi)) * (uMax - uMin)
sideV = vMin + ((localY - yMin) / (yMax - yMin)) * (vMax - vMin)
```

This places the visible Front at the horizontal center of the side island and the seam down the Back. The
top and bottom caps receive separate islands with fixed orientation. A faceted cylinder produces one stable
vertical atlas band per facet, so `upper-arm.front` or `torso.front` is an exact known area rather than a
visual guess.

The continuous `atan2` formula does not by itself choose both copies of the exact rear vertex. A cylinder
with `N` facets therefore compiles `N+1` UV columns. Facet `k` owns columns `k` and `k+1`; column zero and
column `N` share the same rear 3D position but receive `uMin` and `uMax` respectively. The compiler assigns
this branch from stable facet topology, never floating-point angle coincidence.

### 12.3 Exact sphere and ellipsoid policy

The OF-01 shoulder, elbow, and hand use one versioned faceted ellipsoid topology. Version 1 defaults to 12
longitude facets and 6 latitude segments, including the two pole fans. For radii `(rX,rY,rZ)`, intermediate
ring `j` and longitude column `k` use:

```text
latitude  = pi/2 - j*pi/latitudeSegments, j = 1..latitudeSegments-1
longitude = -pi + 2*pi*k/longitudeFacets,  k = 0..longitudeFacets-1
x = rX * cos(latitude) * sin(longitude)
y = rY * sin(latitude)
z = rZ * cos(latitude) * cos(longitude)
```

Adjacent ring quads split on one stable recipe-declared diagonal. North and south use outward-wound triangle
fans. The rear column is seam-expanded exactly like the cylinder. UV longitude is `k/longitudeFacets` across
the allocated island; UV latitude is `j/latitudeSegments`. Each pole is physically coincident but exported
once per wedge with the wedge-midpoint U and pole V, preventing a triangle from spanning the rear seam.

An ellipsoid uses the same topology scaled by its radii. Its analytic smooth normal is proportional to
`(x/rX^2, y/rY^2, z/rZ^2)`; the recipe may instead select explicit flat triangle/facet normals. Normal policy
is part of the topology signature. Stable longitude-facet and latitude-band IDs provide semantic region
ownership; human-friendly Front/Back/side aliases are derived from declared local orientation.

Other primitives require equally explicit topology, winding, normal, seam, pole/cap, and UV policies before
they may enter a deterministic Template.

### 12.4 Island and region ownership

The UV compiler MUST derive seams only from versioned program rules, including:

- Physical component boundaries
- Declared rear or hidden seams
- Hard-facet boundaries when separate islands are required
- Surface or Material Region boundaries
- Template-specific cuts needed to keep distortion bounded

Every triangle belongs to exactly one Surface Region and one Material Region. Every island identifies its
owning surfaces. Surface semantics are stable across regeneration within a Template/Atlas version.

For character-focused atlases, important readable surfaces SHOULD receive deliberate placement. Examples:

- Face/front head near a stable high-attention area
- Torso Front and Back in predictable separate bands
- Left and right limbs in named areas
- Wings, horns, eyes, mouth, claws, and attachment surfaces separately identified

This policy intentionally differs from the existing furniture equal-grid packer.

### 12.5 Deterministic packing

Packing MUST:

- Sort by stable semantic region/island key, then declared priority, area, and source face identity.
- Use integer texel coordinates.
- Permit only explicitly enumerated rotations.
- Produce no unintended overlap and no out-of-bounds texels.
- Record packer name, version, atlas dimensions, rotations, gutters, and dilation.
- Reproduce byte-identical output for an equal normalized recipe.

**Initial proposal:** default to a 1024x1024 RGBA atlas, allow a Template to declare another bounded size,
require at least 8 pixels between occupied island interiors, and locally dilate island edge color by 4 pixels
after painting. The current Viewer presentation bound admits a 1024x1024 diffuse image. Atlas dimensions and
spacing are part of the Atlas signature and cannot change silently.

### 12.6 Texel density

The Template assigns a base pixels-per-foot target and optional semantic weights. Density exceptions for
face, eyes, insignia, or other features are explicit data, not accidental packer results. The compiler MUST
report physical area, allocated texel area, and effective density per Surface Region.

**Initial proposal:** regions with equal semantic weight remain within +/-5% density after integer packing
for one compiled Form. Declared compatible broad/thin/radius variants retain their fixed Atlas layout so an
Appearance remains reusable; the compiler reports resulting physical-density drift rather than repacking.
Each Template declares the maximum compatible drift. Crossing it requires an explicit new Atlas revision and
Appearance migration. Exceptions are named in the Atlas Contract.

## 13. Atlas artifacts and `regions.json`

The compiler produces these logical artifacts before any provider call:

| Artifact | Purpose |
|---|---|
| `diffuse.png` | Active deterministic base-color atlas referenced by MTL; initialized before any Appearance painting. |
| `atlas-guide.png` | Human- and generator-readable island edges, orientation marks, and permitted labels. |
| `atlas-coverage.png` | Exact binary occupied-texel mask. |
| `atlas-regions.png` | Lossless flat region-ID colors; never a presentation image. |
| `regions.json` | Semantic mapping from region IDs to geometry and atlas facts. |
| `model.obj` | Canonical static mesh with `mtllib` and `usemtl`. |
| `material.mtl` | Generated material that binds the accepted diffuse PNG. |

`regions.json` is a Talisman-defined sidecar, not an OBJ feature. Its version 1 logical fields are:

```text
schemaVersion
atlasIdentity / atlasRevision / atlasWidth / atlasHeight
coordinateConvention / packerIdentity / packerVersion
gutterPixels / dilationPixels
materials[]: materialId, displayName
regions[]:
  regionId
  displayName
  partId
  materialId
  semanticTags[]
  triangleRanges[]
  islandIds[]
  integerUvBounds
  physicalAreaSquareFeet
  effectivePixelsPerFoot
  regionMaskColor
  declaredFrontBackLeftRight
  invariantSurfaceDescription
islands[]:
  islandId
  owningRegionIds[]
  integerBounds
  rotation
  mirroredSharingPolicy
artifactHashes
```

Triangle ranges and island ownership refer to the exact Geometry/Atlas revision and MUST never be rebound to
a changed mesh by best effort. Region mask colors are generated stable identifiers, not aesthetic colors.

Version 1 canonical JSON is UTF-8 with LF, schema-ordered fields, canonical numeric formatting, stable array
ordering, and no insignificant platform-dependent data. `regions.json.artifactHashes` covers the other
listed artifacts but MUST NOT contain the hash of `regions.json` itself; the outer Native Object manifest
stores that hash and removes the self-reference.

Deterministic PNGs are 8-bit RGBA, contain no clock or platform metadata, and use one pinned encoder version.
The initial `diffuse.png` uses opaque white `(255,255,255,255)` on covered texels and transparent black
`(0,0,0,0)` outside coverage. Guide/mask encodings and color-space chunks are fixed by their artifact schema.
Appearance-specific generator wording does not belong in invariant `regions.json`; one request-scoped
appearance-instructions record contains it and is retained with request provenance.

The manifest MAY grow, but unknown schema versions fail closed. An implementation MUST preserve unknown
future fields only through a deliberate compatible loader; it MUST NOT silently discard data and resave.

## 14. Static OBJ/MTL/PNG package

OBJ, MTL, and PNG are the first interoperable static output. They are generated automatically.

The OBJ MUST:

- Declare one safe relative `mtllib` filename.
- Declare stable `usemtl` values.
- Emit deterministic `v`, `vt`, `vn`, and triangular `f` records.
- Use one-to-one expanded position/UV/normal indices.
- Contain no absolute filesystem paths.

The proven current canonical OBJ writer converts native `+Z` forward to OBJ presentation by negating Z and
reversing face winding. Object Factory tests MUST preserve that round-trip convention. The current writer
also emits one global material name and cannot yet express its input mesh's per-triangle material slots. The
initial Object Factory package therefore uses exactly one Material Region, `body`; multiple Material Regions
require a deliberate writer/admission extension rather than misleading output.

The initial diffuse-only MTL follows the proven current Talisman writer:

```mtl
# Generated by Talisman Object Factory
newmtl body
Kd 1.000000 1.000000 1.000000
Ka 0.000000 0.000000 0.000000
Ks 0.000000 0.000000 0.000000
d 1.000000
illum 1
map_Kd diffuse.png
```

`Ks` is specular color; all zeros make the initial material non-specular through this legacy contract. `d`
is dissolve/opacity; `1` is fully opaque. Users need not know or edit either value.

The shown comment is the normative future Object Factory output; the proven current writer says “Generated
by Talisman texture mapping.” Either retained text is semantically harmless, but deterministic golden bytes
must choose one in OF-01. Because the current filename sanitizer does not by itself reject absolute or
traversal-style names, the Object Factory package validator MUST first require a safe single relative basename
for every `mtllib` and `map_Kd` value.

The first accepted diffuse is a dimension-exact RGBA PNG. Additional normal, emissive, metallic, roughness,
or occlusion maps are later Appearance channels. When added, they belong to explicit schema versions and
Viewer capability declarations rather than ad hoc MTL extensions.

OBJ cannot carry the authoritative Rig, pose field, or behavior library. A posed OBJ MAY be baked for a
thumbnail, Viewer compatibility, or export, but it remains a derivative of the Form + Rig + Pose.

## 15. Appearance contract

An Appearance is compatible only with one exact Atlas signature. It contains:

- Appearance identity and immutable revision
- Atlas identity/revision/signature
- Diffuse PNG identity, dimensions, digest, and color-space declaration
- Optional future texture-map identities and roles
- Material scalar values
- Creation method and complete provenance
- Coverage and review diagnostics
- Optional generator request and response evidence

Replacing the PNG MUST NOT change geometry, UVs, Surface Regions, Rig, poses, or mechanical behavior
compatibility. Presentation-dependent review evidence may become inapplicable as defined in section 27.

### 15.1 Appearance creation methods

The product supports four routes behind the same acceptance boundary:

1. Import an already compatible atlas PNG.
2. Deterministically project the registered six-view mapping deck through the known geometry and UVs.
3. Edit or paint through local region-aware tools.
4. Ask a configured image provider for controlled orthographic source views or a tightly masked repair.

Whichever route is used, Talisman validates and normalizes the final PNG locally before it can become an
accepted Appearance revision.

### 15.2 AI painting boundary

The preferred AI input is an ordinary strict-orthographic mapping template or a tightly bounded unresolved
surface mask, not a discontinuous UV puzzle. Talisman projects and bakes accepted view pixels into the fixed
atlas. The already landed exact-atlas painting route remains a compatible specialist option, not the next or
primary mapping workflow.

The AI request assembler receives only program-owned geometry evidence and user-approved appearance intent:

- Exact initial diffuse/base atlas, guide, coverage mask, and region-ID mask
- Exact atlas dimensions and immutable Atlas identity
- Named Surface Region descriptions and orientation
- Selected registered references and/or deterministic renders
- Appearance description, palette, age, markings, clothing, damage, and surface detail
- Explicit restrictions such as preserving island boundaries and not adding background text

When AI creates a mapping view, the request also supplies the exact body-type silhouette, camera identity,
head-up orientation, splayed pose, semantic landmark/region guide, and accepted Front ancestry. When AI
repairs a bake, the writable mask is the entire authority: pixels outside it cannot change. AI never moves a
landmark, region boundary, seam, island, or UV coordinate.

The provider output is treated as untrusted image bytes. It MUST pass:

- File-format and bounded-size admission
- Exact width and height validation
- Finite decodable color data and declared color-space normalization
- Application of the authoritative coverage mask
- Region-safe edge dilation and gutter handling
- Preview on the exact 3D form
- User acceptance before durable replacement of an existing Appearance

After returned bytes are admitted, OF-SHADER-CAGE-50 may calculate a deterministic first-fit transform from
their visible silhouette to the demanded current Mapping silhouette. That fit and every later landmark drag
remain local registration evidence, not provider truth or a source-image mutation. Hidden or low-confidence
anatomy remains unresolved, and Reset removes the derived registration to recover the untouched result.

AI MUST NOT choose body dimensions, topology, seams, island placement, material-region identity, rigging, or
behavior. Natural-language body editing, if later offered, is a separate command interface over explicit
Template parameters; it is not authority delegated to the texture generator.

No paid provider call is implicit. The user MUST explicitly initiate a reviewed request under the existing
provider/settings boundary. Rejection leaves the prior Appearance intact.

### 15.3 One form, many characters

The reusable relationship is:

```text
Body Form       = program-owned shape + Atlas Contract + optional Rig
Appearance      = texture maps compatible with that Atlas Contract
Character       = exact Form revision + exact Appearance revision + selected behaviors
```

Many Character Objects may share Form and Behavior revisions while owning different Appearances. This is
the primary scaling model for SRD creatures: program the form once, then create visual variety through maps.

## 16. General import-and-convert service

Import and conversion are reusable platform capabilities. They belong at the checked Assets/application
boundary; Object Factory supplies native target contracts and geometry-specific recipes.

### 16.1 Typed operation

A logical import request contains:

- Candidate filename and immutable bytes or a checked file handle
- Declared target kind, such as static model, Body Form candidate, Appearance, Rig, or behavior
- Optional declared format when it cannot be safely sniffed
- Unit and orientation hints
- License, source, author, and permitted-use metadata when known
- Selected adapter and Conversion Recipe
- Expected operation identity/revision for cancellation and stale-result rejection

The operation returns immutable progress snapshots and one terminal result: staged candidate, rejected with
diagnostics, cancelled, stale, or durably committed. A visible screen is not required for semantic work.

### 16.2 Pipeline stages

The shared service MUST perform these stages in order:

1. **Admission:** bound bytes, inspect real format, sanitize names, and reject unsafe packages.
2. **Decode:** use a named adapter/version; never trust extension alone.
3. **Source description:** calculate digest and capture declared provenance without mutating durable state.
4. **Normalization:** establish units, axes, handedness, origin, naming, and supported material roles; convert
   every admitted physical distance to canonical feet before native candidate identity is calculated.
5. **Capability inventory:** report mesh, UV, materials, maps, components, Rig, weights, animations, and
   unsupported data.
6. **Conversion:** apply only explicit versioned recipe steps.
7. **Validation:** prove target invariants and identify lost or unresolved capabilities.
8. **Preview:** compare source and candidate with metrics and deterministic views.
9. **Commit:** after acceptance, atomically admit immutable source evidence and native derived artifacts.
10. **Receipt:** retain hashes, adapter and recipe versions, transforms, diagnostics, and artifact links.

Preview and rejection MUST NOT create partial logical objects or modify existing ones. Commit uses the
existing checked admission and Factory Object application service; import code MUST NOT write direct SQL or
create a second byte store.

### 16.3 Deterministic conversion identity

For deterministic adapters, the candidate identity is derived from a domain-separated, length-framed
envelope—not ambiguous text concatenation:

```text
SHA-256(
  UTF8("TALISMAN_IMPORT_CONVERSION_V1")
  || frame(sourceDigestBytes)
  || frame(adapterIdentityBytes)
  || frame(adapterVersionBytes)
  || frame(canonicalConversionRecipeBytes)
  || frame(targetSchemaCompilerVersionBytes)
)

frame(value) = unsigned-64-bit big-endian byte length || value
```

Equal inputs MUST produce equal pre-appearance artifacts. A nondeterministic external result, if ever used,
is immutable evidence with its own digest and request provenance; it is never falsely described as
recomputed deterministic output.

### 16.4 Format progression

| Format | Initial role | Important limitation or policy |
|---|---|---|
| OBJ + MTL + PNG | First static import/export family | Can carry geometry, UVs, normals, and basic materials; no authoritative Rig or behavior. |
| STL | Early geometry-only import | No UV, material, semantic regions, or Rig; these must be generated explicitly. |
| UnityFS fixture | Existing specialized checked conversion | Keep its exact adapter boundary; do not generalize by package-name convention. |
| glTF/GLB | **Future** articulated import/export | Good delivery container for nodes, skins, materials, and animation; still an adapter, not the internal domain model. |
| FBX and other DCC formats | **Future** only when a maintained decoder and rights model exist | No format is accepted merely because another application can open it. |

### 16.5 Reduction, planarization, and faceting

These are separate recipe operations:

- **Collapse decimation** reduces triangle count by collapsing topology. It may approximate a surface but
  does not inherently create intentional broad planes or hard facets.
- **Planarization** groups a reviewed or deterministically selected region and projects its vertices onto a
  plane under explicit error and boundary constraints.
- **Faceting** establishes intentional surface regions and hard-normal boundaries. It may use already planar
  polygons or reconstruct them; it is not equivalent to smooth-shaded decimation.

Any topology-changing operation invalidates dependent UV, raw-vertex landmarks, weights, and bindings unless
the operation produces and validates an exact migration. Stale dependents MUST NOT be silently reused.

A general imported-model recipe SHOULD record:

- Source and candidate vertex/triangle/component counts
- Bounds and units
- Manifold and winding condition
- Orthographic silhouette displacement
- Surface error and volume change where meaningful
- Protected components, landmarks, Material Regions, and boundaries
- Normal/facet clustering thresholds
- Every rejected or accepted step

**Initial proposal:** use silhouette displacement no greater than 0.5% of bounding-box diagonal and RMS
surface displacement no greater than 0.25% as starting review thresholds for a qualifying recipe. These are
not facts derived from Dwarf War and MUST remain versioned, visible recipe fields.

## 17. Rig, Control Point, and Skeleton contract

### 17.1 Rig authority

The Rig is a structured articulation authority over Form Space Nodes, separate from the Geometry artifact.
It contains:

- Rig identity, revision, schema, and solver compatibility
- References to an acyclic Space Node hierarchy
- Stable articulated Control Point, joint, and bone-connection semantic IDs
- Root frame and canonical rest pose
- Parent-relative rest translation in feet, normalized rotation, and positive scale for every articulated node
- Derived reference segment distances where a constraint or solver uses them
- Local axes and declared degrees of freedom
- Complete hinge, swing-cone, twist, distance, scale, and other kinematic constraints as applicable
- Preferred bend/pole directions
- Capability declarations
- Binding identity

The Rig MUST reject cycles, missing parents, non-finite transforms, invalid rotations, duplicate semantics,
and inconsistent limits or constraints.

### 17.2 Control, Semantic Site, and channel profiles

The Rig does not own every semantic handle or surface site. Three independently versioned profiles qualify a
Rig/Form combination:

1. **Control Profile** — Controls, required solver capabilities, affected channels, target mappings, and
   dependencies.
2. **Semantic Site Set** — sites whose locator is rig-local, part-parametric, Binding-derived, or explicitly
   topology-bound.
3. **Animation Channel/Deformer Profile** — complete skeletal and nonskeletal channels, stable IDs/types,
   per-component admission policy (locked, directly animatable, solver-controlled, or derived), defaults,
   ranges, composition rules, and mappings to bones or deformation targets.

A Semantic Site declares its owner and exact dependencies, coordinate frame, locator method, and shape. Site
shapes include point, oriented frame, contact surface, and tolerance volume. Palm, chest, or foot contact
therefore does not collapse to one context-free point. A broad/thin geometry variant may retain the same Rig
while producing a new Site Set whose chest surface follows the changed Body Form.

Core anatomy is not assumed bilateral or humanoid. A Rig may offer any number of manipulators, locomotors,
wings, tails, eyes, or unpaired appendages. Symmetry and mirroring use explicit versioned groups and role
mappings over arbitrary members; they are never a global left/right transform. Analytic two-bone IK is one
initial solver capability, not a universal Rig shape.

### 17.3 Declared transform freedom and body variation

There is no global rule that a Skeleton cannot move or change its parent-relative spacing. The exact Rig and
Animation Channel/Deformer Profile are jointly authoritative: the Profile owns admitted channel values and
the Rig owns kinematic constraints over those values.

- A conventional limb MAY lock local translation and scale, making its parent-to-child distance effectively
  fixed while allowing rotation.
- A telescoping, squash-and-stretch, resizing, mechanical, magical, or editor-construction Rig MAY expose
  positive local scale and/or local translation.
- A Control Point update always changes its own local transform relative to its parent. Forward composition
  then moves every attached Part and descendant Space Node together, including inherited scale.
- A Rig constraint or named solver MUST state whether it preserves distance, permits bounded translation, permits
  bounded scale, or derives a value from another channel. The UI labels that policy instead of implying a
  universal anatomical law.

Changing a primitive radius can make a Form broad or thin without changing the Rig. Changing a rest-space
shoulder width or limb offset creates a new geometry/bind and Rig component revision but can retain a qualified
Rig-family signature. A live change through an already-declared translation/scale channel is Pose state, not a
new Form. Retargetable Behavior Intent can solve against new proportions; exact rig-specific joint values
cannot be assumed valid across signatures.

### 17.4 Binding version 1

Version 1 rigidly attaches each complete primitive shell to one Space Node. Any number of shells may share
that node. Joint balls may attach to a parent node or use a declared blended presentation rule, but the rule
is explicit and deterministic. Changing a node transform changes world-space geometry for every attached
shell and descendant while leaving shape-local topology, UVs, Surface Regions, and Appearance unchanged.

Smooth skinning later adds vertex weights or another deformation Binding. It MUST remain downstream of the
same Rig and MUST declare how topology revisions invalidate or migrate weights.

### 17.5 Kinematic evaluation

Pose Snapshots store complete **absolute parent-local transforms/channels**, not deltas from rest. The rest
Pose uses the Rig's parent-local rest values. Forward kinematics evaluates parents before children in stable
hierarchy order:

```text
worldMatrix(controlPoint) = worldMatrix(parent) * TRS(poseLocal(controlPoint))
```

The exact multiplication and quaternion conventions are versioned and tested. Repeated evaluation from the
same Rig and Pose Snapshot MUST yield the same result within declared numerical tolerance.

The first inverse-kinematics solver SHOULD be an analytic two-segment chain whose initial fixture declares
locked translation/scale with:

- End-effector target
- Pole/preferred-bend target
- Joint limits
- Exact solve when reachable under both chain lengths and declared joint limits
- Deterministic unreachable-target clamp under that fixture's no-stretch constraint
- Stable near-singularity behavior
- Visible failure or residual diagnostic

Iterative multi-chain IK, balance, contacts, foot planting, and physics are later solver capabilities.

## 18. Pose authoring

Pose authoring and Puppeteer field editing are separate modes.

In **Pose** mode, users manipulate:

- Direct joint rotation handles
- Semantic end-effector Pose Controls/Handles such as hand, foot, head, tail tip, or wing tip
- Pole or bend-direction Pose Controls/Handles
- Pins and explicit contacts when the solver supports them
- Numeric values and reset-to-rest operations

A Pose Control is not necessarily a joint. For example, a hand target may solve shoulder and elbow rotations; a
gaze target may solve eyes, neck, and head according to its Control Profile.

“Save Pose” creates an immutable complete Pose Snapshot for one exact motion signature. Every declared pose
channel has a value; untouched channels store their rest/default values rather than remaining absent.
Locked or derived Space Node channels MUST equal their declared rest/derived values. Directly animatable or
solver-controlled local translations and positive scales are saved like rotations and are not rejected merely
because they move or resize the Skeleton relative to its parent.

Before saving, a Pose Snapshot MUST reject missing, duplicate, or unknown channels; non-finite values;
non-positive scales; invalid quaternions; changes to locked/derived translation or scale; and values outside hard
channel or joint limits. A saved exact dot therefore cannot be silently altered by ordinary playback limit
projection.

A Pose Snapshot contains no mesh, UV, material, camera, reference-image, or lighting state.

OF-06 MAY initialize a reviewed Pose candidate by matching the known Form, Rig, semantic Sites, and joint
projections against one admitted six-face Token deck. Equal Form/Rig/deck/registration/matcher/solver inputs
MUST produce the same candidate, per-observation residuals, per-joint confidence, and unresolved-channel
set. Evidence is weighted only by its explicit confidence and visibility. Unobserved or ambiguous channels
remain at the current/rest value and are marked unresolved; they are not guessed merely to complete a
silhouette. The candidate is not a Pose Snapshot and mutates no Rig, Form, selection, history, or durable
state. The user reviews overlays and residuals, may adjust through ordinary Pose Handles, and explicitly
accepts through the same exact-revision Pose command used for manual authoring. A stale deck, Form, Rig,
session, matcher, or solver revision rejects acceptance without partial mutation.

The editor SHOULD support pose labels, reset, update-as-new-revision, explicit mirrored creation where a
semantic mapping exists, and undo/redo. Deleting a pose revision that is referenced by a field or behavior is
denied until references are removed or migrated.

## 19. Puppeteer pose-field contract

### 19.1 Correct conceptual model

The Puppeteer interface is an abstract two-dimensional field of complete poses:

- A dot represents a complete Pose Snapshot.
- The dot's `(x,y)` position is chosen by the author and has no anatomical or time meaning.
- Moving a dot changes its influence layout; it does not edit joints.
- Moving the performance cursor blends nearby field poses into one complete result.
- Directly on a dot, that pose has 100% influence.
- Recording captures a timed cursor path through the field.

The user-facing modes SHOULD be:

- **Teach** — pose the Rig separately, save a Pose Snapshot, and place or update a dot.
- **Perform** — move the cursor and preview continuous pose blending without writing time data.
- **Record** — capture the timed cursor gesture as a Performance Path.

This is intentionally different from a flat anatomical joint-control diagram, which belongs to Pose mode.

### 19.2 Persisted field

A version 1 field logically contains:

```text
fieldId
revision
exactMotionSignature
evaluatorIdentity / evaluatorVersion
rotationChartPoseIdentity / revision
logicalBounds = [-1,+1] x [-1,+1]
dots[]:
  dotId
  xQ / yQ
  poseIdentity / poseRevision
  label
```

Field coordinates are quantized to integer millionths of a logical unit. Canvas resize changes only
presentation; a non-square panel letterboxes the logical square. Duplicate dot coordinates are invalid. The
editor offers replace, move, or cancel rather than inventing an offset.

Every dot and the interpolation-chart Pose reference immutable complete poses compatible with the same exact
motion signature. The chart Pose is an evaluator coordinate anchor, not a composition mask or additive
reference. A Field always evaluates a complete pose. Partial-body ownership is applied later by one Behavior
Layer; neither the Field nor its dots own a composition mask.

### 19.3 Deterministic field compilation

Compilation is independent of dot insertion or serialization order:

1. Zero dots is invalid.
2. One dot yields that pose everywhere.
3. Two dots project the cursor to the connecting segment and linearly blend the endpoints.
4. Collinear dots define one common line. An off-line cursor first projects orthogonally to that line, then
   clamps beyond the two endpoint dots. Dots sort on the axis with greatest range, with X winning ties;
   adjacent enclosing dots blend.
5. Three or more non-collinear dots produce a deterministic Delaunay triangulation using exact integer
   orientation/incircle predicates.
6. All zero-incircle/cocircular cases, including five or more points, use stable-ID symbolic perturbation
   (“simulation of simplicity”). Stable IDs compare by unsigned canonical UTF-8 bytes, never locale. For a
   four-point case this yields the diagonal whose sorted endpoint-ID pair is bytewise first.
7. Inside the convex hull, the containing triangle supplies barycentric weights.
8. On a shared edge, only its two endpoint weights remain, producing the same value from either triangle.
9. Outside the convex hull, the cursor projects to the nearest hull segment. No extrapolation occurs.
10. Equal-distance hull ties use the lexicographically first sorted endpoint-ID pair.
11. Weights clamp to `[0,1]` within a documented numerical epsilon and renormalize to sum to one.

The compiler also enforces a versioned minimum distinct-dot distance and minimum logical triangle area/
conditioning bound so exact predicates cannot admit a field that is numerically unusable during blending.
**Version 1 proposal:** minimum Euclidean separation `0.002` logical units and minimum absolute signed
double-area `1e-6` logical units squared.

This creates local, continuous, piecewise-linear **weights**. Translation/scalar channels are piecewise
linear; rotations use the continuous chart in section 19.4 and are not described as piecewise-linear
quaternions. Version 1 has no hidden spatial smoothing; an offline smoothing operation, if added, creates a
new field/evaluator revision.

### 19.4 Channel blending

For barycentric weights `w_i`:

- Translation and ordinary scalar channels use `sum(w_i * value_i)`.
- Positive scale uses `exp(sum(w_i * log(scale_i)))`.
- Scalars clamp to declared channel ranges.
- Translation and scale channels declared locked remain at rest; admitted bounded/animatable channels blend
  under their exact Rig plus Animation Channel/Deformer Profile.

Joint rotations use one global sign-invariant log/exp chart per Field and joint:

1. Let `a` be that joint's normalized quaternion in the Field's immutable rotation-chart Pose.
2. Normalize source `q_i` and form `delta_i = inverse(a) * q_i`.
3. Negate `delta_i` when needed so its scalar component is positive. Stored `q_i` and `-q_i` therefore map
   to the same rotation. An exact zero scalar uses the sign whose first nonzero `x,y,z` component is positive.
4. Convert `delta_i` to its shortest rotation vector `r_i`: axis times full angle, with
   `angle = 2*atan2(length(vectorPart), scalarPart)`.
5. Require `angle <= 170 degrees` (**proposal**) for every dot/joint relative to the chart Pose. A field that
   needs the excluded antipodal neighborhood must choose another chart Pose or split into another Field.
6. Calculate `r = sum(w_i * r_i)`.
7. Return `q = normalize(a * quaternionExp(r))`, where quaternion exponential uses half-angle sine/cosine.

The fixed chart and continuous barycentric/segment weights make the rotation result continuous across shared
edges and immune to eigenvector degeneracy. It is independent of pose iteration order and quaternion sign.
At an exact dot, log/exp returns that dot's rotation. The evaluator persists and pins its chart Pose and
numeric conventions; changing or re-anchoring the chart creates a new Field revision.

### 19.5 Editing and revision behavior

- Saving a new Rig pose creates a Pose revision before placing it.
- Adding, moving, deleting, or rebinding a dot creates a new Field revision.
- Existing Performance Paths pin the old Field revision and do not change.
- The UI MAY offer an explicit rebind operation with before/after preview.
- Moving a dot MUST NOT alter its Pose Snapshot.
- Updating the Pose associated with a dot means rebinding that dot to a new Pose revision.

These commands participate in transient undo/redo. Durable history remains append-only through object
versions rather than destructive mutation of past revisions.

## 20. Puppeteer recording and playback

### 20.1 Authoritative performance data

The authoritative recorded artifact is the cursor path, not a silently baked collection of joint keys. A
Performance Path pins its exact Rig/channel, Field, Pose, and evaluator revisions. It owns field evaluation
and timing only. A Behavior Layer separately owns masks, composition, placement, and root policy.

One path logically contains:

```text
pathId / revision
exactMotionSignature
fieldIdentity / fieldRevision
fieldEvaluatorIdentity / version
tickRate = 60
sourceElapsedMicroseconds
durationTicks
closedC0Evidence? = {
  exactMotionSignature
  evaluatorIdentity / version
  endpointPoseDigest0 / endpointPoseDigestN
  measuredMaxTranslationScalarAngularErrors
  qualificationTolerances
}
cursorSamples[] = {tick, xQ, yQ}
```

### 20.2 Recording

Recording uses a monotonic clock:

- Pointer-down begins a take at tick zero.
- Movement is captured while held.
- Holding still records elapsed time at the same coordinate.
- Pointer-up ends the take.
- Leaving the visible canvas retains pointer capture and clamps logical coordinates to the field bounds.
- Another drag begins a new take revision.

Raw events use integer microseconds from pointer-down and MUST be monotonic. Same-timestamp events retain input
sequence and the last coordinate at that timestamp wins; out-of-order timestamps are rejected. Logical
coordinates clamp to `[-1,+1]` and quantize to integer millionths by round-to-nearest with exact half ties
away from zero.

The path is resampled at exactly 60 ticks per second by linear cursor interpolation. Its logical duration is
tick-quantized:

```text
durationTicks = ceil(sourceElapsedMicroseconds * 60 / 1,000,000)
```

Samples cover tick zero through `durationTicks`. For a sampling tick after the physical pointer-up time, the
final pointer-up coordinate is held through that ceiling tick. A zero-duration take has one sample at tick
zero. `sourceElapsedMicroseconds` is retained as provenance, but playback duration is the stored tick count.
“Exact hold duration” therefore means exact canonical tick duration, not sub-tick wall-clock preservation.
No implicit temporal smoothing occurs.

Behavior time is independent of display frame rate. Playback at 30, 60, or 144 Hz interpolates the same
cursor samples and MUST produce the same logical pose at the same time within tolerance.

### 20.3 Editing

Version 1 editing is explicit and non-destructive:

- Trim and split on integer ticks
- Loop off by default
- Closed/C0-loop qualification evaluates and compares the complete endpoint poses. Matching first/last cursor
  coordinates within one coordinate quantum is a cheap editor prerequisite, not the normative proof.
- Optional **temporal path smoothing** only as a new Path revision with algorithm/version/parameters

Append, Overdub, concatenation/hard cuts, crossfades, rational time scaling, and velocity-continuous loop
qualification are deferred until their editing and boundary algorithms receive an explicit specification.
Field-space smoothing is a different operation that creates a new Field/evaluator revision.

A separate Bake operation MAY emit ordinary Pose keys or a Solved Clip for interchange and runtime
efficiency. A version 1 Solved Clip stores complete absolute parent-local Pose samples at 60 ticks per second,
the same duration, scene-local root convention, and all source Rig/Field/Pose/evaluator/limit-projector/
solver revisions. Between clip ticks, translation/scalars interpolate linearly, positive scale
logarithmically, and rotations by shortest-arc SLERP; it never extrapolates outside its active interval. The
baked result never replaces the authoritative recorded path. Equality with authoritative Path evaluation is
normative at stored bake ticks only. Between those ticks, the Solved Clip is a declared interpolation
approximation. Bake evidence records its validation-sample policy and measured maximum translation, scalar,
and angular errors. A caller MAY require an error threshold and reject that bake; version 1 does not claim an
unbounded-time intermediate equivalence guarantee. Adaptive subdivision, if later required, is a new
versioned clip format/evaluator rather than a silent change to this contract.

## 21. Behavior layers and composition

Layering is required for combinations such as walking while waving, looking toward someone while carrying
an object, or performing facial expression while the body moves.

Each layer declares:

- Stable identity and unique explicit order
- Exactly one authoritative source: Performance Path, exact Solved Clip, or retargetable Behavior Intent
- `startTick`, effective duration, and contribution interval
- Repeat count (`1` by default; values greater than one or `INDEFINITE` require a nonzero
  closed/C0-qualified source)
- Channel mask with weights in `[0,1]`
- Composition mode
- Opacity in `[0,1]`
- Immutable additive reference Pose when required
- Root-motion ownership
- Structured Capability Requirements and any explicit target-role binding
- Pinned versions for every live limit projector, contact solver, or IK solver
- Optional semantic constraints; events are reserved but deferred in version 1

For a finite source, the active interval is inclusive:
`startTick <= t <= startTick + effectiveDurationTicks`. Before or after that interval, a layer contributes
nothing and the underlying pose is unchanged. Endpoint holding requires an explicit longer path/layer
duration; it is never inferred. A zero-duration layer contributes only at its exact start tick. Hard
discontinuities require a future explicit segment/cut representation and are not synthesized by linear
samples.

For repeat count `R = 1`, no modulo operation occurs: local time is the source time, including evaluation of
the source endpoint at tick `N = sourceDurationTicks`. A zero-duration source is legal only in this mode and
contributes at the layer's exact start tick.

For finite `R > 1`, effective duration is `R * N` and `N` MUST be positive. Internal cycle boundaries
`k * N`, where `0 < k < R`, may evaluate source tick zero because qualification proves that source ticks zero
and `N` have equal complete poses. The final finite boundary `R * N` MUST explicitly evaluate source endpoint
tick `N`, never its modulo-zero alias. `INDEFINITE` likewise requires positive `N`, has no terminal endpoint,
and maps each boundary to tick zero until explicitly stopped.

Closed/C0 qualification is source-specific and stored as evidence, not inferred merely from a repeat flag:

- A Performance Path qualifies only when its complete evaluated poses at ticks zero and `N` are equal within
  the exact motion tolerances. Equal endpoint cursor coordinates are a useful cheap prerequisite, but pose
  equality is normative.
- A Solved Clip qualifies only when its complete stored endpoint poses are equal within those tolerances.
- A Behavior Intent qualifies only when its compiler/solver declares a closed endpoint constraint and emits
  qualified solved evidence for the target exact Rig.

Sources that fail this qualification cannot use `R > 1` or `INDEFINITE`; no discontinuous wrap is synthesized.

Root translation and rotation have zero mask weight unless explicitly enabled. Across overlapping active
intervals, exactly one source—including the base behavior—may own absolute root motion. Additive root layers
may coexist only when explicitly declared, use the same scene-local coordinate convention, and do not claim
absolute ownership.

Version 1 supports:

1. **Override:** define `alpha = clamp(opacity * channelMaskWeight, 0, 1)` and blend the incoming pose toward
   the layer pose. Quaternion override uses shortest-arc SLERP.
2. **Additive:** calculate the layer pose delta from an immutable reference pose. Translation/scalar deltas
   add, scale ratios combine logarithmically, and rotational delta applies as:

```text
qOut = qIn * exp(alpha * log(inverse(qReference) * qLayer))
```

All quaternion inputs normalize first. The additive delta is negated when necessary to select its shortest
branch. At exact 180 degrees, the sign whose first nonzero `x,y,z` component is positive wins. This makes
additive evaluation invariant to stored `q` versus `-q`. Joint/Control Point translation is parent-local. The
exact Animation Channel/Deformer Profile declares whether local translation and positive scale are locked,
bounded, directly animatable, solver-controlled, or derived; the exact Rig supplies applicable constraints.
Root translation is scene-local. Other Behavior Intent target spaces are declared explicitly.

Duplicate explicit layer order is rejected. Layers apply in ascending order and every shared-channel
conflict is visible. The runtime sequence is:

```text
sample base behavior and Puppeteer paths
-> evaluate each pose field
-> compose masks, priorities, override, and additive layers
-> run only explicitly declared, version-pinned contact/IK solvers that themselves honor limits
-> perform final ordinary limit projection/validation
-> report unsatisfied residuals
-> normalize output pose
-> render
```

OF-08A/OF-08B version 1 stops after ordinary final joint-limit projection; it does not infer or run live contacts,
balance, foot planting, or IK from nearby dots. Event channel types are reserved, but ordering, looping,
seeking, reverse-playback, and crossfade semantics are deferred before runtime event emission.

Quaternions interpolate rotations; they do not resolve ownership conflicts. Masks, order, composition mode,
root-motion rules, and constraints are therefore first-class persisted data.

## 22. Behavior Intent, Solved Clip, and retargeting

Motion is stored at two levels when retargeting matters:

1. **Behavior Intent** — semantic targets, Pose Controls, paths, contacts, timing, dynamics, channel masks, events,
   and required capabilities.
2. **Solved Clip** — dense complete permitted parent-local TRS/channel values, scene-local root transforms,
   and deformation values for one exact Rig, produced by a named solver/version.

Raw local transforms alone are not safely retargetable. If shoulder width changes, a clip whose hands once met may
make them miss. A retargetable intent instead records goals such as `hands.palms meet`, `hand at chin`, or
`foot planted`, then solves those goals against the target object's qualified Semantic Site Set, Control
Profile, channel/deformer profile, Rig, and proportions.

Every persisted behavior declares exactly one compatibility scope:

- **`exactRig`** — a Pose Snapshot, Puppeteer Field/Performance Path, or Solved Clip bound to one exact motion
  signature.
- **`rigFamilyRetargetable`** — reviewed Behavior Intent that can be solved against a compatible Rig family
  and qualified Semantic Site/Control profiles.
- **`capabilityMapped`** — Behavior Intent whose Capability Requirements are satisfied through one explicit,
  reviewed role mapping to a target's Capability Offer.

A Puppeteer path is `exactRig` by default. It cannot silently become retargetable Behavior Intent merely
because its movement looks semantic. A Behavior revision selects one authoritative source: exact
Performance Path + Field, exact Solved Clip, or retargetable Intent + named solver. Other forms are derived
caches/fallbacks and never silently replace that authority.

Each retargetable target declares its coordinate space and normalization policy: root-relative,
body-normalized, Semantic-Site-local, scene/object-relative, or a future discourse/signing locus. Role
binding supports arbitrary appendage cardinality rather than hard-coded humanoid left/right channels.

Every solver result MUST record its solver identity/version and input revisions. A solver upgrade never
silently changes an existing saved performance. Re-solving is an explicit operation that creates a new
revision and permits comparison with the retained baked fallback.

Initial walking and striking MAY begin as exact Rig-specific clips. Retargeting is a separate capability and
MUST fail visibly when requirements, role binding, Controls, Sites, channels, or solver qualifications are
absent.

## 23. Persistence, derivation, and object lifecycle

### 23.1 Draft versus durable state

Editor changes exist in a transient draft until Save. Preview, import inspection, UV compilation, projection,
pose-field editing, and provider review MUST NOT mutate a durable Character Object merely because a worker
finished.

A draft has an immutable revision/epoch. Every asynchronous result identifies the exact draft revision from
which it began. If the draft changes, a late candidate is stale and cannot replace current state.

### 23.2 Save operations

Object Factory MUST extend the existing Factory Object operation path:

- **Save New** creates a new logical object and first version.
- **Save** appends a version to one exact current object identity and rejects a stale writer.
- **Save As** creates a distinct logical object from the exact current snapshot.
- **Reload** reconstructs an equivalent draft from admitted authoritative records and artifacts.

This requires a real package-model extension, not simply placing more bytes into the current fixed request.
The proven current Factory kinds are closed around walls/surfaces/tables; the request shape has fixed recipe,
OBJ/STL/MTL/UV-preview/guide/diffuse roles; and current reload reconstructs recipe plus OBJ rather than an
arbitrary native component graph.

OF-05A therefore introduces strict logical kinds `BODY_FORM` and `CHARACTER` plus a versioned Native Package
request. Each object version owns one outer manifest and ordered component memberships:

```text
objectIdentity / objectVersion / nativeKind
manifestAssetIdentity / manifestSchemaVersion
components[]:
  stableComponentIdentity
  componentRole
  componentSchemaVersion
  componentRevision
  admittedAssetIdentity
  contentDigest
  required
  ordinal when the role is repeatable
```

Version 1 roles include Form recipe, indexed-geometry evidence/canonical OBJ, MTL, active diffuse, Atlas
Contract, `regions.json`, guide/coverage/region PNGs, Rig, Binding, Control Profile, Semantic Site Set,
Channel/Deformer Profile, Pose, Field, Performance Path, Behavior Intent, Solved Clip, reference registration,
Source Asset, conversion receipt, appearance instructions, provider evidence, Review Record, and preview.
Optional roles are explicitly absent;
required roles are defined by native kind and capability declaration.

The manifest pins exact identities, revisions, schemas, roles, digests, compatibility signatures, and
capabilities. Load verifies the entire required graph before publishing an immutable loaded result. Missing,
duplicate, unknown-required, hash-mismatched, or incompatible components fail closed. Legacy Factory requests
and reload behavior remain supported through their existing path; they are not silently reinterpreted as
Body Forms.

The operation commits all required memberships, derivation links, artifacts, and object-version records in
one transaction. Cancellation, stale result, validation failure, or storage failure leaves no partial graph.

### 23.3 Absorb, do not inherit

When a new object starts from a Template, Body Form, Character, or imported source, it absorbs exact component
revisions. It does not retain a live “latest type” link.

This satisfies the intended derivation behavior without mutable inheritance:

- A saved dragon remains the dragon that was accepted.
- Updating the shared Template affects only new compilations or explicit migrations.
- Save As produces an independently editable identity.
- Existing uses pin exact revisions, so “locking once used” is not required for safety. The UI may prevent
  destructive editing of a referenced revision by requiring a new version or Save As.

### 23.4 Provenance

Each saved revision records, where applicable:

- Source Object and component revisions
- Template, compiler, UV packer, solver, field evaluator, and conversion recipe versions
- Input and output digests
- Reference image identities and registrations
- Six-face Token deck role identities, accepted-Front ancestry, camera/registration versions,
  per-observation confidence, residuals, and unresolved evidence when pose matching is used
- Appearance creation method
- Provider request/result evidence and configured model identity
- Immutable Review Records
- Import source, license, declared rights, and transformation receipt

Provenance is append-only evidence. Display names may change by new object/version metadata; hashes and
historical receipts do not.

A Review Record names every exact dependency it evaluated; reviewer role and qualification; language/
variant when applicable; method; audience and permitted use context; findings; result; and supersession or
invalidation rules. When a dependency changes, the old verdict becomes inapplicable but remains preserved.

Human-derived motion additionally reserves protected creator/signer identity, consent scopes for capture,
derivatives, retargeting, redistribution, and machine use, license, permitted contexts, source hashes, and a
withdrawal policy.

## 24. Application-service and thread boundaries

The implementation MUST follow Talisman's typed application route:

```text
UI client
-> bounded typed intent
-> UI-independent Behavior/adapter
-> owning application/domain service
-> immutable progress snapshot or terminal result
-> UI projection
```

Representative intent families include:

- Create or derive Body Form draft
- Apply validated form-parameter command
- Compile geometry and Atlas Contract
- Register/remove reference image
- Project reference views
- Import and convert source
- Save New / Save / Save As
- Save Pose and edit Puppeteer Field
- Record/edit/play Performance
- Assemble/request/review Appearance generation

The exact Java names are an implementation-body decision, but there MUST be one semantic authority for each
mutation. UI panels, Viewer surfaces, and provider adapters MUST NOT become competing domain owners.

Heavy geometry compilation, UV packing, image projection, file conversion, IK baking, and provider calls run
on bounded workers. Swing/JavaFX presentation threads MUST NOT block on them or call one another
synchronously. Every asynchronous intent provides immediate busy acknowledgement, duplicate-activation
protection, cancellation where meaningful, stale-epoch rejection, disposal behavior, and one terminal
outcome.

Bus delivery means only that an intent was delivered. It is not proof of operation acceptance, saved state,
or provider completion. UI truth derives from immutable service outcomes and revisions.

## 25. Component ownership

| Component | Owns | Must not own |
|---|---|---|
| **Object Factory domain/application service** | Templates, Form recipes, programmatic geometry, semantic surfaces, Atlas Contracts, Rig/Binding, pose authoring, Puppeteer fields, behavior authoring, compatibility, candidate validation | Direct SQL, raw credential access, generic byte-store duplication, Viewer rendering internals |
| **Assets/Factory Object services** | Checked artifact admission, durable object identity/version lifecycle, membership, derivations, transactions, stale-writer guards | Geometry design, UV semantics, pose interpolation, provider prompting |
| **Shared Import/Conversion service** | Format adapters, source inspection, normalization, conversion receipts, deterministic recipe execution | A private Object Factory persistence path or guessed semantics |
| **3D Viewer** | Rendering, camera, picking, presentation resources, mesh wireframe diagnostic, static or later articulated realization | Form truth, semantic-region authority, UV policy, save lifecycle |
| **Texture projection services** | Exact view-to-surface-to-atlas projection and confidence evidence | Inventing unseen coverage or changing geometry |
| **Provider/settings boundary** | Credentials, selected provider/model, network execution, provider evidence, cancellation | Geometry/UV decisions, silent paid calls, automatic acceptance |
| **Object Factory appearance assembler** | Exact guide/mask request, appearance instructions, output validation, review candidate | Provider credentials or durable byte admission |
| **Runtime behavior engine** | Sampling accepted behaviors, deterministic layer composition, event emission | Editing authoritative fields or rewriting saved behavior revisions |
| **GM/Player/Scripted Walkthrough consumers** | Addressing and invoking accepted object/behavior identities in their own workflows | Gating Object Factory construction or owning its editor semantics |

The permanent owner of the shared Import/Conversion platform lane should be confirmed through normal Talisman
coordination before its implementation body. Regardless of task assignment, these component boundaries are
normative.

Current Assets Manager Factory design explicitly defers rigs and animation. The permanent Object Factory
task must establish its own canonical Active Design/Atlas seat and, before OF-03, decide one presentation
question: Object Factory may be a separate product window or a clearly bounded Assets Factory mode. Either
choice uses the same Viewer, Assets persistence, provider, and application-service owners above; it cannot
move semantic behavior into the screen or create parallel stores. This is a UI placement decision, not a
reason to delay OF-01/OF-02 domain contracts.

## 26. Natural-language assistance boundary

The product may let a user describe an Appearance conversationally. The description can become palette,
surface, marking, wear, clothing, and regional painting instructions.

Body construction remains programmatic. A conversational convenience may later translate phrases such as
“broader chest” into a visible, bounded parameter command, but:

- It operates over the same explicit Template controls available in the editor.
- It shows the resulting parameter changes before acceptance.
- It cannot add unsupported topology or bypass validation.
- It is independent from the image provider.
- It never makes AI-generated mesh bytes authoritative.

The first AI implementation SHOULD therefore focus only on painting a known Atlas Contract.

## 27. Future body language and signed-language compatibility

Signed-language work is not required for the first release, but the core MUST avoid assumptions that would
make it impossible later.

### 27.1 Reserved architectural concepts

The following remain distinct from the beginning:

- Rig versus Geometry
- Controls versus joints
- Landmarks versus raw vertex indices
- Behavior Intent versus Solved Clip
- Manual versus nonmanual motion tracks
- Motion validity versus language review
- Generic behavior metadata versus signed-language provenance

Track types reserve position, quaternion, scalar, vector, event, and constraint channels. Capability
declarations can later include:

- Pelvis, spine, torso, neck, head, shoulders, and clavicles
- Full arms, wrists, hands, fingers, and thumbs
- Handshape, palm orientation, fingertip and palm Landmarks
- Hand-to-hand, hand-to-body, foot, and object contact
- Gaze, eyes, eyelids, brows, cheeks, mouth, lips, jaw, and facial deformation
- Path direction, size, speed, holds, repetition, attack/sustain/release, and transitions
- Dominant hand, symmetry, handedness, and explicit mirroring
- Frame-accurate parallel manual and nonmanual timing

These names and extensible channel types are reserved; most need not be implemented in the first Rig.

### 27.2 Signing-ready is not language-approved

A mechanically signing-ready Rig eventually requires complete finger/thumb articulation, body-relative 3D
signing space, contact-preserving IK, facial/mouth/gaze channels, synchronized tracks, and sufficient visual
clarity. That capability does not make a generated gesture valid ASL or another signed language.

Candidate signed behavior MUST record:

- Actual signed language and regional/community variant
- Source signer/author or protected identity
- Consent, license, permitted uses, and transformation history
- Exact Rig/behavior revisions
- Candidate/reviewed status

English gloss is metadata, never the sole language specification. Released signed-language content requires
Deaf bilingual leadership and community comprehension review. Mechanical, retargeting, naturalness,
linguistic, accessibility, and deployment approvals are separate statuses, not one `approved` flag.

A language/accessibility Review Record pins or constrains the exact Rig, Control Profile, Semantic Site Set,
channel/deformer profile, solver, behavior/clip, Appearance visibility/contrast, and presentation profile it
reviewed. A texture or material change preserves mechanical motion compatibility but may make that review
inapplicable—for example when hand/face contrast or visibility changes.

Signing qualification applies only to a specifically qualified anatomical/profile combination. An arbitrary
dragon or other creature never inherits human signing capability merely because it has manipulators.

High-stakes interpretation remains a separate governance decision and cannot be authorized by technically
successful playback.

### 27.3 Future notation/import adapter

HamNoSys, SiGML, motion capture, BVH, or glTF animation may later enter through adapters that produce
Behavior Intent and/or Solved Clips. Their external schemas MUST NOT become the core Object Factory domain
model.

[Sign Language as a Root Morph Capability](SIGN-LANGUAGE-MORPH-CAPABILITY.md) is the controlling design for
the native Morph question/answer contract, capability-based retargeting, language identity, foreign evidence,
and source-authoritative default library with safe database materialization. It authorizes no implementation.

## 28. Nonfunctional requirements

### 28.1 Determinism

- Equal normalized procedural input and compiler versions produce byte-identical pre-provider artifacts.
- Stable IDs and ordering do not depend on thread scheduling, locale, or map iteration.
- Motion evaluation is frame-rate independent and deterministic within declared numeric tolerances.
- Provider results are immutable evidence, not claimed deterministic recomputation.

### 28.2 Safety and data integrity

- All external bytes pass checked admission.
- Preview does not imply commit.
- Cancellation, stale epoch, and error produce no partial object revision.
- Source assets and prior object versions are never overwritten.
- Missing capabilities and unsupported formats fail visibly.
- No hidden paid call or provider-side mutation occurs.

### 28.3 Boundedness

Recipe schemas bound part count, facet count, triangle count, atlas size, references, pose dots, track count,
duration, file bytes, and worker concurrency. Rejections include actionable diagnostics.

### 28.4 Performance

- Parameter editing and forward-kinematic preview SHOULD feel interactive for admitted low-poly forms.
- Expensive compilation is cancellable and does not block presentation threads.
- Puppeteer preview SHOULD evaluate within one display frame for admitted Rig/field bounds.
- Runtime may cache compiled triangulations, transforms, and Solved Clips by immutable content identity.

### 28.5 Inspectability

The product exposes:

- Geometry counts and validation
- Selected Surface Region and exact atlas correspondence
- Seam, island, gutter, and density evidence
- Projection coverage/confidence
- Rig hierarchy, limits, and active controls
- Puppeteer weights and contributing dots when requested
- Behavior layers, channel conflicts, constraints, and residual failures
- Source/conversion/provider provenance

### 28.6 Accessibility and usability

Every visual handle has a named semantic identity and keyboard/numeric alternative where practical. Color-only
region displays also expose labels and selection outlines. Asynchronous actions acknowledge immediately with
a specific active verb and accessible busy/live state.

## 29. Implementation gate

No Talisman implementation may begin until this permanent ownership unit exists and is verified:

- Worktree: `/Users/mmiller/Git/talisman-git/talisman-object-factory`
- Branch: `codex/object-factory`
- Upstream: corresponding published origin branch
- Saved local Codex project: the existing named worktree, not a Codex-generated checkout
- Permanent pinned task: **Object Factory**, attached to that saved project
- Starting point: exact current `origin/main`
- State: correct path, correct branch/upstream, clean, and upstream-equal

The 2026-08-29 read-only worktree inventory proves that this worktree did not yet exist at specification
time; `talisman-main` was clean and equal to `origin/main` at `2d95595f`. This is an audit baseline, not the
future branch point—the permanent task must use whatever exact `origin/main` is current when authorized.
This document does not authorize creating it from the projectless task. When implementation is
requested, stop and obtain the permanent named worktree/project/task gate through the normal Talisman setup
process.

After attachment, the permanent task must read and apply its own current copies of `AGENTS.md`,
`AGENTS.project.md`, the Development Architecture Atlas and relevant companions, and `AGENTS.index.md` before
repository planning or edits. Every implementation body maintains the canonical Object Factory Active
Design and cumulative `TEST-PLAN.md` required by current Talisman policy.

Atlas Bodies 6, 7, and 8 remain optional later integration consumers. They are not Object Factory start
gates.

## 30. Proven foundations and audited references

### 30.1 Current Talisman foundations

The following are **proven** in the read-only current source and should be extended rather than duplicated:

| Foundation | Proven role |
|---|---|
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/services/app/factory/FactoryIndexedMesh.java` | Immutable X-right/Y-up/Z-forward indexed mesh; one normal and UV per expanded vertex; finite, area, winding, closed/manifold, material, and UV validation. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/services/app/factory/FactoryFurnitureGenerator.java` | Deterministic faceted cylinder and prism construction precedent. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/services/app/factory/FactoryFurnitureFaceUv.java` | Stable semantic part/face, facet, vertex span, dimensions, and exact UV-bound evidence; close to the Surface Region contract. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/services/app/factory/FactoryModelCandidate.java` | Existing immutable generated-model candidate boundary. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/services/app/factory/FactoryPhysicalFaceUv.java` | Existing physical-face/UV semantic evidence beyond furniture. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/services/app/factory/FactoryTextureAtlas.java` | Existing deterministic Factory atlas artifact precedent. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/database/userassets/FactoryRecipeCodec.java` | Existing strict Factory recipe decode/admission seam. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/services/app/CanonicalModel3DObjWriter.java` | Deterministic canonical OBJ with one-to-one `v/vt/vn` output and optional MTL binding. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/services/app/texturemapping/ObjMaterialWriter.java` | Deterministic MTL text, including `Ks 0`, `d 1`, and optional `map_Kd`; caller-side safe-basename validation remains required. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/services/app/texturemapping/TextureModelOrientation.java` | Exact reviewed Front orientation. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/services/app/texturemapping/TextureReferenceView.java` | Front-first reference-camera ring. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/services/app/texturemapping/TextureProjectionRenderer.java` | Calibrated surface/view visibility and screen-pixel correspondence. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/services/app/texturemapping/TextureProjectionBaker.java` | Depth-tested visible/front-facing projection into an existing UV atlas with explicit unresolved coverage. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/services/app/texturemapping/UvMeshInspection.java` | Existing mesh/UV inspection and correspondence evidence. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/services/app/texturemapping/UvAtlasArtifactRenderer.java` | Existing flattened atlas guide/inspection rendering. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/services/app/texturemapping/TextureProjectionPainter.java` | Existing controlled projection-painting seam. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/services/app/texturemapping/TextureProjectionReprojectionValidator.java` | Existing reprojection validation seam. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/services/app/texturemapping/TalismanUvPipeline.java` | Existing public UV preparation route, including generated and known-UV paths. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/ui/mapeditor/viewer3d/StlObjPreviewSurface.java` | Existing registered-image overlay and mesh-edge Wireframe presentation precedent. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/database/userassets/FactoryObjectOperationService.java` | Application-scoped operation lifecycle and existing Save route. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/database/userassets/FactoryObjectLibraryService.java` | Stable Factory Object identity, append-only versions, exact Save, and snapshot Save As. |
| `/Users/mmiller/Git/talisman-git/talisman-main/src/main/java/com/moondance/talisman/app/database/userassets/FactoryModelAdmissionService.java` | Checked artifact persistence/admission boundary. |

The existing furniture packer's equal grid is useful evidence but not the chosen character-atlas policy.
The current OBJ/Arena mesh model is static and has no Rig, weights, IK, Pose, Puppeteer, or behavior authority.
Existing whole-object waypoint movement is a scheduling precedent only, not a skeletal motion engine.

The current public UV pipeline may generate replacement UVs, and its known-UV adapter represents admitted
UVs as one chart rather than preserving Object Factory semantic island IDs. Object Factory therefore needs a
`preserve program UV + semantic charts or fail` adapter. It MUST NOT route a compiled Atlas Contract through
an unwrapper that silently repacks or collapses its semantic chart identity.

The local `talisman-main` worktree was clean during this read-only inspection. No build, test, launch, or
source modification was performed.

### 30.2 Cet reference fixture

Conversational “Set” was confirmed by Mark as **Cet**. The exact source inventory is:

| Role | Exact path | SHA-256 / fact |
|---|---|---|
| Unity source | `/Users/mmiller/Git/Seasons World Content/Characters/Cet/3D/Cet Young_TTS_assetBundle.unity3d` | `095ece075ef2697129a6bf807af74109a03eae7103f5f7404e9ab14c34ef358d` |
| Adjacent OBJ | `/Users/mmiller/Git/Seasons World Content/Characters/Cet/3D/Cet Young TTS Assets/Cet Young TTS Mesh.obj` | `dea5cc191a97a006ea45d418059e4fa41da3fdb17d3694d164000ac15ba4f0c1` |
| Diffuse | `/Users/mmiller/Git/Seasons World Content/Characters/Cet/3D/Cet Young TTS Assets/Cet Young TTS diffuse.png` | 2048x1024 RGBA; `82504efc415b1bb73f30ef98756cccc0d5d65c1e5f9cb77671eac10637065bbf` |
| Emissive | `/Users/mmiller/Git/Seasons World Content/Characters/Cet/3D/Cet Young TTS Assets/Cet Young TTS emissive.png` | 2048x1024 RGBA; `2730851d04468f26f3878b3fddf610ce0b5337e86897eefd71df1f8d355031c1` |
| Metallic/gloss | `/Users/mmiller/Git/Seasons World Content/Characters/Cet/3D/Cet Young TTS Assets/Cet Young TTS metal.png` | 2048x1024 RGBA; `2604005e128b5c752e2cecf9f309aa8989b7723450bb44b0ff568b40154e376a` |
| Normal | `/Users/mmiller/Git/Seasons World Content/Characters/Cet/3D/Cet Young TTS Assets/Cet Young TTS normal.png` | 2048x1024 RGBA; `4c4c2b03ac427fc6b20b37611f310eac6d6201dc2da755f7bb13ec17f372c9e7` |
| Occlusion | `/Users/mmiller/Git/Seasons World Content/Characters/Cet/3D/Cet Young TTS Assets/Cet Young TTS occlusion.png` | 2048x1024 RGBA; `941dfeb02da6e1a41b7328cbc6a91a6c6d919897512db156fb3ba214a396e3e1` |
| Printing comparison | `/Users/mmiller/Git/Seasons World Content/Characters/Cet/3D/Cet Young.stl` | No UV; `781d6f9153758f76b54702a70e1547c88051dab72f309bdd2678186dd002ed97` |

The OBJ has 53,833 positions, UVs, and normals; 92,910 triangles; 46,553 exact welded positions; 50 closed
components; 384 UV islands; and no adjacent MTL declaration. Its five texture roles are proven through the
Unity material bindings `_MainTex`, `_EmissionMap`, `_MetallicGlossMap`, `_BumpMap`, and `_OcclusionMap`.

Cet proves the exact mesh/UV/texture relationship and visually recognizable island organization. Semantic
names for particular islands remain human inference. Cet is not the Object Factory low-poly budget or
topology template.

The full evidence, measurement method, hashes, proven-versus-inferred distinctions, and side-by-side image
are in `REFERENCE-AUDIT.md` and `images/evidence/cet-set-uv-and-textured-object.png` beside this design
package.

### 30.3 Dwarf War conversion evidence

The exact batch seam is:

`/Users/mmiller/Git/dwarf-war-combat-simulator/tools/blender/prepare_cavern.py`

It imports practical STL inputs, joins them, applies Blender collapse decimation at the requested ratio,
triangulates, marks all polygons smooth, and emits OBJ plus metrics JSON and optional `.blend`. It has no
numeric silhouette, landmark, component, manifold, UV, or material-preservation guarantee.

The exact local planar-edit seam is:

`/Users/mmiller/Git/dwarf-war-combat-simulator/web-lab/src/main.js`

It can select a user-seeded nearly coplanar connected region and project selected vertices to one plane. It
is local and reviewed, not an automatic global silhouette-preserving faceter or export pipeline.

The portable evidence is staging, candidate metrics, immutable recipes, original preservation, snapshots,
visual approval, and local plane-selection tolerances. Talisman MUST independently implement any generic
algorithm and MUST NOT depend on Dwarf War.

### 30.4 Evidence classification

| Classification | Examples |
|---|---|
| **Proven current behavior** | Talisman static Factory mesh/writers, projection services, Save As snapshot semantics, exact Cet files/bindings/counts, exact Dwarf operations. |
| **Confirmed product direction** | Programmatic geometry and UV; canonical feet; hierarchical Control Point spaces with inherited translation/rotation/scale; declared rather than universal fixed-length constraints; AI texture maps; one form/many appearances; general import; Skeleton label; whole-pose Puppeteer dots; snapshot derivation. |
| **Chosen implementation design** | Structured native authority, rigid-shell first binding, deterministic triangulated pose field, path-authoritative recording, layer masks, explicit owner boundaries. |
| **Initial proposal** | 1024x1024 default atlas, 8-pixel gutter, 4-pixel dilation, +/-5% equal-weight density, 170-degree Field-chart bound, reduction thresholds. |
| **Future/deferred** | Smooth skinning, glTF/GLB delivery, automatic retargeting, advanced contact/physics, signed-language content and review. |

Three boundaries deserve explicit caution:

- Current Talisman generation can color calibrated 3D views and project them into an atlas. Direct AI
  painting of the exact flattened 2D atlas is confirmed product direction, not a proven current route.
- Cet proves island/seam geometry and texture binding. Human names assigned to individual Cet islands are
  visual inference because the source package has no semantic-region manifest.
- The Puppeteer field triangulation, log/exp rotation chart, path schema, and layer mathematics in this document are
  chosen new Object Factory design. They are not claimed as DAZ internals or existing Talisman behavior.

## 31. Predicted implementation bodies

Use `OF-*` identifiers so implementation bodies cannot be confused with anatomical Body Forms. Each body is
independently reviewable, focused-testable, committable, and publishable from the permanent Object Factory
worktree. Bodies may be refined before execution, but their ownership boundaries should remain intact.

### OF-UI-00 — Object Factory experience design

**Status: completed and approved by Mark on 2026-08-29.** The exact approved image set is frozen in
`APPROVED-UI-REFERENCE.md`.

This non-code design body is the visual contract for every later UI-bearing body. It contains the complete
screen and dialog inventory, shared editor shell, final terminology, labeled central-workspace image plates,
responsive/expected-width compositions, accessible interaction rules, operation-feedback states, and the
reviewed multi-screen journey. It records which controls invoke typed application intents and which are
presentation-only.

Acceptance:

- Every screen, panel/section, primary action, modal/sheet, and owner body is identified.
- Build clearly presents `Control Point -> attached Shapes + child Control Points` and parent-relative TRS in
  canonical feet with explicit display conversion.
- Skeleton Setup displays each node's admitted Position, Rotation, and Scale policy rather than claiming a
  universal fixed-length skeleton.
- OBJ, MTL, `regions.json`, hashes, and provider details stay behind Advanced/Review surfaces.
- Empty, busy, cancelled, stale, failed, unsaved, incompatible, and destructive/revision-changing states are
  visually specified.
- Asynchronous actions acknowledge activation immediately with an active verb, stable progress, cancellation
  where allowed, and accessible busy/live state.
- Paid appearance generation has an explicit reviewed action and cannot occur silently.
- Mark explicitly approved the images and journey on 2026-08-29. OF-03, OF-04, OF-06, OF-07, OF-08*,
  OF-10, and OF-P01 UI work remain separately dependency-gated implementation bodies.

### OF-01 — Native Form contract and deterministic arm fixture

This is the exact minimum first implementation body.

Build a pure, UI-independent fixture containing shoulder ball, upper-arm faceted cylinder, elbow ball,
forearm tapered cylinder, and flattened hand. It establishes:

- Form recipe/schema boundary and stable semantic IDs
- Canonical-foot, Space Node, Control Point, local-transform, and socket conventions
- Any-number Part attachment beneath one Space Node, plus inherited descendant translation/rotation/scale
- Closed deterministic primitive shells
- Named Surface and Material Regions
- Back-seam cylinder UV mapping and cap islands
- Exact faceted sphere/ellipsoid rings, pole fans, seam expansion, normals, and UV mapping
- Deterministic atlas packing
- OBJ, automatic MTL, active base `diffuse.png`, guide/coverage/region PNGs, and `regions.json`
- Side-by-side flattened-atlas and checker-textured 3D inspection artifact
- Explicit absence of Rig/Binding/motion components without placeholder identities

**Fixture proposal:** 12 side facets; 1.00 ft upper arm; 0.23 ft upper radius; 0.85 ft forearm; 0.21 ft to
0.16 ft forearm taper; 0.25 ft elbow radius; 0.46 x 0.30 x 0.13 ft hand ellipsoid. These values are fixture
data, not a universal anatomy standard. A display may render them as feet-and-inches or another explicit
unit conversion while persisted values remain feet.

**Implemented OF-01 baseline (2026-08-29):** `ObjectFactoryNativeFormRecipe`,
`ObjectFactoryNativeFormCompiler`, and `ObjectFactoryNativeFormCandidate` now own this pure direct-call
boundary. The fixture uses the stated dimensions and stable five-Part/four-Space-Node identities, with
small deterministic shell overlaps, 456 triangles, five named Surface Regions, one `body` Material Region,
nine fixed integer islands, and the required static artifacts plus checker and side-by-side inspection
evidence. This implementation adds no Rig, Binding, motion placeholder, UI, import, durable owner, Viewer
source, provider, database, or application route.

Acceptance:

- Equal canonical recipe produces byte-identical artifacts.
- Every triangle belongs to exactly one named Surface Region and Material Region.
- Shells are finite, non-degenerate, outward-facing, closed, and manifold.
- UVs are in bounds, non-overlapping, correctly padded, and exact at the rear seam.
- Dimension-only changes preserve semantic topology and atlas layout within the recipe version.
- Changing the PNG changes only appearance.
- No database, provider, imported model, Viewer-owner source, or existing Factory object is modified.

### OF-02 — Rig, rigid Binding, Pose Snapshot, and forward kinematics

Add the authoritative Rig records and constraints over OF-01 Space Nodes, parent-relative rest TRS,
hierarchy validation, rigid Part attachments, Control Profile, initial Semantic Site Set, Channel/Deformer
Profile with per-component admission policies, complete Pose Snapshots, pure forward
kinematics, immutable Skeleton presentation state, and optional posed-OBJ bake.

Acceptance:

- Cycles, invalid parents/transforms/quaternions, and limit inconsistencies fail closed.
- Rest pose reproduces OF-01 geometry exactly.
- Repeated evaluation is deterministic within tolerance.
- Posing changes world transforms but never topology, UVs, regions, or texture.
- The fixture's locked translation/scale channels preserve its declared segment distances; a separate focused
  fixture proves that an admitted local translation or positive scale moves its complete descendant space.
- OBJ remains explicitly derivative rather than Rig authority.

**Implemented OF-02 baseline (2026-08-29):** `ObjectFactoryRig` qualifies the exact OF-01 recipe with four
Rig nodes, five complete-shell rigid bindings, stable Controls/Sites/reference segments, complete per-node
translation/rotation/scale policy, hard rotation limits, and explicit capabilities. Complete canonical
`ObjectFactoryPoseSnapshot` bytes fail closed on missing/unknown/invalid or disallowed channels.
`ObjectFactoryForwardKinematics` evaluates parents first and produces immutable world frames, Skeleton
joints/bones/sites, a posed closed mesh, and optional canonical OBJ. Rest returns exact OF-01 geometry;
posing never changes topology, UVs, Surface/Material Regions, or Appearance artifacts. The flexible proof
Rig separately admits bounded parent-local translation and positive scale. No IK solver, UI, persistence,
Viewer source, provider, application route, or later Object Factory body is present.

### OF-03 — Body Form editor and reference registration

After OF-02 and approved OF-UI-00, add the approachable editor with Skeleton, Solid, Textured, and separate
Mesh Wireframe presentations; a `Control Point -> attached Shapes + child Control Points` hierarchy;
parent-relative Position/Rotation/Scale controls; primitive parameters; Front/Side/Back orthographic views;
one to three independently registered reference images; selection; and transient undo/redo.

Acceptance:

- A straight-on SRD image can be aligned without altering its bytes.
- Registration survives mode and view switches.
- Form and joint editing routes only through typed draft commands and OF-01/OF-02 authorities.
- Reparenting explicitly preserves either world placement or current local values according to the reviewed
  user choice; it never guesses.
- Front drag preserves depth unless another explicit depth control is used.
- Closing/replacing an unsaved draft releases presentation resources without saving.
- Expected editor widths are visually reviewed as designed groups rather than uncontrolled widget wrapping.

**Implemented OF-03 baseline (2026-08-29):** `ObjectFactoryBodyFormSession` is the UI-independent transient
typed-command and history authority. Every node or Part edit recompiles through OF-01 and requalifies the
same stable OF-02 Rig semantics before replacement; invalid changes remain atomic. It owns exact selection,
Front/Side/Back depth rules, explicit keep-world/keep-local reparenting, bounded undo/redo, and immutable
Front/Side/Back references whose defensive source bytes never change with registration. The real Factory
host projects grouped Build, Reference, and Skeleton tabs through EDT-only Swing canvases with separate
Solid, Textured, Skeleton, and Mesh Wireframe presentations. One generation-guarded worker first verifies
encoded size, PNG/JPEG ImageReader format, positive edge and total-pixel budgets from metadata, and then
requires decoded dimensions to match before returning exact source bytes/digest to the session. Close
rejects even a successfully decoded late result and releases session/reference/presentation state. There
is no Save, database, provider, Viewer-owned source, projection/Appearance, or OF-04 authority in this
baseline.

The CM-07 Context Management integration remains a composition-owned adapter, not an OF-03 command or mesh
owner. Its `ContextProvider` emits concise deterministic fragments with stable semantic keys for the
Factory object, model/version, edit state, Part/Bone/joint ancestry, animation/state-machine state,
selection set, explicit invocation target, and capabilities. Multi-joint context emits common ancestry
once, one selection-set node, and one child per selected joint; pointer, focus, selection, and explicit
target stay distinct. Facts use IDs, summaries, counts/transforms, and resolvable references only—never
mesh buffers, reference-image bytes, or complete tracks. The shared Context Engine owns immutable snapshot
composition, and Open in TaliTalk carries that exact captured snapshot.

**Implemented CM-07 feature seam (2026-08-30):** `ObjectFactoryContextCapture` is the bounded immutable
scalar contract consumed by the separately owned provider. It contains exact optional Object/native-package
identity, model/Form/session/Rig/rest-Pose identity, counts, transient workspace/activity/dirty/busy truth,
ordered semantic selection, separately stamped invocation target, local transforms, ancestry, and explicit
present/unavailable capabilities. The EDT-owned Body Form panel rejects hidden, closed, unknown-target, or
changed lifecycle/session/Appearance/durable stamps before returning a capture. The contract structurally
exposes no mesh, image, reference, track, path, digest, payload, or private provenance. At CM-07 landing,
authored Pose, animation, Puppeteer Field, clip, and state-machine capabilities were unavailable. OF-06 now
projects current transient/rest/saved Pose identity, Pose busy/dirty truth, and `AUTHOR_POSE`; animation,
Field, clip, and state-machine capabilities remain unavailable. Provider fragments, actions, Monitors,
policy, payloads, and application composition remain Context-owned and outside this feature seam.

### OF-04 — UV inspection, projection, and local Appearance review

Connect the known Body Form Atlas Contract to existing projection machinery. Add the new orthographic
Body Form camera, semantic-boundary propagation, and per-texel source/confidence evidence. Show textured form and flattened
atlas together; select correspondence both directions; display seams, regions, density, gutters, direct
coverage, propagated coverage, and unresolved coverage; import a compatible PNG; and project registered
views. No provider call is required.

Acceptance:

- Atlas and 3D selection resolve to the same triangle and semantic region.
- Rear-facing, hidden, and off-image texels do not receive fabricated direct coverage.
- One Front image leaves explicit unresolved sides/back.
- PNG replacement leaves Form/Rig/mechanical behavior compatibility unchanged.
- Checker and reference-projected results round-trip through canonical OBJ/MTL/PNG Viewer presentation.

**Implemented OF-04 baseline (2026-08-29):** `ObjectFactoryAppearanceSession` owns one transient local
Appearance over the exact current Native Form candidate. Its stable atlas raster maps occupied texels to
triangle barycentrics and named Surface Regions. Registered Front/Side/Back images project through their
orthographic registration with per-view visibility/depth and facing checks; only visible samples become
direct coverage. Bounded propagation copies color and attenuated confidence only inside the same named
region, leaving hidden, off-image, rear-facing, and still-unseen texels explicitly unresolved. Compact
immutable evidence reports coverage class, optional source view, and confidence per occupied texel.
Reciprocal Form/atlas inspection resolves one exact triangle, region, Part, UV, position, and evidence.
Compatible 1024-square PNG intake checks format and dimensions before decode and retains the exact admitted
bytes. A Form recipe change resets the local Appearance to its canonical checker without changing Form,
Rig, UVs, regions, or mechanical behavior. The Swing Skin workspace shows the textured Form and flattened
atlas together and runs projection/import on the existing bounded generation-guarded worker with immediate
busy feedback. This body owns no Save/reload, database, Viewer source, provider, Context integration, or
later Object Factory route.

### OF-05A — Native package model and durable Form/Appearance/Rig lifecycle

After the OF-02 and OF-04 component sets stabilize, extend the landed Factory Object seam with the explicit
Native Package membership/manifest model from section 23.2, strict `BODY_FORM`/`CHARACTER` kinds, exact Save
New, Save, Save As, complete component reload, version compatibility, derivations, and provenance. Do not
create another store or overload the legacy fixed request ambiguously.

Acceptance:

- Save As creates a distinct object that absorbs the exact source snapshot.
- Reload reconstructs equal Form, mesh, Atlas, Appearance, and optional Rig hashes.
- Stale writer, cancellation, invalid artifact, and transaction failure leave no partial graph.
- Existing Factory object kinds remain backward compatible.
- Source and prior revisions are retained.
- Required/optional/repeatable components load by exact role, identity, revision, schema, and digest.

**Implemented OF-05A baseline (2026-08-29):** `ObjectFactoryNativePackage` is the toolkit- and
persistence-independent complete-package authority. It captures deterministic ordered components for the
exact Form recipe and canonical artifacts, Rig/binding/profiles/sites, Appearance and compact coverage
evidence, plus zero-to-many exact source references and registrations. Load checks role cardinality,
identity/schema/revision/ordinal/digest truth, recompiles every derived Form/Rig artifact, verifies
Appearance/evidence compatibility, metadata-preflights reference images before decode, and returns one
immutable verified draft or nothing.

`FactoryNativePackageService` extends the existing Factory Object and project content store with version
manifest/component membership rows. Its one transaction creates/reuses immutable content and project
assets, the visible canonical OBJ asset and diffuse derivation, append-only object/native versions, exact
memberships, source-object/version provenance for Save As, and the current Object pointer. Exact Save is
optimistically guarded; invalid, stale, cancelled-before-commit, or forced transaction failure leaves no
partial graph. `FactoryObjectOperationService` exposes separate typed Native Save New/Save/Save As operation
types without ambiguously overloading the legacy ZOF request codec. The Swing editor captures the immutable
candidate on the EDT, acknowledges immediately, delegates database work off the EDT, and installs only the
current completion; close detaches from accepted app-scoped work. No provider, Context integration,
Viewer-owned source, OF-06, or later body is part of this baseline.

### OF-06 — Pose controls and deterministic inverse kinematics

After OF-03 supplies authoring rendering and interaction, add direct joint editing, semantic end-effector
Pose Handles, pole/bend Handles, profile-admitted local translation/scale controls, pins supported by the
first solver, reset, mirrored-pose commands where valid, and an analytic two-segment IK solve under the
fixture's declared locked-translation/scale constraint profile.

This is also the smallest body that adopts the standard six-face Token pose-observation deck. OF-06 reads
one settled immutable exact-Creature deck snapshot, deterministically matches visible semantic Sites,
joints, and silhouettes against the known Form/Rig, and presents a non-authoritative Pose candidate with
per-view/per-joint confidence and residuals. Manual Pose authoring remains available with no deck. Missing
non-front roles reduce evidence rather than blocking; missing/queued/in-flight Front yields
`WAITING_FOR_FRONT` and starts no inference. No generation/provider/queue/persistence authority enters
Object Factory. The implemented route retains those ownership boundaries.

Acceptance:

- A reachable hand target is met within tolerance.
- Under the initial fixed-segment profile, an unreachable target clamps without undeclared stretch, NaN, or
  topology change.
- A separate admitted profile permits bounded parent-local translation/positive scale and moves the entire
  attached descendant space deterministically.
- Preferred bend remains stable near singularity and does not flip nondeterministically.
- Joint limits are enforced with a visible residual/failure diagnostic.
- The same target and starting pose produce the same result.
- Saving creates a complete immutable Pose Snapshot.
- Icon is excluded; mixed Creature/Front ancestry, stale deck/Form/Rig/session, or noncanonical view basis
  rejects without mutation.
- Equal admitted inputs reproduce the same candidate/confidence/residuals, while missing or occluded views
  leave explicit unresolved channels and never fabricate observations.

Implementation result: `ObjectFactoryTwoSegmentIk` and `ObjectFactoryPoseSession` are the pure solver and
transient command/history authorities. `ObjectFactoryPoseObservationDeck` and `ObjectFactoryPoseMatcher`
admit only bounded scalar/landmark/silhouette observations and produce one immutable reviewed candidate.
`ObjectFactoryPosePanel` is the EDT projection with one generation/session/close-guarded matcher worker.
`ObjectFactoryNativePackage` stores zero-to-eight accepted complete Pose components, and the Body Form editor
permits that append only through exact loaded-revision Save Pose. No OF-07, provider, Critter queue/control,
or alternate persistence route is included.

### OF-07 — Puppeteer Field editing and preview

Implement complete-pose dots, quantized logical coordinates, deterministic field compilation, barycentric
pose blending, sign-invariant log/exp rotation charts, Perform preview, dot editing/revision, and visible contributing
weights.

Acceptance is the field-only mathematical test set in section 32.4, including exact-dot, two-dot, collinear,
triangulation, arbitrary cocircular sets, hull, quaternion-sign/chart-boundary, shared-edge, and
serialization-order cases. Existing-Performance revision pinning begins in OF-08A.

### OF-08A — Puppeteer recording and exact-Rig persistence

After OF-05A and OF-07, implement canonical 60-tick cursor recording, quantized holds, replay, trim/split,
closed-loop validation, exact dependency pinning, Native Package motion-component roles, and an optional
explicit Solved Clip bake. Prove one named arm gesture; do not claim walking or whole-body dancing on the
arm-only fixture.

Append, Overdub, crossfade, concatenation, hard cuts, and time scaling remain deferred as specified in
section 20.3.

Acceptance:

- Identical pointer events create identical canonical cursor samples.
- Playback agrees across render frame rates.
- Holds retain exact canonical tick duration.
- Existing performance playback remains pinned when its Field is edited.
- Save/reload preserves the authoritative path and exact dependencies.

Implementation result: `ObjectFactoryPerformancePath` owns canonical observation admission, exact 60-tick
resampling, playback, trim/split, loop evidence, and immutable dependency pins.
`ObjectFactorySolvedClip` owns only the explicit derived complete-TRS bake and its source/version/error
evidence. `ObjectFactoryPuppeteerSession` retains transient edit/record/replay authority, while exact Save
uses the existing Native Package operation/transaction with Field, motion-Pose, Path, and optional Clip
memberships. The expected-width Swing projection proves one named arm gesture and exposes no OF-08B layer,
mask, root-composition, walking, or dancing behavior.

### OF-09A — Template engine and complete biped slice

Move primitive composition from one fixture into data-driven Template families and complete one bounded
segmented biped with torso, head, two arms, two legs, Rig, sites, controls, and Atlas Contract. Broad and
slender surface variants retain declared compatible signatures and report density drift.

Acceptance:

- Templates are immutable recipes, not UI-specific conditional code.
- The Rig and schema make no universal two-arm/two-leg assumption despite this fixture.
- Declared compatible dimension variants preserve signatures and recompute geometry-dependent sites.
- Topology or structural changes create explicit revisions.

Implementation result: `ObjectFactoryTemplate` stores only immutable bounded parameter formulas, semantic
node/Part data, a cardinality-neutral Rig recipe, and one Atlas policy. The pure synchronous
`ObjectFactoryTemplateEngine` expands one complete request into a Native Form candidate, exact Rig,
normalized parameter snapshot, independent topology/Geometry-Bind/Atlas/Rig/exact-motion/Rig-family
signatures, and per-region density evidence. `object-factory.template.biped.basic` supplies the complete
segmented fixture described above. Standard, broad, and slender inputs retain their declared topology,
Atlas, and Rig-family compatibility while geometry-dependent chest/palm/sole sites and exact motion identity
follow the compiled proportions. Definition changes without a higher revision fail closed. The engine also
compiles a reduced non-biped cardinality fixture, and the landed arm continues through its unchanged fixed
atlas route. This body adds no Create Form screen, durable Template membership, behavior layer, provider,
Critter action, live-data route, or application service.

### OF-08B — Layered whole-body behaviors and dance

After OF-08A and OF-09A, implement Behavior Layers, unique order, masks, override/additive composition,
root-motion ownership, active intervals, pinned projector versions, conflicts, and exact-Rig behavior
persistence. Record and compose a base step/walk behavior with an upper-body wave, then record a short
whole-body dance gesture.

Acceptance:

- Masked layers leave unowned channels unchanged through final limit projection.
- Absolute root ownership is unique over overlapping time.
- Layer order and additive quaternion branches are deterministic.
- A Puppeteer path refuses structural retargeting without an explicit Behavior Intent revision.
- Save/reload preserves authoritative source, layers, masks, intervals, and all pinned dependencies.

### OF-09B — Quadruped and first dragon families

Extend the Template engine after OF-09A. Recommended content progression:

1. Quadruped
2. Winged quadruped / `dragon.basic`

The first dragon includes bounded torso, head/snout, four limbs, neck/tail chains, wings, horns, eyes, mouth,
semantic surfaces, Rig, and one stable Atlas Contract.

Acceptance:

- Declared compatible dimension variants preserve signatures.
- Topology changes create explicit Template/Atlas revisions.
- One dragon Form accepts several distinct Appearance PNGs without geometry or Rig change.
- Shared compatible poses/behaviors remain reusable.

Implementation result: `ObjectFactoryCreatureTemplateLibrary` supplies immutable bounded
`quadruped.basic` and `dragon.basic` family data to the unchanged pure OF-09A compile route. The quadruped
has 17 nodes/Parts and a complete four-locomotor Rig. The 25-node/33-Part dragon includes every named
component above, 33 stable semantic regions, 71 deterministic islands, a complete 33-binding Rig, 22
controls, nine sites, and 24 reference segments. Shape-only mass/length/wing variants retain the declared
topology, Atlas, Rig-component, exact-motion, and Rig-family signatures while Geometry/Bind and density
evidence follow the dimensions. Structural changes require explicit higher Template and Atlas revisions.
One exact dragon candidate accepts multiple distinct Appearance PNGs without changing Form, Rig, rest Pose,
forward-kinematic geometry, or the exact Behavior compatibility signature. This body adds no UI, Native
Package membership, durable action, provider, Critter route, import, or later Object capability.

### OF-10 — AI atlas painting and revision loop

After OF-04 and OF-05A prove atlas truth, acceptance, and Appearance persistence, assemble the exact guide/mask/reference request,
invoke the existing configured provider boundary only by explicit user action, validate the returned PNG,
preview, reject/revise/accept, and persist provenance. MTL remains automatic and invisible.

Acceptance:

- No request can change geometry, UV, Region, Rig, or behavior state.
- No paid call occurs without explicit action and a reviewed provider/settings snapshot.
- Wrong dimensions, malformed bytes, stale results, and cancelled calls cannot replace Appearance.
- Reject preserves the prior revision; accept creates a new immutable Appearance revision.
- One unchanged compatible Form can retain multiple named accepted skins; OF-09B supplies the later dragon
  proof.

Implementation result: `ObjectFactoryAppearanceGenerationSession` owns one toolkit-neutral reviewed
request, exact Form/Atlas/base-Appearance pins, deterministic prompt/input fingerprints, explicit
invocation identity, untrusted-result validation, preview/reject/cancel, and exact-revision acceptance.
The request contains only the current diffuse, guide, coverage/region masks, bounded intent, and up to eight
byte-owned registered references. `ObjectFactoryAppearanceGeneratorPanel` groups intent, provider/cost
truth, request review, flat preview, and Accept/Reject/Revise/Cancel; Generate changes immediately to
`Generating…` and disables duplicates. The production adapter reuses the existing configured image client,
credential, mask-edit, and settings boundary. Returned bytes undergo PNG metadata preflight before decode,
must be exactly 1024 square with no transparent covered texel, and are clipped to the program-owned coverage
mask before deterministic four-pixel region-safe edge dilation. Reject, cancel,
validation failure, stale Form/Appearance, and close preserve the prior Appearance. Accept alone creates a
new `GENERATED` Appearance with path-free request/response provenance; the existing OF-05A Native Package
transaction preserves and verifies that provenance on reload. No provider call is made by construction,
review, tests, save, reload, or panel opening.

### OF-P01A — Shared imported-model staging and conversion lane

This parallel platform lane can start after OF-01 freezes the native candidate boundary. Initial formats are
checked STL and OBJ-family inputs. It implements source staging, normalization, explicit repair/reduction/
faceting recipes, metrics, candidate preview, and an unsigned commit-ready receipt. It performs no durable
commit before OF-P01B.

Acceptance:

- Original source bytes remain untouched; preview creates no logical object or durable membership.
- Units, axes, transforms, materials, UVs, and lost capabilities are explicit.
- Collapse reduction is reported separately from planarization/faceting.
- Topology-changing steps invalidate or deliberately regenerate UV/bindings.
- Repeating an equal deterministic recipe reproduces equal candidate artifacts.
- Talisman has no runtime or source dependency on Dwarf War or Blender.

Implementation result: `ObjectFactoryImportedModelStagingService` now owns bounded path-free primary and
companion bytes, checked real-format/extension/reference admission, explicit units/orientation/provenance,
canonical repair/collapse/planarization/faceting/UV recipes, capability and loss inventory, deterministic
native candidate artifacts, metrics, and a complete unsigned receipt. Conversion identity uses the required
domain-separated length-framed source/adapter/full-normalization-recipe/compiler envelope. The grouped
`ObjectFactoryImportPanel` performs regular-file read/recheck and candidate construction on one worker,
acknowledges immediately, rejects stale/closed completion, compares source with candidate, and visibly keeps
Accept Conversion unavailable. No logical Object, Native Package member, managed Asset, database row,
provider call, Viewer-owned source, Blender, or Dwarf War dependency exists in this body.

### OF-P01B — Atomic import commit integration

After OF-05A establishes Native Package membership, connect an accepted OF-P01A source/candidate/receipt to
checked source-plus-derived admission, object creation/versioning, reload, cancellation, and rollback.

Acceptance:

- Original source bytes and digest are retained with the accepted native derivative.
- Source, candidate, receipt, manifest, and memberships commit atomically.
- Cancellation, stale candidate, hash mismatch, or transaction failure leaves no partial object graph.
- Reload reproduces source provenance and the complete target package.

Implementation result: `ObjectFactoryImportedModelPackage` independently repeats the reviewed OF-P01A
conversion and matches source set, recipe, candidate, metrics, capabilities, artifacts, and unsigned receipt
before creating an `IMPORTED_MODEL` Native Package. Its path-free graph retains one canonical request, every
exact source member, canonical OBJ/MTL, conversion report, and receipt. The existing
`FactoryNativePackageService` transaction now admits that profile into the same Object/version/content/
project-asset/user-asset/membership/derivation/manifest graph used by native procedural packages; it owns
reload verification and rolls back the complete graph on stale currentness or failure. The dedicated
`imported-model-commit` semantic operation carries only bounded guards and preserves candidate bytes inside
the Factory owner. `ObjectFactoryImportPanel` exposes immediate single-flight acceptance and rejects changed
or closed review generations before commit. Imported reload returns to the Import and Convert surface and
does not invent Rig, regions, Appearance, Pose, motion, or any other absent capability.

### OF-11 — Runtime consumers and articulated delivery

After authoring truth exists, add **runtime publication** of articulated Viewer realization, behavior
invocation, and—only after a separate exact event specification—semantic events; add glTF/GLB delivery if
selected and adapters for GM, Player, and Scripted Walkthrough. These consumers do not
own or gate the authoring domain. Authoring-time articulated rendering already exists as an OF-03/OF-06
requirement and is not deferred to OF-11.

Acceptance:

- A consumer invokes an exact Character/Behavior revision and receives deterministic start/progress/end
  outcomes.
- Missing capability or incompatible Rig fails visibly.
- Static OBJ/diffuse Viewer compatibility remains intact.
- Adapter and joined-observability work does not introduce a second motion authority.

Implementation result: `ObjectFactoryRuntimePublication` consumes one immutable fully validated Character
Native Package plus exact Object, package, and Behavior revisions. Publication visibly rejects a stale
package fingerprint, absent articulation or Behavior, changed Behavior revision, and a consumer-declared
exact-Rig mismatch. Invocation accepts only one bounded caller-supplied ordered tick set; the existing
`ObjectFactoryBehaviorComposer` remains the sole motion evaluator and
`ObjectFactoryForwardKinematics` remains the sole articulated mesh/posed-OBJ realization. Immutable
start/progress/end outcomes retain exact identities, contributions, conflicts, and final-limit residuals.
`ObjectFactoryRuntimeConsumers` supplies read-only Viewer, GM, Player, and Scripted Walkthrough adapters
over that already-evaluated result. Each delivery includes the unchanged canonical OBJ/MTL/diffuse payload
and deterministic posed OBJ frames but no authoring state, provenance, timer, event, command, persistence,
or motion authority. `FactoryRuntimePublicationService` is the exact-current durable-load adapter, while
the grouped Publish / Use in Talisman panel reviews the same immutable contract. Semantic events and
glTF/GLB remain absent because neither separately selected exact contract exists.

### OF-12A — Lizard Mapping Body Type and deterministic six-view texture bake — LANDED

Turn the proven JavaScript mapping-editor workflow into one bounded Object Factory product body. Define the
first reusable **Lizard Mapping Body Type**, calibrate it with Giant Crocodile, and produce a deterministic
Appearance from the six accepted Token views without asking AI to infer geometry or paint a discontinuous
atlas.

Scope:

- Compile one fixed hips-rooted Lizard joint graph, splayed mapping pose, Standing angle set, simple closed
  geometry, stable topology, UV layout, seams, semantic regions, and region-ID map.
- Register Front, Back, Left, Right, Top, and Bottom as six independent 2D landmark maps using the camera
  and anatomical conventions in section 11.3.1.
- Keep 2D mapping edits separate from 3D geometry edits. Opposite-face copy is a seed, not a live link.
- Fit the creature-owned mapping without mutating the reusable default body type.
- Project compatible visible image evidence through the fitted form, preserve source/confidence truth, and
  deterministically bake one Atlas-compatible Appearance.
- Prove reuse with Giant Crocodile plus one second lizard-family creature.

Acceptance:

- Equal body-type revisions and equal mappings produce byte-equal geometry, UV, semantic maps, and bake.
- Every view is head-up, strictly orthographic, anatomically named, and independently editable.
- Mapping-point movement cannot mutate the 3D body; 3D edits cannot silently rewrite image observations.
- The semantic color map and UV identities remain stable across admitted compatible Lizard proportions.
- Direct, blended, and unresolved coverage remains visible and traceable to exact source views.
- The complete focused proof is local and provider-free; no live Critter, database, or Seasons mutation is
  part of this body.

### OF-UI-01 — Object Factory Shelf and Body Form compendium workbench

Replace the browser proving-ground composition with the production-target Object Factory workbench while
reusing the exact landed domain and Shader behavior. One nested Object Factory Shelf presents Shader and
Puppeteer Shelf Items. The Object Box presents the sole current 3D object. The Shader Box contains the
existing
six-view observations, mapping, UV/regions, Appearance, coverage, and reviewed image actions. The Puppeteer
Box contains a declarative Body Form Type compendium, derivation lineage, bounded proportions,
transformation commands, stance library/editing, and the landed Field/Path/Behavior entry points. Object owns
all surface, camera, Control Point, Skeleton, Pose/Shape, grounding, and selection presentation in one
switchable 3D workspace. Each Box opens and closes independently without changing domain state.

The initial compendium covers these reusable morphotypes:

- Humanoid and Avian bipeds;
- generic, Horse-like, and Lizard quadrupeds;
- Winged Lizard and Dragon derivatives;
- Serpentine axial bodies; and
- Arachnid and Tentacled radial bodies.

Types are deterministic declarative recipes with stable IDs/revisions, parent lineage, generated joint/
Part/control inventory, topology/Atlas/Rig-family identity, bounded parameters, and named stances. A
derived type absorbs one exact parent revision. Structural appendages such as digits, wings, horns, limb
segments, and tentacles belong in that type recipe rather than repetitive per-creature edits.

Dragon wings expose four semantic distal controls per side: one leading hook and three fingers. In Mapping
stance they form a long, legible fan matching the standard generated Dragon reference layout rather than a
short cluster at the wing tip.

Each type metadata record also names one versioned Morph Form cover, its deterministic Skeleton recipe,
ovoid/cylinder primitive-envelope vocabulary, and its bounded stance commands. Compendium and later
Creature-form galleries show that neutral skeleton-plus-envelope cover first, before texture or Creature
imagery, so structural review remains independent of Appearance.

The first delivery is packaged JavaScript on the existing loopback Application Server route. Stable
`data-of-*` identities and explicit intent/result comments preserve the later DTDT and Java translation
seam. JavaScript remains presentation/proving state: the pure Template/Lizard compilers remain Form/Rig
authority, the Native Package service remains durable authority, and Critter remains Creature/deck/queue/
asset authority.

The guarded Regenerate successor remains a transport consumer, not Factory domain authority. Its existing
five-field exact-view intent crosses the managed host only when bootstrap declares the complete available
POST/session/CSRF/epoch/idempotency contract. CSRF stays private to the client; the returned stamped direct
operation must match request identity, Creature, and role. Page load is admission-inert, browser close does
not cancel accepted work, and SRD retains every lane, provider, revision, persistence, and operation truth.

OF-GENMETA-11A extends only the consolidated owner-domain admission behind that host. The optional immutable
`ObjectFactoryImageControlMetadataPackage` is strict, size/count bounded, path-free, and exact-schema. It
binds one Creature/role to Body Form, Morph, Skeleton, stance, orthonormal camera basis, stable semantic
ancestry, normalized control/projection/radius values, and bounded enclosing geometry. Admission freezes
canonical JSON and SHA-256. The provider adapter appends the canonical value as explicitly untrusted
structured data only when present; absence returns the prior effective prompt exactly. Same-operation replay
must match the frozen hash.
Only that hash enters durable checkpoint evidence. TAS transport and browser opt-in remain later owner bodies.

Acceptance:

- Shader and Puppeteer Shelf Items open independent Boxes together or separately around one shared result.
- Every seeded type has a stable identity/revision, family/parent, generated control inventory, and at
  least one named stance; equal catalog inputs generate equal projection data.
- Every type stores a stable Morph Form/Skeleton identity, primitive-envelope vocabulary, and stance command
  metadata; its first gallery/card image renders the neutral Skeleton inside ovoid/cylinder geometry.
- Short/broad/tall/lean and family-specific parameters are bounded named values, not mesh dragging guesses.
- Mapping uses one natural fully splayed joint layout in the canonical image frame: X across, Y up the
  spine, and Z as shallow depth. Centerlines remain readable, each appendage extends from its anatomical
  attachment, and intermediate controls remain separate and easy to manipulate.
- A type or stance change does not move any six-view observation; an Appearance action cannot mutate Form.
- The existing six-view mapping, UV, coverage, generation, camera, and paid-action review remain available
  in Shader without behavior changes.
- Shader exposes independent Outline, Projected rig, Control Point, Skeleton-segment, label, and region/mask
  review controls. It exposes no combined Paint Guide card or preset; exact generation constraints remain
  request evidence rather than a duplicate composite control.
- Returned paint follows the same surface-to-Atlas projection and render-back validation loop; it never
  becomes Form, stance, Skeleton, UV, or region authority.
- The later 235-Creature assignment remains an explicit reviewed proposal/acceptance workflow and performs
  no live-data mutation in OF-UI-01.
- Focused proof is resource-local and provider-free; no server restart, controller/database write, or
  application launch is required.

### OF-SHADER-CAGE-50 — Non-destructive Shader control-cage registration — ACTIVE

Extend the existing global Shader image registration with one independently revisioned control cage per
canonical view. A newly admitted accepted image receives a deterministic silhouette first fit. The cage uses
only visible, sufficiently confident observations, reports unresolved controls, and holds the canvas boundary
fixed. Direct landmark drag updates one bounded local offset; the adapter refuses a move before any adjacent
triangle can invert or collapse.

`six-view-texture-mapping.js` remains the UI-independent browser adapter for normalization, cage construction,
bounded movement, and inverse sampling. It returns immutable derived snapshots and paints one registered
canvas for the changed view. Object retains canonical first-claim UVs and samples that finished canvas rather
than repeating the cage calculation during orbit or animation. Source identity and pixels remain under the existing Application Server and asset
authorities. Browser recovery stores only the separate registration draft and admits it for the matching view
and source image; a new image resets it, and a geometry, transform, orientation, viewport, or cage revision
invalidates only the derived cache. Orbit, stance, and motion reuse the existing result.

The body adds no service, bus, worker, provider request, database write, Core Morph mutation, accepted-media
rewrite, or durable Appearance authority. Reset clears global and local registration and restores exact source
presentation. Pane disposal ends an active drag and releases its browser work.

### OF-OBJECT-GPU-51 — Retained six-view Object surface — ACTIVE

`object-surface-webgl.js` is a presentation-only renderer beneath `six-view-texture-mapping.js`. It packs one
stable triangle roster into at most seven retained draw batches: one for each canonical view owner and one
for unmapped triangles. Rest shape, stance evaluation, and motion interpolation all preserve the same ordered
triangle identities, including temporarily edge-on faces; posing uploads positions and cannot remove a face,
renumber the roster, or trigger a new ownership bake.

Projection ownership is baked from three independently assignable Shader inspection-pose pairs rather than
from the current Object pose or selected card. Front/Back use one breadth pose, Left/Right use one shared
asymmetric pose with one forelimb raised and the opposite forelimb lowered, and Top/Bottom use one depth-spread
pose. Within a pair, only the canonical opposing camera changes. Shader View Parameters owns the browser-local
stance assignment for each pair; Morph remains the sole playback authority.

For each enabled source, the canonical camera rejects reverse-facing and truly edge-on triangles, then a
bounded depth raster assigns only the first visible hit. Every finite non-degenerate projected triangle,
including a sub-pixel face with a valid witness, remains eligible. Stable triangle identity joins that
source-pose claim to the retained Object surface. Selected-card focus and live Object camera orientation do
not alter the checked sources or their priority.

First-hit ownership, canonical UVs, per-source opacity, and unmapped/card colors are baked only when source,
cage, surface geometry, checked-view membership, enabled order, or a pair-pose assignment changes. Camera
orbit changes uniforms without rebuilding topology or warping media; Morph stance and motion upload only
posed positions. Morph remains the sole stance, motion, playback, and loop owner, so Object exposes no
Animation Shelf or duplicate playback state.

The Object picture composes three ordered presentation layers: a cheap Canvas2D ground plane, the transparent
retained WebGL surface, and SVG annotations. Unavailable WebGL, context loss, or a draw failure selects the
exact Canvas2D surface implementation with the same claims, UVs, source order, opacity, and unmapped colors;
it never produces a blank success state. Context restoration may rebuild retained graphics resources from
the immutable packed snapshot. Page disposal deletes buffers and textures and ends pending frame work.

Shader lays its picture into the complete remaining height after its optional data shelves. The Object
named-view HUD is presentation derived from the current camera on every orbit frame. Its arrow begins at the
selected named viewpoint, points inward toward the Object, and is clamped to visible bounds; it never becomes
camera, card, stance, or projection authority.

This body adds no command, Behavior, service, bus, worker, provider request, catalog, database, Native Package,
Core Morph, accepted-media, or durable Appearance route. It changes only browser-local projection cost and
preserves the current Application Server and asset authorities.

### OF-MORPH-SAVE-52 — Template/private Morph save authority — QUEUED

OF-MORPH-SAVE-52 begins only after alpha 1.66.1 is accepted live. One explicit top-left **Template** control
selects the authority targeted by a later Save while leaving the in-memory draft untouched. ON means the
canonical reusable master Morph type selected by the editor, for example Ancient Dragon. OFF means a private
Morph revision associated only with the current Creature. This save-scope control is not image-template
state, projection state, a second draft, or permission to mutate either authority directly from the browser.

The first Save after selecting a scope must describe the context-appropriate **New** and **Update** choices
and obtain explicit review. Every accepted route appends a complete immutable revision; Update never mutates
the current revision in place. A Template update advances the canonical master only for future copies. Every
Creature is created with and pins a distinct private Morph copy/revision, so no master update changes any
existing Creature or private copy, whether the Creature was made before or after another master revision.
A private update advances only the current Creature's private Morph association. Switching ON/OFF preserves
the same unsaved draft and performs no automatic migration, association, or write.

A later body may compare a private copy's master ancestry with the latest master and present **Master template
changed; review update**. That state is informational until the user explicitly previews and accepts a
migration. Migration creates a new private revision, retains the previous private revision for recovery, and
never propagates automatically. It is not part of OF-MORPH-SAVE-52.

The existing canonical Morph owner validates and stores canonical revisions; the existing Creature owner
validates the exact Creature association. The Application Server may coordinate one checked atomic operation,
but Object Factory remains the authoring UI and creates no browser database, second catalog, mutable revision,
or propagation loop.

### Deferred projection-card, OBJ-intake, and AI Morph sequence — NOT ACTIVE

The canonical six cards are current defaults, not a permanent enumeration. A future reviewed card body may
add **New projection card**, ordering/removal, and explicit lock/unlock. While unlocked, its camera orientation
is one synchronized presentation value shared by Morph and Object. Lock captures that orientation as a named
card view. No arbitrary card becomes mapping authority until that body's identity, persistence, migration,
and review contracts are selected.

A subsequent OBJ-intake body may use a user-declared body family such as humanoid to propose candidate views
and estimated control points. Imported geometry and heuristic observations remain evidence only; all proposed
controls are adjustable and require review before admission to a Morph revision.

After OF-MORPH-SAVE-52 and that projection/OBJ sequence, reviewed text-only AI-assisted Morph authoring may
send the current Morph identity, control graph, constraints, stances, motions, and user intent through an
OpenAI call, Codex session, or Ollama. The returned package is only a proposed new immutable Morph revision
until previewed and accepted. It contains no binary image, mesh, audio, or sound. Sound generation and
attachment retain their separate design and authority.

### OF-UI-MIGRATE-02 — Hosted Critter Gallery and Creature surfaces

Critter Image Batch, Gallery, and Creature are separate first-class pages on the one managed Talisman
Application Server. Object Factory packages Gallery and Creature presentation. SRD owns the Batch page and
all Critter domain truth. The Application Server owner registers entries, routes, exact assets, typed
capabilities, sessions, health, and lifecycle.

The Creature page `critter.creature` at `/monster-detail.html` hosts the nested Shader/Puppeteer Shelf.
Gallery page `critter.gallery` at `/critter-gallery/` reads only
`critter.current-asset-catalog/v1` and one all-current `critter.operation-progress/v1` projection, then
joins by exact entity key and role. Creature consumes
`critter.monster-detail/v1`; media uses `critter.managed-media/v1`. Gallery leads each record with Morph Form
and shows compact collection/card progress. Creature shows progress on its relevant role subdeck.

Batch page `critter.image-batch` at `/critter-image-batch/` remains focused and never embeds Gallery. Its
separately owned entry bundle is unavailable. Application Server has fixed the read-only Gallery/Creature
identity and wire contract but must still publish and land its registrations; Object Factory does not infer
availability before that exact tip.

The browser admits only opaque media ID plus digest and bounded scalar status. Each read is revalidated
against exact host, manifest, page bundle, and workspace-session stamps. Changed stamps reject the result as
stale. Closing aborts reads and polling without sending cancellation. TAS-CRITTER-02 is read-only;
generation and cancellation controls remain unavailable, and the browser invents no CSRF/replay transport.

The Destination Shelf retains the last valid exact Creature key in browser-session route state so returning
from Batch or Gallery restores the same Creature. Explicit Gallery choice replaces the hint. Missing,
malformed, or oversized hints fail closed; the typed Creature detail read remains the sole authority.

Gallery, Creature, and Batch are the draggable labels of one browser-local Manual Destination Shelf. The
default order is Gallery, Creature, Batch; dragging a label persists the exact complete admitted order and
the same order follows the user across all three pages. The labels themselves are the only visible reorder
handles. Object's Lit/Texture/Source/Seams/Coverage and Pose/Shape choices replace the one mounted 3D
viewport child; no second surface or editable-Skeleton stage remains mounted beside it.

The legacy path-shaped image URL, browser controller token, and direct regenerate route do not enter the
hosted owner bundle. No fallback to `8766` is permitted. Atomic owner-asset replacement is visible on the
next request without restarting the managed server.

### OF-12B — Advanced deformation, contacts, and retargeting

Add only as separately bounded needs arise: weighted skinning, multi-chain IK, foot planting, contacts,
balance, gait synthesis, collision, physics, and semantic retargeting between Rig-family variants.

Acceptance is capability-specific. In particular, retargeting must demonstrate one Behavior Intent on at
least two substantially different proportion variants while preserving declared endpoints, contacts,
facing, handedness, and timing within visible tolerances.

### OF-13A — Expressive and mechanically signing-ready profiles

Add fingers, handshape calibration, face/mouth/gaze channels, signing-space Landmarks, synchronized tracks,
and contact-preserving solve as specifically qualified mechanical capabilities.

### OF-13B — Notation and candidate language-content authoring

Add reviewed notation/import adapters, symbolic intent channels, candidate language/variant provenance, and
content-authoring workflows without representing candidates as released language.

### OF-13C — Deaf-led review and release governance

Add immutable dependency-scoped Review Records, community comprehension evidence, release states, permitted
deployment contexts, supersession/invalidation, and withdrawal handling.

Mechanical readiness and released signed-language content are separate completion gates. This body cannot be
declared complete merely because an avatar can imitate a visually plausible gesture.

### 31.1 Dependency and parallelism map

```text
Approved system specification ---- OF-UI-00 Experience design/approval
        |
Permanent worktree gate
        |
      OF-01 Native form + UV contract
        +------------------------------ OF-P01A Import stage/convert
        |
      OF-02 Rig/FK
        +---- OF-03 Editor <---------------- OF-UI-00
        |       +---- OF-04 Atlas/projection ---- OF-05A Native package ---- OF-10 AI painting
        |       |                                      |
        |       |                                      +---- OF-P01B Import commit <---- OF-P01A
        |       |
        |       +---- OF-06 Pose/IK ---- OF-07 Pose field ---- OF-08A Path record/persist
        |
        +---- OF-09A Biped template
                     |
                     +---- OF-09B Quadruped/dragon families

OF-05A + OF-07                    -> OF-08A Path record/persist
OF-08A + OF-09A                    -> OF-08B Layer/walk/dance
OF-04 + OF-05A + OF-08B + OF-09* -> OF-11 runtime publication
OF-04 + OF-09B + settled Token deck -> OF-12A Lizard mapping and deterministic bake
OF-12A + landed Template/Puppeteer UI -> OF-UI-01 nested-Shelf Body Form compendium workbench
specific OF-02/06/08 capabilities -> OF-12B advanced motion/retargeting
qualified motion capabilities     -> OF-13A mechanical signing readiness
OF-13A + governance authority     -> OF-13B candidate content -> OF-13C reviewed release
```

OF-UI-00 is complete and visually approved. Each UI-bearing implementation body still waits for its own
domain dependencies and the permanent worktree gate. The first static code proof is OF-01. The first saved
visual product milestone is OF-01 through OF-05A. The first recorded arm gesture is OF-08A. The first
“teach her to dance” milestone adds OF-09A and OF-08B. The first “one form, many dragons” milestone is
OF-09B plus OF-10. Import remains useful and parallel, not a prerequisite for any of them; OF-P01A supplies
conversion preview and OF-P01B supplies durable import. OF-12A supplies one reusable Lizard mapping family
and deterministic six-view texture bake. Landed OF-UI-01 unifies Form/Puppeteer and Shader in the Object
Factory Shelf Workspace. Active OF-UI-MIGRATE-02 packages Gallery and Creature for the managed shared host.
OF-12B and OF-13 remain deferred.

## 32. Verification specification

Each implementation body maintains focused automated and manual scenarios in the permanent Object Factory
`TEST-PLAN.md`. A body runs only the smallest focused verification authorized for that work; this
specification does not authorize a broad Talisman suite, application launch, live-data mutation, or provider
call.

### 32.1 Geometry and artifact proofs

Required focused tests include:

1. Canonical recipe serialization is independent of locale and map iteration.
2. Equal recipe and compiler versions produce equal geometry and artifact hashes.
3. Primitive shells pass finite, area, winding, manifold, and volume checks.
4. Invalid dimensions, transforms, facet counts, identities, and topology fail closed.
5. Every triangle has exactly one Surface and Material Region.
6. Expanded seam vertices preserve exact position while owning permitted distinct UVs.
7. Cylinder Front maps to the side-island center and Back maps to its two seam borders.
8. Caps have distinct, correctly oriented islands.
9. Sphere/ellipsoid ring count, stable quad diagonals, pole fans, wedge-expanded pole UVs, rear seam, winding,
   and selected normal policy match exact fixtures.
10. Packing has no unintended overlap, out-of-bounds coordinate, or insufficient gutter.
11. Region density and declared exceptions match one compiled Form.
12. A declared compatible dimension variant retains its Atlas signature, reports density drift, and requires
    explicit migration only beyond its Template threshold.
13. OBJ re-admits to equal static geometry and one-to-one UV correspondence after native-Z export conversion.
14. MTL has safe relative basenames, one initial material, `Ks 0`, `d 1`, and the exact diffuse binding.
15. PNG masks, guide, coverage, canonical JSON, non-self-referential hashes, and outer-manifest hash match the
    Atlas Contract.
16. Replacing diffuse bytes changes no Form, Atlas, Rig component, or exact motion signature.
17. Stored dimensions and physical-density evidence use canonical feet/square feet/pixels per foot; explicit
    display-unit round trips do not change normalized recipe bytes.
18. Transforming one Space Node moves every attached Part and descendant by the exact composed matrix, while
    unrelated branches remain unchanged.
19. Shape-local TRS composes after owner-node TRS; nonuniform positive scale realizes normals through the
    declared inverse-transpose rule, and node/shape ordering remains deterministic.
20. Changing an admitted Part shape-local translation, rotation, or scale changes Geometry/Bind identity
    while retaining Topology and Atlas identity when its Template declares those compatible.

Property tests SHOULD vary admitted dimensions and facet counts within bounds. Golden fixtures freeze exact
normalized recipe bytes, mesh arrays, artifact text, masks, and hashes for each schema/compiler version.

### 32.2 Reference and projection proofs

1. Reference registration round-trips without changing source bytes.
2. Front/Side/Back transforms remain independent.
3. A Front screen drag preserves depth by declared rule.
4. Visible, front-facing texels project to the expected source pixels.
5. Hidden, rear-facing, occluded, and off-image texels remain unresolved.
6. Multiple views blend by explicit confidence and never claim false direct coverage.
7. Per-texel evidence identifies source view/confidence and reproduces aggregate counts.
8. Bounded propagation cannot cross a Surface Region boundary unless the Atlas Contract permits it.
9. Orthographic Body Form reference and proven legacy 36-degree perspective projection remain explicitly
   distinct camera identities.
10. 3D-to-atlas and atlas-to-3D picking resolve the same exact triangle and semantic region.
11. Coverage, bounded propagation, and unresolved evidence round-trip through save/reload.
12. Six mapping planes retain the declared camera bases, head-up orientation, and anatomical handedness.
13. A 2D landmark move changes only that face's mapping revision; 3D geometry and other faces remain equal.
14. Opposite-face copy applies the declared camera transform once and then leaves both faces independent.
15. Side-face mapping exposes only the near-side anatomical chain while retaining the complete 3D body.
16. Equal Lizard body-type and mapping inputs produce byte-equal mesh, UV, semantic map, and texture bake.
17. Giant Crocodile and a second lizard-family fixture reuse one body type without sharing creature edits.
18. Every baked texel identifies direct, blended, or unresolved evidence and its exact contributing view.
19. Auto-fit, cage adjustment, and reset leave the accepted source bytes, identity, and digest unchanged.
20. Equal source, Mapping observations, global transform, viewport, and cage inputs produce an equal cage and
    byte-equal derived registered canvas.
21. Hidden, low-confidence, missing, and occluded observations remain unresolved and are never synthesized.
22. A local landmark move has bounded influence, preserves shared triangle edges, and cannot invert or
    collapse an adjacent triangle.
23. Reset removes every cage offset and global transform and reproduces the untouched source presentation.
24. A recovered registration for another source image or canonical view fails closed; geometry, transform,
    orientation, viewport, and cage revisions invalidate a stale derived mesh.
25. Shader preview and Object first-claim sampling use the same current derived registered canvas while Object
    preserves canonical first-claim UVs.
26. Camera orbit, stance, and motion reuse the cached registration; source, cage, enabled-view priority, and
    geometry changes invalidate only the dependent texture work.
27. Ending a drag or disposing the pane releases pointer/listener work without a provider, network, database,
    Core Morph, or accepted-media mutation.

### 32.3 Rig and pose proofs

1. Hierarchy cycles, missing parents, duplicate semantics, and invalid Space Node transforms are rejected.
2. Rest Pose exactly reproduces generated local component placement.
3. A nonzero rest translation and non-identity rest rotation golden proves absolute parent-local Pose
   transforms are applied once, not double-applied.
4. Forward kinematics evaluates in stable order and composes parent-relative translation, normalized
   rotation, and positive scale exactly once.
5. Posing never changes topology, UVs, Surface Regions, or Appearance.
6. Two or more Parts attached to one Control Point and a nested child fixture all inherit the same node
   transform, including nonuniform positive scale.
7. A locked-translation/scale fixture rejects local distance/scale change; an animatable fixture accepts it,
   moves relative to its parent, and records it in the complete Pose Snapshot.
8. Reachable-under-declared-length-and-limit constraints analytic IK meets target within tolerance.
9. Unreachable or limit-infeasible IK clamps/fails visibly without undeclared stretch or non-finite values.
10. Chain-origin, full-extension, full-folding, collinear-pole, and near-singularity fixtures are stable.
11. Preferred bend and limits remain deterministic near singularity.
12. Missing/duplicate/unknown channels, non-finite values, non-positive scale, invalid quaternion, changes to
    locked/derived translation or scale, and hard-limit violations cannot be saved as a Pose.
13. Complete Pose Snapshot save/load reproduces all channels.
14. Equal rigs with different rest transforms have different exact motion signatures but may share one qualified
    Rig-family signature.
15. Rig-signature mismatch fails rather than guessing a mapping.
16. Broad/thin geometry retains a compatible Rig while recomputing a chest contact site; `hand touches
    chest` resolves against the new Site Set.
17. Differential signature fixtures prove each ownership boundary independently:
    - A Part-local TRS change changes Geometry/Bind; Topology and Atlas remain equal when the Template
      declares that change compatible.
    - An articulated Space Node rest-TRS change changes Geometry/Bind, Rig component, and exact-motion
      signatures.
    - An unarticulated Space Node rest-TRS change changes Geometry/Bind and does not invent a Rig or
      exact-motion identity.
    - A Rig-owned constraint change changes Rig component and exact-motion signatures without changing
      Geometry/Bind.
    - A Channel/Deformer admission-policy change changes Channel/Deformer and exact-motion signatures
      without changing Rig component or Geometry/Bind.
    - An evaluation-affecting constraint-projector or solver-convention change changes exact-motion identity.
18. The standard deck maps only `token`, `token_back`, `token_left`, `token_right`, `token_top`, and
    `token_bottom` to the exact canonical camera bases; Icon and unknown roles reject.
19. Every admitted non-front observation shares one Creature identity/revision and exact accepted-Front
    identity/revision/digest; cross-creature, mixed-Front, stale-Front, mirrored, or nonuniformly scaled
    observations fail closed.
20. Missing/queued/in-flight Front yields `WAITING_FOR_FRONT` without starting inference or changing the
    external queue. Any settled subset of non-front roles remains admissible with explicit missing-view and
    affected-joint confidence loss.
21. Equal Form, Rig, deck snapshot, registration, matcher, and solver versions produce equal Pose candidate,
    per-observation residuals, per-joint confidence, and unresolved-channel evidence.
22. Occluded, contradictory, or absent observations never become fabricated landmarks or silently averaged
    certainty. Candidate preview mutates nothing; explicit exact-revision acceptance alone creates a complete
    Pose Snapshot, and any stale dependency rejects atomically.

### 32.4 Puppeteer Field proofs

1. An exact dot returns every stored channel exactly within tolerance.
2. A triangle centroid produces one-third weights and expected blended transforms.
3. Crossing a shared triangle edge creates no pose discontinuity.
4. Outside-hull motion clamps to the nearest hull edge without extrapolation.
5. Shuffling dot serialization order produces identical triangulation and results.
6. Cocircular four-, five-, and six-dot fixtures produce one stable symbolic-perturbation triangulation.
7. Zero, one, two, on-line/off-line collinear, beyond-endpoint, equal-distance hull, and near-degenerate cases
   follow section 19.3.
8. Quaternion `q` and `-q` produce identical rotations.
9. Equal weighting of identity and a 90-degree rotation produces 45 degrees within tolerance.
10. Every `q/-q` permutation and dot serialization order produces the same log/exp chart result, including at
    triangle centroids and shared edges.
11. Any dot/joint rotation beyond the declared 170-degree distance from the immutable chart Pose is rejected
    with an explicit choose-another-chart-or-split-the-Field diagnostic.
12. Duplicate/too-close dots, poorly conditioned triangles, and incomplete poses are rejected.
13. Moving/rebinding a dot creates a Field revision.
14. Exact-dot tests evaluate the complete Field pose before any Behavior Layer mask.
15. Serialize, load, compile, and evaluate reproduces golden poses within tolerance.
16. Profile-admitted parent-local translation and positive XYZ scale interpolate to the expected values and
    move every attached/descendant Part through the same evaluated Space Node transform.

OF-07 implementation result: `ObjectFactoryPoseField` freezes the section 19.3 evaluator as canonical
reference-only bytes plus a deterministic compiled topology; `ObjectFactoryPuppeteerSession` supplies the
transient revision/history/cursor authority. The production Puppeteer projection exposes Teach/Perform,
named dots, contributing weights, and the live complete-Pose result. The focused pure-domain and Swing
proofs cover every item above, including stable four/five/six-dot cocircular fixtures and the reviewed
expected-width plate. Recording, Performance Path, Native Package Field membership, and later behavior
composition remain absent for OF-08A and later bodies.

Initial cross-platform numerical tolerances are **proposal**:

- Translation error no greater than `1e-6 feet`
- Dimensionless scalar/scale error no greater than `1e-6`
- Quaternion angular error no greater than `1e-5` radians

Persisted pose values are canonical finite float32; evaluation may use float64 internally and normalizes at
bake boundaries.

### 32.5 Recording and composition proofs

1. The same monotonic pointer-event fixture produces equal 60-tick cursor samples.
2. Zero-duration, sub-tick, exact-tick, and just-over-tick fixtures obey ceiling/last-coordinate rules.
3. Same-timestamp events use last-input coordinate; out-of-order events reject.
4. A stationary held cursor records the exact canonical tick duration.
5. Playback at different render frame rates agrees at shared timestamps.
6. Trim, split, and closed/C0-loop validation are deterministic; deferred edits are unavailable rather than
   underspecified.
7. `R=1` evaluates source endpoint `N` without modulo and permits zero duration; finite `R>1` and indefinite
   repeat require positive qualified duration, are continuous at internal cycle boundaries, and a finite
   final boundary evaluates endpoint `N` rather than tick zero.
8. Before-start, after-end, offset-layer, zero-duration, and explicit endpoint-contribution cases follow the
   declared no-contribution policy.
9. A mask weight of zero leaves the incoming channel unchanged through final limit projection.
10. A head-only or arm-only layer leaves every unmasked channel unchanged.
11. Root motion remains unchanged unless explicitly included.
12. Fractional masks and `alpha=0/1` produce exact expected endpoints.
13. Override and additive formulas match golden poses, including all `q/-q`, near-pi, and exact-pi branches.
14. Duplicate layer order rejects; valid explicit order resolves noncommuting conflicts predictably.
15. Overlapping dual absolute-root owners, including the base, reject; permitted additive-root ownership is
    explicit.
16. Missing Rig, Field, Pose, mask, evaluator, projector, or solver revision fails closed.
17. Editing a Field creates a new revision and leaves an existing Performance Path pinned to old playback.
18. An exact-Rig Path refuses structural retargeting until an explicit Intent revision exists.
19. A baked Solved Clip records exact source/solver versions, equals authoritative Path evaluation at every
    bake tick, and records measured errors for its declared intermediate validation samples. A requested
    bake-error threshold accepts or rejects the bake rather than turning interpolation into an unstated
    equivalence guarantee.
20. Every profile-permitted parent-local translation, rotation, positive scale, root, and deformation channel
    survives Solved Clip bake, save, reload, and evaluation at each stored tick.

OF-08A implementation result: the focused domain proof covers items 1 through 6 and exact dependency/
Field-edit/Solved-Clip requirements 16 through 20 that apply before Behavior Layers. The isolated Native
Package proof reconstructs the authoritative Field/Pose/Path/Clip graph and rejects a missing Path. Items 7
through 15 that require repeat/layer/mask/root composition remain OF-08B; no implied implementation is made.

OF-08B implementation result: items 7 through 15 now have focused pure-domain proof over immutable
canonical Behavior revisions and pinned exact-Rig sources. The composer implements inclusive intervals,
qualified finite/indefinite repeat, endpoint truth, per-component masks, deterministic order, visible
conflicts, override/additive formulas including q/-q and exact-pi branches, default-zero root channels,
explicit root ownership, and final ordinary joint-limit residuals. One bounded biped fixture demonstrates
an in-place step plus offset upper-body wave and one short whole-body dance. Native Package membership and
the grouped mixer surface preserve the same exact source/revision boundary; retargeting and advanced
contacts/events remain deferred.

### 32.6 Persistence and operation proofs

1. Save New creates one coherent object/version/artifact graph.
2. Save appends only against an exact expected current revision.
3. Save As produces a distinct identity with equal absorbed component revisions.
4. Later source/template changes do not alter the derived object.
5. Reload reconstructs equal authoritative component hashes and compatibility signatures.
6. Cancellation before commit leaves no partial durable state.
7. Stale worker result and stale writer cannot commit.
8. Failure at every transaction boundary rolls back the whole operation.
9. Existing Factory object kinds and Viewer behavior remain unchanged.
10. The Native manifest rejects missing, duplicate, unknown-required, reordered-repeatable, or hash-mismatched
    components and reloads every present Rig/Atlas/Appearance/motion profile rather than recipe+OBJ only.

### 32.7 Import/conversion proofs

1. Extension mismatch is detected by checked format inspection.
2. Unsafe names, paths, archive entries, and oversized inputs are rejected.
3. Unit/axis/handedness normalization matches a frozen transform fixture whose admitted result is canonical
   feet regardless of source unit or chosen display unit.
4. Unknown unit/orientation requires explicit resolution.
5. Equal deterministic recipe produces equal candidate and receipt hashes.
6. Different field framing cannot collide through ambiguous concatenation; the domain-separated envelope has
   frozen byte fixtures.
7. Source bytes remain immutable and linked to the accepted derivative.
8. Collapse reduction and planarization metrics are separately reported.
9. Invalid topology or error threshold rejects the candidate without source loss.
10. Topology changes invalidate dependent UV, weights, and raw-index mappings.
11. Missing Rig/semantics are reported rather than inferred as present.

### 32.8 Appearance-provider proofs

All ordinary request-assembly tests use offline deterministic fake providers.

1. Request contains the exact Atlas identity, dimensions, guide, masks, and selected references.
2. It contains no authority to modify geometry or Rig.
3. Wrong-size, malformed, transparent-invalid, stale, cancelled, and failed results cannot replace current
   Appearance.
4. Accepted output is coverage-masked, dilated by the local program, and previewed on the exact Form.
5. Reject leaves the prior Appearance byte-identical.
6. Accept creates an immutable revision with complete request/result provenance.
7. No network or paid call occurs in focused tests.

### 32.9 Future capability and review proofs

1. A six-appendage nonhuman Rig serializes and mirrors only through explicit symmetry groups, without
   humanoid special cases.
2. A behavior requiring two manipulators accepts a qualified four-manipulator target only through explicit
   role mapping.
3. Adding facial channels creates a new Channel/Deformer signature; old complete Poses remain pinned to their
   prior schema.
4. A texture change preserves mechanical behavior compatibility but makes a presentation-dependent language
   Review Record inapplicable.
5. Every language-review verdict becomes inapplicable—not deleted—when any scoped dependency changes.
6. An unqualified dragon cannot accept a human signed-language behavior by role-name coincidence.

### 32.10 Manual end-to-end acceptance journeys

The following user-visible journeys define product milestones:

**Static form journey**

1. Open a procedural fixture.
2. Adjust an arm radius and length.
3. See stable semantic regions in Solid and flattened UV views.
4. Replace the checker PNG.
5. See the Textured form change with no geometry change.
6. Save As and confirm the source remains unchanged.

**Reference journey**

1. Choose a basic Body Form.
2. Register a straight-on SRD creature image.
3. Align it through opacity, scale, and translation.
4. Adjust the form while Front remains registered.
5. Project observed color and inspect explicit unresolved sides/back.

**Puppeteer journey**

1. Pose an arm down and save a dot.
2. Pose the arm up and save a second dot.
3. Move the Perform cursor between them and see continuous motion.
4. Add a third whole-body pose and traverse the resulting field.
5. Record a timed gesture, including a hold.
6. Replay it and save it as a named behavior.
7. Compose it with another masked behavior and observe deterministic conflict handling.

**One dragon, many dragons journey**

1. Open one accepted `dragon.basic` Form.
2. Apply three distinct compatible Appearance revisions.
3. Confirm identical topology, UV, Rig, and behavior signatures.
4. Play the same pose field or dance on each visual character.

**Import journey**

1. Inspect an external STL or OBJ without committing.
2. Review units, components, materials/UV presence, candidate metrics, and conversion recipe.
3. Accept the conversion and retain both immutable source and native derivative.
4. Continue editing through the same native Object Factory workflow.

## 33. Overall definition of success

The initiative is successful when a non-specialist can:

1. Select a known programmatic creature form.
2. Adjust understandable dimensions and parts without touching mesh syntax.
3. Register a Front reference and understand what it can and cannot prove.
4. See exactly how every named 3D surface maps to the flattened atlas.
5. Describe a visual character and receive a reviewed texture map on that unchanged form.
6. Save many distinct characters sharing the same form, Rig, and behaviors.
7. Pose the Skeleton through direct Pose Handles under the selected Rig's visible translation, rotation, scale,
   and constraint policies.
8. Save complete poses as dots, move through their 2D field, and record a performance.
9. Reuse and layer behaviors so the character can walk, wave, strike, and dance.
10. Derive new saved objects without live inheritance.
11. Optionally import an external file and convert it into the same native contracts.

The deepest architectural promise is simple:

> Build the body once. Paint it many ways. Teach it how to move. Preserve everything as explicit,
> inspectable, versioned Talisman knowledge.

---

## 34. Specification disposition

This document is the clean handoff for future implementation planning. It does not itself change Talisman,
Seasons content, Dwarf War, a database, provider state, or the running SRD batch. No paid image request was
made.

Before coding, establish and verify the permanent Object Factory ownership gate in section 29. The first
authorized implementation body is OF-01 and no larger: the deterministic arm geometry-plus-UV fixture and
its offline artifacts. Subsequent bodies should be re-confirmed against the then-current Talisman Atlas and
landed source before execution.
