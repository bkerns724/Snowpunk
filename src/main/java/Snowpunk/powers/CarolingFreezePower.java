package Snowpunk.powers;

import Snowpunk.actions.ChangeChristmasSpiritAction;
import Snowpunk.patches.CardTemperatureFields;
import Snowpunk.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Random;

import static Snowpunk.SnowpunkMod.makeID;

public class CarolingFreezePower extends AbstractEasyPower {
    public static String POWER_ID = makeID(CarolingFreezePower.class.getSimpleName());
    public static PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static String[] DESCRIPTIONS = strings.DESCRIPTIONS;
    public static PowerStrings carolStrings = CardCrawlGame.languagePack.getPowerStrings(HolidayCheerPower.POWER_ID);
    public static String[] CAROL_DESCRIPTIONS = carolStrings.DESCRIPTIONS;

    public CarolingFreezePower(AbstractCreature owner, int amount) {
        super(POWER_ID, strings.NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + (amount == 1 ? DESCRIPTIONS[1] : DESCRIPTIONS[2]);
    }

    @Override
    public void atStartOfTurn() {
        Wiz.atb(new DrawCardAction(amount, new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : DrawCardAction.drawnCards) {
                    CardTemperatureFields.addHeat(c, -99);
                }
                this.isDone = true;
            }
        }));
        if (!owner.hasPower(CarolingCoolPower.POWER_ID))
            carol();
    }

    private void carol() {
        Random random = new Random();
        int speech = 1 + random.nextInt(CAROL_DESCRIPTIONS.length - 2);
        Wiz.atb(new TalkAction(true, CAROL_DESCRIPTIONS[speech], 2, 2));
    }
}
