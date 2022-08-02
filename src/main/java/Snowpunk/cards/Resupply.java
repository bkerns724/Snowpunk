package Snowpunk.cards;

import Snowpunk.actions.TinkerAction;
import Snowpunk.cardmods.BetterExhaustMod;
import Snowpunk.cards.abstracts.AbstractEasyCard;
import Snowpunk.cards.abstracts.AbstractMultiUpgradeCard;
import Snowpunk.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Snowpunk.SnowpunkMod.makeID;

public class Resupply extends AbstractMultiUpgradeCard {
    public final static String ID = makeID(Resupply.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int COST = 1;
    private static final int UP_COST = 0;
    private static final int DRAW = 2;
    private static final int UP_DRAW = 1;

    public Resupply() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = DRAW;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DrawCardAction(magicNumber, new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : DrawCardAction.drawnCards) {
                    Wiz.att(new TinkerAction(c, true));
                }
                this.isDone = true;
            }
        }));
    }

    @Override
    public void addUpgrades() {
        addUpgradeData(this, () -> upgradeMagicNumber(UP_DRAW));
        addUpgradeData(this, () -> {
            this.isInnate = true;
            uDesc();
        });
        addUpgradeData(this, () -> {
            upgradeBaseCost(UP_COST);
            CardModifierManager.addModifier(this, new BetterExhaustMod());
        });
    }
}