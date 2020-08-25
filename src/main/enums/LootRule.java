package main.enums;

/**
 * @author fissban
 */
public enum LootRule
{
	ITEM_LOOTER(ESystemMessage.LOOTING_FINDERS_KEEPERS),
	ITEM_RANDOM(ESystemMessage.LOOTING_RANDOM),
	ITEM_RANDOM_SPOIL(ESystemMessage.LOOTING_RANDOM_INCLUDE_SPOIL),
	ITEM_ORDER(ESystemMessage.LOOTING_BY_TURN),
	ITEM_ORDER_SPOIL(ESystemMessage.LOOTING_BY_TURN_INCLUDE_SPOIL);

	private final ESystemMessage _smId;

	private LootRule(ESystemMessage smId)
	{
		_smId = smId;
	}

	public ESystemMessage getMessageId()
	{
		return _smId;
	}
}
