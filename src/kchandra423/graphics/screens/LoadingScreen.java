package kchandra423.graphics.screens;

import g4p_controls.GAbstractControl;
import kchandra423.graphics.textures.Texture;

public class LoadingScreen implements Screen{
    Texture loading;
    @Override
    public void draw(DrawingSurface d) {
        d.background(100);
        loading.draw(d, d.width/2-loading.getWidth()/2, d.height/2- loading.getHeight()/2);
    }

    @Override
    public void setup(DrawingSurface d) {
        loading = Texture.TextureBuilder.getTexture("res/Images/misc/Loading.gif");
        float multiplier =((d.width+d.height)/4f)/((loading.getWidth()+loading.getHeight())/2f);
        loading.resize((int) (loading.getWidth()*multiplier), (int)(loading.getHeight()*multiplier));
    }

    @Override
    public GAbstractControl[] getUI() {
        return new GAbstractControl[0];
    }
}
