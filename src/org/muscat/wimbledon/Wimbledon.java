package org.muscat.wimbledon;

import java.util.Date;

public class Wimbledon implements ScoresHandler {

  final static String ESC = "\033[";
  public static void main(final String[] args) {

    final Wimbledon w = new Wimbledon();

    new ScoresFetcher(w).run();
  }

  @Override
  public void handle(final Scores scores) {
    System.out.print(ESC + "2J"); // clear screen

    for (final MatchScore match : scores.getMatches()) {
      System.out.format("%-14s%25s %-5s  %-25s %-25s\n", match.getCourt(), match.getPlayer1(), match.getPointsAsString(), match.getScoresAsString(), match.getPlayer2());
    }

    System.out.println(new Date());

  }

}
