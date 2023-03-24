package Snowpunk.powers;

import Snowpunk.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static Snowpunk.SnowpunkMod.makeID;

public class SingePower extends AbstractEasyPower {
    public static String POWER_ID = makeID(SingePower.class.getSimpleName());
    public static PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static String[] DESCRIPTIONS = strings.DESCRIPTIONS;
    private final AbstractCreature source;

    public int removeThisTurn;

    public SingePower(AbstractCreature owner, AbstractCreature source, int amount) {
        this(owner, source, amount, -1);
    }

    public SingePower(AbstractCreature owner, AbstractCreature source, int amount, int removeThisTurn) {
        super(POWER_ID, strings.NAME, PowerType.DEBUFF, true, owner, amount);
        this.loadRegion("flameBarrier");
        this.source = source;
        priority = -1;
        this.removeThisTurn = removeThisTurn;
    }

    @Override
    public void atEndOfRound() {
        //Wiz.atb(new DamageAction(owner, new DamageInfo(source, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        if (removeThisTurn < 0)
            Wiz.atb(new RemoveSpecificPowerAction(owner, owner, this));
        else {
            Wiz.atb(new ReducePowerAction(owner, owner, this, removeThisTurn));
            removeThisTurn = -1;
        }
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        if (damageType == DamageInfo.DamageType.NORMAL)
            return damage + amount;
        return damage;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS &&
                info.owner != null && info.owner != owner) {
            flash();
        }
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}
