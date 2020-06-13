package com.poi.testpoi.service.Impl;

import com.poi.testpoi.mapper.HyDaexIMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.pojo.HyDaexI;
import com.poi.testpoi.pojo.HyStscA;
import com.poi.testpoi.service.HyStscAService;
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
public class HyStscAServiceImpl implements HyStscAService {
    @Autowired
    private HyStscAMapper hyStscAMapper;
    @Autowired
    private HyDaexIMapper HyDaexIMapper;// 附录

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {
        List<HyStscA> hyStscAList = new ArrayList<>();
        CellTypeUtil cellTypeUtil = new CellTypeUtil();
        IsRowNull isRowNull = new IsRowNull();
        SheetUtil sheetUtil = new SheetUtil();
        boolean notNull = false;
        Sheet sheet = sheetUtil.batchImport(fileName, file);
        if (sheet != null) {
            notNull = true;
        }
        HyStscA hyStscA0;
        HyDaexI hyDaexI = new HyDaexI();
        String nt = null;
        String year = cellTypeUtil.cellTypeYear(fileName, file, 0, 0);
        System.out.println(year);
        String stcd = null;
        int yr = Integer.parseInt(year);


        if (yr < 1986) {
            for (int r = 2; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);// 通过sheet表单对象得到 行对象
                if (r == sheet.getLastRowNum()) {
                    Boolean flag = isRowNull.rowIsNull(row);// false表示不为空
                    if (flag) {// true,最后一行为空
                    } else {
                        nt = isRowNull.rowHasNt(row);//附注
                        if (nt == null) {
                        } else {
                            continue;
                        }
                    }
                }
                Boolean falg = isRowNull.rowIsNull(row);
                if (falg) {
                    continue;
                }

                hyStscA0 = new HyStscA();
                stcd = cellTypeUtil.cellTypeStcd(fileName, file, r, 3);
                hyStscA0.setStcd(stcd);




                //站名
                hyStscA0.setStnm(cellTypeUtil.cellTypecommon(fileName,file,r,4));
                //类型
                hyStscA0.setStct(cellTypeUtil.cellTypecommon(fileName,file,r,5));

                //河名
                hyStscA0.setRvnm(cellTypeUtil.cellTypecommon(fileName, file, r, 6));
                   /* //东经
                    hyStscA0.setLgtd(cellTypeUtil.cellTypecommon(fileName,file,r,5));
                    //北纬
                    hyStscA0.setLttd(cellTypeUtil.cellTypecommon(fileName,file,r,6));*/
                //基面名称
               // hyStscA0.setFdtmnm(cellTypeUtil.cellTypecommon(fileName, file, r, 10));
                //地址
              //  hyStscA0.setStlc(cellTypeUtil.cellTypecommon(fileName, file, r, 4));
                hyStscAList.add(hyStscA0);


                if (stcd == null || "".equals(stcd)) {
                    continue;
                }
            }
        }
        for (HyStscA hyStscAResord : hyStscAList) {
            Integer count = hyStscAMapper.selectstcd(hyStscAResord);
            if (count > 0) {
                hyStscAMapper.delete(hyStscAResord);
            }
            hyStscAMapper.add(hyStscAResord);
        }
        hyDaexI.setStcd(stcd);
        hyDaexI.setTbid("HY_STSC_A");
        hyDaexI.setYear(year);
        if (nt == null || "".equals(nt)) {
            Integer count = HyDaexIMapper.selectstcd(hyDaexI);
            if (count > 0) {
                HyDaexIMapper.delete(hyDaexI);
            }
        } else {
            hyDaexI.setNt(nt);
            Integer count = HyDaexIMapper.selectstcd(hyDaexI);
            if (count > 0) {
                HyDaexIMapper.delete(hyDaexI);
            }
            HyDaexIMapper.add(hyDaexI);
        }

        return notNull;
    }
}
