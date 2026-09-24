# Commit and push installed Kits

This script publishes the files identified by a completed installer distribution receipt. It does not
install a release, create a new shared Kit release or include unrelated working-file changes.

Preview the current installation:

```sh
python3 scripts/publish_mpk_installs.py \
  --distribution /tmp/mpk-preserved/install-0.1.9-applied/distribution.json \
  --state-dir /tmp/mpk-publication-0.1.9
```

Commit and push those installations:

```sh
python3 scripts/publish_mpk_installs.py \
  --distribution /tmp/mpk-preserved/install-0.1.9-applied/distribution.json \
  --state-dir /tmp/mpk-publication-0.1.9 \
  --apply
```

Run from the publisher checkout or provide `--source`. Use a durable `--state-dir` outside all project
roots if retaining the journal; `/tmp` is temporary. Reuse that same journal and original distribution
receipt to retry interrupted pushes. `--only lee finian` restricts targets. The script uses the receipt's
project paths, not a new or changed registry, so the release and installation evidence stay connected.

## Exact scope

- Verify the release tag on origin and every installed Kit file against the tagged manifest.
- Group destinations by Git repository. Branch worktrees share one repository delivery.
- Resolve the repository's default branch from `origin` and publish the release there. A temporary
  worktree isolates delivery from active coding checkouts.
- Begin with the latest remote default branch. Existing local commits on that default branch are
  included when possible; divergence is merged in the isolated worktree.
- Commit only `project-kit/` and `moondance.lock.json`. `project-kit-local/` is always excluded because
  it belongs to the separate collection and framework-improvement process.
- Stage deletions under `project-kit/` as well as additions and updates. Use a path-limited commit;
  unrelated working changes, staged files and active worktrees are left alone.
- Push the recorded commit to the default branch on `origin`. Never force push.
- Verify the remote branch now names the exact commit and report per-project results.

Non-Git task folders (currently Grand Pubah) are reported as skipped; the script cannot push a folder
without a repository/remote. Uncommitted or staged application work does not block delivery. A changed
installed Kit, an origin that does not advertise a default branch, a genuine merge conflict, or a
non-fast-forward race still blocks that repository for review. Other repositories can proceed, so a
multi-project run is not atomic.

The journal records the repository, default branch, release, members and published commit. A retry starts
from the then-current remote default branch and verifies exact release bytes again. Temporary delivery
worktrees are removed after each repository; active owner indexes and working files are never reset.

The isolated worktree means owners may keep coding while delivery runs. Run one MPK publisher at a time;
Git locks and fast-forward checks still apply. If a hook alters the scoped files, committed-byte
verification must pass before any push.

## Reports and exit codes

`result-preview.json` or `result-apply.json` describes each outcome. Apply also maintains
`publication.json` as the retry journal, including exact commits and destinations. Exit **0** means
all selected projects completed or were already published (or passed preview); **2** means at least
one project was blocked/skipped; **1** means source/configuration failure. The final stdout JSON names
the report. No runtime restart, database update or implementation acceptance follows from a Git push.
