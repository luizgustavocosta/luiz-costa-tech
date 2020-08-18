package tech.costa.luiz.grouping;

/**
 * The type Champion.
 */
public class Champion {

    private String season;
    private String winnerNation;
    private String winnerTeam;
    private String score;
    private String runnersUpNation;
    private String runnersUpTeam;
    private String venue;
    private Integer attendance;

    private Champion(ChampionBuilder builder) {
        this.season = builder.season;
        this.winnerNation = builder.winnerNation;
        this.winnerTeam = builder.winnerTeam;
        this.score = builder.score;
        this.runnersUpNation = builder.runnersUpNation;
        this.runnersUpTeam = builder.runnersUpTeam;
        this.venue = builder.venue;
        this.attendance = builder.attendance;
    }

    /**
     * Gets season.
     *
     * @return the season
     */
    public String getSeason() {
        return season;
    }

    /**
     * Gets winner nation.
     *
     * @return the winner nation
     */
    public String getWinnerNation() {
        return winnerNation;
    }

    /**
     * Gets winner team.
     *
     * @return the winner team
     */
    public String getWinnerTeam() {
        return winnerTeam;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public String getScore() {
        return score;
    }

    /**
     * Gets runners up team.
     *
     * @return the runners up team
     */
    public String getRunnersUpTeam() {
        return runnersUpTeam;
    }

    /**
     * Gets runners up nation.
     *
     * @return the runners up nation
     */
    public String getRunnersUpNation() {
        return runnersUpNation;
    }

    /**
     * Gets venue.
     *
     * @return the venue
     */
    public String getVenue() {
        return venue;
    }

    /**
     * Gets attendance.
     *
     * @return the attendance
     */
    public Integer getAttendance() {
        return attendance;
    }

    /**
     * A builder champion builder.
     *
     * @return the champion builder
     */
    public static ChampionBuilder aBuilder() {
        return new Champion.ChampionBuilder();
    }

    /**
     * To champion champion.
     *
     * @param line the line
     * @return the champion
     */
    public static Champion toChampion(String line) {
        final String[] columns = line.split(";");
        return aBuilder()
                .withSeason(columns[0].trim())
                .withWinnerNation(columns[1].trim())
                .withWinnerTeam(columns[2].trim())
                .withScore(columns[3].trim())
                .withRunnersUpNation(columns[4].trim())
                .withRunnersUpTeam(columns[5].trim())
                .withVenue(columns[6].trim())
                .withAttendance(Integer.parseInt(columns[7].replace(",","")))
                .build();
    }

    @Override
    public String toString() {
        return "Champion{" +
                "season='" + season + '\'' +
                ", winnerNation='" + winnerNation + '\'' +
                ", winnerTeam='" + winnerTeam + '\'' +
                ", score='" + score + '\'' +
                ", runnersUpNation='" + runnersUpNation + '\'' +
                ", runnersUpTeam='" + runnersUpTeam + '\'' +
                ", venue='" + venue + '\'' +
                ", attendance=" + attendance +
                '}';
    }

    /**
     * The type Champion builder.
     */
    public static class ChampionBuilder {
        private String season;
        private String winnerNation;
        private String winnerTeam;
        private String score;
        private String runnersUpNation;
        private String runnersUpTeam;
        private String venue;
        private Integer attendance;

        /**
         * With season champion builder.
         *
         * @param season the season
         * @return the champion builder
         */
        public ChampionBuilder withSeason(String season) {
            this.season = season;
            return this;
        }

        /**
         * With winner nation champion builder.
         *
         * @param winnerNation the winner nation
         * @return the champion builder
         */
        public ChampionBuilder withWinnerNation(String winnerNation) {
            this.winnerNation = winnerNation;
            return this;
        }

        /**
         * With winner team champion builder.
         *
         * @param winnerTeam the winner team
         * @return the champion builder
         */
        public ChampionBuilder withWinnerTeam(String winnerTeam) {
            this.winnerTeam = winnerTeam;
            return this;
        }

        /**
         * With score champion builder.
         *
         * @param score the score
         * @return the champion builder
         */
        public ChampionBuilder withScore(String score) {
            this.score = score;
            return this;
        }

        /**
         * With runners up nation champion builder.
         *
         * @param awayNation the away nation
         * @return the champion builder
         */
        public ChampionBuilder withRunnersUpNation(String awayNation) {
            this.runnersUpNation = awayNation;
            return this;
        }

        /**
         * With runners up team champion builder.
         *
         * @param runnersUpTeam the runners up team
         * @return the champion builder
         */
        public ChampionBuilder withRunnersUpTeam(String runnersUpTeam) {
            this.runnersUpTeam = runnersUpTeam;
            return this;
        }

        /**
         * With venue champion builder.
         *
         * @param venue the venue
         * @return the champion builder
         */
        public ChampionBuilder withVenue(String venue) {
            this.venue = venue;
            return this;
        }

        /**
         * With attendance champion builder.
         *
         * @param attendance the attendance
         * @return the champion builder
         */
        public ChampionBuilder withAttendance(Integer attendance) {
            this.attendance = attendance;
            return this;
        }

        /**
         * Build champion.
         *
         * @return the champion
         */
        public Champion build() {
            return new Champion(this);
        }

    }
}
