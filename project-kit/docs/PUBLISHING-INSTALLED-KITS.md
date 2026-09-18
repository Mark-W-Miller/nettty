# Commit and push installed Kits — MPK 0.1.10 tool

This script publishes the files identified by a completed installer distribution receipt. It does not
install a release, create a new shared Kit release or include unrelated project changes. This tool is
included in 0.1.10; the immutable 0.1.9 tag/archive remain unchanged.

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
- Commit only `project-kit/`, `moondance.lock.json` and `project-kit-local/README.md` when the installer
  created that exact unchanged index. Existing local supplements/findings are not included.
- Stage deletions under `project-kit/` as well as additions and updates. Use a path-limited commit;
  unrelated working changes are left alone. Preserve normal Git hooks and repository Git configuration.
- Push the recorded commit to the current branch's existing `origin` upstream branch. No default-branch
  guessing, force push, branch creation/switching, merge/rebase or other-remote fallback.
- Verify the remote branch now names the exact commit and report per-project results.

Non-Git task folders (Sam and Grand Pubah) are reported as skipped; the script cannot push a folder
without a repository/remote. Staged changes, detached HEAD, missing upstream, an in-progress Git
operation, changed installed bytes or HEAD differing from origin block that project. Reconcile these
through the project owner. Unrelated unpublished commits are never pushed just to deliver the Kit.
Other eligible projects can still proceed, so a multi-project run is not atomic.

After a network push failure, the journal retains this script's commit and parent identity. A retry
can push that exact verified commit without creating another one. If origin or the checkout changed,
the script stops for reconciliation. A failed commit may leave the scoped staging visible for review;
it never resets the index automatically. If the process is killed between committing and recording the
commit, reconcile it manually rather than guessing which unpublished work is safe to push.

Run one publisher at a time while owners are not performing Git operations in the same checkouts.
Git locks and fast-forward checks still apply; this is not a cross-process transaction coordinator.
If a hook alters the scoped files, committed-byte verification must pass before any push.

## Reports and exit codes

`result-preview.json` or `result-apply.json` describes each outcome. Apply also maintains
`publication.json` as the retry journal, including exact commits and destinations. Exit **0** means
all selected projects completed or were already published (or passed preview); **2** means at least
one project was blocked/skipped; **1** means source/configuration failure. The final stdout JSON names
the report. No runtime restart, database update or implementation acceptance follows from a Git push.
