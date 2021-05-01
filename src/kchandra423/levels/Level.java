package kchandra423.levels;

import kchandra423.actors.Room;

public class Level {
    private Room[] rooms;
    int floorNum;
    int levelNum;
    public Level(int floornum, int levelNum){
        rooms=getRooms(floornum,levelNum);
        this.floorNum=floornum;
        this.levelNum=levelNum;
    }
    private static Room[] getRooms(int floornum, int levelnum){
        return null;
    }
}
