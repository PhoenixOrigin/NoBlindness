package net.phoenix.noblindness.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.phoenix.noblindness.NoBlindness;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.Map;

@Mixin(LivingEntity.class)
public class LivingEntityPlayerMixin {
    @Shadow
    @Final
    private Map<StatusEffect, StatusEffectInstance> activeStatusEffects;

    @Inject(method = "getStatusEffects", at = @At("HEAD"), cancellable = true)
    private void getStatusEffects(CallbackInfoReturnable<Collection<StatusEffectInstance>> cir) {
        if (!NoBlindness.enabled) return;
        activeStatusEffects.remove(StatusEffects.BLINDNESS);
        cir.setReturnValue(activeStatusEffects.values());
    }

    @Inject(method = "addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z", at = @At("HEAD"), cancellable = true)
    private void getStatusEffects(StatusEffectInstance effect, CallbackInfoReturnable<Boolean> cir) {
        if (!NoBlindness.enabled) return;
        if (effect.getEffectType() == StatusEffects.BLINDNESS) {
            cir.setReturnValue(false);
        }
    }
}
