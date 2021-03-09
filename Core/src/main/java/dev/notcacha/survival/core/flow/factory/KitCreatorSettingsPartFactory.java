package dev.notcacha.survival.core.flow.factory;

import dev.notcacha.survival.core.flow.part.KitCreatorSettingsPart;
import me.fixeddev.commandflow.annotated.part.PartFactory;
import me.fixeddev.commandflow.part.CommandPart;

import java.lang.annotation.Annotation;
import java.util.List;

public class KitCreatorSettingsPartFactory implements PartFactory {

    @Override
    public CommandPart createPart(String s, List<? extends Annotation> list) {
        return new KitCreatorSettingsPart(s);
    }
}
