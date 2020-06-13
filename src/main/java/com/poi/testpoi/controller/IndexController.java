package com.poi.testpoi.controller;

import javax.servlet.http.HttpSession;

import com.poi.testpoi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class IndexController {
	@Autowired
	private StnmService StnmService;//站名
	@Autowired
	private HyDcsCService hyDcsCService;//逐日含沙量-日表
	@Autowired
	private HyMtcsEService hyMtcsEService;
	@Autowired
	private HyYrcsFService hyYrcsFService;
	@Autowired
	private HyDpCService hyDpCService;//逐日降水量-日降水量表
	@Autowired
	private HyMtpEService hyMtpEService;
	@Autowired
	private HyYrpFService hyYrpFService;
	@Autowired
	private HyDmxpFService hyDmxpFService;
	@Autowired
	private HyDqCService hyDqCService;//逐日平均流量表-日平均流量表
	@Autowired
	private HyMtqEService hyMtqEService;
	@Autowired
	private HyYrqFService hyYrqFService;
	@Autowired
	private HyDqsCService hyDqsCService;//逐日悬移质输沙率表-日平均输沙率表
	@Autowired
	private HyMtqsEService hyMtqsEService;
	@Autowired
	private HyYrqsFService hyYrqsFService;
	@Autowired
	private HyDweCService hyDweCService;//逐日水面蒸发量表-日水面蒸发量表
	
	@Autowired
	private HyDzCService hyDzCService;//逐日平均水位表-日水位表(暂定总表)
	@Autowired
	private HyMtzEService hyMtzEService;
	@Autowired
	private HyYrzFService hyYrzFService;
	@Autowired
	private HyWfdzFService hyWfdzFService;
	@Autowired
	private HyFdheexBService hyFdheexBService;//洪水水文要素摘录表
	@Autowired
	private HyHmxpFService hyHmxpFService;//各时段最大降水量表（二）
	@Autowired
	private HyImxfwFService HyImxfwFService;//各时段最大洪水总量统计表
	@Autowired
	private HyMmxpFService hyMmxpFService;//各时段最大降水量表（一）
	@Autowired
	private HyObqGService hyObqGService;//实测流量成果表
	@Autowired
	private HyPrexBService hyPrexBService;//降水量摘录表
	@Autowired
	private HyRvfhexBService hyRvfhexBService;// 水库水文要素摘录表
	@Autowired
	private HyWsfhexBService hyWsfhexBService;//堰闸洪水水文要素摘录表
	@Autowired
	private HyWsqrHService hyWsqrHService;//堰闸流量率定成果表
	@Autowired
	private HyXsmsrsGService hyXsmsrsGService;//实测大断面成果表
	
	@Autowired
	private YbIndexService ybIndexService;//索引表
	@Autowired
	private HyStscAService hyStscAService;//测站一览表

	@RequestMapping("/index")
	public String showUser() {
		return "index";
	}
	
	@RequestMapping(value = "/import")
	public String exImport(@RequestParam(value = "filename")MultipartFile file, HttpSession session) {

		boolean a = false;

		String fileName = file.getOriginalFilename();
		try {
			String stnm1 = StnmService.batchImport(fileName, file);
			if(stnm1.equals("实测大断面成果表")) {
				a = hyXsmsrsGService.batchImport(fileName, file);
			}else if("逐日含沙量表".equals(stnm1)) {
				a = hyDcsCService.batchImport(fileName, file);
			}else if("含沙量月统计表".equals(stnm1)) {
				a = hyMtcsEService.batchImport(fileName, file);
			}else if("含沙量年统计表".equals(stnm1)) {
				a = hyYrcsFService.batchImport(fileName, file);
			}else if("逐日输沙率表".equals(stnm1)) {
				a = hyDqsCService.batchImport(fileName, file);
			}else if("悬移质输沙率月统计表".equals(stnm1)) {
				a = hyMtqsEService.batchImport(fileName, file);
			}else if("悬移质输沙率年统计表".equals(stnm1)) {
				a = hyYrqsFService.batchImport(fileName, file);
			}else if("堰闸流量率定成果表".equals(stnm1)) {
				a = hyWsqrHService.batchImport(fileName, file);
			}else if("实测流量成果表".equals(stnm1)) {
				a = hyObqGService.batchImport(fileName, file);
			}else if("逐日平均流量表".equals(stnm1)) {
				a = hyDqCService.batchImport(fileName, file);
			}else if("流量月统计表".equals(stnm1)) {
				a = hyMtqEService.batchImport(fileName, file);
			}else if("流量年统计表".equals(stnm1)) {
				a = hyYrqFService.batchImport(fileName, file);
			}else if("逐日降水量表".equals(stnm1)) {
				a = hyDpCService.batchImport(fileName, file);
			}else if("降水量月统计表".equals(stnm1)) {
				a = hyMtpEService.batchImport(fileName, file);
			}else if("降水量年统计表".equals(stnm1)) {
				a = hyYrpFService.batchImport(fileName, file);
			}else if("日时段最大降水量表".equals(stnm1)) {
				a = hyDmxpFService.batchImport(fileName, file);
			}else if("洪水水文要素摘录表".equals(stnm1)) {
				a = hyFdheexBService.batchImport(fileName, file);
			}else if("堰闸洪水水文要素摘录表".equals(stnm1)) {
				a = hyWsfhexBService.batchImport(fileName, file);
			}else if("各时段最大洪水总量统计表".equals(stnm1)) {
				a = HyImxfwFService.batchImport(fileName, file);
			}else if("逐日平均水位表".equals(stnm1)) {
				a = hyDzCService.batchImport(fileName, file);
			}else if("水位月统计表".equals(stnm1)) {
				a = hyMtzEService.batchImport(fileName, file);
			}else if("水位年统计表".equals(stnm1)) {
				a = hyYrzFService.batchImport(fileName, file);
			}else if("保证率水位表".equals(stnm1)) {
				a = hyWfdzFService.batchImport(fileName, file);
			}else if("逐日水面蒸发量表".equals(stnm1)) {
				a = hyDweCService.batchImport(fileName, file);
			}else if("降水量摘录表".equals(stnm1)) {
				a = hyPrexBService.batchImport(fileName, file);
			}else if("水库水文要素摘录表".equals(stnm1)) {
				a = hyRvfhexBService.batchImport(fileName, file);
			}else if("各时段最大降水量表（二）".equals(stnm1)) {
				a = hyHmxpFService.batchImport(fileName, file);
			}else if("各时段最大降水量表（一）".equals(stnm1)) {
				a = hyMmxpFService.batchImport(fileName, file);
			}else if("测站一览表".equals(stnm1)){
				a = ybIndexService.batchImport(fileName,file);
			}else if("小河站测站一览表".equals(stnm1)){
				a = hyStscAService.batchImport(fileName,file);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:index";
	}
}
