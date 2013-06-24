package org.muscat.wimbledon;

import java.io.IOException;

public class ScoresFetcher implements Runnable {

  private static final int WAIT = 3000;

  private static final String SCORES_URL = "http://www.wimbledon.com/en_GB/scores/index2.html";

  private boolean _running = true;

  private final ScoresHandler _handler;

  public ScoresFetcher(final ScoresHandler handler) {
    _handler = handler;
  }

  @Override
  public void run() {
    while (_running) {

      try {
        // get stuff
        final Scores s = Scores.parse(SCORES_URL);

        _handler.handle(s);
        Thread.sleep(WAIT);
      }
      catch (final InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      catch (final IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public void stop() {
    _running = false;
  }

}
