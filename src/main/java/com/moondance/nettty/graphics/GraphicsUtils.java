package com.moondance.nettty.graphics;

import com.moondance.nettty.utils.octree.OctMember;
import com.moondance.nettty.utils.octree.OctNode;
import com.moondance.nettty.utils.octree.Octree;
import com.moondance.nettty.utils.octree.OctreeWalker;
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
        group.addChild(makeColorLineShape(new Point3d(0, 0, 0), new Point3d(1000, 0, 0),new Color3f(1,0,0), null));
        group.addChild(makeColorLineShape(new Point3d(0, 0, 0), new Point3d(0, 1000, 0),new Color3f(0,1,0), null));
        group.addChild(makeColorLineShape(new Point3d(0, 0, 0), new Point3d(0, 0, 1000),new Color3f(0,0,1), null));

        return group;
    }
    public static BranchGroup makeAxisAt(Point3d c, double size) {
        BranchGroup group = new BranchGroup();
        group.addChild(makeColorLineShape(c, new Point3d(c.x + size, c.y, c.z),new Color3f(1,0,0), null));
        group.addChild(makeColorLineShape(c, new Point3d(c.x, c.y + size, c.z),new Color3f(0,1,0), null));
        group.addChild(makeColorLineShape(c, new Point3d(c.x, c.y, c.z + size),new Color3f(0,0,1), null));

        return group;
    }

    public static Shape3D makeColorLineShape(Point3d from, Point3d to, Color3f color, Appearance appearanceOver){
        LineArray lineArr = new LineArray(2, LineArray.COORDINATES);
        lineArr.setCoordinate(0, from);
        lineArr.setCoordinate(1, to);
        Appearance appearance = appearanceOver ;
        if(appearanceOver == null){
            appearance = new Appearance();
        }
        ColoringAttributes ca = new ColoringAttributes();
        ca.setColor(color);
        appearance.setColoringAttributes(ca);
        return new Shape3D(lineArr, appearance);
    }

    public static void verifyBB(BoundingBox bb) {
        if (bb.getMinX() >= bb.getMaxX() ||
                bb.getMinY() >= bb.getMaxY() ||
                bb.getMinZ() >= bb.getMaxZ()) {
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

    public static <T extends OctMember> Group makeOctTreeGroup(Octree<T> tree) {
        BranchGroup group = new BranchGroup();
        group.setCapability(BranchGroup.ALLOW_DETACH);
        Appearance boxApp = getDebugStructureAppearance(new Color3f(0, 1, 1), 0.75f, false);
        Appearance dotApp = getDebugStructureAppearance(new Color3f(0, 0, 1), 0.85f, false);
        Appearance crowdedApp = getDebugStructureAppearance(new Color3f(1, 0, 0), 0f, true);
        Appearance toNodeCenter = getDebugStructureAppearance(new Color3f(1, .64f, 0), 0.85f, true);
        new OctreeWalker<T>(tree.getRoot()) {

            @Override
            public void visitLeaf(OctNode<T> node, int level) {
                makeOctNodeDecoration(node);
            }

            @Override
            public boolean visitBranch(OctNode<T> node, int level) {
                makeOctNodeDecoration(node);
                return true ;
            }

            private void makeOctNodeDecoration(OctNode<T> node) {
                TransformGroup octRegionBox = makeSquareAt(node.getCenter().getAddress(), node.getVoxelSize(), boxApp);
                group.addChild(octRegionBox);
                if (!node.isBranchNode()) {
                    Appearance finalApp = node.getData().size() > 1 ? crowdedApp : dotApp ;
                    boolean crowded = finalApp == crowdedApp ;
                    Point3d part = node.getData().get(0).getOctAddress().getAddress();
//                    group.addChild(makeSphereAt(part, 0.5, finalApp, crowded ? 16 : 8));
                    group.addChild(makeSphereAt(node.getCenter().getAddress(), 0.1, finalApp, crowded ? 16 : 8));
                    group.addChild(makeColorLineShape(node.getCenter().getAddress(),part, new Color3f(1, .64f, 0), toNodeCenter));
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

    private static TransformGroup makeSphereAt(Point3d center, double size, Appearance app, int divisions) {
        Transform3D t = new Transform3D();
        t.setTranslation(new Vector3d(center));
        TransformGroup objTrans = new TransformGroup(t);
        Sphere sphere = new Sphere(
                (float) size,     // sphere radius
                Sphere.GENERATE_NORMALS | Sphere.GENERATE_TEXTURE_COORDS,  // generate normals
                divisions,         // 16 divisions radially
                app);      // it's appearance
        sphere.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
        objTrans.addChild(sphere);
        return objTrans;
    }
    @SuppressWarnings("unused")
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

    private static Appearance getDebugStructureAppearance(Color3f color, float transparency, boolean solid) {
        Appearance appearance = new Appearance();

        Material material = new Material();
        material.setDiffuseColor(color);
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
        if(solid){
            polygonAttributes.setPolygonMode(PolygonAttributes.POLYGON_FILL);
        } else {
            polygonAttributes.setPolygonMode(PolygonAttributes.POLYGON_LINE);

        }
        appearance.setPolygonAttributes(polygonAttributes);

        ColoringAttributes ca = new ColoringAttributes();
        ca.setColor(color);
        appearance.setColoringAttributes(ca);

        return appearance;
    }

}
