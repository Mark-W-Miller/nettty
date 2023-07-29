package com.moondance.nettty.graphics;

import com.moondance.nettty.utils.octtree.OctNode;
import com.moondance.nettty.utils.octtree.OctTree;
import com.moondance.nettty.utils.octtree.OctTreeWalker;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point3D;
import org.jdesktop.j3d.examples.collision.Box;
import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.geometry.Sphere;
import org.jogamp.vecmath.*;

import static com.moondance.nettty.utils.Handy.err;

public class GraphicsUtils {

    public static Group makeAxis() {
        BranchGroup group = new BranchGroup();
        LineArray lineArr = new LineArray(6, LineArray.COORDINATES);
        group.addChild(makeColorLineShape(new Point3d(0, 0, 0), new Point3d(1000, 0, 0),new Color3f(1,0,0)));
        group.addChild(makeColorLineShape(new Point3d(0, 0, 0), new Point3d(0, 1000, 0),new Color3f(0,1,0)));
        group.addChild(makeColorLineShape(new Point3d(0, 0, 0), new Point3d(0, 0, 1000),new Color3f(0,0,1)));
        group.addChild(makeColorLineShape(new Point3d(-1000, -1000, -1000), new Point3d(1000, 1000, 1000),new Color3f(1,1,0)));

        return group;
    }

    public static Shape3D makeColorLineShape(Point3d from, Point3d to, Color3f color){
        LineArray lineArr = new LineArray(2, LineArray.COORDINATES);
        lineArr.setCoordinate(0, from);
        lineArr.setCoordinate(1, to);
        Appearance appearance = new Appearance();
        ColoringAttributes ca = new ColoringAttributes();
        ca.setColor(color);
        appearance.setColoringAttributes(ca);
        Shape3D shape = new Shape3D(lineArr, appearance);
        return shape ;
    }

    public static void verifyBB(BoundingBox bb) {
        if (bb.getMinX() >= bb.getMaxX() ||
                bb.getMinY() >= bb.getMaxY() ||
                bb.getMinY() >= bb.getMaxY()) {
            err("Bad Bounding Box:" + bb);
        }
    }

    public static Point3D p3dToP3D(Point3d point3d){
        return new Point3D(point3d.x,point3d.y,point3d.z);
    }

    public static String tup3dStr(Tuple3d tuple3d){
        return String.format("[%.2f %.2f %.2f]",tuple3d.getX(),tuple3d.getY(),tuple3d.getZ());
    }

    public static String bb2str(BoundingBox bb){
        return String.format("BB X:[%.2f %.2f] Y[%.2f %.2f] Z[%.2f %.2f]",bb.getMinX(),bb.getMaxX(),bb.getMinY(),bb.getMaxY(),bb.getMinZ(),bb.getMaxZ());
    }

    public static <T> Group makeOctTreeGroup(OctTree<T> tree) {
        BranchGroup group = new BranchGroup();
        group.setCapability(BranchGroup.ALLOW_DETACH);
        Appearance boxApp = getDebugStructureAppearance(new Color3f(0, 1, 1), 0.30f);
        Appearance dotApp = getDebugStructureAppearance(new Color3f(0, 0, 1), 0f);
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
                TransformGroup octRegionBox = makeSquareAt(node.getCenter().getAddress(), node.getVoxelSize(), boxApp);
                group.addChild(octRegionBox);
                if (!node.isBranchNode()) {
                    Point3d part = node.getData().get(0).getOctAddress().getAddress();
                    group.addChild(makeSphereAt(part, 0.5, dotApp));
                    group.addChild(makeSphereAt(node.getCenter().getAddress(), 2, dotApp));
                    group.addChild(makeColorLineShape(node.getCenter().getAddress(),part, new Color3f(1, .64f, 0)));
                }
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
        polygonAttributes.setPolygonMode(PolygonAttributes.POLYGON_LINE);
        appearance.setPolygonAttributes(polygonAttributes);

        ColoringAttributes ca = new ColoringAttributes();
        ca.setColor(color);
        appearance.setColoringAttributes(ca);

        return appearance;
    }

}
