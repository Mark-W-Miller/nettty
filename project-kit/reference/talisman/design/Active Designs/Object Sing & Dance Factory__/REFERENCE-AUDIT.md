# Object Factory: Cet (“Set”) and Dwarf War read-only audit

Date: 2026-08-29  
Disposition: evidence and prototype contract only; no repository implementation authorized or begun.

## Executive finding

The only evidence-backed fixture matching **Set** in the Object Factory discussion is the Seasons character
**Cet** (the phonetic “Set”). No separate file, database row, or Unity source literally named `Set` was
found. Cet's exact source fixture is a Hero Forge/Tabletop Simulator-style UnityFS bundle with an adjacent
OBJ and five PNG maps. Current Talisman source independently pins and converts that exact bundle, so the
Cet source identity and mesh/material/texture relationship are proven even though the adjacent OBJ itself
contains no `mtllib` or `usemtl` statement. Treating conversational “Set” as Cet is the strongly supported
identification, but remains an inference rather than a filename-level fact.

Cet is useful as a UV-organization reference, not as a low-poly budget: it has 53,833 seam-expanded
vertices and 92,910 triangles. Exact-position welding reduces that to 46,553 positions in 50 closed,
watertight components. Its atlas has 384 non-overlapping UV islands and 7,037 welded edges deliberately cut
as seams. The islands occupy 34.81% of the full 2048×1024 atlas by summed triangle area and use only the
lower 62.4% of its height. The important reference traits are stable vertex/UV/normal correspondence,
recognizable semantic islands, explicit seams, and one exact atlas shared by the textured mesh.

Dwarf War does **not** currently contain a generic silhouette-preserving planarization/faceting algorithm.
Its batch reduction is Blender collapse decimation followed by smooth shading. Its browser laboratory has a
separate, user-seeded local operation that projects selected nearly coplanar vertices onto one plane. That
local tool is genuinely planarization, but it is not global, automatic, silhouette constrained, or an OBJ
export pipeline. Dwarf War is therefore a reference for reduction staging, metrics, visual approval, local
plane-selection tolerances, snapshots, and provenance—not a dependency or a ready-made global faceter.

## Exact Cet source inventory

All paths below are read-only source evidence under Seasons World Content.

| Role | Exact path | SHA-256 / measured fact |
|---|---|---|
| Immutable Unity source | `/Users/mmiller/Git/Seasons World Content/Characters/Cet/3D/Cet Young_TTS_assetBundle.unity3d` | `095ece075ef2697129a6bf807af74109a03eae7103f5f7404e9ab14c34ef358d`; 2,376,251 bytes |
| Adjacent mesh export | `/Users/mmiller/Git/Seasons World Content/Characters/Cet/3D/Cet Young TTS Assets/Cet Young TTS Mesh.obj` | `dea5cc191a97a006ea45d418059e4fa41da3fdb17d3694d164000ac15ba4f0c1`; 10,613,772 bytes |
| Diffuse/base color | `/Users/mmiller/Git/Seasons World Content/Characters/Cet/3D/Cet Young TTS Assets/Cet Young TTS diffuse.png` | `82504efc415b1bb73f30ef98756cccc0d5d65c1e5f9cb77671eac10637065bbf`; 2048×1024 RGBA |
| Emissive | `/Users/mmiller/Git/Seasons World Content/Characters/Cet/3D/Cet Young TTS Assets/Cet Young TTS emissive.png` | `2730851d04468f26f3878b3fddf610ce0b5337e86897eefd71df1f8d355031c1`; 2048×1024 RGBA |
| Metallic/gloss | `/Users/mmiller/Git/Seasons World Content/Characters/Cet/3D/Cet Young TTS Assets/Cet Young TTS metal.png` | `2604005e128b5c752e2cecf9f309aa8989b7723450bb44b0ff568b40154e376a`; 2048×1024 RGBA |
| Tangent-space normal | `/Users/mmiller/Git/Seasons World Content/Characters/Cet/3D/Cet Young TTS Assets/Cet Young TTS normal.png` | `4c4c2b03ac427fc6b20b37611f310eac6d6201dc2da755f7bb13ec17f372c9e7`; 2048×1024 RGBA |
| Ambient occlusion | `/Users/mmiller/Git/Seasons World Content/Characters/Cet/3D/Cet Young TTS Assets/Cet Young TTS occlusion.png` | `941dfeb02da6e1a41b7328cbc6a91a6c6d919897512db156fb3ba214a396e3e1`; 2048×1024 RGBA |
| Printing-source comparison, not the Unity UV fixture | `/Users/mmiller/Git/Seasons World Content/Characters/Cet/3D/Cet Young.stl` | `781d6f9153758f76b54702a70e1547c88051dab72f309bdd2678186dd002ed97`; no UVs |
| Separate sword STL, not part of this OBJ atlas | `/Users/mmiller/Git/Seasons World Content/Characters/Cet/3D/Cet Young Sword.stl` | `1519e9b67d587f01f99637ddec2aae52f8ac5fba09a2fc3871fc7a82967fa9f4` |

There is no adjacent `.mtl` file. The adjacent OBJ contains 53,833 `v`, 53,833 `vt`, 53,833 `vn`, and
92,910 triangular `f` records, and every face uses identical `v/vt/vn` indices. It has no `mtllib`, no
`usemtl`, no smoothing directive, and only one face-bearing group (`default_0`). Therefore the sibling
diffuse association is external/package convention in that export, not an OBJ-declared relationship.

## Proven model/material/texture relationship in Talisman

Current Talisman source pins the exact Unity source path and SHA in:

- `/Users/mmiller/Git/talisman-git/talisman-main/src/test/java/com/moondance/talisman/app/services/app/UnityFsBundleConverterTest.java`
- `/Users/mmiller/Git/talisman-git/talisman-main/src/test/java/com/moondance/talisman/app/database/userassets/UnityFsBundleOperationServiceTest.java`
- `/Users/mmiller/Git/talisman-git/talisman-main/Design/Active Designs/Assets Manager/UNITYFS-BUNDLE-CONVERSION.md`
- `/Users/mmiller/Git/talisman-git/talisman-main/tasks/Doing.md`

The converter proves one uncompressed Unity Mesh, one Material, and exactly five Texture2D bindings from
the embedded type tree:

| Unity material property | Talisman role | Adjacent export |
|---|---|---|
| `_MainTex` | diffuse | `Cet Young TTS diffuse.png` |
| `_EmissionMap` | emissive | `Cet Young TTS emissive.png` |
| `_MetallicGlossMap` | metallic | `Cet Young TTS metal.png` |
| `_BumpMap` | normal | `Cet Young TTS normal.png` |
| `_OcclusionMap` | occlusion | `Cet Young TTS occlusion.png` |

The reviewed profile is UnityFS 8, player `5.x.x`, Unity `6000.0.62f1`, serialized format 22, archive
flags `0x243`, one block, two nodes, LZMA data, and BC1/BC3 textures. Talisman emits deterministic
code-owned OBJ, MTL, five PNGs, and a manifest. Its generated MTL declares `map_Kd` (diffuse), `map_Ke`
(emissive), and `map_Bump` (normal). Metallic and occlusion remain manifest-bound Assets artifacts because
the current Viewer does not render them. The Viewer consumes canonical OBJ plus one diffuse image; Cet's
2048×1024 diffuse exceeds its 1,048,576-pixel presentation bound, so this conversion path creates a bounded
1024×512 Viewer derivative while retaining the full source map.

## UV and mesh measurements

The side-by-side inspection artifact is `cet-set-uv-and-textured-object.png`. Left: the exact diffuse PNG
with only UV island boundaries overlaid. Right: the same OBJ rendered with that diffuse map.

| Measurement | Result |
|---|---:|
| OBJ vertices / UVs / normals | 53,833 / 53,833 / 53,833 |
| Triangles | 92,910 |
| Exact welded 3D positions | 46,553 |
| Closed physical components after exact weld | 50 |
| Open / non-manifold edges after weld | 0 / 0 |
| UV islands | 384 |
| Deliberate seam edges after weld | 7,037 |
| UV boundary edges after seam expansion | 14,074 |
| UV bounds | U `0.000530..0.999512`; V `0.000721..0.624023` |
| Summed non-overlapping UV triangle area | 0.348131 of the complete atlas |
| Model dimensions | 17.9554 × 18.3308 × 40.9917 source units (consistent with miniature millimetres) |
| Total mesh surface area | 3,339.24 square source units |
| Overall linear diffuse density | 14.79 pixels/source unit |
| Area-weighted face density P10 / P50 / P90 | 8.26 / 11.59 / 16.54 pixels/source unit |

The density spread is material/feature prioritization, not uniform density. The face and body receive large,
recognizable islands; equipment, straps, trim, and repeated details receive many smaller islands. The atlas
is easy to understand visually, but it is neither compact nor a suitable topology budget for Object Factory.

## What is proven versus inferred

### Proven

- The Unity bundle SHA, Unity profile, mesh count, five map roles, and one-material binding are pinned by
  current Talisman tests and design records.
- The adjacent OBJ/PNG paths, file hashes, dimensions, exact OBJ indices, seam/island counts, manifold
  condition, UV bounds, and density measurements above come directly from read-only inspection.
- The current Talisman UnityFS converter emits a canonical OBJ/MTL/PNG package, and the current Viewer uses
  canonical OBJ plus diffuse only.
- An immutable query of `/Users/mmiller/.talisman/talisman-project.sqlite` found no `Set`-named or UnityFS
  managed asset. Its two current `Cet Young.obj` rows derive from `Cet Young.stl` SHA
  `781d6f…002ed97`, not from the Unity bundle.

### Inferred or not established

- The conversational fixture name “Set” means the character `Cet`. This is strongly supported because the
  exact Cet Unity fixture is pinned throughout current Talisman and no separate Set asset exists locally,
  but the spelling equivalence is not encoded in a source manifest.
- The adjacent OBJ and PNG files are documented as the intended adjacent result of the Unity bundle and
  match its decoded counts/roles. There is no adjacent digest-bearing manifest, so their byte-for-byte
  derivation from that exact bundle is documented rather than cryptographically demonstrated locally.
- Historic ChatGPT “Generative UV Dialog” screenshots omit attached image bytes. It is therefore not proven
  that those screenshots displayed this exact Unity OBJ/PNG package. The live database instead proves an
  STL-derived Cet OBJ was also used by Talisman.
- The visual semantic names of individual islands (face, boot, bow, etc.) are human inspection, not labels
  encoded in the OBJ. The package has one material and no semantic region file.

## Exact Dwarf War source seam

### Automatic batch reduction

Primary seam: `/Users/mmiller/Git/dwarf-war-combat-simulator/tools/blender/prepare_cavern.py`.

- Inputs: one or more `--input` paths, practically STL only (`bpy.ops.wm.stl_import`).
- Controls: required `--ratio` in `(0,1]`, required `--output`, optional `--name`, optional `--save-blend`.
- Algorithm: join imported meshes; Blender `DECIMATE` modifier, `COLLAPSE`, requested ratio,
  `use_collapse_triangulate=true`; then mark every polygon smooth.
- Outputs: Y-up triangulated OBJ with normals; no UV, colors, or materials; sibling JSON with source/output
  counts, source dimensions, ratio, bytes, time, and notes; optional `.blend`.
- Preservation: it records source dimensions and retains the source input, but supplies no numerical
  silhouette, volume, Hausdorff, landmark, component, or manifold guarantee. Approval is visual.

The Sewers benchmark at
`/Users/mmiller/Git/dwarf-war-combat-simulator/docs/sewers-reduction-benchmark.md` measured 227,004 source
triangles, 113,502 at 50%, 56,750 at 25%, and 22,700 at 10%. The accepted production heuristic is
approximately 20,000–25,000 triangles at normal board distance, preserving gameplay silhouette and
recognizable details rather than invisible print fidelity. That cavern-specific budget is not a general
Object Factory budget.

The approved runtime contract is stated in
`/Users/mmiller/Git/dwarf-war-combat-simulator/assets/3d/caverns/README.md`: STL is input-only; reviewed
runtime assets are OBJ plus MTL and textures with stable scale, orientation, origin, names, and anchors.

### Local planar cleanup and repair

The browser laboratory is
`/Users/mmiller/Git/dwarf-war-combat-simulator/web-lab/src/main.js` plus
`/Users/mmiller/Git/dwarf-war-combat-simulator/web-lab/index.html`.

- Browser import accepts OBJ only.
- A clicked seed selects connected triangles whose normal differs by at most 4°, whose center is within
  0.18 model units of the seed plane, and whose shared-edge vertices match a 0.02-unit quantization.
- `smooth-selection` projects selected vertices exactly onto the seed plane. “Remove pimples” moves only
  positive offsets greater than 0.001; full flattening moves all selected vertices.
- It records operation recipes, snapshots geometry for undo, and preserves the current mesh when CSG or
  smoothing fails.
- Sealed-cylinder addition and bottom trimming are CSG editing operations, not decimation or faceting.
- The browser does not export the edited OBJ; it persists UI/edit state for replay.

`/Users/mmiller/Git/dwarf-war-combat-simulator/tools/blender/find_flat_regions.py` is analysis-only: it
finds connected upward faces with normal Z ≥0.995, adjacent center-height change <0.02, and minimum area
4 mm² by default. `repair_sewers.py` is a cavern-specific semantic surgery script (eight measured socket
caps plus selected center treatment) followed by the same collapse decimation and smooth shading.

### Decimation versus faceting conclusion

| Capability | Dwarf War today |
|---|---|
| Reduce triangle count | Yes: generic collapse decimation |
| Preserve silhouette by measured constraint | No; visual review only |
| Create globally broad intentional planes | No |
| Flatten one reviewed local region | Yes: seed-plane projection |
| Produce hard faceted shading | No; batch scripts explicitly smooth every polygon |
| Preserve UV/material regions | No; reduction exports disable both |
| Preserve original on failed browser edit | Yes: cloned snapshots / delayed replacement |
| Emit stable reviewed runtime artifact | Batch OBJ+JSON; browser edits are replay state only |

Portable ideas: staged source/candidate separation, exact counts/dimensions/timing metadata, multiple ratio
candidates, repeatable inspection views, original preservation, explicit visual approval, local plane
selection tolerances, immutable operation recipes, and undo snapshots. Do not port Blender as a runtime
dependency, cavern constants, browser CSG, the 10% budget, or any assumption that collapse decimation is
planarization.

## First isolated deterministic topology/UV prototype contract

The following is the proposed contract. Numeric thresholds marked **proposal** are design choices derived
from the evidence; they are not facts encoded in Cet or Dwarf War.

**Disposition note (2026-08-29):** this section records the audit-era proposal. The normative
`SYSTEM-DESIGN.md` now supersedes its sequencing details: the first code body is **OF-01**, a procedural
arm fixture only; the default atlas proposal is 1024x1024; and imported-model staging/reduction is the
parallel **OF-P01A** lane after OF-01 freezes the native-candidate boundary. The Cet and Dwarf War findings
remain source evidence, not a reason to put an imported fixture in OF-01.

1. **One stable geometry candidate.** Normalize to Factory X-right/Y-up/Z-forward, retain component IDs,
   stable vertex/face order, source bounds, and source digest. Output must be finite, closed, outward-facing,
   two-manifold, and non-degenerate—the invariants already enforced by `FactoryIndexedMesh`.
2. **Two entrances, one downstream mesh.** Procedural topology enters directly. Imported STL or OBJ later
   enters through the checked parsers, then explicit reduction/faceting. No FBX/GLB is proposed initially.
   Both routes ultimately produce the same immutable indexed-mesh contract before UV work, but import is
   OF-P01A and is not part of the first OF-01 code body.
3. **Reduction is not enough.** Deterministic edge collapse may create a bounded candidate, but the stable
   Object Factory geometry is produced only after deterministic plane clustering and projection. Reuse the
   Dwarf 4° normal threshold as the first **proposal**, lock component/material/region boundaries, and reject
   any collapse or projection that opens, inverts, merges, or changes a protected component.
4. **Measured preservation.** Freeze canonical orthographic review views and landmarks. First **proposal**:
   maximum silhouette displacement ≤0.5% of bounding-box diagonal, RMS surface displacement ≤0.25%, and
   no protected landmark/component/material-boundary movement. Thresholds belong in a versioned recipe,
   never hidden defaults.
5. **Program-owned seams and islands.** Build seams only from physical component boundaries, approved hard
   facet boundaries, and explicit material-region boundaries. Duplicate vertices at seams so each exported
   index owns one position, one normal, and one UV, matching Cet's proven `v/vt/vn` one-index form.
6. **Deterministic packing.** Sort islands by stable semantic region ID, then area, then source face ID;
   permit only enumerated rotations; use integer texel coordinates; reject overlap and out-of-bounds UVs.
   Atlas dimensions, gutter, and packer version are recipe fields. The audit first considered a 1024x512
   Cet-shaped atlas; the normative first proposal is now a 1024x1024 logical atlas with 8-pixel edge gutter
   and 4-pixel dilation, within the current Viewer pixel bound.
7. **Texel density is explicit.** Choose a target density by object family and region weight. Enforce ±5%
   within a region (**proposal**) and record deliberate semantic exceptions. Do not copy Cet's observed
   8.26–16.54 px/unit spread accidentally.
8. **One initial material region.** The first package is exactly OBJ + MTL + diffuse PNG. The OBJ declares
   one `mtllib` and stable `usemtl`; the MTL declares `map_Kd`; the PNG dimensions and SHA are frozen. More
   regions and PBR maps are later versions, not implicit extras.
9. **AI paints, never lays out.** The provider receives the exact blank/guide atlas whose dimensions,
   islands, semantic region mask, and immutable identity were produced locally. The returned diffuse must
   match dimensions and coverage exactly. No topology, seam, island, density, or material-region decision
   is delegated to the image model.
10. **Reproducibility proof.** Same normalized recipe and equal input identity must reproduce byte-identical
    indexed geometry, UV arrays, OBJ, MTL, guide/mask, and blank diffuse before any provider call.

## Exact minimum future implementation body and owner boundary

**Object Factory OF-01: native Form contract, deterministic procedural arm, and offline artifacts.**

Minimum source work, only after the permanent project gate:

- Add the Object Factory-owned pure native Form/compiler boundary and one procedural fixture: shoulder ball,
  upper-arm faceted cylinder, elbow ball, tapered forearm cylinder, and flattened hand.
- Establish canonical-foot Space Nodes/Control Points, parent-relative TRS, any-number Part attachment,
  stable semantic IDs and Regions, deterministic primitive shells, seams, islands, packing, and normals.
- Reuse `FactoryIndexedMesh`, `CanonicalModel3DObjWriter`, and Viewer preview-frame types where their checked
  contracts fit. Do not place imported-model parsing or reduction in OF-01.
- Emit byte-stable OBJ, automatic MTL, active diffuse PNG, guide/coverage/region PNGs, and `regions.json`.
- Assert manifold/normal truth, non-overlap, gutter, density, exact seam relationships, inherited TRS, and
  identity-signature behavior. Rig, persistence, provider calls, and imported sources remain absent.
- Produce a local side-by-side flattened-atlas/textured-object preview from a deterministic checker/region
  texture. Stop before provider calls, UI workflow, or durable admission.

Imported STL/OBJ staging, normalization, repair, reduction, faceting, metrics, and candidate preview belong
to **OF-P01A**. That parallel lane may begin only after OF-01 freezes the native-candidate boundary; it is
not a prerequisite for procedural construction.

Owner boundaries:

- **Object Factory owns:** native topology, Space/Part form recipes, seams, islands, packer, density,
  material-region identities, guide/mask, deterministic artifact bytes, and—within OF-P01A—conversion
  recipe semantics and candidate truth.
- **Assets Manager owns:** checked persistence/admission and Media membership through the already-landed
  Factory Object Save/application-service seam. Object Factory must not create another store.
- **3D Viewer owns:** presentation of canonical OBJ plus diffuse; it does not own geometry or UV semantics.
- **Generative provider adapter owns later:** one exact reviewed atlas-paint request and returned PNG bytes.
  It never owns geometry/UV decisions and is excluded from OF-01.
- **Dwarf War:** no source change and no runtime/library dependency. Only port the small, generic ideas that
  survive an independent Talisman implementation.
- **Atlas Bodies 6–8:** optional later consumers only; no start gate or required source change.

Repository implementation must not begin in `talisman-main` or a generated checkout. The required gate is
a permanent named Talisman worktree/project at
`/Users/mmiller/Git/talisman-git/talisman-object-factory`, branch `codex/object-factory`, tracking
`origin/codex/object-factory`, saved in Codex as the permanent **Object Factory** project/task and reconciled
clean/equal to current Main before source work.

## Audit safety record

The Talisman `AGENTS.md`, `AGENTS.project.md`, Atlas, index, and package routing guidance were read and
applied. During this reference audit, Talisman Main, Seasons content, Dwarf War, databases, provider state,
and the SRD batch were not modified. Database inspection used SQLite immutable mode. No application was
launched, no tests/builds were run in Talisman or Dwarf War, and no paid or provider image call was made.
The only generated audit files are this report, its inspection PNG, and disposable analysis intermediates
in the projectless Object Factory task directory. A later user-authorized documentation-only save copied
the finished design package into Main without changing source code.
