# Collect pinned project updates — release toolinging

When Mark tells a project owner to **update the MPK**, the owner records local findings and proposed
shared improvements. This alone is not an instruction to upgrade its installed release or publish it.
Preserve the released `project-kit/` and official `moondance.lock.json` pin. Update the local index,
supplements and handoff described below. Merlin then collects and curates a separate candidate release.

## Owner handoff

1. Maintain `project-kit-local/README.md`, linking current findings and preserving local authority.
2. Record owner/date, exact source or working-file hashes, adopted Kit version/digest, proposed wording,
   evidence/acceptance limits and local-only decisions using the existing supplement/feedback forms.
3. Copy [the receipt template](../lifecycle/MPK-HANDOFF.template.json) to
   `project-kit-local/MPK-HANDOFF.json`. Fill every placeholder. Use `changes` or `no-changes` and a clear
   summary. List relative Markdown/JSON document paths under `project-kit-local/`; include the index.
   Keep secrets, recordings, binary assets and private payloads out of these review documents.
4. Stage any necessary portable evidence as reviewed documents in that local folder. Links to evidence
   elsewhere are references only; the collector does not traverse them or certify completeness.
   Label provisional candidate guidance explicitly; it never changes the official release pin.

## Collector command

Requires Python 3.10+ and Git. Run from any directory:

```sh
python3 /Users/mmiller/Git/moondance-project-kit/scripts/collect_mpk.py
```

Defaults to a uniquely named directory under `/tmp/mpk-collections`. For durable storage, pass a NEW
output path outside all consumer roots. Existing output directories are never overwritten.

```sh
python3 /Users/mmiller/Git/moondance-project-kit/scripts/collect_mpk.py \
  --output /absolute/review-storage/mpk-next-collection \
  --previous /absolute/review-storage/prior-collection/collection.json
```

The [owner registry](../collection/pinned-projects.json) was checked against pinned tasks and saved
projects on 2026-09-18. It includes Lee, Finian/Thinian, Slice, Dara, Beetle, Atlas, Judy, Tassy, Mandy,
Sally, Sam, Netty, Simon/President and Grand Pubah. Lee occurs once; Merlin and companion chats are
excluded. Repository paths come from saved projects rather than a task's possibly unrelated working
folder. Sam and Grand Pubah currently use task folders and are reported as non-Git sources.

This is an explicit registry snapshot, not live access to the sidebar. Refresh it when owners are
pinned/unpinned or projects move; `--registry` accepts an updated file using the same shape. Branches
share repository identity: `repository_groups` groups owners by their Git common directory while
preserving each worktree's local notes, branch, HEAD, upstream and dirty state. Do not collapse distinct
worktree findings merely because their repository is shared.

## Output and checks

- `sources/`: exact working-file snapshots, including uncommitted Markdown supplements and explicitly
  listed JSON documents. The collector never executes collected content or follows symlinks.
- `collection.json`: per-owner source identity, selected release-pin fields, hashes, issues and changes
  since the optional previous report. Removed files are recorded; first snapshots are labelled.
- `REVIEW.md`: human-readable findings and pending promote/retain/defer/reject decisions.
- `inventory.json`: byte counts and SHA-256 hashes of the collected bundle (excluding itself).

Mechanical checks cover local index/receipt presence, receipt metadata, declared pin versus vendored
manifest, bounded UTF-8 documents, path confinement, changed sources and false no-change declarations.
They do not establish remote publication, semantic evidence quality, native implementation or adoption.
Missing projects/receipts remain visible and other sources still collect. Review the report before
preparing release content; policy gaps never become a successful all-clear.

Exit codes: **0** = mechanical policy checks passed, still needs curation; **2** = bundle produced with
policy/collection gaps; **1** = invocation/configuration failure. Standard output ends with one JSON
object containing status, output directory and counts. No consumer edits, checkout changes, fetches,
commits, tags, archives, network calls, provider calls or publication are performed.

## Later System Administrator button

The button should invoke this fixed script with an argument array (no shell interpolation), a trusted
registry and a fresh output directory. Disable duplicate clicks while running; show the final JSON
status and open `REVIEW.md`. Treat exit 2 as a reviewable partial collection, not success or a lost bundle.
Pass the previous report when available. Retain bundles in administrator-controlled durable storage.
Do not automatically execute source documents, merge guidance, bump versions or publish from the button.
No System Administrator UI wiring is included in this change.

## Release preparation remains separate

Merlin reviews each finding under [the shared policy](../contracts/local-supplements.md), records its
disposition and incorporates accepted general lessons. Then validate candidate content, provenance,
migration and inventory; publish only when requested. Consumer release installation needs its own receipt.
