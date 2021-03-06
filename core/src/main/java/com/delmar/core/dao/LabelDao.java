/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.core.dao;

import com.delmar.core.bo.LabelBo;
import com.delmar.core.model.Label;
import com.delmar.core.dao.CoreDao;

import java.util.List;

/**
 * @author 刘大磊 2016-09-10 13:45:14
 */
public interface LabelDao extends CoreDao<Label> {

    Label getLabelByValue(String s);

    List<LabelBo> selectLocaleLabel(String locale);
}
