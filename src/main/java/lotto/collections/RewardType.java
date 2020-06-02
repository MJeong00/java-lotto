package lotto.collections;

import java.util.Arrays;
import java.util.Map;

public enum RewardType {
	NOTHING(0, new Money(0)),
	THIRD(3, new Money(5000)),
	FOURTH(4, new Money(50000)),
	FIFTH(5, new Money(1500000)),
	FIFTH_BONUS(5, new Money(30000000)),
	SIXTH(6, new Money(2_000_000_000));

	private final int code;
	private final Money reward;

	RewardType(int code, Money reward) {
		this.code = code;
		this.reward = reward;
	}

	public static int calculateProfit(Map<Integer, Integer> lottoStatistics) {
		return Arrays.stream(values())
			.filter(type -> lottoStatistics.containsKey(type.code))
			.map(type -> type.reward.getMoney() * lottoStatistics.get(type.code))
			.reduce(0, Integer::sum);
	}

	public static RewardType findTypeByCount(final Integer matchCount, final boolean matchBonus) {
		if (matchCount.equals(FIFTH.code)) {
			return findFifthOrFifthBonus(matchBonus);
		}
		return Arrays.stream(values())
			.filter(type -> type.code == matchCount)
			.findFirst()
			.orElse(NOTHING);
	}

	private static RewardType findFifthOrFifthBonus(final Boolean matchBonus) {
		if (matchBonus.equals(true)) {
			return FIFTH_BONUS;
		}
		return FIFTH;
	}

	public int getCode() {
		return code;
	}

	public Money getReward() {
		return reward;
	}
}
