package Snowpunk.cardmods.cores;

import Snowpunk.cardmods.cores.effects.AbstractCardEffectMod;
import Snowpunk.cards.cores.AbstractCoreCard;
import Snowpunk.cards.cores.AssembledCard;
import Snowpunk.cards.cores.util.OnUseCardInstance;
import Snowpunk.util.Wiz;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;

public class DoubleBarrelMod extends AbstractCardEffectMod {
    public DoubleBarrelMod(String description, AbstractCoreCard.ValueType type, int effect, int upEffect, boolean secondVar) {
        super(description, type, effect, upEffect, secondVar);
        this.priority = -1;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        if (card instanceof AssembledCard) {
            ((AssembledCard) card).addUseEffects(new OnUseCardInstance(priority, (p, m) -> {
                int amount = useSecondVar ? ((AssembledCard) card).secondDamage : card.damage;
                Wiz.atb(new DamageAction(m, new DamageInfo(p, amount, card.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                Wiz.atb(new DamageAction(m, new DamageInfo(p, amount, card.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }));
        }
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DoubleBarrelMod(description, type, effect, upEffect, useSecondVar);
    }
}
