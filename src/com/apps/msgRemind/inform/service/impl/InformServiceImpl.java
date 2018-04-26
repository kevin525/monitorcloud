package com.apps.msgRemind.inform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.msgRemind.inform.dao.InformDao;
import com.apps.msgRemind.inform.domain.AsopInform;
import com.apps.msgRemind.inform.service.InformService;
import com.common.StringHelpers;
import com.common.pagetag.PageGridPost;
import com.sys.service.impl.BaseServiceImpl;
/**
 * 
 * @ClassName: MailServiceImpl 
 * @Description: 邮件service层实现类
 * @author 张梦琦 
 * @date 2018年4月9日 下午2:54:41
 */
@Service(value="informService")
public class InformServiceImpl extends BaseServiceImpl implements InformService {

	@Autowired
	private InformDao infromDao;


	@Override
	public void saveOrupdateInform(AsopInform inform) {
		infromDao.saveOrupdateInform(inform);
	}

	@Override
	public List<AsopInform> getListByPage(AsopInform inform,
			PageGridPost pageGridPost) {
		if(null == pageGridPost){
			return infromDao.getListNoPage(inform);
		}else{
			return infromDao.getListByPage(inform, pageGridPost);
			
		}
	}

	@Override
	public void deleteInform(List<String> ids) {
		if(ids != null && ids.size() > 0){
			for(String id:ids){
			    if(!StringHelpers.isNull(id)){
			    	infromDao.deleteInform(Long.parseLong(id));
			    }
			}
		}
		
	}

	@Override
	public AsopInform getInformById(long id) {
		return infromDao.getInformById(id);
	}

	
}
