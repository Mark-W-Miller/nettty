package com.moondance.nettty.graphics;

import org.jogamp.java3d.*;
import org.jogamp.vecmath.Color3f;
import org.jogamp.vecmath.Color4f;

public class Appearences {
    public static Appearance getDefaultSpinAppearance() {
        Appearance app;
        app = new Appearance();
        Material material = new Material();
        material.setDiffuseColor(new Color3f(0.8f, 0.3f, 0.1f));
        material.setSpecularColor(new Color3f(0.0f, 0.0f, 0.0f));
        material.setShininess(128f);
        app.setMaterial(material);
        TransparencyAttributes opacity = new TransparencyAttributes();
        opacity.setTransparencyMode(TransparencyAttributes.NICEST);
        opacity.setTransparency(0.3f);
        app.setTransparencyAttributes(opacity);
        return app;
    }
    public static Appearance getSentinelSpinAppearance() {
        Appearance app;
        app = new Appearance();
        Material material = new Material();
        material.setDiffuseColor(new Color3f(1,1,1));
        material.setSpecularColor(new Color3f(0.0f, 0.0f, 0.0f));
        material.setShininess(128f);
        app.setMaterial(material);
        TransparencyAttributes opacity = new TransparencyAttributes();
        opacity.setTransparencyMode(TransparencyAttributes.NICEST);
        opacity.setTransparency(0.3f);
        app.setTransparencyAttributes(opacity);
        return app;
    }

    public static Appearance getAppearanceYPointer() {
        Appearance app;
        app = new Appearance();
        Material material = new Material();
        material.setDiffuseColor(new Color3f(1f, 0.1f, 0.9f));
        material.setSpecularColor(new Color3f(0.0f, 1.0f, 0.0f));
        material.setShininess(1.0f);
        app.setMaterial(material);
        TransparencyAttributes opacity = new TransparencyAttributes();
        opacity.setTransparencyMode(TransparencyAttributes.NICEST);
        opacity.setTransparency(0f);
        app.setTransparencyAttributes(opacity);
        ColoringAttributes ca = new ColoringAttributes();
        ca.setColor(0.6f, 0.3f, 0.0f);
        app.setCapability(app.ALLOW_COLORING_ATTRIBUTES_WRITE);
        return app;
    }
    static Color3f eColor = new Color3f(0.0f, 0.0f, 0.0f);
    static Color3f sColor = new Color3f(1.0f, 1.0f, 1.0f);
    static Color3f objColor = new Color3f(0.6f, 0.6f, 0.6f);

    public static Appearance makeSpinningTexture(Texture texture){
//        TextureLoader loader = new TextureLoader("K:\\3d\\Arizona.jpg",
//                "LUMINANCE", new Container());
//        Texture texture = Images.getSpinTextureEarth();
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));

        // Set up the texture attributes
        //could be REPLACE, BLEND or DECAL instead of MODULATE
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        Appearance app = new Appearance();
        app.setTexture(texture);
        app.setTextureAttributes(texAttr);

        //set up the material
        app.setMaterial(new Material(objColor, eColor, objColor, sColor, 1.0f));
        TransparencyAttributes opacity = new TransparencyAttributes();
        opacity.setTransparencyMode(TransparencyAttributes.NICEST);
        opacity.setTransparency(0.4f);
        app.setTransparencyAttributes(opacity);
        return app ;
    }

}
