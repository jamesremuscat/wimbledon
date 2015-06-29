package org.muscat.wimbledon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scores {

  private final List<MatchScore> _matches = new ArrayList<MatchScore>();

  public List<MatchScore> getMatches() {
    return _matches;
  }

  public void addMatch(final MatchScore match) {
    _matches.add(match);
  }

  public static Scores parse(final String url) throws IOException {

    final Scores scores = new Scores();

    final Document document = Jsoup.connect(url).get();

    final Elements tables = document.select("div.scoringtable");

    for (final Element table : tables) {
      final MatchScore ms = new MatchScore();

      final String courtName = table.select("div.courtName").text();
      ms.setCourt(courtName);

      final Elements teamOne = table.select("div.teamOne");
      final Elements teamTwo = table.select("div.teamTwo");

      ms.setPlayer1(getPlayerNamesFrom(teamOne.select("div.name")));
      ms.setPlayer2(getPlayerNamesFrom(teamTwo.select("div.name")));

      final int[] scores1 = new int[5];
      final int[] scores2 = new int[5];
      for (int i = 0; i < 5; i++) {
        scores1[i] = getScoreFrom(teamOne.select("span.set" + (i + 1)).first().ownText());
        scores2[i] = getScoreFrom(teamTwo.select("span.set" + (i + 1)).first().ownText());
      }

      ms.setScores1(scores1);
      ms.setScores2(scores2);

      ms.setPoints1(teamOne.select("span.pts").text());
      ms.setPoints2(teamTwo.select("span.pts").text());

      if (teamOne.select("div.currentServer").size() == 1) {
        ms.setServer(1);
      }
      else if (teamTwo.select("div.currentServer").size() == 1) {
        ms.setServer(2);
      }

      if (teamOne.select("div.winner").size() == 1) {
        ms.setWinner(1);
      }
      else if (teamTwo.select("div.winner").size() == 1) {
        ms.setWinner(2);
      }

      scores.addMatch(ms);
    }

    return scores;

  }

  private static String getPlayerNamesFrom(final Elements namesContainer) {
    final Elements as = namesContainer.first().select("a");
    if (namesContainer.first().classNames().contains("singles")) {
      return as.text();
    }
    if (namesContainer.first().classNames().contains("doubles")) {
      final String firstSurname = as.get(0).text();
      final String secondSurname = as.get(1).text();
      return firstSurname + " / " + secondSurname;
    }
    return namesContainer.text();
  }

  private static int getScoreFrom(final String text) {
    if (text.equals(" ") || text.isEmpty()) { // &nbsp;
      return -1;
    }
    return Integer.parseInt(text);
  }


}
