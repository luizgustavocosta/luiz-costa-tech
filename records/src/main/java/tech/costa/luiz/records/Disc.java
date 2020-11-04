package tech.costa.luiz.records;


/**
 * The type Disc.
 * Data from https://www.discogs.com/Titãs-Titãs/release/9436152
 */
record Disc(String stamp, String format, String country, int releaseYear, String genre, String style) {
    public Disc {
        if (releaseYear < 1500) {
            throw new IllegalArgumentException("Invalid release year "+releaseYear);
        }
    }
}
