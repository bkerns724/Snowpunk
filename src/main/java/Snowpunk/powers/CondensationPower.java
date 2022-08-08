package Snowpunk.powers;

import Snowpunk.actions.CondenseRandomCardToDrawPileAction;
import Snowpunk.cards.Fireball;
import Snowpunk.util.Wiz;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static Snowpunk.SnowpunkMod.makeID;

public class CondensationPower extends AbstractEasyPower implements OnCondensePower {
    public static String POWER_ID = makeID(CondensationPower.class.getSimpleName());
    public static PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static String[] DESCRIPTIONS = strings.DESCRIPTIONS;

    public CondensationPower(AbstractCreature owner, int amount) {
        super(POWER_ID, strings.NAME, PowerType.BUFF, false, owner, amount);
        //this.loadRegion("retain");
    }

    @Override
    public void atStartOfTurn() {
        Wiz.att(new CondenseRandomCardToDrawPileAction(amount));
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public void onCondense() {

    }
}
