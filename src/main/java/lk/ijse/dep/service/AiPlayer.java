package lk.ijse.dep.service;

public class AiPlayer extends Player {

    public AiPlayer(Board board) {
        super(board);
    }

    public void movePiece(int col) {

        do{
            col= (int) Math.floor(Math.random()*Board.NUM_OF_COLS);

        } while(!this.board.isLegalMove(col));

     this.board.updateMove(col,Piece.GREEN);
     this.board.getBoardUI().update(col,false);

        Winner winner = this.board.findWinner();
        if (winner.getWinningPiece()!=Piece.EMPTY) {
            this.board.getBoardUI().notifyWinner(winner);

        }else if (!this.board.existLegalMove()) {
            this.board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
        }
    }
}


