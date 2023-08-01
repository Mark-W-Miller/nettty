package com.moondance.nettty.scripts.writers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class NettWriter {
    List<ParticleOut> particles = new ArrayList<>();
}
