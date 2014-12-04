package com.yishang.A.global.Enum.push;

/**
 * ������Դ
 * @author MM_Zerui
 *
 */
public enum Enum_PushSource {
	DEFAULT(-1),
	ALL(0), //����ȫ��
	SYSTEM(1),  //����ϵͳ
	COMPANY(2), //������ҵ
	USER(3),	//�����û�
	NEWFRIEND(4),//����������
	RELACOMLIST(5);//���Թ�����ҵ�б�
	private int value;
	private Enum_PushSource(int value) {
		// TODO Auto-generated constructor stub
		this.value=value;
	}
	public final int value(){
		return value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(value);
	}
	public static Enum_PushSource valueOf(int i){
		switch (i) {
		case 1:
			return Enum_PushSource.SYSTEM;
		case 2:
			return Enum_PushSource.COMPANY;
		case 3:
			return Enum_PushSource.USER;
		case 4:
			return Enum_PushSource.NEWFRIEND;
		default:
			return Enum_PushSource.DEFAULT;
		}
	}
}
