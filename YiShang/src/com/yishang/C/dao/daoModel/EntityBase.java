/*
 * Copyright (c) 2013. wyouflf (wyouflf@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yishang.C.dao.daoModel;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.yishang.A.global.baseClass.BaseEntity;

/**
 * Author: wyouflf
 * Date: 13-8-13
 * Time: ����11:15
 */
public abstract class EntityBase extends BaseEntity{


    //@Id // �������û��������Ϊid��_id��ʱ����ҪΪ������Ӵ�ע��
    //@NoAutoIncrement // int,long���͵�idĬ������������ʹ������ʱ��Ӵ�ע��
	@Id
    private int _id;
	
	
	 //�������ڱ�ʶ�Ƿ��Ѿ�ѡ�е�״̬
	
	protected boolean item_select;
	
	

	public boolean isItem_select() {
		return item_select;
	}

	public void setItem_select(boolean item_select) {
		this.item_select = item_select;
	}


	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}
	

}
