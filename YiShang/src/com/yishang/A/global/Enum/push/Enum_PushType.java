package com.yishang.A.global.Enum.push;

/**
 * 推送消息类型
 * @author MM_Zerui
 *
 */
public enum Enum_PushType {
	DEFAULT(-1),
	SYS_INFORM(11),//系统通知
	COM_CHECK(21), //公司关联审核
	COM_BAOBEI(22),//报备消息
	COM_INFORM(23),//企业通知
	COM_AWARD(24), //企业奖励（颁发积分）
	RES_RECEV(31), //收到文档
	RES_INTEREST(32);//对文档感兴趣
	private int value;
	private Enum_PushType(int value) {
		// TODO Auto-generated constructor stub
		this.value=value;
	}
	public int value(){
		return value;
	}
	public void setValue(int value){
		this.value=value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(value);
	}
	public static Enum_PushType valueOf(int i){
		switch (i) {
		case 11:
			return Enum_PushType.SYS_INFORM;
		case 21:
			return Enum_PushType.COM_CHECK;
		case 22:
			return Enum_PushType.COM_BAOBEI;
		case 23:
			return Enum_PushType.COM_INFORM;
		case 24:
			return Enum_PushType.COM_AWARD;
		case 31:
			return Enum_PushType.RES_RECEV;
		case 32:
			return Enum_PushType.RES_INTEREST;
		default:
			return Enum_PushType.DEFAULT;
		}
	}
}
