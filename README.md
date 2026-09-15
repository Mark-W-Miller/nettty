# Nettty — The Brain of God

An animated film study of Mark William Miller’s Netty Particle Model.

Open [the film](visualization/index.html) directly in a browser. It works offline with no build or dependency installation. For a local preview, run this from the repository root:

```sh
python3 -m http.server 8765 --bind 127.0.0.1
```

Then visit http://127.0.0.1:8765/visualization/.

## Project contents

- [Readable film script](design/NETTY-FILM-SCRIPT.md): the sequence in human language.
- [Production notes and voice-over draft](design/FILM-NOTES.md): Mark’s direction and narration ideas.
- [Original design](design/Project%20Netty%20Anon.pdf): the source brief.
- [Visualization](visualization/README.md): browser animation, controls, assets and checks.

The film uses JavaScript and the browser’s Canvas drawing engine. Maven, Java and IntelliJ project files are not required. The retired Java / Java3D prototype and its sample assets remain available in earlier Git history.
