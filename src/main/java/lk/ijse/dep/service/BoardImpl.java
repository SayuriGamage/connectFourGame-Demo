package lk.ijse.dep.service;

public class BoardImpl implements Board{
    private  Piece [] [] pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];

    private BoardUI boardUI ;

    public Piece[][] getPieces() {
        return pieces;
    }

    public void setPieces(Piece[][] pieces) {
        this.pieces = pieces;
    }

    public void setBoardUI(BoardUI boardUI) {
        this.boardUI = boardUI;
    }


    public BoardImpl(BoardUI boardUI) {

        this.boardUI = boardUI;

        for (int i=0;i<NUM_OF_COLS;i++){
            for (int j=0;j<NUM_OF_ROWS;j++){
                pieces[i][j]=Piece.EMPTY;
            }
        }
    }


    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for(int i=0;i<pieces[col].length;i++) {
            if (pieces[col][i].equals(Piece.EMPTY)) {
                return i;
            }
        }
        return -1;

    }

    @Override
    public boolean isLegalMoves(int col) {
        return (findNextAvailableSpot(col)==-1)?false:true;

    }

    @Override
    public boolean existLegalMove() {
        for (int i=0;i<NUM_OF_COLS;i++){
            for (int j=0;j<NUM_OF_ROWS;j++){
                if(pieces[i][j].equals(Piece.EMPTY)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        for(int i=0;i<pieces[col].length;i++){
            if(pieces[col][i].equals(Piece.EMPTY)){
                pieces[col][i]=move;
                break;
            }
        }

    }
    @Override
    public void updateMove(int col, int row, Piece move) {

        pieces[col][row]=move;
    }

    @Override
    public Winner findWinner() {
        int humanCount=0,aiCount=0;

        for (int i=0;i<NUM_OF_COLS;i++){

            humanCount=0;
            aiCount=0;

            for (int j=0;j<NUM_OF_ROWS;j++){
                if(pieces[i][j].equals(Piece.BLUE)){
                    humanCount++;
                    aiCount=0;
                } else if (pieces[i][j].equals(Piece.GREEN)) {
                    aiCount++;
                    humanCount=0;
                }

                if(humanCount==4){
                    return new Winner(Piece.BLUE,i,(j-3),i,j);
                } else if (aiCount==4) {
                    return new Winner(Piece.GREEN,i,(j-3),i,j);
                }

            }

        }

        for (int i=0;i<NUM_OF_ROWS;i++){

            humanCount=0;
            aiCount=0;

            for (int j=0;j<NUM_OF_COLS;j++){

                if(pieces[j][i].equals(Piece.BLUE)){
                    humanCount++;
                    aiCount=0;
                } else if (pieces[j][i].equals(Piece.GREEN)) {
                    aiCount++;
                    humanCount=0;
                }else {
                    aiCount=0;
                    humanCount=0;
                }

                if(humanCount==4){
                    return new Winner(Piece.BLUE,(j-3),i,j,i);
                } else if (aiCount==4) {
                    return new Winner(Piece.GREEN,(j-3),i,j,i);
                }

            }
        }
        return new Winner(Piece.EMPTY);
    }


}
