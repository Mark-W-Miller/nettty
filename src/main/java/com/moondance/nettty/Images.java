package com.moondance.nettty;

import org.jdesktop.j3d.examples.Resources;
import org.jogamp.java3d.Texture;
import org.jogamp.java3d.utils.image.TextureLoader;

import java.awt.*;

public class Images {

    public static void initTextures(Component observer){
        java.net.URL spinTextureURL = Resources.getResource("main/resources/images/earth.jpg");
        spinTexture = new TextureLoader(spinTextureURL,
                TextureLoader.BY_REFERENCE | TextureLoader.Y_UP,
                observer).getTexture();
        Images.class.getResourceAsStream("main/resources/images/earth.jpg");
    }
    public static Texture spinTexture ;
    public static Image spinImage ;
}
