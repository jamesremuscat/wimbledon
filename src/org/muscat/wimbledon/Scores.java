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

      final String fullEventText = table.select("div.eventinfo").text();
      ms.setCourt(fullEventText.substring(0, fullEventText.indexOf("-") - 1));

      final Elements teamOne = table.select("div.teamOne");
      final Elements teamTwo = table.select("div.teamTwo");

      ms.setPlayer1(teamOne.select("div.name").text());
      ms.setPlayer2(teamTwo.select("div.name").text());

      final int[] scores1 = new int[5];
      final int[] scores2 = new int[5];
      for (int i = 0; i < 5; i++) {
        scores1[i] = getScoreFrom(teamOne.select("span.set" + (i + 1)).text());
        scores2[i] = getScoreFrom(teamTwo.select("span.set" + (i + 1)).text());
      }

      ms.setScores1(scores1);
      ms.setScores2(scores2);

      scores.addMatch(ms);
    }

    return scores;

  }

  private static int getScoreFrom(final String text) {
    if (text.equals(" ")) { // &nbsp;
      return -1;
    }
    return Integer.parseInt(text);
  }


}
