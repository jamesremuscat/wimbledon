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

      ms.setPlayer1(table.select("div.teamOne").select("div.name").text());
      ms.setPlayer2(table.select("div.teamTwo").select("div.name").text());

      scores.addMatch(ms);
    }

    return scores;

  }


}
