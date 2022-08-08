package Snowpunk.cards;

import Snowpunk.actions.AssembleCardAction;
import Snowpunk.cards.abstracts.AbstractMultiUpgradeCard;
import Snowpunk.util.Wiz;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Snowpunk.SnowpunkMod.makeID;

public class Create extends AbstractMultiUpgradeCard {
    public final static String ID = makeID(Create.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int COST = 1, CORES = 1, PARTS = 2;

    public Create() {
        super(ID, COST, TYPE, RARITY, TARGET);
        FleetingField.fleeting.set(this, true);
        isEthereal = true;
        tags.add(CardTags.HEALING); // We don't want this generated in combat
        baseInfo = info = 3;
        magicNumber = baseMagicNumber = CORES;
        secondMagic = baseSecondMagic = PARTS;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AssembleCardAction(magicNumber, secondMagic, info));
    }

    @Override
    public void addUpgrades() {
        addUpgradeData(this, () -> upgradeSecondMagic(1));
        addUpgradeData(this, () -> upgradeMagicNumber(1));
        addUpgradeData(this, () -> upgradeInfo(2));
    }
}