
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01											  *
 *	作者：刘大磊								                              *
 * 电话：0532-66701118                                                        *
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.cargo.service;

import com.delmar.cargo.model.Production;
import com.delmar.core.service.CoreService;
import com.delmar.cargo.model.ProductionLine;
import java.util.List;
/**
 * @author 刘大磊 2016-08-29 15:01:00
 */
public interface ProductionService extends CoreService<Production> {
	/**
	 * @param ids
	 */
	public void deleteProductionList(Integer[] ids);
    public List<ProductionLine> getProductionLineListByProductionId(Integer productionId);

public Integer saveProduction(Production production,List<ProductionLine> productionLineList);

}