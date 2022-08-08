package Snowpunk.cards;

import Snowpunk.cardmods.FluxMod;
import Snowpunk.cards.abstracts.AbstractMultiUpgradeCard;
import Snowpunk.patches.CardTemperatureFields;
import Snowpunk.powers.BurnPower;
import Snowpunk.powers.ChillPower;
import Snowpunk.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Snowpunk.SnowpunkMod.makeID;

public class ThermalShock extends AbstractMultiUpgradeCard {
    public final static String ID = makeID(ThermalShock.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;

    private static final int COST = 2;
    private static final int BURN = 5;
    private static final int UP_BURN = 2;
    private static final int CHILL = 2;

    public ThermalShock() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = BURN;
        info = baseInfo = 0;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            if (!mo.isDeadOrEscaped()) {
                Wiz.applyToEnemy(mo, new BurnPower(mo, p, magicNumber));
                Wiz.applyToEnemy(mo, new ChillPower(mo, magicNumber));
            }
        }
    }

    @Override
    public void addUpgrades() {
        addUpgradeData(this, () -> upgradeMagicNumber(UP_BURN));
        addUpgradeData(this, () -> CardModifierManager.addModifier(this, new FluxMod()));
        addUpgradeData(this, () -> upgrade3(), 1);
    }

    private void upgrade3() {
        CardTemperatureFields.addInherentHeat(this, 1);
        exhaust = false;
        upgradeInfo(1);
    }
}