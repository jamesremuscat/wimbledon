package org.muscat.wimbledon;

public class MatchScore {

  private String _court;
  private String _player1;
  private String _player2;
  private int _seed1;
  private int _seed2;
  private int _server;
  private int _winner;

  private int[] _scores1 = new int[5];
  private int[] _scores2 = new int[5];

  private String _points1 = "0", _points2 = "0";

  public String getPoints1() {
    return _points1;
  }

  public void setPoints1(final String points1) {
    _points1 = points1;
  }

  public String getPoints2() {
    return _points2;
  }

  public void setPoints2(final String points2) {
    _points2 = points2;
  }

  public MatchScore() {
    for (int i = 1; i < 5; i++) {
      _scores1[i] = -1;
      _scores2[i] = -1;
    }
  }

  public String getCourt() {
    return _court;
  }
  public void setCourt(final String court) {
    _court = court;
  }
  public String getPlayer1() {
    return _player1;
  }
  public void setPlayer1(final String player1) {
    _player1 = player1;
  }
  public String getPlayer2() {
    return _player2;
  }
  public void setPlayer2(final String player2) {
    _player2 = player2;
  }
  public int getSeed1() {
    return _seed1;
  }
  public void setSeed1(final int seed1) {
    _seed1 = seed1;
  }
  public int getSeed2() {
    return _seed2;
  }
  public void setSeed2(final int seed2) {
    _seed2 = seed2;
  }
  public int getServer() {
    return _server;
  }
  public void setServer(final int server) {
    _server = server;
  }

  public int getWinner() {
    return _winner;
  }

  public void setWinner(final int winner) {
    _winner = winner;
  }

  public int[] getScores1() {
    return _scores1;
  }
  public void setScores1(final int[] scores1) {
    _scores1 = scores1;
  }
  public int[] getScores2() {
    return _scores2;
  }
  public void setScores2(final int[] scores2) {
    _scores2 = scores2;
  }

  public String getScoresAsString() {

    final StringBuilder b = new StringBuilder();

    for (int set = 1; set < 6; set++) {
      if (_scores1[set - 1] > -1) {
        b.append(_scores1[set - 1]);
        b.append("-");
        b.append(_scores2[set - 1]);
        b.append("  ");
      }
    }

    return b.toString().trim();
  }

  public String getPointsAsString() {
    return String.format("%2s-%-2s", _points1, _points2);
  }

}
