package com.moondance.nettty.graphics;

import com.moondance.nettty.utils.octtree.AddressedData;
import com.moondance.nettty.utils.octtree.OctNode;
import com.moondance.nettty.utils.octtree.OctTree;
import com.moondance.nettty.utils.octtree.OctTreeWalker;
import org.jdesktop.j3d.examples.collision.Box;
import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.geometry.Sphere;
import org.jogamp.vecmath.Color3f;
import org.jogamp.vecmath.Point3d;
import org.jogamp.vecmath.Point3f;
import org.jogamp.vecmath.Vector3d;

public class GraphicsUtils {

    public static Group makeAxis() {
        BranchGroup group = new BranchGroup();
        Point3d coords[] = new Point3d[4];
        Appearance app = new Appearance();
        coords[0] = new Point3d(0, 0, 0);
        coords[1] = new Point3d(1, 0, 0);
        coords[2] = new Point3d(0, 1, 0);
        coords[3] = new Point3d(0, 0, 1);
        LineArray lineArr = new LineArray(6, LineArray.COORDINATES);
        lineArr.setCoordinate(0, new Point3f(0, 0, 0));
        lineArr.setCoordinate(1, new Point3f(0, 300, 0));

        lineArr.setCoordinate(2, new Point3f(0, 0, 0));
        lineArr.setCoordinate(3, new Point3f(100, 0, 0));

        lineArr.setCoordinate(4, new Point3f(0, 0, 0));
        lineArr.setCoordinate(5, new Point3f(0, 0, 100));

        Shape3D shape = new Shape3D(lineArr, app);

        group.addChild(shape);
        return group;
    }

    public static <T> Group makeOctTreeGroup(OctTree<T> tree) {
        BranchGroup group = new BranchGroup();
        group.setCapability(BranchGroup.ALLOW_DETACH);
        Appearance boxApp = getDebugStructureAppearance(new Color3f(0,1,1), 0.97f) ;
        Appearance dotApp = getDebugStructureAppearance(new Color3f(0,0,1), 0f) ;
        new OctTreeWalker<T>(tree.getRoot()) {

            @Override
            public void visitLeaf(OctNode<T> node, int level) {
                makeOctNodeDecoration(node);
            }

            @Override
            public void visitBranch(OctNode<T> node, int level) {
                makeOctNodeDecoration(node);
            }

            private void makeOctNodeDecoration(OctNode<T> node) {
                TransformGroup octRegionBox = makeSquareAt(node.getCenter().getAddress(),node.getVoxelSize(), boxApp);
                group.addChild(octRegionBox);
//                if(node.isBranchNode()){
//                    group.addChild(makeSphereAt(node.getCenter().getAddress(),0.5, dotApp));
//                }
            }
        };
        return group;
    }


    private static TransformGroup makeSquareAt(Point3d center, double size, Appearance app) {
        Transform3D t = new Transform3D();
        t.setTranslation(new Vector3d(center));
        TransformGroup objTrans = new TransformGroup(t);
        Shape3D shape = new Box(size, size, size);
        shape.setAppearance(app);
        objTrans.addChild(shape);
        return objTrans;
    }

    private static TransformGroup makeSphereAt(Point3d center, double size, Appearance app) {
        Transform3D t = new Transform3D();
        t.setTranslation(new Vector3d(center));
        TransformGroup objTrans = new TransformGroup(t);
        Sphere sphere = new Sphere(
                (float) size,     // sphere radius
                Sphere.GENERATE_NORMALS | Sphere.GENERATE_TEXTURE_COORDS,  // generate normals
                8,         // 16 divisions radially
                app);      // it's appearance
        sphere.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
        objTrans.addChild(sphere);
        return objTrans;
    }
    private static Appearance getDebugStructureAppearance() {
        Appearance appearance = new Appearance();

        Material material = new Material();
        material.setDiffuseColor(new Color3f(1f, 0f, 0f));
        material.setSpecularColor(new Color3f(0.0f, 0.0f, 0.0f));
        material.setShininess(0.0f);
        appearance.setMaterial(material);

        TransparencyAttributes opacity = new TransparencyAttributes();
        opacity.setTransparencyMode(TransparencyAttributes.NICEST);
        opacity.setTransparency(0.8f);
        appearance.setTransparencyAttributes(opacity);

        PolygonAttributes polygonAttributes = new PolygonAttributes();
        polygonAttributes.setCapability(PolygonAttributes.POLYGON_LINE);
        polygonAttributes.setCullFace(PolygonAttributes.CULL_NONE);
        appearance.setPolygonAttributes(polygonAttributes);

        ColoringAttributes ca = new ColoringAttributes();
        ca.setColor(0.0f, 1f, 0.0f);
        appearance.setColoringAttributes(ca);

        return appearance;
    }

    private static Appearance getDebugStructureAppearance(Color3f color, float transparency) {
        Appearance appearance = new Appearance();

        Material material = new Material();
        material.setDiffuseColor(new Color3f(0f, 1f, 1f));
        material.setSpecularColor(new Color3f(0.0f, 0.0f, 0.0f));
        material.setShininess(0.0f);
        appearance.setMaterial(material);

        TransparencyAttributes opacity = new TransparencyAttributes();
        opacity.setTransparencyMode(TransparencyAttributes.NICEST);
        opacity.setTransparency(transparency);
        appearance.setTransparencyAttributes(opacity);

        PolygonAttributes polygonAttributes = new PolygonAttributes();
        polygonAttributes.setCapability(PolygonAttributes.POLYGON_LINE);
        polygonAttributes.setCullFace(PolygonAttributes.CULL_NONE);
        appearance.setPolygonAttributes(polygonAttributes);

        ColoringAttributes ca = new ColoringAttributes();
        ca.setColor(color);
        appearance.setColoringAttributes(ca);

        return appearance;
    }

}
