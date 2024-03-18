package lk.ijse.dep.service;

public interface Board {
    public int NUM_OF_ROWS = 5;
    public int NUM_OF_COLS = 6;

    public BoardUI getBoardUI();

    public int findNextAvailableSpot (int col);

    public boolean isLegalMoves(int col);

    public boolean existLegalMove();

    public void updateMove (int col, Piece move);

    void updateMove(int col, int row, Piece move);

    public Winner findWinner ();


}
