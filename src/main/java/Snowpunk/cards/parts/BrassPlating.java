package Snowpunk.cards.parts;

import Snowpunk.cardmods.BlockBuffMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.function.Predicate;

import static Snowpunk.SnowpunkMod.makeID;

public class BrassPlating extends AbstractPartCard {
    public static final String ID = makeID(BrassPlating.class.getSimpleName());

    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardRarity RARITY = CardRarity.COMMON;

    public BrassPlating() {
        super(ID, TYPE, RARITY);
    }

    @Override
    public Predicate<AbstractCard> getFilter() {
        return isSkill.and(hasBlockValue);
    }

    @Override
    public void apply(AbstractCard card) {
        CardModifierManager.addModifier(card, new BlockBuffMod(3));
    }
}
