# Neuromic - Terminal Antivirus Scanner

Neurowmic is a terminal-based antivirus scanner written in Java. It scans files and folders for known malware by comparing the SHA-256 hash of each file with a database of known malware hashes.

This project was built to learn Java and Data Structures and Algorithms (DSA) while building a real tool.

![Neurowmic Flowchart](FlowChart.png)

## Features

- **--help** : Show usage and all commands
- **--scan** : Scan a file or folder once for malware
- **--watch** : Watch a file or folder every 1 second and re-scan on change
- **--add** : Add one SHA-256 hash to the malware database

## How It Works

1. **Make file hash:** Each file is read in small pieces of 1024 bytes using a buffer. Those pieces are given to `MessageDigest` with SHA-256. The result is converted to a 64-character hex string. This is the file hash.

2. **Load database:** On start, `DatabaseHash_full.txt` is loaded with `Files.readAllLines`. All lines are stored separately in a `List`, then converted to a `HashSet` for fast lookup.

3. **Hash lookup:** For each scanned file, the program calls `set.contains(fileHash)`. `HashSet` makes an index from the hash and checks only that place. No loop is used for checking. If it returns `true`, the file is `MALWARE`. If `false`, it is `CLEAN`.

4. **Scan:** `scan` walks through a folder, checks every file, and goes into sub-folders with recursion.

5. **Watch:** `watch` uses a `while (true)` loop with `Thread.sleep(1000)`. Every 1 second it calls `scan` again. It does not burn CPU while sleeping.

6. **Add:** `add` opens the database file in append mode and writes one new hash + new line, without erasing old hashes.

## Requirements

- Java JDK 17 or later
- Terminal / PowerShell / Command Prompt

## Project Structure

    Neuromic/
    ├── Main.java                # Command dispatcher: --help, --scan, --watch, --add
    ├── Engine.java              # getHash, scan, watch, addHash
    ├── loadHash.java            # load database file, return List of hashes
    ├── DatabaseHash_full.txt    # Database of known malware SHA-256 hashes, one per line
    └── README.md

## How to Run

Compile:

    javac Main.java

Show help:

    java Main --help

Scan a folder or file:

    java Main --scan <folder>
    java Main --scan <file>

Watch a folder or file:

    java Main --watch <folder>
    java Main --watch <file>

Add one hash to the database:

    java Main --add <SHA-256 hash>

Example:

    java Main --scan <folder>
    java Main --add e92ecb7a4b62b4ad159b8ea86d70c2a58e93e12adf330278bad8b9ecd9135407

## Example Output

    Working dir... ---> <folder>
    flow.jpg
    MALWARE
    Main.java
    CLEAN

## Data Structures Used

- `Array` : Fixed size storage
- `List` : Growing storage, from `Files.readAllLines`
- `HashSet` : Fast hash lookup with `contains`, O(1) average
- `StringBuilder` : Build hex string from hash bytes
- `FileInputStream` + buffer : Read file in 1024-byte pieces

## Concepts Learned

- Class, object, constructor, method
- Method overloading and overriding
- Encapsulation with `private`
- File handling, exception handling with `try-catch`
- Recursion for nested folders
- Hashing, SHA-256, hash lookup

## Future Work

- Real-time watch with `WatchService` instead of polling every 1 second
- Separate handling for file deleted and moved events
- Import hashes from another file
- Quarantine for malware files
