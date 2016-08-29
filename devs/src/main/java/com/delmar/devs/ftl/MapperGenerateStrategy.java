package com.delmar.devs.ftl;

import com.delmar.cons.IntelliKeyWord;
import com.delmar.core.dto.ColumnMetaDataDto;
import com.delmar.core.dto.ForeignKey;
import com.delmar.core.dto.TableMetaDataDto;
import com.delmar.devs.model.ColumnInfo;
import com.delmar.devs.model.GenModelDto;
import com.delmar.utils.CommonConverter;
import com.delmar.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/8/26.
 */
public class MapperGenerateStrategy {
    public MapperGenerateStrategy(TableMetaDataDto tableMetaDataDto,GenModelDto genModelDto)
    {
        this.tableMetaDataDto=tableMetaDataDto;
        this.genModelDto=genModelDto;
    }
    private TableMetaDataDto tableMetaDataDto;
    private GenModelDto genModelDto;
    public void generateMapper()
    {
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("module",genModelDto.getModule());
        param.put("model",genModelDto.getModelName());
        param.put("tableName",tableMetaDataDto.getName());
        List<ColumnInfo> columnInfoList=new ArrayList<ColumnInfo>();
        for(ColumnMetaDataDto cmd:tableMetaDataDto.getColumnList())
        {
            ColumnInfo columnInfo=new ColumnInfo();
            try {
                CommonConverter.copyProperties(cmd,columnInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String linkType=IntelliKeyWord.getSQlType(columnInfo.getType());
            if(linkType!=null)
            {
                columnInfo.setType(linkType);
            }
            columnInfo.setPropertyName(StringUtil.fieldToProperty(cmd.getColumnName()));
            System.out.println("columnInfo.getPropertyName()="+columnInfo.getPropertyName()+" type:"+columnInfo.getType());
            columnInfo.setKeyWord(IntelliKeyWord.isKeyWord(columnInfo.getColumnName()));
            columnInfoList.add(columnInfo);
        }
        param.put("columnList",columnInfoList);
        List<ColumnInfo> foreignList=new ArrayList<ColumnInfo>();
        List<ForeignKey>  list=tableMetaDataDto.getImportedFK();
        if(list!=null)
        for(ColumnInfo c:columnInfoList)
        {
            for(ForeignKey key:list)
            {
                if(key.getFkColumnName().equals(c.getColumnName()))
                {
                    foreignList.add(c);
                    break;
                }
            }
        }
        param.put("foreignList",foreignList);
        FreeMarkerHelper.getInstance().outFile("mapper.ftl",param,"src/main/resources/com/delmar/"+genModelDto.getModule()+"/mybatis/sql/"+genModelDto.getModelName()+"Mapper.xml");

    }

}