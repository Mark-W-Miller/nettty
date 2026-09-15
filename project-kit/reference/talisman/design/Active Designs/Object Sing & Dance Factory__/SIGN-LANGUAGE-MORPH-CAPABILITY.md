# Sign Language as a Root Morph Capability

Status: normative design direction; no implementation body is authorized.

Owner boundary: the Moondance Morph language is the sole canonical runtime and interchange authority.
HamNoSys 4, SiGML, motion datasets, and other notations are foreign evidence and ingestion formats. They may
inform translation into native Morph structures but MUST NOT dictate the internal model.

## Navigation

1. [Purpose and decisions](#1-purpose-and-decisions)
2. [Root capability model](#2-root-capability-model)
3. [Question contract](#3-question-contract)
4. [Answer contract](#4-answer-contract)
5. [Language and internationalization](#5-language-and-internationalization)
6. [Foreign notation mapping](#6-foreign-notation-mapping)
7. [Source authority and database materialization](#7-source-authority-and-database-materialization)
8. [Validation and acceptance](#8-validation-and-acceptance)
9. [Smallest vertical slice](#9-smallest-vertical-slice)
10. [Staged evolution](#10-staged-evolution)
11. [Non-goals and open design freedom](#11-non-goals-and-open-design-freedom)
12. [Public references and licensing](#12-public-references-and-licensing)

Normative terms **MUST**, **MUST NOT**, **SHOULD**, and **MAY** describe the intended contract. They remain
design requirements until a separately authorized implementation is accepted.

## 1. Purpose and decisions

Signed language combines manual articulation, timing, spatial coordination, facial expression, gaze, head,
shoulder, torso, and mouth activity. It is not an opaque animation clip and not prose attached to a hand
gesture. Moondance needs a native, inspectable representation that can participate in Morph authoring,
retargeting, editing, validation, provenance, and explicit proposal acceptance.

The governing decisions are:

- Native Morph is the only canonical runtime and interchange result.
- Sign capability belongs to a reusable root Morph capability library, not to Human alone.
- Compatibility is structural and capability-based, never species-name based.
- The first articulation profile is humanoid, but a human, dragon, or another Morph MAY use or adapt it.
- Unusual but explicit mappings are valid. Missing capabilities produce typed failure, never guessed motion
  or silent structural corruption.
- No proposed sign mutates its source Morph before validation, inspection, and explicit acceptance through
  the existing proposal boundary.

## 2. Root capability model

The root library defines reusable capability vocabulary. A Morph declares which capabilities it supplies
and how native semantic roles map onto its own structure. A humanoid profile is an initial profile, not the
definition of signing.

A capability map MUST be sufficient to resolve:

- joints and their stable identities, hierarchy, allowed ranges, and relevant rest state;
- named body and signing-space anchors;
- manual and nonmanual articulators;
- contact, collision, reach, and other constraints;
- dominant and nondominant handedness or an explicit non-hand adaptation;
- symmetry and coordination groups; and
- proportions and scale needed to translate relative location and movement.

A dragon-like Morph may map humanoid-capable roles to forelimbs, talons, wings, neck, head, face, or other
declared articulators. Such mapping is neither automatically accepted nor rejected for being strange. It is
validated against declared structure and the language record's required capabilities. A Morph that cannot
satisfy them returns `MISSING_RIG_CAPABILITY` with exact missing roles.

## 3. Question contract

One request is stateless and self-contained. It carries the minimum sufficient context to answer one
sophisticated question and is fired once to one selected reasoning engine. It MUST NOT rely on conversation
history, an AI session, accumulated hidden context, unstated repository knowledge, or a prior answer.

### 3.1 Required request fields

| Field | Requirement |
| --- | --- |
| `request_id` | Stable unique identity for the attempt. |
| `contract_version` | Exact question and answer contract version. |
| `intent` | Bounded user intent, from an isolated sign through an eventual sentence-level request. |
| `sign_language` | Signed language identity, separate from spoken-language labels. |
| `spoken_language_context` | Optional labels or source text; never a substitute for signed-language
  identity. |
| `target_morph` | Exact Morph identity/revision or a complete compact reference. |
| `target_structure` | Minimum relevant joints, anchors, hierarchy, proportions, and constraints. |
| `capabilities` | Available joints, anchors, articulators, constraints, handedness, and proportions. |
| `library_refs` | Exact root capability-library record identities, versions, and digests. |
| `constraints` | Optional provenance, timing, expression, performance, and style constraints. |
| `output_schema` | Exact requested native proposal schema and version. |
| `validation` | Required structural, linguistic-evidence, numeric, timing, and capability checks. |

Signed language MUST use an ISO 639-3 identity represented directly or within a valid BCP 47 tag. Initial
examples are `ase` for American Sign Language and, later, `pso` for Polish Sign Language. The signed-language
field remains distinct from English, Polish, or another spoken/written language used for labels or source
text.

### 3.2 Excluded request material

The request contains no graphic or texture payload, private path, mutable database handle, unrelated Morph
state, repository checkout, hidden prompt history, or broad project transcript. A compact Morph may refer to
texture or other asset identities; separate image generation can create or improve those assets through its
own authority and acceptance route.

## 4. Answer contract

The answer is exactly one of:

1. one complete native Morph-language proposal; or
2. one typed structured failure.

### 4.1 Successful proposal

A successful result contains, as appropriate:

- native Morph poses and Motion;
- joint, anchor, contact, reach, and coordination constraints;
- checked timing, phase, simultaneity, repetition, transition, and hold information;
- manual articulation and two-hand coordination;
- facial expression, gaze, head, shoulder, torso, and mouth channels;
- language, lexeme, variant, source, transformation, and model provenance; and
- exact target Morph, capability-library, request, schema, and validator identities.

The proposal MUST be inspectable, editable, deterministic where its inputs require determinism, and valid
before acceptance is offered. It MUST NOT return HamNoSys or SiGML as runtime output, an opaque clip, prose
instructions, executable repository code, or an unvalidated mutation. The original Morph remains unchanged
until the existing explicit proposal/acceptance boundary accepts the exact proposal against current
revisions.

### 4.2 Structured failure

Failures are complete, bounded, and safe to inspect. At minimum they distinguish:

- `MISSING_RIG_CAPABILITY`, including exact absent or incompatible roles;
- `UNSUPPORTED_LANGUAGE_ELEMENT`, including the unresolved language element identity;
- `AMBIGUOUS_SIGN_REQUEST`, including bounded alternatives or missing disambiguation; and
- `VALIDATION_FAILURE`, including schema, topology, constraint, timing, provenance, or numeric diagnostics.

Failures contain no partial accepted motion and make no source Morph or database mutation.

## 5. Language and internationalization

There is no universal sign language. Concepts, signed-language lexemes, and spoken-language labels are
separate identities.

For example, one concept such as `concept:greeting.hello` may reference an independently reviewed
`ase:<lexeme-id>` and `pso:<lexeme-id>`. The placeholder illustrates separation; it does not claim that two
signs share form, grammar, regional use, or exact meaning. International Sign MAY be recorded as a possible
contact-system resource, never as a universal signed language or automatic fallback.

Records MUST allow regional, dialect, community, generation, and performance variants. Each released record
requires linguistic provenance and review by fluent signers appropriate to the named language and community.
Geometric plausibility, successful retargeting, and attractive animation are not linguistic correctness.

Sentence evolution MUST model language-specific grammar, spatial reference, agreement, co-articulation,
prosody, and nonmanual structure rather than concatenate isolated spoken-language glosses.

## 6. Foreign notation mapping

Foreign notation is translated at a checked adapter boundary:

| Foreign evidence | Native Morph destination |
| --- | --- |
| Handshape | Finger-joint configuration and manual articulation constraints. |
| Location | Named Morph anchor or checked relative signing-space anchor. |
| Orientation | Native joint or articulator rotation. |
| Movement | Native Motion path or curve with timing. |
| Contact | Morph constraint with exact participants and phase. |
| Symmetry or two-hand relation | Native coordination and symmetry rules. |
| Facial expression | Native face channels. |
| Gaze, head, shoulders, torso, mouth | Matching native nonmanual or body channels. |

Imported notation is retained only as immutable provenance and audit evidence. HamNoSys 4 and SiGML do not
become Morph runtime objects, persistence schemas, or public result contracts.

## 7. Source authority and database materialization

The default root SignMorph capability library MUST live as explicit, reviewable, versioned files in the
source tree. It MUST NOT exist only as mutable database records.

The source-controlled library contains only files actually required by the final design, potentially:

- schemas and validation vocabularies;
- capability and Rig profiles;
- canonical handshapes or articulation primitives;
- initial language-specific sign records;
- provenance and licence metadata; and
- exact import manifests.

These files are authority for shipped defaults. The application database is the operational materialization
used for efficient runtime reads. A clean installation MUST be reproducible deterministically from the
source tree without hand-created database state.

A future validated importer MUST:

- read only declared source-library files and verify schema, identity, version, digest, provenance, and
  licence metadata;
- be repeatable, version-aware, and identity-stable;
- insert or reconcile complete records transactionally;
- preserve user-authored data, locally authored variants, and user modifications;
- never silently delete, overwrite, or relabel user material;
- return typed validation and conflict failures without partial materialization; and
- produce an inspectable receipt relating source versions and digests to the resulting database revision.

The data model distinguishes shipped canonical defaults, imported external evidence, locally authored
variants, and user modifications. A newer source library reconciles against an existing database by stable
identity and exact prior source provenance. Unmodified prior defaults MAY advance under an explicit upgrade
rule. Diverged or user-modified records MUST follow an explicit conflict policy: preserve both, retain the
local value with a pending source candidate, or require review. They MUST NOT be overwritten by recency or
name matching alone.

Runtime MAY read the materialized database for performance. That convenience never transfers source
authority to mutable database rows and never permits runtime repair to invent shipped defaults.

## 8. Validation and acceptance

A proposal is eligible for review only when:

- question and answer schemas, versions, and target identities match;
- the target Morph and capability-library revisions remain current;
- every referenced joint, anchor, articulator, and constraint resolves exactly;
- rotations, paths, contacts, timing, and coordination remain within declared bounds;
- manual and nonmanual requirements are complete for the requested record;
- provenance and language/variant identity are present;
- no undeclared fallback, hidden state, opaque clip, or foreign runtime representation remains; and
- renderer-independent native semantics survive round trip without loss.

Technical validation is separate from linguistic acceptance. Fluent signer review evaluates language,
meaning, naturalness, regional appropriateness, expression, and comprehensibility. Mechanical review checks
retargeting, collisions, reach, continuity, timing, and renderer parity. Both decisions remain explicit.

Eventual acceptance MUST prove equivalent native meaning and coordination through JavaScript and Java
renderers within declared tolerances. This document does not require either renderer implementation now.

## 9. Smallest vertical slice

The first proof uses one isolated sign or a deliberately small reviewed vocabulary before sentence support.
It exercises one complete question-to-answer transaction:

1. select one language-specific reviewed lexeme and one versioned root capability profile;
2. package one self-contained question for an exact current Morph;
3. receive one native Morph proposal or typed failure;
4. validate and inspect the proposal without changing the source Morph;
5. explicitly accept or reject it through the existing proposal boundary; and
6. preserve request, source, transformation, review, and acceptance provenance.

Retargeting proof covers at least one human Morph, one dragon-like Morph that declares the humanoid-capable
profile or an explicit adaptation, and one incompatible Morph. The first two must retain semantic intent
without assuming identical anatomy. The incompatible target must return `MISSING_RIG_CAPABILITY` and remain
unchanged.

## 10. Staged evolution

1. **Capability vocabulary:** freeze the minimum native joints, anchors, articulators, channels,
   coordination, constraints, schemas, and typed failures.
2. **Isolated reviewed sign:** prove one-shot question, native proposal, validation, inspection, rejection,
   and acceptance.
3. **Small vocabulary:** add independent `ase` records, provenance, variants, and cross-Morph retargeting.
4. **Additional language:** add reviewed `pso` records without routing through English or ASL identity.
5. **Sequence and sentence:** introduce co-articulation, grammar, spatial reference, prosody, and discourse
   constraints through native Morph semantics.
6. **Renderer parity and broader evidence:** prove JavaScript/Java equivalence and evaluate additional
   licensed datasets and notations.

Each stage remains independently reviewed. A later dataset or engine enriches or validates the canonical
model; it never replaces native Morph authority.

## 11. Non-goals and open design freedom

This document does not:

- implement source-library files, an importer, database schema, renderer, UI, provider, or model call;
- assign work to Slice, Object Factory, Morph, or another owner;
- select a reasoning engine, prompt wording, JavaScript data structure, Java type hierarchy, or database
  layout;
- claim linguistic correctness for generated motion;
- define a universal sign language or equate sign lexemes with spoken words;
- require sentence generation in the first proof; or
- authorize copying, training on, or redistributing any external corpus.

Slice retains design freedom over internal implementation, provided the external question and answer
contracts, native Morph authority, validation, source-library authority, database-materialization safety,
language identity, and acceptance boundaries remain true.

## 12. Public references and licensing

Public references are evidence inputs, not incorporated assets:

- [HamNoSys 4, University of Hamburg](https://www.sign-lang.uni-hamburg.de/hamnosys/) describes an
  internationally applicable phonetic transcription system and its manual/nonmanual vocabulary.
- [SiGML overview, ViSiCAST/eSIGN](https://www.visicast.cmp.uea.ac.uk/eSIGN/Images/sigmlSigningFlyerA4.pdf)
  describes an XML signing-gesture notation influenced by HamNoSys.
- [BCP 47 / RFC 5646](https://www.rfc-editor.org/info/rfc5646/) defines language-tag structure and
  registration use.
- [SignWriting](https://www.signwriting.org/) is a possible future writing-system evidence source.
- [SignAvatars](https://signavatars.github.io/) and
  [ASL3DWord/SignAvatar](https://github.com/dongludeeplearning/SignAvatar) provide research references for
  holistic and word-level 3D signing motion.
- [How2Sign](https://how2sign.github.io/) provides continuous multi-view ASL research data and annotations.

Every source requires a recorded licence and permitted-use review before download, transformation,
redistribution, model use, or source-tree admission. Dataset availability does not imply commercial,
derivative, redistribution, or training rights. How2Sign, for example, describes its dataset as research-use
material and notes separate copyright constraints for underlying content. Code licences, dataset licences,
performer consent, likeness/privacy rights, annotations, model weights, fonts, and notation specifications
MUST be evaluated separately. Retain attribution and provenance required by each accepted source and copy no
external corpus merely because this design links to it.
