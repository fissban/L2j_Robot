package main.enums;

/**
 * This class defines all classes (ex : human fighter, darkFighter...) that a player can chose.
 * <ul>
 * <li>id : The Identifier of the class</li>
 * <li>isMage : True if the class is a mage class</li>
 * <li>race : The race of this class</li>
 * <li>parent : The parent ClassId or null if this class is the root</li>
 * </ul>
 */
public enum EClassId
{
	HUMAN_FIGHTER(ERace.HUMAN, EAi.FIGHTER, 0, "Human Fighter", null),
	WARRIOR(ERace.HUMAN, EAi.FIGHTER, 1, "Warrior", HUMAN_FIGHTER),
	GLADIATOR(ERace.HUMAN, EAi.FIGHTER, 2, "Gladiator", WARRIOR),
	WARLORD(ERace.HUMAN, EAi.FIGHTER, 2, "Warlord", WARRIOR),
	KNIGHT(ERace.HUMAN, EAi.FIGHTER, 1, "Human Knight", HUMAN_FIGHTER),
	PALADIN(ERace.HUMAN, EAi.FIGHTER, 2, "Paladin", KNIGHT),
	DARK_AVENGER(ERace.HUMAN, EAi.FIGHTER, 2, "Dark Avenger", KNIGHT),
	ROGUE(ERace.HUMAN, EAi.FIGHTER, 1, "Rogue", HUMAN_FIGHTER),
	TREASURE_HUNTER(ERace.HUMAN, EAi.FIGHTER, 2, "Treasure Hunter", ROGUE),
	HAWKEYE(ERace.HUMAN, EAi.ARCHER, 2, "Hawkeye", ROGUE),

	HUMAN_MYSTIC(ERace.HUMAN, EAi.MYSTIC, 0, "Human Mystic", null),
	HUMAN_WIZARD(ERace.HUMAN, EAi.MYSTIC, 1, "Human Wizard", HUMAN_MYSTIC),
	SORCERER(ERace.HUMAN, EAi.MYSTIC, 2, "Sorcerer", HUMAN_WIZARD),
	NECROMANCER(ERace.HUMAN, EAi.MYSTIC, 2, "Necromancer", HUMAN_WIZARD),
	WARLOCK(ERace.HUMAN, EAi.MYSTIC, 2, "Warlock", HUMAN_WIZARD),
	CLERIC(ERace.HUMAN, EAi.PRIEST, 1, "Cleric", HUMAN_MYSTIC),
	BISHOP(ERace.HUMAN, EAi.PRIEST, 2, "Bishop", CLERIC),
	PROPHET(ERace.HUMAN, EAi.PRIEST, 2, "Prophet", CLERIC),

	ELVEN_FIGHTER(ERace.ELF, EAi.FIGHTER, 0, "Elven Fighter", null),
	ELVEN_KNIGHT(ERace.ELF, EAi.FIGHTER, 1, "Elven Knight", ELVEN_FIGHTER),
	TEMPLE_KNIGHT(ERace.ELF, EAi.FIGHTER, 2, "Temple Knight", ELVEN_KNIGHT),
	SWORD_SINGER(ERace.ELF, EAi.FIGHTER, 2, "Sword Singer", ELVEN_KNIGHT),
	ELVEN_SCOUT(ERace.ELF, EAi.FIGHTER, 1, "Elven Scout", ELVEN_FIGHTER),
	PLAINS_WALKER(ERace.ELF, EAi.FIGHTER, 2, "Plains Walker", ELVEN_SCOUT),
	SILVER_RANGER(ERace.ELF, EAi.ARCHER, 2, "Silver Ranger", ELVEN_SCOUT),

	ELVEN_MYSTIC(ERace.ELF, EAi.MYSTIC, 0, "Elven Mystic", null),
	ELVEN_WIZARD(ERace.ELF, EAi.MYSTIC, 1, "Elven Wizard", ELVEN_MYSTIC),
	SPELLSINGER(ERace.ELF, EAi.MYSTIC, 2, "Spellsinger", ELVEN_WIZARD),
	ELEMENTAL_SUMMONER(ERace.ELF, EAi.MYSTIC, 2, "Elemental Summoner", ELVEN_WIZARD),
	ELVEN_ORACLE(ERace.ELF, EAi.PRIEST, 1, "Elven Oracle", ELVEN_MYSTIC),
	ELVEN_ELDER(ERace.ELF, EAi.PRIEST, 2, "Elven Elder", ELVEN_ORACLE),

	DARK_FIGHTER(ERace.DARK_ELF, EAi.FIGHTER, 0, "Dark Fighter", null),
	PALUS_KNIGHT(ERace.DARK_ELF, EAi.FIGHTER, 1, "Palus Knight", DARK_FIGHTER),
	SHILLIEN_KNIGHT(ERace.DARK_ELF, EAi.FIGHTER, 2, "Shillien Knight", PALUS_KNIGHT),
	BLADEDANCER(ERace.DARK_ELF, EAi.FIGHTER, 2, "Bladedancer", PALUS_KNIGHT),
	ASSASSIN(ERace.DARK_ELF, EAi.FIGHTER, 1, "Assassin", DARK_FIGHTER),
	ABYSS_WALKER(ERace.DARK_ELF, EAi.FIGHTER, 2, "Abyss Walker", ASSASSIN),
	PHANTOM_RANGER(ERace.DARK_ELF, EAi.ARCHER, 2, "Phantom Ranger", ASSASSIN),

	DARK_MYSTIC(ERace.DARK_ELF, EAi.MYSTIC, 0, "Dark Mystic", null),
	DARK_WIZARD(ERace.DARK_ELF, EAi.MYSTIC, 1, "Dark Wizard", DARK_MYSTIC),
	SPELLHOWLER(ERace.DARK_ELF, EAi.MYSTIC, 2, "Spellhowler", DARK_WIZARD),
	PHANTOM_SUMMONER(ERace.DARK_ELF, EAi.MYSTIC, 2, "Phantom Summoner", DARK_WIZARD),
	SHILLIEN_ORACLE(ERace.DARK_ELF, EAi.PRIEST, 1, "Shillien Oracle", DARK_MYSTIC),
	SHILLIEN_ELDER(ERace.DARK_ELF, EAi.PRIEST, 2, "Shillien Elder", SHILLIEN_ORACLE),

	ORC_FIGHTER(ERace.ORC, EAi.FIGHTER, 0, "Orc Fighter", null),
	ORC_RAIDER(ERace.ORC, EAi.FIGHTER, 1, "Orc Raider", ORC_FIGHTER),
	DESTROYER(ERace.ORC, EAi.FIGHTER, 2, "Destroyer", ORC_RAIDER),
	MONK(ERace.ORC, EAi.FIGHTER, 1, "Monk", ORC_FIGHTER),
	TYRANT(ERace.ORC, EAi.FIGHTER, 2, "Tyrant", MONK),

	ORC_MYSTIC(ERace.ORC, EAi.MYSTIC, 0, "Orc Mystic", null),
	ORC_SHAMAN(ERace.ORC, EAi.MYSTIC, 1, "Orc Shaman", ORC_MYSTIC),
	OVERLORD(ERace.ORC, EAi.MYSTIC, 2, "Overlord", ORC_SHAMAN),
	WARCRYER(ERace.ORC, EAi.MYSTIC, 2, "Warcryer", ORC_SHAMAN),

	DWARVEN_FIGHTER(ERace.DWARF, EAi.FIGHTER, 0, "Dwarven Fighter", null),
	SCAVENGER(ERace.DWARF, EAi.FIGHTER, 1, "Scavenger", DWARVEN_FIGHTER),
	BOUNTY_HUNTER(ERace.DWARF, EAi.FIGHTER, 2, "Bounty Hunter", SCAVENGER),
	ARTISAN(ERace.DWARF, EAi.FIGHTER, 1, "Artisan", DWARVEN_FIGHTER),
	WARSMITH(ERace.DWARF, EAi.FIGHTER, 2, "Warsmith", ARTISAN),

	DUMMY_1(null, null, -1, "dummy 1", null),
	DUMMY_2(null, null, -1, "dummy 2", null),
	DUMMY_3(null, null, -1, "dummy 3", null),
	DUMMY_4(null, null, -1, "dummy 4", null),
	DUMMY_5(null, null, -1, "dummy 5", null),
	DUMMY_6(null, null, -1, "dummy 6", null),
	DUMMY_7(null, null, -1, "dummy 7", null),
	DUMMY_8(null, null, -1, "dummy 8", null),
	DUMMY_9(null, null, -1, "dummy 9", null),
	DUMMY_10(null, null, -1, "dummy 10", null),
	DUMMY_11(null, null, -1, "dummy 11", null),
	DUMMY_12(null, null, -1, "dummy 12", null),
	DUMMY_13(null, null, -1, "dummy 13", null),
	DUMMY_14(null, null, -1, "dummy 14", null),
	DUMMY_15(null, null, -1, "dummy 15", null),
	DUMMY_16(null, null, -1, "dummy 16", null),
	DUMMY_17(null, null, -1, "dummy 17", null),
	DUMMY_18(null, null, -1, "dummy 18", null),
	DUMMY_19(null, null, -1, "dummy 19", null),
	DUMMY_20(null, null, -1, "dummy 20", null),
	DUMMY_21(null, null, -1, "dummy 21", null),
	DUMMY_22(null, null, -1, "dummy 22", null),
	DUMMY_23(null, null, -1, "dummy 23", null),
	DUMMY_24(null, null, -1, "dummy 24", null),
	DUMMY_25(null, null, -1, "dummy 25", null),
	DUMMY_26(null, null, -1, "dummy 26", null),
	DUMMY_27(null, null, -1, "dummy 27", null),
	DUMMY_28(null, null, -1, "dummy 28", null),
	DUMMY_29(null, null, -1, "dummy 29", null),
	DUMMY_30(null, null, -1, "dummy 30", null),

	DUELIST(ERace.HUMAN, EAi.FIGHTER, 3, "Duelist", GLADIATOR),
	DREADNOUGHT(ERace.HUMAN, EAi.FIGHTER, 3, "Dreadnought", WARLORD),
	PHOENIX_KNIGHT(ERace.HUMAN, EAi.FIGHTER, 3, "Phoenix Knight", PALADIN),
	HELL_KNIGHT(ERace.HUMAN, EAi.FIGHTER, 3, "Hell Knight", DARK_AVENGER),
	SAGGITARIUS(ERace.HUMAN, EAi.FIGHTER, 3, "Sagittarius", HAWKEYE),
	ADVENTURER(ERace.HUMAN, EAi.FIGHTER, 3, "Adventurer", TREASURE_HUNTER),
	ARCHMAGE(ERace.HUMAN, EAi.MYSTIC, 3, "Archmage", SORCERER),
	SOULTAKER(ERace.HUMAN, EAi.MYSTIC, 3, "Soultaker", NECROMANCER),
	ARCANA_LORD(ERace.HUMAN, EAi.MYSTIC, 3, "Arcana Lord", WARLOCK),
	CARDINAL(ERace.HUMAN, EAi.PRIEST, 3, "Cardinal", BISHOP),
	HIEROPHANT(ERace.HUMAN, EAi.PRIEST, 3, "Hierophant", PROPHET),

	EVAS_TEMPLAR(ERace.ELF, EAi.FIGHTER, 3, "Eva's Templar", TEMPLE_KNIGHT),
	SWORD_MUSE(ERace.ELF, EAi.FIGHTER, 3, "Sword Muse", SWORD_SINGER),
	WIND_RIDER(ERace.ELF, EAi.FIGHTER, 3, "Wind Rider", PLAINS_WALKER),
	MOONLIGHT_SENTINEL(ERace.ELF, EAi.FIGHTER, 3, "Moonlight Sentinel", SILVER_RANGER),
	MYSTIC_MUSE(ERace.ELF, EAi.MYSTIC, 3, "Mystic Muse", SPELLSINGER),
	ELEMENTAL_MASTER(ERace.ELF, EAi.MYSTIC, 3, "Elemental Master", ELEMENTAL_SUMMONER),
	EVAS_SAINT(ERace.ELF, EAi.PRIEST, 3, "Eva's Saint", ELVEN_ELDER),

	SHILLIEN_TEMPLAR(ERace.DARK_ELF, EAi.FIGHTER, 3, "Shillien Templar", SHILLIEN_KNIGHT),
	SPECTRAL_DANCER(ERace.DARK_ELF, EAi.FIGHTER, 3, "Spectral Dancer", BLADEDANCER),
	GHOST_HUNTER(ERace.DARK_ELF, EAi.FIGHTER, 3, "Ghost Hunter", ABYSS_WALKER),
	GHOST_SENTINEL(ERace.DARK_ELF, EAi.FIGHTER, 3, "Ghost Sentinel", PHANTOM_RANGER),
	STORM_SCREAMER(ERace.DARK_ELF, EAi.MYSTIC, 3, "Storm Screamer", SPELLHOWLER),
	SPECTRAL_MASTER(ERace.DARK_ELF, EAi.MYSTIC, 3, "Spectral Master", PHANTOM_SUMMONER),
	SHILLIEN_SAINT(ERace.DARK_ELF, EAi.PRIEST, 3, "Shillien Saint", SHILLIEN_ELDER),

	TITAN(ERace.ORC, EAi.FIGHTER, 3, "Titan", DESTROYER),
	GRAND_KHAVATARI(ERace.ORC, EAi.FIGHTER, 3, "Grand Khavatari", TYRANT),
	DOMINATOR(ERace.ORC, EAi.MYSTIC, 3, "Dominator", OVERLORD),
	DOOMCRYER(ERace.ORC, EAi.MYSTIC, 3, "Doom Cryer", WARCRYER),

	FORTUNE_SEEKER(ERace.DWARF, EAi.FIGHTER, 3, "Fortune Seeker", BOUNTY_HUNTER),
	MAESTRO(ERace.DWARF, EAi.FIGHTER, 3, "Maestro", WARSMITH);

	/**
	 * The ID of the class
	 */
	private final int id;

	/**
	 * The RaceType object of the class
	 */
	private final ERace race;

	/**
	 * The ClassType of the class
	 */
	private final EAi type;

	/**
	 * The level of the class
	 */
	private final int level;

	/**
	 * The name of the class
	 */
	private final String name;

	/**
	 * The parent ClassId of the class
	 */
	private final EClassId parent;

	/**
	 * Implicit constructor.
	 *
	 * @param r : Class race.
	 * @param t : Class type.
	 * @param l : Class level.
	 * @param n : Class name.
	 * @param p : Class parent.
	 */
	private EClassId(ERace r, EAi t, int l, String n, EClassId p)
	{
		id = ordinal();
		race = r;
		type = t;
		level = l;
		name = n;
		parent = p;
	}

	/**
	 * Returns the ID of the {@link EClassId}.
	 *
	 * @return int : The ID.
	 */
	public final int getId()
	{
		return id;
	}

	/**
	 * Returns the {@link ERace} of the {@link EClassId}.
	 *
	 * @return {@link ERace} : The race.
	 */
	public final ERace getRace()
	{
		return race;
	}

	/**
	 * Returns the {@link EAi} of the {@link EClassId}.
	 *
	 * @return {@link EAi} : The type.
	 */
	public final EAi getType()
	{
		return type;
	}

	/**
	 * Returns the level of the {@link EClassId}.
	 *
	 * @return int : The level (-1=dummy, 0=base, 1=1st class, 2=2nd class, 3=3rd class)
	 */
	public final int level()
	{
		return level;
	}

	public String getName()
	{
		return name;
	}

	/**
	 * Returns the parent {@link EClassId} of the {@link EClassId}.
	 *
	 * @return {@link EClassId} : The parent.
	 */
	public final EClassId getParent()
	{
		return parent;
	}
}