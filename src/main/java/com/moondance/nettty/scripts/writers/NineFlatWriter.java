package com.moondance.nettty.scripts.writers;

import com.moondance.nettty.model.Nett;
import com.moondance.nettty.model.Particle;
import com.moondance.nettty.model.SpinSignature;
import lombok.SneakyThrows;
import org.jogamp.vecmath.*;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;

import java.util.Arrays;
import java.util.List;

import static com.moondance.nettty.scripts.NetttyYamlFileWriter.writeNetttyFile;
import static com.moondance.nettty.utils.Handy.out;
import static com.moondance.nettty.utils.VecUtils.parsePoint3d;

public class NineFlatWriter {

    @SneakyThrows
    static public void writeFile(String name, List<String> args) {
        String spinSig = args.get(2);
        String centerStr = "0,0,0";
        if (args.size() > 3) {
            centerStr = args.get(3);
        }
        Point3d center = parsePoint3d(centerStr);
        out("NineFlatWriter writeFile centerPt:" + center);
        out("NineFlatWriter writeFile spinSig:" + spinSig);
        StringBuilder builder = new StringBuilder("");
        NettWriter nett = particles(spinSig, center);
        String yaml = makeYaml().dump(nett);
        builder.append(yaml);
        out(builder.toString());
        writeNetttyFile(name, builder);
    }

    static protected Yaml makeYaml() {
        Representer representer = new Representer() {
            {
                representers.put(Point3d.class, new Represent() {
                    @Override
                    public Node representData(Object data) {
                        Tuple3d t = (Tuple3d) data ;
                        return represent( t.x + " " + t.y + " " + t.z);
                    }
                });
                representers.put(Point3d.class, new Represent() {
                    @Override
                    public Node representData(Object data) {
                        Tuple3d t = (Tuple3d) data ;
                        return represent( t.x + " " + t.y + " " + t.z);
                    }
                });
            }
        };

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        return new Yaml(new Constructor(), representer, options);
    }


    static NettWriter particles(String spinSig, Point3d center) {
        NettWriter nett = new NettWriter();
        SpinSignature spinSignature = SpinSignature.valueOf(spinSig);
        SpinSignature compSignature = spinSignature.getCompSpin();
        nett.getParticles().add(ParticleOut.makeSentinel(center,spinSignature));
        List<Vector3d> dirVectors = spinSignature.getPrimaryPlane().makeDirVectors();
        for(int d=0 ; d< 8; d++){
            Point3d point = new Point3d(dirVectors.get(d));
            point.add(center);
            nett.getParticles().add(ParticleOut.makeSentinel(point,compSignature));
        }
        return nett;
    }
}
