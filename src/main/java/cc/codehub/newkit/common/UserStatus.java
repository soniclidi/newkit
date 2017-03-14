package cc.codehub.newkit.common;


public enum UserStatus {

	/**
	 * 正常
	 */
	NORMAL("正常", 0),
	
	/**
	 * 已删除
	 */
	DELETE("已删除", 1),

	/**
	 * 已禁用
	 */
	STOP("已禁用", 2);

	// 成员变量
	private final String key;
	private final Integer value;

	UserStatus(String key, Integer value) {
		this.key = key;
		this.value = value;
	}

	public Integer getValue()
	{
		return this.value;
	}
}
