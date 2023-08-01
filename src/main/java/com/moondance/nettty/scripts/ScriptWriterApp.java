package com.moondance.nettty.scripts;

import com.moondance.nettty.scripts.writers.NineFlatWriter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.moondance.nettty.scripts.NetttyYamlFileWriter.writeNetttyFile;
import static com.moondance.nettty.utils.Handy.out;

public class ScriptWriterApp {
    public static void main(String[] args) {
        out("ScriptWriter args:" + Arrays.toString(args));
        if(args.length < 3) {
            out(" args: <Name of Yaml File> <TYPE=NINE_FLAT> <args=spinSig>");
        }
        List<String> argList = Arrays.asList(args);
        out(" argList:" + argList);
        String name = argList.stream().collect(Collectors.joining("_"));
        out(" name:" + name);
        NineFlatWriter.writeFile(name, argList);
    }


}
