package top.fzqblog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import top.fzqblog.exception.BussinessException;
import top.fzqblog.mapper.SysResMapper;
import top.fzqblog.po.model.SysRes;
import top.fzqblog.po.query.SysResQuery;
import top.fzqblog.po.vo.State;
import top.fzqblog.po.vo.Tree;
import top.fzqblog.service.SysResService;
import top.fzqblog.utils.CollectionUtil;
import top.fzqblog.utils.StringUtils;

@Service
public class SysResServiceImpl implements SysResService {

	@Autowired
	private SysResMapper<SysRes, SysResQuery> sysResMapper;
	
	@Override
	public List<SysRes> findAllRes() {
		return this.sysResMapper.selectList(new SysResQuery());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=BussinessException.class)
	public void deleteSysRes(Integer[] ids) throws BussinessException {
		if(ids == null || ids.length == 0){
			throw new BussinessException("参数错误");
		}
		
		sysResMapper.deletePermission(ids);
		sysResMapper.delete(ids);
	}

	@Override
	public void addSysRes(SysRes sysRes) throws BussinessException {
		if(StringUtils.isEmpty(sysRes.getName()) || sysRes.getPid() == null 
				|| StringUtils.isEmpty(sysRes.getUrl()) || sysRes.getSeq() == null
				|| sysRes.getType() == null || sysRes.getEnabled() == null
				|| StringUtils.isEmpty(sysRes.getKey())){
			throw new BussinessException("参数错误");
		}
		
		sysRes.setModifydate(new Date());
		
		sysResMapper.insert(sysRes);
	}

	
	@Override
	public void updateSysRes(SysRes sysRes) throws BussinessException {
		if(sysRes.getId() == null || StringUtils.isEmpty(sysRes.getName()) || 
				sysRes.getPid() == null || StringUtils.isEmpty(sysRes.getUrl()) 
				|| sysRes.getSeq() == null|| sysRes.getType() == null 
				|| sysRes.getEnabled() == null	|| StringUtils.isEmpty(sysRes.getKey())){
				throw new BussinessException("参数错误");
		}
		
		sysRes.setModifydate(new Date());
		
		sysResMapper.update(sysRes);
	}

	@Override
	public List<SysRes> findAllMenu() throws BussinessException {
		SysResQuery sysResQuery = new SysResQuery();
		sysResQuery.setType(1);
	    return sysResMapper.selectList(sysResQuery);
	}

	@Override
	public List<Tree> findAllTree() throws BussinessException {
		List<Tree> trees = new ArrayList<>();
		
		SysResQuery sysResQuery = new SysResQuery();
		sysResQuery.setPid(0);
		sysResQuery.setEnabled(1);
		
		List<SysRes> mainmenu = sysResMapper.selectList(sysResQuery);
		
		for(SysRes sysRes : mainmenu){
			Tree tree = new Tree();
			tree.setId(sysRes.getId());
			tree.setText(sysRes.getName());
			State state = new State(true, false);
			tree.setState(state);
		
			List<Tree> firstChildren = new ArrayList<>();
			sysResQuery = new SysResQuery();
			sysResQuery.setPid(sysRes.getId());
			sysResQuery.setEnabled(1);
			List<SysRes> childrenMenu = sysResMapper.selectList(sysResQuery);
			
			for(SysRes childrenSysRes : childrenMenu){
				Tree childtree = new Tree();
				childtree.setId(childrenSysRes.getId());
				childtree.setText(childrenSysRes.getName());
				state = new State(true, false);
				childtree.setState(state);
				
				List<Tree> secondChildren = new ArrayList<>();
				sysResQuery = new SysResQuery();
				sysResQuery.setPid(childrenSysRes.getId());
				sysResQuery.setEnabled(1);
				List<SysRes> secondChildrenMenu = sysResMapper.selectList(sysResQuery);
				
				for(SysRes secondChildRes : secondChildrenMenu){
					Tree secondTree = new Tree();
					secondTree.setId(secondChildRes.getId());
					secondTree.setText(secondChildRes.getName());
					state = new State(false, false);
					secondTree.setState(state);
					secondChildren.add(secondTree);
					
					childtree.setChildren(secondChildren);
				}
				
				firstChildren.add(childtree);
			}
			
			tree.setChildren(firstChildren);
			trees.add(tree);
		}
		
		return trees;
	}
	
	
	@Override
	public List<SysRes> findMenuByRoleIds(Set<Integer> roleIds) {
		if(roleIds.isEmpty()){
			return null;
		}
		List<SysRes> list = sysResMapper.selectMenuByRoleIds(roleIds);
		List<SysRes> listResult = (List<SysRes>) CollectionUtil.removeNullValue(list);
		return listResult;
	}

}
