package assignment2;

import javax.swing.plaf.nimbus.State;

public class World {
    private Caterpillar caterpillar;
    private Position position;
    private Region region;
    private ActionQueue actionQueue;
    private TargetQueue targetQueue;
    private GameState gameState;

    public World(TargetQueue targetQueue, ActionQueue actionQueue){
        this.targetQueue = targetQueue;
        this.actionQueue = actionQueue;
        this.region = new Region(0,0,15,15);
        this.caterpillar = new Caterpillar();
        this.position = this.targetQueue.dequeue();
        this.gameState = GameState.MOVE;
    }

    public void step(){
        if (this.actionQueue.isEmpty()){
            this.gameState = GameState.NO_MORE_ACTION;
        }
        else{
            Direction nextDirection = this.actionQueue.dequeue();
            if (!this.gameState.equals(GameState.MOVE) && !this.gameState.equals(GameState.EAT)) {
                return;}

            if (nextDirection.equals(Direction.NORTH)){
                Position headCaterpillar = new Position(this.caterpillar.getHead().getX(),this.caterpillar.getHead().getY());
                headCaterpillar.moveNorth();
                if (this.region.contains(position)) {
                    if (this.region.contains(headCaterpillar)) {
                        if (!this.caterpillar.selfCollision(headCaterpillar)) {
                            if (this.region.contains(headCaterpillar)) {
                                this.caterpillar.move(headCaterpillar);
                                if (this.position.equals(headCaterpillar)) {
                                    if (this.targetQueue.isEmpty()) {
                                        this.gameState = GameState.DONE;
                                    } else {
                                        this.caterpillar.eat(position);
                                        this.gameState = GameState.EAT;
                                    }
                                }
                            }else{
                                return;
                            }
                        } else {
                            this.gameState = GameState.SELF_COLLISION; }
                    } else {
                        this.gameState = GameState.WALL_COLLISION; }
                } else {
                    throw new IndexOutOfBoundsException("The position suggested is out of the bounds of the game");
                } }

            if (nextDirection.equals(Direction.SOUTH)){
                Position headCaterpillar = new Position(this.caterpillar.getHead().getX(),this.caterpillar.getHead().getY());
                headCaterpillar.moveSouth();
                if (this.region.contains(position)) {
                    if (this.region.contains(headCaterpillar)) {
                        if (!this.caterpillar.selfCollision(headCaterpillar)) {
                            if (this.region.contains(headCaterpillar)) {
                                this.caterpillar.move(headCaterpillar);
                                if (this.position.equals(headCaterpillar)) {
                                    if (this.targetQueue.isEmpty()) {
                                        this.gameState = GameState.DONE;
                                    } else {
                                        this.caterpillar.eat(position);
                                        this.gameState = GameState.EAT;
                                    }
                                }
                            }else{
                                return;
                            }
                        } else {
                            this.gameState = GameState.SELF_COLLISION; }
                    } else {
                        this.gameState = GameState.WALL_COLLISION; }
                } else {
                    throw new IndexOutOfBoundsException("The position suggested is out of the bounds of the game");
                } }


            if (nextDirection.equals(Direction.EAST)){
                Position headCaterpillar = new Position(this.caterpillar.getHead().getX(),this.caterpillar.getHead().getY());
                headCaterpillar.moveEast();
                if (this.region.contains(position)) {
                    if (this.region.contains(headCaterpillar)) {
                        if (!this.caterpillar.selfCollision(headCaterpillar)) {
                            if (this.region.contains(headCaterpillar)) {
                                this.caterpillar.move(headCaterpillar);
                                if (this.position.equals(headCaterpillar)) {
                                    if (this.targetQueue.isEmpty()) {
                                        this.gameState = GameState.DONE;
                                    } else {
                                        this.caterpillar.eat(position);
                                        this.gameState = GameState.EAT;
                                    }
                                }
                            }else{
                                return;
                            }
                        } else {
                            this.gameState = GameState.SELF_COLLISION; }
                    } else {
                        this.gameState = GameState.WALL_COLLISION; }
                } else {
                    throw new IndexOutOfBoundsException("The position suggested is out of the bounds of the game");
                } }

            if (nextDirection.equals(Direction.WEST)){
                Position headCaterpillar = new Position(this.caterpillar.getHead().getX(),this.caterpillar.getHead().getY());
                headCaterpillar.moveWest();
                if (this.region.contains(position)) {
                    if (this.region.contains(headCaterpillar)) {
                        if (!this.caterpillar.selfCollision(headCaterpillar)) {
                            if (this.region.contains(headCaterpillar)) {
                                this.caterpillar.move(headCaterpillar);
                                if (this.position.equals(headCaterpillar)) {
                                    if (this.targetQueue.isEmpty()) {
                                        this.gameState = GameState.DONE;
                                    } else {
                                        this.caterpillar.eat(position);
                                        this.gameState = GameState.EAT;
                                    }
                                }
                            }else{
                                return;
                            }
                        } else {
                            this.gameState = GameState.SELF_COLLISION; }
                    } else {
                        this.gameState = GameState.WALL_COLLISION; }
                } else {
                    throw new IndexOutOfBoundsException("The position suggested is out of the bounds of the game");
                } }





        }
    }

    public GameState getState(){
        return this.gameState;
    }

    public Caterpillar getCaterpillar(){
        return this.caterpillar;
    }

    public Position getFood(){
        return this.position;
    }

    public boolean isRunning (){
        return (this.gameState == GameState.MOVE || this.gameState == GameState.EAT);
    }


}
