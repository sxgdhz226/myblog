/******************************************************************
 *
 *    
 *
 *    Copyright (c) 2016-forever 
 *    http://www.fzqblog.top
 *
 *    Package:     top.fzqblog.service
 *
 *    Filename:    StatisticalData.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    Company:     fzqblog
 *
 *    @author:     抽离
 *
 *    @version:    1.0.0
 *
 *    Create at:   2016年11月3日 下午3:16:30
 *
 *    Revision:
 *
 *    2016年11月3日 下午3:16:30
 *        - first revision
 *
 *****************************************************************/
package top.fzqblog.service;

import java.util.List;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.echart.Echart;
import top.fzqblog.po.model.Statistics;

/**
 * @ClassName StatisticalData
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author 抽离
 * @Date 2016年11月3日 下午3:16:30
 * @version 1.0.0
 */
public interface IStatisticalDataService {
	
	public void caculateData();
	
	public List<Statistics> findStatistics() throws BussinessException;
	
	public List<Echart> findEcharts() throws BussinessException;
}
