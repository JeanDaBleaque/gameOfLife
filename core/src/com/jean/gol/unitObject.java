package com.jean.gol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class unitObject {
    public int x, y;
    public int neighbours = 0;
    private int unit;
    public boolean isLive;

    public unitObject(int unit) {
        this.unit = unit;
        this.isLive = false;
    }

    public void setNeighbours(ArrayList<unitObject> list) {
        neighbours = 0;
        Iterator<unitObject> it = list.iterator();
        while (it.hasNext()) {
            unitObject curObject = it.next();
            if (curObject.x >= x - 1 && curObject.x <= x + 1 && curObject.y >= y - 1 && curObject.y <= y + 1 && curObject.isLive) {
                if (curObject.x != x || curObject.y != y) {
                    //System.out.println("curX: " + curObject.x + " normX: " + x);
                    //System.out.println("curY: " + curObject.y + " normY: " + y);
                    neighbours++;
                }
            }
        }
        if (neighbours != 0) {
            System.out.println("Neighbours: " + neighbours);
            System.out.println("--------------------------");
        }
        nextStage(list);
    }

    public void nextStage(ArrayList<unitObject> list) {
        if (neighbours < 2 || neighbours > 3) {
            isLive = false;
        } else if (neighbours == 2 || neighbours == 3) {
            //int[][] moveArea = new int[9][2];
            ArrayList<unitObject> moveList = new ArrayList<unitObject>();
            int i = 0;
            Iterator<unitObject> it = list.iterator();
            while (it.hasNext()) {
                unitObject curObject = it.next();
                if (curObject.x >= x - 1 && curObject.x <= x + 1 && curObject.y >= y - 1 && curObject.y <= y + 1 && !curObject.isLive) {
                    if (curObject.x != x || curObject.y != y) {
                        //moveArea[i][0] = curObject.x;
                        //moveArea[i][1] = curObject.y;
                        moveList.add(curObject);
                        i++;
                    }
                }
            }
            Random rnd = new Random();
            int rndArea = rnd.nextInt(i);
            unitObject moveObject = moveList.get(rndArea);
            moveObject.isLive = true;
            isLive = false;
        }
    }
}
