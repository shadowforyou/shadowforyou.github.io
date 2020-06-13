package com.poi.testpoi.service.Impl;

import com.poi.testpoi.mapper.YbIndexMapper;
import com.poi.testpoi.pojo.YbIndex;
import com.poi.testpoi.service.YbIndexService;
import com.poi.testpoi.util.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class YbIndexServiceImpl implements YbIndexService {
    @Autowired
    private YbIndexMapper ybIndexMapper;


    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {
        List<YbIndex> ybIndexList = new ArrayList<>();
        CellTypeUtil cellTypeUtil = new CellTypeUtil();
        SheetUtil sheetUtil = new SheetUtil();
        boolean notNull = false;
        Sheet sheet = sheetUtil.batchImport(fileName,file);
        if (sheet!=null){
            notNull = true;
        }
        YbIndex ybIndexa;
        YbIndex ybIndex0;
        String year = cellTypeUtil.cellTypeYear(fileName,file,0,0);
        String stcd = null;
        int yr = Integer.parseInt(year);
        System.out.println(year);
        if (yr>1985){
            for (int r = 3;r<sheet.getLastRowNum();r++){
                Row row = sheet.getRow(r);// 通过sheet表单对象得到 行对象

                String name = cellTypeUtil.cellTypecommon(fileName, file, r, 1);//河名
                String dname = cellTypeUtil.cellTypecommon(fileName,file,r,2);//点名
                String yname = cellTypeUtil.cellTypecommon(fileName,file,r,3);//雨量点名


                if(dname != null || "".equals(dname)){
                    ybIndex0 = new YbIndex();
                    stcd = year + "-" + name + dname + "-水位水文站";
                    ybIndex0.setStcd(stcd);
                    ybIndex0.setYear(year);
                    try{
                        //逐日平均水位表
                        ybIndex0.setHy_dz_c(cellTypeUtil.cellTypecommon(fileName,file,r,11));
                        //实测大断面成果表
                        ybIndex0.setHy_xsmsrs_g(cellTypeUtil.cellTypecommon(fileName,file,r,12));
                        //实测流量成果表
                        ybIndex0.setHy_obq_g(cellTypeUtil.cellTypecommon(fileName,file,r,13));
                        //堰闸流量率定成果表
                        ybIndex0.setHy_wsqr_h(cellTypeUtil.cellTypecommon(fileName,file,r,14));
                        //逐日平均流量表
                        ybIndex0.setHy_dq_c(cellTypeUtil.cellTypecommon(fileName,file,r,15));
                        //洪水水文要素摘录表
                        ybIndex0.setHy_fdheex_b(cellTypeUtil.cellTypecommon(fileName,file,r,17));
                        //堰闸洪水水文要素摘录表
                        ybIndex0.setHy_wsfhex_b(cellTypeUtil.cellTypecommon(fileName,file,r,18));
                        //水库水文要素摘录表
                        ybIndex0.setHy_rvfhex_b(cellTypeUtil.cellTypecommon(fileName,file,r,19));
                        //逐日平均含沙量表
                        ybIndex0.setHy_dcs_c(cellTypeUtil.cellTypecommon(fileName,file,r,20));
                        //逐日平均悬移质输沙率表
                        ybIndex0.setHy_dqs_c(cellTypeUtil.cellTypecommon(fileName,file,r,21));

                        ybIndexList.add(ybIndex0);
                    }catch (NumberFormatException e){
                        e.printStackTrace();
                    }

                }
                if (yname != null || "".equals(yname)){
                    ybIndexa = new YbIndex();
                    stcd = year + "-" + name + yname + "-降水蒸发站";
                    ybIndexa.setStcd(stcd);
                    ybIndexa.setYear(year);

                    try{
                        //逐日降水量表
                        ybIndexa.setHy_dp_c(cellTypeUtil.cellTypecommon(fileName,file,r,22));
                        //降水量摘录表
                        ybIndexa.setHy_prex_b(cellTypeUtil.cellTypecommon(fileName,file,r,23));
                        //各时段最大降水量表（1）
                        ybIndexa.setHy_mmxp_f(cellTypeUtil.cellTypecommon(fileName,file,r,24));
                        //各时段最大降水量表（2）
                        ybIndexa.setHy_hmxp_f(cellTypeUtil.cellTypecommon(fileName,file,r,25));
                        //逐日水面蒸发量表
                        ybIndexa.setHy_dwe_c(cellTypeUtil.cellTypecommon(fileName,file,r,26));
                        ybIndexList.add(ybIndexa);
                    }catch (NumberFormatException e){
                        e.printStackTrace();
                    }

                }
                if(stcd==null || "".equals(stcd)){continue;}
            }
        }
        for (YbIndex ybIndexResord : ybIndexList){
            Integer count = ybIndexMapper.selectstcd(ybIndexResord);
            if (count>0){
                ybIndexMapper.delete(ybIndexResord);
            }
            ybIndexMapper.add(ybIndexResord);
        }
        return notNull;
    }


}
