package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Rene Sommerfeld on 28.06.2017.
 */
public class MineSweeperField {

    private MineSweeperTile[][] tiles;
    private int width;
    private int height;
    private int mineCount;

    /**
     * Constructs a minesweeper field consisting of {@code mineCount} mines
     * and a size of {@code width} times {@code height} tiles.
     * @param width
     * @param height
     * @param mineCount
     */
    public MineSweeperField(int width, int height, int mineCount) {
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
        initialize();
    }

    /**
     * Initializes the whole field by calling
     * {@link MineSweeperField#generateTiles()}
     * {@link MineSweeperField#placeRandomMines(MineSweeperTile[][])}
     * {@link MineSweeperField#calculateAdjacentMineCounts(MineSweeperTile[][])}
     */
    private void initialize() {
        this.tiles = generateTiles();
        placeRandomMines(tiles);
        calculateAdjacentMineCounts(tiles);
    }

    /**
     * Initializes the tiles array
     * @return the tile array
     */
    private MineSweeperTile[][] generateTiles() {
        MineSweeperTile[][] tiles = new MineSweeperTile[height][width];
        //initialize every tile
        for(int y = 0; y < tiles.length; y++) {
            for(int x = 0; x < tiles[y].length; x++) {
                tiles[y][x] = new MineSweeperTile(x, y);
            }
        }
        return tiles;
    }

    /**
     * Places randomly specified by {@code mineCount} mines
     * across the field
     * @param tiles the tiles to randomly place the mines on
     */
    private void placeRandomMines(MineSweeperTile[][] tiles) {
        boolean minePlaced = false;
        Random r = new Random();
        for(int i = 0; i < mineCount; i++) {
            minePlaced = false;
            do {
                int randX = r.nextInt(width);
                int randY = r.nextInt(height);
                if(!tiles[randY][randX].isMine()) {
                    tiles[randY][randX].setMine(true);
                    minePlaced = true;
                }
            } while(!minePlaced);
        }
    }

    /**
     * Calculates and sets the number of adjacent mines of each
     * tile of this field
     * @param tiles the tiles to calculate the adjacent mine counts from
     */
    private void calculateAdjacentMineCounts(MineSweeperTile[][] tiles) {
        for(int y = 0; y < tiles.length; y++) {
            for(int x = 0; x < tiles[y].length; x++) {
                MineSweeperTile current = getTile(x, y);
                int adjacentMineCount = 0;
                List<MineSweeperTile> adjacentTiles = getAdjacentTiles(current);
                for(MineSweeperTile adjacentTile : adjacentTiles) {
                    if(adjacentTile.isMine()) {
                        adjacentMineCount++;
                    }
                }
                current.setAdjacentMineCount(adjacentMineCount);
            }
        }
    }

    /**
     * Returns all adjacent tiles of the passed {@code current}
     * @param current the tile to get the adjacent tiles from
     * @return a list of all adjacent tiles
     */
    private List<MineSweeperTile> getAdjacentTiles(MineSweeperTile current) {
        List<MineSweeperTile> adjacentTiles = new ArrayList<MineSweeperTile>();
        int currentX = current.getX();
        int currentY = current.getY();
        for(int dy = -1; dy <= 1; dy++) {
            for(int dx = -1; dx <= 1; dx++) {
                int adjacentX = currentX + dx;
                int adjacentY = currentY + dy;
                if(isInBounds(adjacentX, adjacentY)) {
                    MineSweeperTile adjacentTile = getTile(adjacentX, adjacentY);
                    adjacentTiles.add(adjacentTile);
                }
            }
        }
        return adjacentTiles;
    }

    /**
     * Returns whether or not the coordinates {@code x} and {@code y} are in bounds
     * of the field.
     * @param x the x coordinate
     * @param y the y coordinate
     * @return whether or not the coordinates are in bounds of the field
     */
    private boolean isInBounds(int x, int y) {
        return y >= 0 && x >= 0 && y < tiles.length && x < tiles[y].length;
    }

    /**
     * Flags the tile located at {@oode x} and {@code y}.
     * @param x the x coordinate
     * @param y the y coordinate
     * @return whether or not the current tile was flagged
     */
    public boolean flag(int x, int y) {
        MineSweeperTile current = getTile(x, y);
        boolean previousState = false;
        if(current != null) {
            previousState = current.isFlagged();
            current.flag();
        }
        return previousState;
    }

    /**
     * Reveals the tile located at {@code x} and {@code y}.
     * Calls the recursive reveal method {@link MineSweeperField#reveal(MineSweeperTile)}
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the tile to reveal
     */
    public MineSweeperTile reveal(int x, int y) {
        MineSweeperTile current = getTile(x, y);
        if(!current.isFlagged()) {
            reveal(current);
        }
        return current;
    }

    /**
     * Reveals all.
     */
    public void revealAll() {
        for(MineSweeperTile[] row : tiles) {
            for(MineSweeperTile tile : row) {
                tile.reveal();
            }
        }
    }

    /**
     * Recursively reveals all adjacent tiles of the passed {@code tile}
     * that don't have a mine in their moore neighbourhood
     * @param tile the tile to reveal
     */
    private void reveal(MineSweeperTile tile) {
        if(tile != null) {
            if(!tile.isRevealed()) {
                tile.reveal();
                if(!tile.hasAdjacentMines()) {
                    List<MineSweeperTile> adjacentTiles = getAdjacentTiles(tile);
                    for(MineSweeperTile adjacentTile : adjacentTiles) {
                        reveal(adjacentTile);
                    }
                }
            }
        }
    }

    /**
     * Returns the tile located at the coordinates {@code x} and {@code y}
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the tile located at this coordinates
     */
    public MineSweeperTile getTile(int x, int y) {
        if(isInBounds(x, y)) {
            return tiles[y][x];
        }
        return null;
    }

    /**
     * Returns the width of this field
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of this field
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the number of mines on this field
     * @return the number of mines
     */
    public int getMineCount() {
        return mineCount;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(MineSweeperTile[] row : tiles) {
            for(MineSweeperTile tile : row) {
                builder.append(tile).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}
