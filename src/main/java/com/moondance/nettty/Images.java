package com.moondance.nettty;

import org.jdesktop.j3d.examples.Resources;
import org.jogamp.java3d.Shape3D;
import org.jogamp.java3d.Texture;
import org.jogamp.java3d.utils.image.TextureLoader;

import java.awt.*;

public class Images {

    public static Texture spinTexture ;
    public static Component observer ;
    public static Texture getSpinTexture() {
        java.net.URL spinTextureURL = Resources.getResource("main/resources/images/earth.jpg");
        spinTexture = new TextureLoader(spinTextureURL,
                TextureLoader.BY_REFERENCE | TextureLoader.Y_UP,
                observer).getTexture();
        spinTexture.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
        Images.class.getResourceAsStream("main/resources/images/earth.jpg");
        return spinTexture ;
    }

    public static void initTextures(NetttyApp netttyApp) {
        observer = netttyApp ;
    }
}
