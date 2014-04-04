package com.madeso.slide2048;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Slide2048 implements ApplicationListener {
	private OrthographicCamera camera;
	private ShapeRenderer batch;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(1, h/w);
		batch = new ShapeRenderer();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
	
	Color background = new Color(0xFAF8EFFF);
	Color gameBackground = new Color(0xBBADA0FF);
	
	Constants constants = new Constants();
	
	@Override
	public void render() {		
		Gdx.gl.glClearColor(background.r, background.g, background.b, background.a);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin(ShapeType.Filled);
		
		constants.update(camera);
		
		batch.setColor(gameBackground);
		batch.rect(constants.boardx, constants.boardy, constants.size, constants.size);
		
		int value = 0;
		
		DoubleColor dc = DoubleColor.FromValue(value);
		batch.setColor( dc.background );
		
		for(int x=0; x<constants.totalTiles; ++x) {
			for(int y=0; y<constants.totalTiles; ++y) {
				batch.rect(constants.boardx + constants.spacing + (constants.tileSize + constants.spacing)*x,
						constants.boardy + constants.spacing + (constants.tileSize + constants.spacing)*y,
						constants.tileSize, constants.tileSize);
			}
		}
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
