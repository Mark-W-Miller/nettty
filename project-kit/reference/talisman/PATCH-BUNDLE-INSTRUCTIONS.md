# Talisman Patch ZIP Instructions

Use these instructions whenever an online chat or another external author prepares changes for the
user to apply to Talisman.

## Deliver exactly one ZIP

Deliver one descriptively named `.patch.zip` file, for example:

```text
authoring-toolbar-cleanup-2026-08-24.patch.zip
```

The ZIP should contain:

- one focused, nonempty `changes.md`;
- one or more numbered Git-compatible patch files in application order.

Older ZIPs without `changes.md` remain supported. For those only, the script generates a simple
fallback `changes.md` from the descriptive ZIP name and retains it with the patches. New online-chat
deliveries should always include the real description because it is more useful in release notes.

Keep those files together at the ZIP's top level. One single wrapper directory containing all of
them is also accepted. Do not nest patches or split the summary and patches between locations.

```text
authoring-toolbar-cleanup-2026-08-24/
├── changes.md
├── 0001-clean-up-toolbar.patch
└── 0002-adjust-focused-tests.patch
```

## Keep changes.md focused

`changes.md` is deliberately plain and readable: start with a short summary, then use useful concise
bullets to describe meaningful or user-visible changes. It may include enough detail to be the
authoritative “what will change” description, but online chats must avoid technical essay material.

```markdown
Simplifies the Authoring toolbar.

- Keeps primary controls easier to find.
- Preserves existing keyboard behavior.
```

Do not include a baseline, installation steps, checksums, generated ledger, script, or unrelated
process material. A generous 64 KiB safety bound prevents accidental non-document payloads without
forcing a useful change description into an arbitrary tiny line limit.

## Patch requirements

- Use repository-relative `a/` and `b/` paths in standard Git-compatible unified patches.
- Use Git binary patch format for binary changes.
- Include only requested source, documentation, and focused-test changes.
- Number every patch (`0001-...patch`, `0002-...patch`) in application order.
- Do not include builds, caches, credentials, private/live data, or temporary files.
- A changed Main `HEAD` is allowed. Do not add a baseline or exact-HEAD lock; `git apply` detects real
  conflicts.

Before delivery, inspect the ZIP, read `changes.md`, and verify the numbered patches in order with
`git apply --check` when an appropriate checkout is available. Report the ZIP name and verification,
but do not claim it has been applied, committed, pushed, released, built, or launched.

## Applying the ZIP

The user runs the repository-root script:

```bash
./apply-patch.sh authoring-toolbar-cleanup-2026-08-24.patch.zip
```

The script refuses loose patches and directories. Before mutation it validates safe ZIP paths, the
single supported layout, any supplied `changes.md`, and numbered top-level patches. It tolerates a
changed Main, displays the supplied or generated `changes.md` as “what this patch bundle will change,”
and lets `git apply` report actual conflicts.

On success, it creates a collision-safe `patches/<bundle-name>/` folder containing `changes.md` and
the numbered patches directly together, normalizes away a ZIP wrapper, removes irrelevant
`__MACOSX` metadata, and deletes the delivery ZIP. The repository retains the unpacked folder, never
the ZIP. It records that folder, patch count, and exact `changes.md` content in
`patches/APPLIED-PATCHES.md`, creates a readable `Patch: Apply ...` commit, and pushes the current
branch to its configured upstream. Pre-existing staged changes stop the operation. Its isolated
index excludes unrelated unstaged changes, including unrelated edits in a file also changed by the
patch. A rejected push retains and reports the exact successful local commit; it never claims remote
publication succeeded.
