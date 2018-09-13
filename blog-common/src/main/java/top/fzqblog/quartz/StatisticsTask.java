package top.fzqblog.quartz;


import top.fzqblog.service.IStatisticalDataService;
import top.fzqblog.utils.SpringContextUtil;

public class StatisticsTask {

	private IStatisticalDataService iStatisticalDataService = (IStatisticalDataService) SpringContextUtil.getBean("statisticalDataServiceImpl");
	
	public void statisticsData(){
		iStatisticalDataService.caculateData();
	}
}
