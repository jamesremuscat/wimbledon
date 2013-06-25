package org.muscat.wimbledon;

import java.util.Date;

public class Wimbledon implements ScoresHandler {

  final static String ESC = "\033[";
  final static String CONSOLE_BOLD = (char) 27 + "[1m";
  final static String CONSOLE_NORMAL = (char) 27 + "[0m";

  public static void main(final String[] args) {

    final Wimbledon w = new Wimbledon();

    new ScoresFetcher(w).run();
  }

  @Override
  public void handle(final Scores scores) {
    System.out.print(ESC + "2J"); // clear screen

    for (final MatchScore match : scores.getMatches()) {
      System.out.format("%-14s%25s %s%s%s %s%-5s%s  %-25s  %s%s%s %-25s\n",
          match.getCourt(),
          match.getPlayer1(),
          CONSOLE_BOLD, (match.getServer() == 1 ? "*" : " ") , CONSOLE_NORMAL,
          CONSOLE_BOLD, match.getPointsAsString(), CONSOLE_NORMAL,
          match.getScoresAsString(),
          CONSOLE_BOLD, (match.getServer() == 2 ? "*" : " ") , CONSOLE_NORMAL,
          match.getPlayer2()
          );
    }

    System.out.println(new Date());

  }

}
