package Snowpunk.cards.cores;

import Snowpunk.cardmods.WhistolMod;
import Snowpunk.cardmods.cores.DoubleBarrelMod;
import Snowpunk.cardmods.cores.edits.CardEditMod;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import static Snowpunk.SnowpunkMod.makeID;

@NoPools
@NoCompendium
public class DoubleBarrel extends AbstractCoreCard {
    public static final String ID = makeID(DoubleBarrel.class.getSimpleName());
    public static String[] TEXT = CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION;

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final ValueType VALUE = ValueType.DAMAGE;

    private static final int COST = 2;
    private static final int DAMAGE = 9;
    private static final int UP_DAMAGE = 3;

    public DoubleBarrel() {
        super(ID, COST, TYPE, VALUE);
        baseDamage = damage = secondDamage = baseSecondDamage = DAMAGE;
        CardModifierManager.addModifier(this, new WhistolMod());
    }

    @Override
    public void apply(AbstractCard card) {
        CardModifierManager.addModifier(card, new CardEditMod(TEXT[0], COST, TYPE, CardRarity.SPECIAL, TARGET));
        CardModifierManager.addModifier(card, new DoubleBarrelMod(rawDescription, VALUE, DAMAGE, UP_DAMAGE, useSecondVar));
        CardModifierManager.addModifier(card, new WhistolMod());
    }

    @Override
    public void upp() {
        upgradeDamage(UP_DAMAGE);
        upgradeSecondDamage(UP_DAMAGE);
    }
}
