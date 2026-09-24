# Install a published Kit into projects

The installer places an exact published release in each registered project's `project-kit/`, updates
`moondance.lock.json` and creates a local guidance index only if missing. It does not install into the
user's home directory, modify root AGENTS/README, overwrite local supplements, or commit/push consumers.
All previous files inside `project-kit/` are replaced: obsolete Kit files do not linger. The previous
folder and lock are preserved in the chosen external state directory before replacement.

Preview:

```sh
python3 scripts/install_mpk.py --tag v0.1.9
```

Apply to registered Git checkouts:

```sh
python3 scripts/install_mpk.py --tag v0.1.9 --apply --state-dir /absolute/new-receipt-directory
```

Use `--include-workspaces` to include registered non-Git task folders (currently Grand Pubah).
Use `--only lee finian` to select registry IDs. The default registry is `collection/pinned-projects.json`;
`--registry` overrides it. Each worktree receives its physical copy while retaining shared repository
identity. No branch creation, switching, deletion or consumer Git operation is performed.

The script reads source bytes from the exact tag, checks the tag on origin, validates manifest version,
content digest, complete tree membership and every size/hash, then stages and reads back each installed
copy. Local dirty source bytes cannot substitute for a tagged release. Network failure blocks source
verification; there is no CLI bypass. Run from the publisher checkout with the requested tag available;
a vendored Kit is not itself the publisher repository.

The publisher repository's own `project-kit-local/` collector guidance is outside the release inventory.
It is never nested inside an installed Kit or substituted for a consumer's local supplements.

Receipts and backups go in a new `--state-dir` outside source and target projects. Defaults use `/tmp`,
which is temporary; select durable storage for retained recovery evidence. Existing receipt directories
are refused. Each project's receipt records replacement state; `distribution.json` records progress and
partial failures. On an ordinary installation exception the prior Kit/lock are restored. A process kill
or power loss is not transactional across both paths: inspect the `replacing` journal, retained backup
and named displaced directory before recovery. There is no all-project atomicity claim.

Exit **0** means all selected destinations were installed/already matched (or previewed); **2** means
some destinations failed or non-Git folders were skipped; **1** means source/configuration failure.
The final stdout JSON names the report directory and individual results. The administrator button can
invoke a fixed argument array, disable duplicate clicks, and show this report. No UI wiring is supplied.

Installation is verified documentation delivery. Owner review, consumer commits, runtime implementation,
database materialization and browser acceptance remain separate. A local index initialized by this tool
does not invent findings or a completed owner handoff. Root project guidance remains authoritative.

`project-kit-local/` is the designated (blessed) directory for local findings and handoffs. Existing
contents are preserved in place, not removed when replacing the shared Kit. The collector reads this
directory; the installer creates it and an index where missing. Only shared `project-kit/` files are
replaced, including removal of files no longer present in the selected release.

From 0.1.9, the published Kit includes exactly one root `VERSION-<version>.md` marker. It is part of
the immutable inventory. Whole-folder replacement removes old markers while keeping local supplements.
