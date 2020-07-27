package com.jean.gol;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Iterator;

public class MainGame extends ApplicationAdapter implements InputProcessor {

    public int[][] gameArea;
    public final int maxFPS = 32;
    public int curFPS;
    public final int unit = 16;
    public ShapeRenderer renderer;
    public ArrayList<unitObject> listUnit;

    @Override
    public void create() {
        Gdx.input.setInputProcessor(this);
        listUnit = new ArrayList<unitObject>();
        renderer = new ShapeRenderer();
        curFPS = 0;
        gameArea = new int[40][30];
        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < 60; j++) {
                unitObject curObject = new unitObject(unit);
                curObject.x = i;
                curObject.y = j;
                listUnit.add(curObject);
            }
        }
    }

    public void nextStage() {
        Iterator<unitObject> it = listUnit.iterator();
        while (it.hasNext()) {
            unitObject curObject = it.next();
            curObject.setNeighbours(listUnit);
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (curFPS == maxFPS) {
            nextStage();
            curFPS = 0;
        } else {
            curFPS++;
        }
        renderer.setAutoShapeType(true);
        renderer.begin();
        renderer.setColor(Color.GRAY);
        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < 60; j++) {
                renderer.rect(i * unit, j * unit, unit - 2, unit - 2);
            }
        }
        renderer.end();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.WHITE);
        Iterator<unitObject> it = listUnit.iterator();
        while (it.hasNext()) {
            unitObject curObject = it.next();
            if (curObject.isLive) {
                renderer.rect(curObject.x * unit, curObject.y * unit, unit - 2, unit - 2);
            }
        }
        renderer.end();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.P) {
            System.out.println("Key pressed!");
            Iterator<unitObject> it = listUnit.iterator();
            while (it.hasNext()) {
                unitObject curObject = it.next();
                if (curObject.x == 10 && curObject.y == 15) {
                    curObject.isLive = true;
                } else if (curObject.x == 11 && curObject.y == 15) {
                    curObject.isLive = true;
                } else if (curObject.x == 12 && curObject.y == 15) {
                    curObject.isLive = true;
                } else if (curObject.x == 11 && curObject.y == 16) {
                    curObject.isLive = true;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
