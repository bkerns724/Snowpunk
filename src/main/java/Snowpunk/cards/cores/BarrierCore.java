package Snowpunk.cards.cores;

import Snowpunk.cardmods.cores.GainBlockMod;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import static Snowpunk.SnowpunkMod.makeID;

@NoPools
@NoCompendium
public class BarrierCore extends AbstractCoreCard {
    public static final String ID = makeID(BarrierCore.class.getSimpleName());
    public static String[] TEXT = CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION;

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 1;
    private static final int EFFECT = 6;
    private static final int UP_EFFECT = 3;

    public BarrierCore() {
        super(ID, COST, TYPE, RARITY);
        baseBlock = block = EFFECT;
    }

    @Override
    public void apply(AbstractCard card) {
        CardModifierManager.addModifier(card, new GainBlockMod(TEXT[0], rawDescription, COST, TYPE, RARITY, TARGET, EFFECT, UP_EFFECT));
    }

    @Override
    public void upp() {
        upgradeBlock(UP_EFFECT);
    }
}