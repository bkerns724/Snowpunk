package Snowpunk.cards;

import Snowpunk.actions.ClankAction;
import Snowpunk.cardmods.GearMod;
import Snowpunk.cards.abstracts.AbstractMultiUpgradeCard;
import Snowpunk.cards.abstracts.ClankCard;
import Snowpunk.patches.CardTemperatureFields;
import Snowpunk.powers.NoGunPower;
import Snowpunk.powers.WidgetsPower;
import Snowpunk.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;

import static Snowpunk.SnowpunkMod.makeID;

public class TooMuch extends AbstractMultiUpgradeCard implements ClankCard {
    public final static String ID = makeID(TooMuch.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int COST = 1, DMG = 13, DRAW = 2, UP_DMG = 3, UP_DRAW = 1;

    public TooMuch() {
        super(ID, COST, TYPE, RARITY, TARGET);
        damage = baseDamage = DMG;
        magicNumber = baseMagicNumber = DRAW;
    }

    public void use(AbstractPlayer player, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addToBot(new ClankAction(this));
        Wiz.atb(new DrawCardAction(magicNumber));
        //Wiz.applyToSelf(new NoGunPower(player, 1));
    }

    @Override
    public void addUpgrades() {
        addUpgradeData(() -> upgradeDamage(UP_DMG));
        addUpgradeData(() -> CardTemperatureFields.addInherentHeat(this, -1));
        addUpgradeData(() -> upgradeMagicNumber(UP_DRAW));
    }

    @Override
    public void onClank() {
        addToTop(new DiscardAction(Wiz.adp(), Wiz.adp(), 999, true));
    }
}