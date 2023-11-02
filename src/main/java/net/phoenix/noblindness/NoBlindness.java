package net.phoenix.noblindness;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class NoBlindness implements ClientModInitializer {
    public static boolean enabled = true;

    public static LiteralArgumentBuilder<FabricClientCommandSource> build() {
        return literal("blindness").executes(context -> {
            enabled = !enabled;
            return 1;
        });
    }

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(build()));
    }
}
