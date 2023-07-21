package com.moondance.nettty;

import com.moondance.nettty.model.Nett;
import com.moondance.nettty.model.Particle;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.moondance.nettty.utils.Handy.*;

import static com.moondance.nettty.NetttyApp.CURRENT_SCRIPT;
import static com.moondance.nettty.utils.Handy.out;

public class Script {
    static private Path scriptsDirectory;

    static {
        try {
            scriptsDirectory = Files.createDirectories(Paths.get("./scripts"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Nett loadNett(String fileName) throws IOException {

        out("NetttyApp loadNett:" + fileName);
        String yamlFile = new String(Files.readAllBytes(scriptsDirectory.resolve(fileName)));
        Yaml yaml = new Yaml(new Constructor(Nett.class));
        for (Object details : yaml.loadAll(yamlFile)) {
            Nett nett = (Nett) details ;
            out("Nett:" + fileName + " " + nett.toString());
            return nett;
        }
        return null;
    }

    public static Nett readNett(String nettScript){
        return null;
    }
}
