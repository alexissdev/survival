package dev.notcacha.survival.core.flow.part;

import dev.notcacha.survival.core.kit.creator.KitCreatorSettings;
import me.fixeddev.commandflow.CommandContext;
import me.fixeddev.commandflow.exception.ArgumentParseException;
import me.fixeddev.commandflow.part.ArgumentPart;
import me.fixeddev.commandflow.stack.ArgumentStack;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class KitCreatorSettingsPart implements ArgumentPart {

    private final String name;

    public KitCreatorSettingsPart(String name) {
        this.name = name;
    }

    @Override
    public List<KitCreatorSettings> parseValue(CommandContext commandContext, ArgumentStack argumentStack) throws ArgumentParseException {

        if (!argumentStack.hasNext()) {
            return null;
        }

        boolean oneValue = argumentStack.nextBoolean();

        if (!argumentStack.hasNext()) {
            return Collections.singletonList(new KitCreatorSettings(oneValue, false));
        }

        boolean secondValue = argumentStack.nextBoolean();

        return Collections.singletonList(new KitCreatorSettings(oneValue, secondValue));
    }

    @Override
    public Type getType() {
        return KitCreatorSettings.class;
    }

    @Override
    public String getName() {
        return name;
    }
}
